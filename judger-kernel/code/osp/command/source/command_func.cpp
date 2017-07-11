
/*
	 如梦令．偶然

	夜里无意心烦，
	发丝如此蓬乱。
	耳边声声颤，
	是谁嘴里轻叹。
	偶然，
	偶然，
	是我太过留恋。

*/


#include "osp\command\include\command_inc.h"

#include "product\judge\include\judge_inc.h"

extern int GL_vjudge_enable;
extern int g_sock_port;
extern int g_judge_mode;
extern char Mysql_url[];
extern char Mysql_username[];
extern char Mysql_password[];
extern char Mysql_table[];
extern int	Mysql_port;

#if M_DES("cmd_debugging_enable_st",1)
DEFUN(cmd_debugging_enable_st, (char*)"debugging enable", (char*)"Debugging switch on", Debugging_enable)
{
	extern int g_oj_debug_switch;
	if (g_debug_switch == DEBUG_ENABLE)
	{
		printf("Info: debugging switch is already enable.\n");
		return 0;
	}

	g_debug_switch = DEBUG_ENABLE;
	g_oj_debug_switch = DEBUG_ENABLE;

	extern ULONG Judge_DebugSwitch(ULONG st);
	Judge_DebugSwitch(DEBUG_ENABLE);

	printf("Info: debugging switch is enable.\n");

	return 0;
}
#endif

#if M_DES("cmd_undo_debugging_enable_st",1)
DEFUN(cmd_undo_debugging_enable_st, (char*)"undo debugging enable", (char*)"Debugging switch off", undo_debugging_enable)
{
	extern int g_oj_debug_switch;

	if (g_debug_switch == DEBUG_DISABLE)
	{
		printf("Info: debugging switch is already disable.\n");
		return 0;
	}

	g_debug_switch = DEBUG_DISABLE;
	g_oj_debug_switch = DEBUG_DISABLE;

	extern ULONG Judge_DebugSwitch(ULONG st);
	Judge_DebugSwitch(DEBUG_DISABLE);

	printf("Info: debugging switch is disable.\n");

	return 0;
}
#endif

#if M_DES("cmd_display_clock_st",1)
DEFUN(cmd_display_clock_st, (char*)"display clock", (char*)"Display clock of device", display_clock)
{
	time_t	timep = time(NULL);
	struct tm *p;

	p = localtime(&timep);
	p->tm_year = p->tm_year + 1900;
	p->tm_mon = p->tm_mon + 1;

	printf(" Date of device:\r\n %04d-%02d-%02d %02d:%02d:%02d UTC(+8) DTS\n",p->tm_year, p->tm_mon, p->tm_mday,p->tm_hour,p->tm_min,p->tm_sec);

	return 0;
}
#endif

#if M_DES("cmd_display_computer_st",1)
DEFUN(cmd_display_computer_st, (char*)"display computer", (char*)"Display computer information", display_computer)
{
	printf("This is Jungle Wei's computer.\n");
	return 0;
}
#endif

#if M_DES("cmd_version_st",1)
DEFUN(cmd_version_st, (char*)"version", (char*)"Display device version", version)
{
	/* support oi mode*/
	printf(" Kernel version: %s, released at %s %s. \n Copyright @ 2011-2017 debugforces.com. All Rights Reserved. \n",
			SOLFWARE_VERSION, __TIME__, __DATE__);

	return 0;
}
#endif

#if M_DES("cmd_sysname_st",1)
DEFUN(cmd_sysname_st, (char*)"sysname STRING<1-24>", (char*)"set system name", sysname)
{
	CMD_DBGASSERT(argv[1] != 0, "sysname");

	strcpy(g_sysname, argv[1]);

	WritePrivateProfileString("System","sysname",g_sysname,INI_filename);

	::SetConsoleTitle(g_sysname);

	printf("Info: system name change to %s successful.\r\n", g_sysname);

	return 0;
}
#endif

