package com.example.ipcTest;

import java.io.Serializable;


//Serialzable�����parcelabel  �ӿ�ʵ�ּ򵥣�ֻ��̳�����ӿڣ����Ƚϳ־û�     ���ٶȿ죬���̽϶���������ڴ档
public class Book implements Serializable {

	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public int id;
	public float f;
	public byte[]  bs = new byte[10];
	public String name = "";

	public int add(){
		return id+1000;
	}
	public int add2(int a, int b){
		return 2*(a+b);
	}

}
