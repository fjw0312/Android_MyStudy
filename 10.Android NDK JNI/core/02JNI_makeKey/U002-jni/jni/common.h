#ifndef __COMMON_H
#define __COMMON_H

#include<stdio.h>
#include<stdlib.h>
#include<string.h>



#define IN
#define OUT

int master(char *argv);  //�����ں���

//void authorPrintf(char *arg);  //�����Ϣ��ѯ

int keyEncrpyt(IN char *key, OUT char *effectKey); //��Կ  �����㷨����

int getCpuInfoSerial(OUT char *serialNum);         //��Կ��׼key ��ȡ ����

int mkKeyFile(IN char *keyFilePath, IN char *contentText);   //������Կ�ļ� ����


#endif