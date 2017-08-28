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
	//���ļ�
	FILE *file = fopen(CPUINFO_FILE, "r");
	if(file == NULL)
	{
		perror("fopen file");
		return -1;
	}
	
	//��ȡ�ļ� ÿ��  �ַ�
	char bufLine[500];
	while( fgets(bufLine, sizeof(bufLine), file) != NULL){
		//�ж�  �ҵ����кŵ���
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
//*** �������ܣ���ȡ cpuinfo serial
//*** author:fjw0312   fjw0312@163.com
//*** date:2017.3.31
//**********************************
int getCpuInfoSerial(OUT char *serialNum)
{
	//�Ȼ�ȡ���к� �ļ���
	char serialLine[500];
	int ret = fgetsSerial(serialLine);
	if(ret != 0)
	{
		printf("getCpuInfoSerial>>δ��ȡ�� cpu ���к���!\n");
		return -1;
	}
	
	//��ȡ�ַ���
	char *buf1 = strtok(serialLine, sub); //��ȡ   �������
	char *buf2 = strtok(NULL, sub);    //��ȡ   �ұ�����
	if(buf1==NULL || buf2==NULL) return -1;
	if(buf2)
	{
		strcpy(serialNum, buf2);
		printf("get serialNum=%s sucess!\n", serialNum);
		return 0;
	}
	
	return -1;
}