#if M_DES("cmd_display_history_st",1)
DEFUN(cmd_display_history_st, (char*)"display history", (char*)"Display history command", display_history)
{
	int try_idx = 0;
	int i = 0;

	for (i = 0;  i < HISTORY_MAX_SIZE; i++)
	{
		if (vty->history[i] == NULL)
			break;
	}

	for (i = i-1; i >= 0; i--)
	{
		if (vty->history[i] == NULL)
			break;

		cmd_outstring(" %s\r\n", vty->history[i]);
	}

	return 0;
}
#endif

#if M_DES("cmd_display_history_n_st",1)
DEFUN(cmd_display_history_n_st, (char*)"display history INTEGER<1-100>", (char*)"Display history command", display_history_n)
{
	int n = 0;
	int i = 0;

	debug_print_ex(CMD_DEBUG_TYPE_FUNC, "%d %s %s %s\n", argc, argv[0], argv[1], argv[2]);

	CMD_DBGASSERT(argv[2], "display_history_n");

	n = atoi(argv[2]);

	for (i = 0;  i < HISTORY_MAX_SIZE; i++)
	{
		if (vty->history[i] == NULL)
			break;
	}

	for (i = i-1; i >= 0 && n > 0; i--,n--)
	{
		if (vty->history[i] == NULL)
			break;

		cmd_outstring("%s \r\n", vty->history[i]);
	}

	return 0;
}

#endif

#if M_DES("cmd_debugging_error_st",1)

DEFUN(cmd_debugging_error_st, (char*)"debugging error", (char*)"open debugging error switch", debugging_error)
{
	CMD_DEBUGMASK_SET(CMD_DEBUG_TYPE_ERROR);
	printf("Info: debugging error switch is on.\r\n");
	return 0;
}
#endif

#if M_DES("cmd_undo_debugging_error_st",1)

DEFUN(cmd_undo_debugging_error_st, (char*)"undo debugging error", (char*)"close debugging error switch", undo_debugging_error)
{
	CMD_DEBUGMASK_CLEAR(CMD_DEBUG_TYPE_ERROR);
	printf("Info: debugging error switch is off.\r\n");
	return 0;
}
#endif

#if M_DES("cmd_debugging_function_st",1)

DEFUN(cmd_debugging_function_st, (char*)"debugging function", (char*)"open debugging function switch", debugging_function)
{
	CMD_DEBUGMASK_SET(CMD_DEBUG_TYPE_FUNC);
	printf("Info: debugging function switch is on.\r\n");
	return 0;
}
#endif

#if M_DES("cmd_undo_debugging_function_st",1)

DEFUN(cmd_undo_debugging_function_st, (char*)"undo debugging function", (char*)"Close debugging function switch", undo_debugging_function)
{
	CMD_DEBUGMASK_CLEAR(CMD_DEBUG_TYPE_FUNC);
	printf("Info: debugging function switch is off.\r\n");
	return 0;
}
#endif

#if M_DES("cmd_debugging_info_st",1)

DEFUN(cmd_debugging_info_st, (char*)"debugging info", (char*)"Open debugging info switch", debugging_info)
{
	CMD_DEBUGMASK_SET(CMD_DEBUG_TYPE_INFO);
	printf("Info: debugging info switch is on.\r\n");
	return 0;
}
#endif

#if M_DES("cmd_undo_debugging_info_st",1)

DEFUN(cmd_undo_debugging_info_st, (char*)"undo debugging info", (char*)"close debugging info switch", undo_debugging_info)
{
	CMD_DEBUGMASK_CLEAR(CMD_DEBUG_TYPE_INFO);
	printf("Info: debug info switch is off.\r\n");
	return 0;
}
#endif

#if M_DES("cmd_debugging_message_st",1)

DEFUN(cmd_debugging_message_st, (char*)"debugging message", (char*)"Open debugging message switch", debugging_message)
{
	CMD_DEBUGMASK_SET(CMD_DEBUG_TYPE_MSG);
	printf("Info: debug message switch is on.\r\n");
	return 0;
}
#endif

