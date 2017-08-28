#include<stdio.h>
#include<stdlib.h>
#include<string.h>


#define IN
#define OUT


//密钥路径   由函数参数传入
//密钥文件名
#define  KEYFILE  "fAPP_Key"

//***********************************
//*** 函数功能：获取 cpuinfo serial
//*** author:fjw0312   fjw0312@163.com
//*** date:2017.3.31
//**********************************
int mkKeyFile(IN char *keyFilePath, IN char *contentText)
{
	if(keyFilePath == NULL)
	{
		printf("keyFilePath is NULL !\n");
		return -1;
	}
	
	//获取 key 文件名
	char fileName[200];
	int num = strlen(keyFilePath);
	if( keyFilePath[num-1] == '/')
	{
		strcpy(fileName, keyFilePath);
		strcat(fileName, KEYFILE);
	}else{
		strcpy(fileName, keyFilePath);
		strcat(fileName, "/");
		strcat(fileName, KEYFILE);
	}
	
	//打开创建文件
	FILE *file = fopen(fileName, "w+");
	if(file == NULL)
	{
		perror("fopen file");
		return -1;
	}
	//写入文件内容
	size_t ret = fwrite(contentText, strlen(contentText), 1, file);
	if(ret == 1)
	{
		printf("fwrite APPKey sucess!\n");
	}
	
	fclose(file);
	return 0;
}