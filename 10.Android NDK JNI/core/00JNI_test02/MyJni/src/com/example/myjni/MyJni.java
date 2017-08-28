package com.example.myjni;


//myJni class 
public class MyJni {

	public native void MyJniTestA();
	public native int MyJniTestB(String str); 
	public native String MyJniTestC(String str);
	
	public native int MyJniFunA(int a,float b,byte c);
	public native String MyJniFunB(String str1,String str2);
	public native int MyJniFunC(int a,int b,int c);
	
	public native String MyJniFunAA(int[] a);
	public native int[] MyJniFunAB(int[] a);
	public native String[] MyJniFunAC(String[] a);
	
 	 static{
		 System.loadLibrary("com_example_myjni_MyJni");
	 }
	 
}
