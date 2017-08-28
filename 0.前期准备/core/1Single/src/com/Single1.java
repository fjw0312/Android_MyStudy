package com;


////饿汉模式  线程安全
public class Single1 {

	public Single1() {
		// TODO Auto-generated constructor stub
	}
	
	private static final Single1 si = new Single1();
	
    public static Single1 get_Single1(){
    	return si;
    }
}
