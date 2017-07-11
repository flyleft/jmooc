#ifndef _JUDGE_SQL_H_
#define _JUDGE_SQL_H_


extern MYSQL *mysql;               //mysql¡¨Ω”
extern char query[1024];           //≤È—Ø”Ôæ‰
extern char Mysql_url[255];
extern char Mysql_username[255];
extern char Mysql_password[255];
extern char Mysql_table[255];
extern int  Mysql_port;
extern char Mysql_Character[255];  //±‡¬Î


extern int SQL_InitMySQL();
extern int SQL_getSolutionSource(JUDGE_SUBMISSION_ST *pstJudgeSubmission);
extern int SQL_getSolutionByID(int solutionID, JUDGE_SOLUTION_ST *pstJudgeSolution, int *pIsExist);
extern void SQL_updateSolution(int solutionId,int verdictId);
extern void SQL_updateSolutionJsonResult(int solutionId, const char *result);
extern void SQL_updateCompileInfo(JUDGE_SUBMISSION_ST *pstJudgeSubmission);

#endif
