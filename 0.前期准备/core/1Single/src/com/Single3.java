package com;


//饱汉(懒汉)模式  线程安全 
public class Single3 {

	public Single3() {
		// TODO Auto-generated constructor stub
	}
	
	private static  Single3 si = null;
	
    public static synchronized Single3 get_Single3(){
    	if(si == null){
    		si = new Single3();  //如果多线程要  synchronize(Single3.class){new}
    	}
    	return si;
    }
}