#if M_DES("cmd_undo_debugging_message_st",1)

DEFUN(cmd_undo_debugging_message_st, (char*)"undo debugging message", (char*)"close debugging message switch", undo_debugging_message)
{
	CMD_DEBUGMASK_CLEAR(CMD_DEBUG_TYPE_MSG);
	printf("Info: debugging message switch is off.\r\n");
	return 0;
}
#endif

#if M_DES("cmd_debugging_fsm_st",1)

DEFUN(cmd_debugging_fsm_st, (char*)"debugging fsm", (char*)"open debugging fsm switch", debugging_fsm)
{
	CMD_DEBUGMASK_SET(CMD_DEBUG_TYPE_FSM);
	printf("Info: debugging fsm switch is on.\r\n");
	return 0;
}
#endif

#if M_DES("cmd_undo_debugging_fsm_st",1)

DEFUN(cmd_undo_debugging_fsm_st, (char*)"undo debugging fsm", (char*)"close debugging fsm switch", undo_debugging_fsm)
{
	CMD_DEBUGMASK_CLEAR(CMD_DEBUG_TYPE_FSM);
	printf("Info: debugging message switch is off.\r\n");
	return 0;
}
#endif

#if M_DES("cmd_debugging_all_st",1)

DEFUN(cmd_debugging_all_st, (char*)"debugging all", (char*)"open debugging all switch", debugging_all)
{
	int i;
	for (i = CMD_DEBUG_TYPE_NONE + 1; i < CMD_DEBUG_TYPE_MAX; i++ )
	{
		CMD_DEBUGMASK_SET(i);
	}

	printf("Info: debugging all switch is on.\r\n");
	return 0;
}
#endif

#if M_DES("cmd_undo_debugging_all_st",1)

DEFUN(cmd_undo_debugging_all_st, (char*)"undo debugging all", (char*)"close debugging all switch", undo_debugging_all)
{
	int i;
	for (i = CMD_DEBUG_TYPE_NONE + 1; i < CMD_DEBUG_TYPE_MAX; i++ )
	{
		CMD_DEBUGMASK_CLEAR(i);
	}

	printf("Info: debugging all switch is off.\r\n");
	return 0;

}
#endif

#if M_DES("cmd_display_debugging_st",1)

DEFUN(cmd_display_debugging_st, (char*)"display debugging", (char*)"display debugging switch", display_debugging)
{
	int i = 0;
	if (g_debug_switch == DEBUG_ENABLE)
	{
		printf("Global debugging is enable.\r\n");
	}
	else
	{
		printf("Global debugging is disable.\r\n");
	}

	printf(" DebugMask(0x%x", g_aulDebugMask[0]);
	for (i = 1; i < CMD_DEBUG_TYPE_MAX/CMD_MASKLENTG + 1 ; i++)
	{
		printf("	,0x%x", g_aulDebugMask[i]);
	}
	printf(").\r\n");

	for (i = CMD_DEBUG_TYPE_NONE + 1; i < CMD_DEBUG_TYPE_MAX; i++ )
	{
		if (CMD_DEBUGMASK_GET(i))
		{
			printf(" Debugging %s switch is on.\r\n", szDebugName[i]);
		}
	}

	return 0;
}
#endif

#if M_DES("cmd_judge_solution_st",1)

DEFUN(cmd_judge_solution_st, (char*)"judge solution INTEGER<1-65535>", (char*)"judge solution <ID>", judge_solution)
{
	int solutionId = 0;

	debug_print_ex(CMD_DEBUG_TYPE_FUNC, "%d %s %s %s\n", argc, argv[0], argv[1], argv[2]);

	solutionId = atoi(argv[2]);

	extern int Judge_PushQueue(int solutionId);
	Judge_PushQueue(solutionId);

	cmd_outstring("Info: Solution judge-request has been sent to Judge-Queue asynchronously.\r\n");

	return 0;
}
#endif


#if M_DES("cmd_display_command_tree_st",1)

