package com;


//����(����)ģʽ  ���̰߳�ȫ ------���̰߳�ȫ���Լ�ͬ������synchronized
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
