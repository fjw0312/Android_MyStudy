package com;


//饱汉(懒汉)模式  非线程安全 ------想线程安全可以加同步保护synchronized
public class Single2 {

	public Single2() {
		// TODO Auto-generated constructor stub
	}
	
	private static  Single2 si = null;
	
    public static Single2 get_Single2(){
    	if(si == null){
    		si = new Single2();
    	}
    	return si;
    }
}
