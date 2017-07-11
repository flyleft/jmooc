/*
  适配OJ MySQL数据库操作
*/

#include <windows.h>
#include <iostream>
#include <conio.h>
#include <stdlib.h>
#include <io.h>
#include <time.h>
#include <queue>
#include <string>
#include <sstream>


#include "product\judge\include\judge_inc.h"


MYSQL *mysql;
char query[1024];
char Mysql_url[255];
char Mysql_username[255];
char Mysql_password[255];
char Mysql_table[255];
int  Mysql_port;
char Mysql_Character[255];

/* BEGIN: Added by weizengke, 2014/7/10  for 多线程判题信号量保护数据库访问 */

HANDLE hSemaphore_SQL;

void SQL_CreateSem()
{
	hSemaphore_SQL = CreateSemaphore(NULL, 1, 1, "SQL_SEM");
}
void SQL_SemP()
{
	WaitForSingleObject(hSemaphore_SQL, INFINITE);
}

void SQL_SemV()
{
	ReleaseSemaphore(hSemaphore_SQL, 1, NULL);
}
/* END:   Added by weizengke, 2014/7/10 */


/* 初始化mysql，并设置字符集 */
int SQL_InitMySQL()
{
	SQL_CreateSem();

	mysql = mysql_init((MYSQL*)0);

	if (mysql != 0 &&
		!mysql_real_connect(mysql,Mysql_url,
							Mysql_username,
							Mysql_password,
							Mysql_table,
							Mysql_port,
							NULL,
							CLIENT_MULTI_STATEMENTS ))
	{
		write_log(JUDGE_ERROR,mysql_error(mysql));
		return 0;
	}

	strcpy(query,"SET CHARACTER SET gbk"); //设置编码 gbk
	int ret = mysql_real_query(mysql,query, (unsigned int)strlen(query));
	if (ret)
	{
		write_log(JUDGE_ERROR, mysql_error(mysql));
		return 0;
	}

	return 1;
}

int SQL_Destroy()
{
	mysql_close(mysql);
}

/*#if M_DES("内部函数，请勿P信号量",1)

#endif*/

int SQL_getSolutionSource(JUDGE_SUBMISSION_ST *pstJudgeSubmission)
{
	if (NULL == pstJudgeSubmission)
	{
		write_log(JUDGE_ERROR,"SQL_getSolutionSourceEx, Input param is null...");
		return OS_ERR;
	}

	SQL_SemP();

	sprintf(query,"select source from solution where solution_id=%d",
			pstJudgeSubmission->stSolution.solutionId);

	int ret=mysql_real_query(mysql,query,(unsigned int)strlen(query));
	if(ret)
	{
		write_log(JUDGE_ERROR,mysql_error(mysql));
		SQL_SemV();
		return OS_ERR;
	}

	MYSQL_RES *recordSet = mysql_store_result(mysql);
	if (recordSet==NULL)
	{
		write_log(JUDGE_ERROR,"SQL_getSolutionSource");
		SQL_SemV();
		return OS_ERR;
	}

	FILE *fp_source = fopen(pstJudgeSubmission->sourcePath, "w");
	char code[MAX_CODE]={0};
	MYSQL_ROW row;

	if(row = mysql_fetch_row(recordSet))
	{
		sprintf(code, "%s", row[0]);
	}
	else
	{
		write_log(JUDGE_ERROR,"SQL_getSolutionSource Error");
	}

    judge_outstring("code:%s\r\n",code);

	if(pstJudgeSubmission->isTranscoding == 1)
	{
		int ii=0;
		/* 解决VS下字符问题 */
		while (code[ii]!='\0')
		{
			if (code[ii]=='\r')
			{
				code[ii] = '\n';
			}

			ii++;
		}
	}

	fprintf(fp_source, "%s", code);

	/* add for vjudge*/
	strcpy(pstJudgeSubmission->szSource, code);

	mysql_free_result(recordSet);
	fclose(fp_source);

	SQL_SemV();

	return OS_OK;
}


