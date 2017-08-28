#include<stdio.h>
#include<string.h>
#include<stdlib.h>


#define IN
#define OUT

#define CPUINFO_FILE "/proc/cpuinfo"
//#define CPUINFO_FILE "./cpuinfo"
#define SERIAL  "Serial"

char *sub = ":";

int fgetsSerial(OUT char *serialLine)
{
	//打开文件
	FILE *file = fopen(CPUINFO_FILE, "r");
	if(file == NULL)
	{
		perror("fopen file");
		return -1;
	}
	
	//读取文件 每行  字符
	char bufLine[500];
	while( fgets(bufLine, sizeof(bufLine), file) != NULL){
		//判断  找到序列号的行
		if(strstr(bufLine, SERIAL))
		{
			strcpy(serialLine, bufLine);
			printf("get serialLine=%s sucess!\n", serialLine);
			fclose(file);
			return 0;
		}
	}
	
	fclose(file);
	return -1;
}


//***********************************
//*** 函数功能：获取 cpuinfo serial
//*** author:fjw0312   fjw0312@163.com
//*** date:2017.3.31
//**********************************
int getCpuInfoSerial(OUT char *serialNum)
{
	//先获取序列号 文件行
	char serialLine[500];
	int ret = fgetsSerial(serialLine);
	if(ret != 0)
	{
		printf("getCpuInfoSerial>>未获取的 cpu 序列号行!\n");
		return -1;
	}
	
	//截取字符串
	char *buf1 = strtok(serialLine, sub); //获取   左边内容
	char *buf2 = strtok(NULL, sub);    //获取   右边内容
	if(buf1==NULL || buf2==NULL) return -1;
	if(buf2)
	{
		strcpy(serialNum, buf2);
		printf("get serialNum=%s sucess!\n", serialNum);
		return 0;
	}
	
	return -1;
}
