#ifndef __COMMON_H
#define __COMMON_H

#include<stdio.h>
#include<stdlib.h>
#include<string.h>



#define IN
#define OUT

int master(char *argv);  //软件入口函数

//void authorPrintf(char *arg);  //软件信息查询

int keyEncrpyt(IN char *key, OUT char *effectKey); //秘钥  加密算法函数

int getCpuInfoSerial(OUT char *serialNum);         //秘钥基准key 获取 函数

int mkKeyFile(IN char *keyFilePath, IN char *contentText);   //生成密钥文件 函数


#endif