#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#include "common.h"

#define VERSION  "FV_0.001"


/**********************************
****main  ：密钥文件生成  执行软件
****author: fjw0312   fjw0312@163.com
****date: 2017.3.31
***********************************/

//软件  mian
int master(char *argv)
{
	
	printf("welcome into key-making !\n");
	
	
	//获取 cpu info key
	char cpuInfoSerial[500];
	if( getCpuInfoSerial(cpuInfoSerial) != 0)
	{
		printf("getCpuInfoSerial Error!\n");
		return -1;
	}
	// key密钥算法
	char effectKey[500];
	if( keyEncrpyt(cpuInfoSerial, effectKey) < 6 )
	{
		printf("keyEncrpyt  Error!\n");
		return -1;
	}
	
	//生成密钥文件
	if( mkKeyFile(argv, effectKey) != 0 )
	{
		printf("mkKeyFile Error !\n");
		return -1;
	}		
	
	return 0;
}