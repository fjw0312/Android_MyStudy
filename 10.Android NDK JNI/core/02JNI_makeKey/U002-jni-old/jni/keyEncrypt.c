#include<stdio.h>
#include<stdlib.h>
#include<string.h>


#define IN
#define OUT

/******************************************
***           秘钥生成函数
***  author:fjw0312 fjw0312@163.com
***  date：2017.3.31
***  算法：字符C=>0x* 
***  奇数-1，偶数+1， 遇8变2  遇2变8 遇5插0  末尾追加cb69cb
*******************************************/
int keyEncrpyt(IN char *key, OUT char *effectKey)
{
	int sValue[200];
	int  cNum = 0;
	int i = 0;
	//遍历字符  字符最多处理100个
	for(i=0;i<100;i++)
	{
		char c = key[i];  //获取每个字符
		if(c==0 || c == '\n' || c == '\0')
		{
			break;
		}
		if(c == ' ') continue;
		//将字符 转化为数字
		char b[2];
		b[0] = c;
		int cValue = (int)strtol(b, NULL, 16);
		
		//判断数据奇偶数
		int addFlag = 0;
		if(cValue%2){ //奇数
			addFlag = -1;
		}else{       //偶数
			addFlag = 1;
		}
		//得到奇偶处理后数据
		sValue[cNum] = cValue+addFlag;
		
		//将字符数据  2=>8  8=>2  遇5插0
		if(sValue[cNum] == 2)
		{
			sValue[cNum] = 8;
		}else if(sValue[cNum] == 8)
		{
			sValue[cNum] = 2;
		}else if(sValue[cNum] == 5)
		{
			cNum++;
			sValue[cNum] = 0;
		}
		cNum++;
	}
	
	//effectKey 字符串赋值
	for(i=0;i<cNum;i++)
	{
		sprintf((char *)(effectKey+i), "%1x", sValue[i]);
	}
	sprintf((char *)(effectKey+cNum), "%s", "cb69cb\n");
	cNum = cNum + 6;
	
	printf("keyEncrpyt sucess! effectKey=%s   %d\n", effectKey, cNum);

	return cNum;
}
