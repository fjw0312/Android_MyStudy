package com.example.ipcTest;

import java.io.Serializable;


//Serialzable相较与parcelabel  接口实现简单（只需继承引入接口）、比较持久化     但速度快，过程较多产生垃圾内存。
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