DEFUN(cmd_display_command_tree_st, (char*)"display command-tree", (char*)"display command tree", display_command_tree)
{
	extern void cmd_show_command_tree();
	cmd_show_command_tree();

	return 0;
}
#endif

#if M_DES("cmd_display_current_configuration_st",1)

DEFUN(cmd_display_current_configuration_st, (char*)"display current-configuration", (char*)"display current-configuration", display_current_configuration)
{
	extern void Judge_ShowCfgContent();
	Judge_ShowCfgContent();

	return 0;
}
#endif


#if M_DES("cmd_display_st",1)

#include"product\thirdpart32\cjson\cJSON.h"

DEFUN(cmd_display_st, (char*)"display", (char*)"display", display)
{
	printf("Info: display thread info.\n");

	extern int GetProcessThreadList();
	GetProcessThreadList();

	char current_path[MAX_PATH] = {0};
	GetCurrentDirectory(sizeof(current_path),current_path);


#if 0
	cJSON *json = cJSON_CreateObject();
	cJSON *array = NULL;

	//向文档中增加一个键值对{"name":"王大锤"}
	cJSON_AddItemToObject(json,"name",cJSON_CreateString("jungle wei"));
	//向文档中添加一个键值对
	cJSON_AddItemToObject(json,"age1",cJSON_CreateNumber(291));
	cJSON_AddNumberToObject(json,"age",29);
	cJSON_AddStringToObject(json,"address","beijing");
	cJSON_AddItemToObject(json,"love",array=cJSON_CreateArray());
	cJSON_AddItemToArray(array,cJSON_CreateString("LOL"));
	cJSON_AddItemToArray(array,cJSON_CreateString("NBA"));
	cJSON_AddItemToArray(array,cJSON_CreateString("Go shopping"));

#endif

#if 0
	cJSON *json = cJSON_CreateArray();
	cJSON *json1 = cJSON_CreateObject();
	cJSON_AddItemToArray(json, json1);
	cJSON_AddItemToObject(json1,"name",cJSON_CreateString("jungle wei"));
	cJSON_AddNumberToObject(json1,"age",29);
	cJSON_AddStringToObject(json1,"address","beijing");

	json1 = cJSON_CreateObject();
	cJSON_AddItemToArray(json, json1);
	cJSON_AddItemToObject(json1,"name",cJSON_CreateString("jungle wei"));
	cJSON_AddNumberToObject(json1,"age",29);
	cJSON_AddStringToObject(json1,"address","beijing");

	json1 = cJSON_CreateObject();
	cJSON_AddItemToArray(json, json1);
	cJSON_AddItemToObject(json1,"name",cJSON_CreateString("jungle wei"));
	cJSON_AddNumberToObject(json1,"age",29);
	cJSON_AddStringToObject(json1,"address","beijing");

	char *buf = cJSON_Print(json);

	printf("\n%s\n", buf);
	free(buf);

	cJSON_Delete(json);

#endif

	cJSON *json = cJSON_CreateObject();
	cJSON *array = NULL;
	cJSON *testcase = NULL;
	cJSON_AddNumberToObject(json,"solutionId", 1001);
	//向文档中添加一个键值对
	cJSON_AddNumberToObject(json,"problemId",1000);
	cJSON_AddStringToObject(json,"username","jungle");
	cJSON_AddStringToObject(json,"language","C++");
	cJSON_AddItemToObject(json,"testcase",array=cJSON_CreateArray());
	for (int i = 0; i<100; i++)
	{
		cJSON_AddItemToArray(array, testcase = cJSON_CreateObject());
		cJSON_AddNumberToObject(testcase, "case", 1);
		cJSON_AddStringToObject(testcase, "verdict", "Accepted");
		cJSON_AddNumberToObject(testcase, "timeused", 10);
		cJSON_AddNumberToObject(testcase, "memused", 1024);
	}

	char *buf = cJSON_Print(json);

	printf("\n%s\nlen=%d\n", buf,strlen(buf));
	free(buf);

	cJSON_Delete(json);

	return 0;
}
#endif

