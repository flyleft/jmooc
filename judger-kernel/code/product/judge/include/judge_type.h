#ifndef _JUDGE_TYPE_H_
#define _JUDGE_TYPE_H_

#include "product\judge\include\judge_inc.h"

#define JUDGE_OJNAME_SIZE_MAX 32

typedef struct tag_Judge_Solution_ST
{
	int solutionId;
	int languageId;
	int verdictId;
	time_t submitDate;
	char username[MAX_NAME];
	int reJudge;
	char languageName[100]={0};
	char languageExt[10]={0};
	char languageExe[10]={0};
	
}JUDGE_SOLUTION_ST;

#define JUDGE_CMD_BUFFER 1024

typedef struct tag_Judge_Submission_ST
{
	JUDGE_SOLUTION_ST     stSolution;

	//int time_used_case;     /* 单个case耗时 */
	//int memory_used_case;

    int isTranscoding;   /* 针对VS的转码 */
    int limitIndex;
    int nProcessLimit;

	char szSource[MAX_CODE];

	char languageName[100];
	char languageExt[10];
	char languageExe[10];

	unsigned long ulSeed;  /* 随机种子 */

	char subPath[MAX_PATH]; /* 子目录 */
	char result[MAX_PATH];//运行结果


	char sourcePath[MAX_PATH];
	char exePath[MAX_PATH];
    char inFileName[MAX_PATH];
    char outFileName[MAX_PATH];
    char DebugFile[MAX_PATH];
    char ErrorFile[MAX_PATH];

	char *result_Json; /* 格式: json，存储判断结果 */

    char compileCmd[1024];
    char runCmd[1024];

	PROCESS_INFORMATION pProRunInfo; /* 运行进程信息 */
	PROCESS_INFORMATION pProComInfo; /* 编译进程信息 */

	HANDLE hJob;         /* 作业句柄 */

    DWORD dwProStatusCode;     /* 定义进程状态 */

	HANDLE hInputFile ;  /* 父进程输入文件句柄 */
	HANDLE hOutputFile;  /* 子进程标准输出句柄 */

	//clock_t startt; /* 每次run的时间点 */
	//clock_t endt ;  /* 每次run的时间点 */

}JUDGE_SUBMISSION_ST;

#endif