int SQL_getSolutionByID(int solutionID, JUDGE_SOLUTION_ST *pstJudgeSolution, int *pIsExist)
{
	int ret = OS_OK;
	MYSQL_RES *recordSet = NULL;
	MYSQL_ROW row;

	if (NULL == pstJudgeSolution
	    ||NULL == pIsExist)
	{
		return OS_ERR;
	}

	*pIsExist = OS_NO;

	SQL_SemP();

	sprintf(query,"select language,user_id,submit_date from solution where solution_id=%d",
		    solutionID);

	ret = mysql_real_query(mysql,query,(unsigned int)strlen(query));
	if(ret)
	{
		write_log(JUDGE_ERROR,mysql_error(mysql));
		SQL_SemV();
		return OS_ERR;
	}

	recordSet = mysql_store_result(mysql);
	if (recordSet == NULL)
	{
		write_log(JUDGE_ERROR,"Error SQL_getSolutionData");
		SQL_SemV();
		return OS_ERR;
	}

	if (row = mysql_fetch_row(recordSet))
	{
		pstJudgeSolution->solutionId = solutionID;
		pstJudgeSolution->languageId = atoi(row[0]);
		strcpy(pstJudgeSolution->username, row[1]);
		StringToTimeEX(row[2],pstJudgeSolution->submitDate);
		*pIsExist = OS_YES;

		write_log(JUDGE_INFO,"Found record. (solutionID=%d)", solutionID);
	}
	else
	{
		write_log(JUDGE_ERROR,"No such record. (solutionID=%d)", solutionID);
	}

	/* 释放结果集 */
	mysql_free_result(recordSet);

	SQL_SemV();

	return OS_OK;
}



/* update Solution table*/
void SQL_updateSolution(int solutionId,int verdictId)
{
	SQL_SemP();

	sprintf(query,"update solution set verdict=%d where solution_id=%d;",verdictId,solutionId);

	if(mysql_real_query(mysql,query,(unsigned int)strlen(query)))
	{
		write_log(JUDGE_ERROR,mysql_error(mysql));
	}

	SQL_SemV();
}

/*void SQL_updateSolutionJsonResult(int solutionId, char *jsonResult)
{
    judge_outstring("SQL_updateSolutionJsonResult\r\n");
	SQL_SemP();
    string str = jsonResult;

    string::iterator it;

    for (it =str.begin(); it != str.end(); ++it)
    {
        if ( *it == '\"')
        {
            str.erase(it);
        }
    }
    judge_outstring("SQL_updateSolutionJsonResult   ... 1 \r\n");
	sprintf(query,"update solution set result = 'hahhaha' where solution_id=%d;",solutionId);

	if(mysql_real_query(mysql,query,(unsigned int)strlen(query)))
	{
		write_log(JUDGE_ERROR,mysql_error(mysql));
        judge_outstring("SQL_updateSolutionJsonResult执行出错%s\r\n",str.c_str());
	}

	SQL_SemV();
}*/

void SQL_updateSolutionJsonResult(int solutionId, const char *result)
{
    SQL_SemP();

    string str = result;

    string::iterator it;

    for (it =str.begin(); it != str.end(); ++it)
    {
        if ( *it == '\"')
        {
            str.erase(it);
        }
    }

    judge_outstring("===============%s\r\n",str.c_str());
    //sprintf(query,"update solution set result='%s' where solution_id=%d;", str.c_str(), solutionId);

    sprintf(query,"update solution set result = \"%s\",verdict=2 where solution_id = %d limit 1", str.c_str(), solutionId);

    if(mysql_real_query(mysql,query,(unsigned int)strlen(query)))
    {
        write_log(JUDGE_ERROR,mysql_error(mysql));
        judge_outstring("error%s\r\n",mysql_error(mysql));
    }

    SQL_SemV();
}


void SQL_updateCompileInfo(JUDGE_SUBMISSION_ST *pstJudgeSubmission)
{
	FILE *fp;
	char buffer[4096]={0};

	if (NULL == pstJudgeSubmission)
	{
		write_log(JUDGE_ERROR,"SQL_updateCompileInfo, Input param is null...");
		return ;
	}


	if ((fp = fopen (pstJudgeSubmission->DebugFile, "r")) == NULL)
	{
		write_log(JUDGE_ERROR,"DebugFile open error");
		return ;
	}

    judge_outstring("准备更新编译信息\r\n");

	SQL_SemP();

	if((fgets(buffer, 4095, fp))!= NULL)
	{
		string str = buffer;
		string::iterator   it;

		for (it =str.begin(); it != str.end(); ++it)
		{
			if ( *it == '\"')
			{
				str.erase(it);
			}
		}


		sprintf(query,"update solution set compile_err = \"%s\",verdict=2 where solution_id = %d limit 1",str.c_str(),pstJudgeSubmission->stSolution.solutionId);

		int ret=mysql_real_query(mysql,query,(unsigned int)strlen(query));
		if(ret)
		{
			write_log(JUDGE_ERROR,mysql_error(mysql));
			fclose(fp);
			SQL_SemV();
			return ;
		}
	}

	fclose(fp);

	SQL_SemV();

}