#if M_DES("cmd_set_config_section_name_value_st",1)

DEFUN(cmd_set_config_section_name_value_st, (char*)"set config STRING<1-24> STRING<1-24> STRING<1-65535>",
		(char*)"Set Config section name value", set_config_section_name_value_st)
{
	char section[25] = {0};
	char name[25] = {0};
	char value[65536]={0};

	debug_print_ex(CMD_DEBUG_TYPE_FUNC, "%d %s %s %s %s %s.\n", argc, argv[0], argv[1], argv[2], argv[3], argv[4]);

	strcpy(section, argv[2]);
	strcpy(name, argv[3]);
	strcpy(value, argv[4]);


	int ret = WritePrivateProfileString(section,name,value,INI_filename);

	debug_print_ex(CMD_DEBUG_TYPE_FUNC, "WritePrivateProfileString return %u..", ret);

}
#endif

#if M_DES("cmd_virtual_judge_enable_st",1)

/* BEGIN: Added by weizengke, 2014/3/3 for 全局使能vjudge */
DEFUN(cmd_virtual_judge_enable_st, (char*)"virtual-judge enable", (char*)"virtual-judge enable", virtual_judge_enable_st)
{
	extern int GL_vjudge_enable;

	if (OS_YES == GL_vjudge_enable)
	{
		printf("Info: virtual-judge is already enable.\r\n");
		return OS_ERR;
	}

	int ret = WritePrivateProfileString("Judge","vjudge_enable","1",INI_filename);
	debug_print_ex(CMD_DEBUG_TYPE_FUNC, "WritePrivateProfileString return %u..", ret);

	if (ret != 1)
	{
		printf("Error: virtual-judge enable failed.\r\n");
		return OS_ERR;
	}

	GL_vjudge_enable = OS_YES;

}
#endif

#if M_DES("cmd_undo_virtual_judge_enable_st",1)

/* BEGIN: Added by weizengke, 2014/3/3 for 全局去使能vjudge */
DEFUN(cmd_undo_virtual_judge_enable_st, (char*)"undo virtual-judge enable", (char*)"undo virtual-judge enable", undo_virtual_judge_enable_st)
{
	extern int GL_vjudge_enable;

	if (OS_NO == GL_vjudge_enable)
	{
		printf("Info: virtual-judge is already disable.\r\n");
		return OS_ERR;
	}

	int ret = WritePrivateProfileString("Judge","vjudge_enable","0",INI_filename);
	debug_print_ex(CMD_DEBUG_TYPE_FUNC, "WritePrivateProfileString return %u..", ret);

	if (ret != 1)
	{
		printf("Error: virtual-judge disable failed.\r\n");
		return OS_ERR;
	}

	GL_vjudge_enable = OS_NO;

}
#endif


#if M_DES("cmd_display_judge_brief_st",1)

/* BEGIN: Added by weizengke, 2014/3/5 judge config brief*/
DEFUN(cmd_display_judge_brief_st, (char*)"display judge brief",
		(char*)"display judge brief", display_judge_brief_st)
{


	printf("# Local Judger Info\r\n");
	printf("  Sysname   : %s\r\n", g_sysname);
	printf("  Sock Port : %d\r\n", g_sock_port);
	printf("  Judge Mode: %s\r\n", (g_judge_mode==JUDGE_MODE_ACM)?"ACM":"OI");

	printf(" =================================================="
		   "========================\r\n");

	printf("# Virtual Judger Info\r\n");
	printf("  Global Virtual Judge Is %s\r\n",
		  (GL_vjudge_enable==OS_YES)?"Enable":"Disable");
	printf("  Judger | Account | Password | Status | Remote |"
		   "   Judger-IP   | J-Port\r\n");
	printf("  -------------------------------------------------"
		   "------------------------\r\n");

	printf(" =================================================="
		   "========================\r\n");

	printf("# MySQL Info\r\n"
		  "  URL       : %s\r\n"
		  "  Username  : %s\r\n"
		  "  Password  : %s\r\n"
		  "  Table-Name: %s\r\n"
		  "  Port      : %d\r\n",
		Mysql_url,Mysql_username,Mysql_password,Mysql_table,Mysql_port);

	printf(" =================================================="
		   "========================\r\n");

}
#endif

