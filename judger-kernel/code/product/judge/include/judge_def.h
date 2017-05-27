#ifndef _JUDGE_DEF_H_
#define _JUDGE_DEF_H_


#define JUDGE_VIRTUAL 		VOS_YES   /* VJUDGE switch */
#define VJUDGE_CURL   		VOS_YES   /* VJUDGE switch */
#define FEATURE_JUDGE_OI    VOS_YES   /* OI switch */


#define STD_MB 1048576
#define STD_T_LIM 2
#define STD_F_LIM (STD_MB<<5)
#define STD_M_LIM (STD_MB<<7)
#define BUFFER_SIZE 512


#define V_ALL 0
#define V_Q 1
#define V_C 2
#define V_CE 3
#define V_RUN 4
#define V_AC 5
#define V_RE 6
#define V_TLE 7
#define V_SE 8

#define JUDGE_ISJUDGE_STOP(v) (v == V_CE || v == V_RE || v == V_SE)

static const char* VERDICT_NAME[] = {"ALL", "Queuing", "Compiling", "Compilation Error", "Running","Accepted","Runtime Error","TIME LIMIT","System Error"};

#define JUDGE_SYSTEM_ERROR 0
#define JUDGE_WARNING 1
#define JUDGE_ERROR 2
#define JUDGE_FATAL 3
#define JUDGE_INFO 4

static const char* LEVEL_NAME[] = {"JUDGE_SYSTEM_ERROR", "JUDGE_WARNING", "JUDGE_ERROR", "JUDGE_FATAL", "JUDGE_INFO"};

#define VOS_YES 1
#define VOS_NO  0


#define JUDGE_DEBUG_OFF 0
#define JUDGE_DEBUG_ON 1


#define BUFSIZE 4096
#define JSONBUFSIZE 65536

const int MAX_NAME = 32;
const int MAX_CODE = 100000;
const int MAX_TITLE = 200;
const int MAX_CONTENT = 100000;
const int MAX_WAITTIME = 10000 ;


#define VJUDGE_MAX_SIZE_BUF 10000000
#define VJUDGE_OVECCOUNT 30    /* should be a multiple of 3 */
#define VJUDGE_MAX_LANG_SIZE 255

#define JUDGE_MODE_ACM  0
#define JUDGE_MODE_OI   1

#endif