#if M_DES("cmd_judge_mode_acm",1)
DEFUN(cmd_judge_mode_acm, (char*)"judge mode acm",
		(char*)"judge mode acm", judge_mode_acm)
{
	extern int g_judge_mode;

	if (JUDGE_MODE_ACM == g_judge_mode)
	{
		printf("Info: judge mode is already acm.\r\n");
		return OS_ERR;
	}

	int ret = WritePrivateProfileString("Judge","judge_mode","0",INI_filename);
	debug_print_ex(CMD_DEBUG_TYPE_FUNC, "WritePrivateProfileString return %u..", ret);

	if (ret != 1)
	{
		printf("Error: judge mode acm failed.\r\n");
		return OS_ERR;
	}

	g_judge_mode = JUDGE_MODE_ACM;

}
#endif

#if M_DES("cmd_judge_mode_oi",1)
DEFUN(cmd_judge_mode_oi, (char*)"judge mode oi",
		(char*)"judge mode oi", judge_mode_oi)
{
	extern int g_judge_mode;

	if (JUDGE_MODE_OI == g_judge_mode)
	{
		printf("Info: judge mode is already oi.\r\n");
		return OS_ERR;
	}

	int ret = WritePrivateProfileString("Judge","judge_mode","1",INI_filename);
	debug_print_ex(CMD_DEBUG_TYPE_FUNC, "WritePrivateProfileString return %u..", ret);

	if (ret != 1)
	{
		printf("Error: judge mode acm failed.\r\n");
		return OS_ERR;
	}

	g_judge_mode = JUDGE_MODE_OI;

}
#endif

#if M_DES("cmd_reboot_st",1)

/* BEGIN: Added by weizengke, 2014/3/3 reset */
DEFUN(cmd_reboot_st, (char*)"reboot", (char*)"reboot", reboot_st)
{
	extern void Judge_Destroy();
	Judge_Destroy();

	extern int OJ_Init();
	OJ_Init();

	extern int OJ_InitData();
	OJ_InitData();

	printf("Info: reboot ok.\r\n");
}
#endif

void cmd_install()
{

    /* reg cmd-element */
    cmd_reg_newcmdelement(CMD_ELEM_ID_CR, 			CMD_ELEM_TYPE_END,			CMD_END,			    ""               );
    cmd_reg_newcmdelement(CMD_ELEM_ID_STRING1TO24,  CMD_ELEM_TYPE_STRING,       "STRING<1-24>",     "String lenth range form 1 to 24");
    cmd_reg_newcmdelement(CMD_ELEM_ID_STRING1TO65535,  CMD_ELEM_TYPE_STRING,    "STRING<1-65535>",  "String lenth range form 1 to 65535");
    cmd_reg_newcmdelement(CMD_ELEM_ID_INTEGER1TO24, CMD_ELEM_TYPE_INTEGER,      "INTEGER<1-100>",   "Integer range form 1 to 100");
    cmd_reg_newcmdelement(CMD_ELEM_ID_INTEGER1TO65535, CMD_ELEM_TYPE_INTEGER,   "INTEGER<1-65535>", "Integer range form 1 to 65535");
    cmd_reg_newcmdelement(CMD_ELEM_ID_COMMAND_TREE, CMD_ELEM_TYPE_KEY,          "command-tree",     "Command tree");

    cmd_reg_newcmdelement(CMD_ELEM_ID_CURRENT_CFG,  CMD_ELEM_TYPE_KEY,          "current-configuration",     "Current Configuration");

    cmd_reg_newcmdelement(CMD_ELEM_ID_SYSNAME, 		CMD_ELEM_TYPE_KEY,   		"sysname",          "Set system name");
    cmd_reg_newcmdelement(CMD_ELEM_ID_UNDO, 		CMD_ELEM_TYPE_KEY,   		"undo",				"Undo operation");
    cmd_reg_newcmdelement(CMD_ELEM_ID_ENABLE, 		CMD_ELEM_TYPE_KEY,   		"enable",			"Enable operation");
    cmd_reg_newcmdelement(CMD_ELEM_ID_DISABLE, 		CMD_ELEM_TYPE_KEY,   		"disable",			"Disable operation");
    cmd_reg_newcmdelement(CMD_ELEM_ID_DISPLAY, 		CMD_ELEM_TYPE_KEY,   		"display",			"Display");
    cmd_reg_newcmdelement(CMD_ELEM_ID_DEBUG,        CMD_ELEM_TYPE_KEY,   		"debugging",		"Debugging switch");
    cmd_reg_newcmdelement(CMD_ELEM_ID_ON, 			CMD_ELEM_TYPE_KEY,   		"on",				"Debug switch open");
    cmd_reg_newcmdelement(CMD_ELEM_ID_OFF, 			CMD_ELEM_TYPE_KEY,   		"off",				"Debug switch close");
    cmd_reg_newcmdelement(CMD_ELEM_ID_VERSION, 		CMD_ELEM_TYPE_KEY,   		"version",			"Show version of solfware");

    cmd_reg_newcmdelement(CMD_ELEM_ID_CLOCK,        CMD_ELEM_TYPE_KEY,   		"clock",			"Show clock now");
    cmd_reg_newcmdelement(CMD_ELEM_ID_COMPUTER, 	CMD_ELEM_TYPE_KEY,   		"computer",			"Show computer information");

    cmd_reg_newcmdelement(CMD_ELEM_ID_HISTTORY, 	CMD_ELEM_TYPE_KEY,   		"history",			"Histrory command");
    cmd_reg_newcmdelement(CMD_ELEM_ID_VJUDGE,	    CMD_ELEM_TYPE_KEY,   		"virtual-judge", 	"Virtual judge");

    cmd_reg_newcmdelement(CMD_ELEM_ID_DEBUG_ERROR,  CMD_ELEM_TYPE_KEY,			"error",			"Error");
    cmd_reg_newcmdelement(CMD_ELEM_ID_DEBUG_FUNC,   CMD_ELEM_TYPE_KEY,			"function",			"Function");
    cmd_reg_newcmdelement(CMD_ELEM_ID_DEBUG_INFO,   CMD_ELEM_TYPE_KEY,			"info",				"Information");
    cmd_reg_newcmdelement(CMD_ELEM_ID_DEBUG_MSG,    CMD_ELEM_TYPE_KEY,			"message",			"Message");
    cmd_reg_newcmdelement(CMD_ELEM_ID_DEBUG_FSM,    CMD_ELEM_TYPE_KEY,			"fsm",				"Finite State Machine");

    cmd_reg_newcmdelement(CMD_ELEM_ID_DEBUG_ALL,    CMD_ELEM_TYPE_KEY,			"all",				"All");

    cmd_reg_newcmdelement(CMD_ELEM_ID_LOGIN,     	CMD_ELEM_TYPE_KEY,			"login",			"Login");

    cmd_reg_newcmdelement(CMD_ELEM_ID_HDUJUDGE,		CMD_ELEM_TYPE_KEY,			"hdu-judge",		"HDU-Judge");
    cmd_reg_newcmdelement(CMD_ELEM_ID_REMOTE_JUDGE,	CMD_ELEM_TYPE_KEY,			"remote-judge",		"Remote-Judge");
    cmd_reg_newcmdelement(CMD_ELEM_ID_USERNAME, 	CMD_ELEM_TYPE_KEY,			"username", 		"Username");
    cmd_reg_newcmdelement(CMD_ELEM_ID_PASSWORD,		CMD_ELEM_TYPE_KEY,			"password",			"Password");


    cmd_reg_newcmdelement(CMD_ELEM_ID_STATUS,		CMD_ELEM_TYPE_KEY,			"status",			"Status");

    cmd_reg_newcmdelement(CMD_ELEM_ID_JUDGE,		CMD_ELEM_TYPE_KEY,			"judge",			"Judge of OJ");
    cmd_reg_newcmdelement(CMD_ELEM_ID_SOLUTION,		CMD_ELEM_TYPE_KEY,			"solution",			"The Solution");

    cmd_reg_newcmdelement(CMD_ELEM_ID_PROBLEM,		CMD_ELEM_TYPE_KEY,			"problem",			"The Problem of OJ");

    cmd_reg_newcmdelement(CMD_ELEM_ID_SET,			CMD_ELEM_TYPE_KEY,			"set",				"Set value");
    cmd_reg_newcmdelement(CMD_ELEM_ID_CONFIG,		CMD_ELEM_TYPE_KEY,			"config",			"Set Config section name value");

    cmd_reg_newcmdelement(CMD_ELEM_ID_REBOOT,		CMD_ELEM_TYPE_KEY,			"reboot",			"Reboot Judge kernel");
    cmd_reg_newcmdelement(CMD_ELEM_ID_BRIEF,		CMD_ELEM_TYPE_KEY,			"brief",			"Brief Information");
    cmd_reg_newcmdelement(CMD_ELEM_ID_IP,			CMD_ELEM_TYPE_KEY,			"ip",			    "IP");
    cmd_reg_newcmdelement(CMD_ELEM_ID_PORT,			CMD_ELEM_TYPE_KEY,			"port",			    "Port");

    /* add for mode acm or oi */
    cmd_reg_newcmdelement(CMD_ELEM_ID_MODE,			CMD_ELEM_TYPE_KEY,			"mode",			    "Judge Mode");
    cmd_reg_newcmdelement(CMD_ELEM_ID_ACM,			CMD_ELEM_TYPE_KEY,			"acm",			    "ACM Mode, break while answer is not accepted");
    cmd_reg_newcmdelement(CMD_ELEM_ID_OI,			CMD_ELEM_TYPE_KEY,			"oi",			    "OI Mode, will run all testcases");

    // install command
    // ---------------------------------------------------

    install_element(&cmd_sysname_st);

    install_element(&cmd_debugging_enable_st);
    install_element(&cmd_undo_debugging_enable_st);

    install_element(&cmd_display_clock_st);
    install_element(&cmd_display_computer_st);
    install_element(&cmd_version_st);
    install_element(&cmd_display_history_st);
    install_element(&cmd_display_history_n_st);

    install_element(&cmd_display_st);

    install_element(&cmd_debugging_error_st);
    install_element(&cmd_undo_debugging_error_st);

    install_element(&cmd_debugging_function_st);
    install_element(&cmd_undo_debugging_function_st);

    install_element(&cmd_debugging_info_st);
    install_element(&cmd_undo_debugging_info_st);

    install_element(&cmd_debugging_message_st);
    install_element(&cmd_undo_debugging_message_st);

    install_element(&cmd_debugging_fsm_st);
    install_element(&cmd_undo_debugging_fsm_st);


    install_element(&cmd_debugging_all_st);
    install_element(&cmd_undo_debugging_all_st);

    install_element(&cmd_display_debugging_st);

    install_element(&cmd_judge_solution_st);
    install_element(&cmd_display_command_tree_st);
    install_element(&cmd_display_current_configuration_st);

    install_element(&cmd_set_config_section_name_value_st);

    install_element(&cmd_virtual_judge_enable_st);
    install_element(&cmd_undo_virtual_judge_enable_st);


    install_element(&cmd_display_judge_brief_st);

    /* add for mode acm or oi */
    install_element(&cmd_judge_mode_acm);
    install_element(&cmd_judge_mode_oi);

    install_element(&cmd_reboot_st);
    // ---------------------------------------------------
}
