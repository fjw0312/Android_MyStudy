package fang.key;


//makeKey.so函数声明  
public class MakeKey {

	public MakeKey() {
		// TODO Auto-generated constructor stub
	}
	public native int makeKeyFile(String path);  //生成密钥  函数    成功返回0
	public native String getCpuSerial();         //获取cpu序列号   密钥基准码
	public native String getAPPKey(); //获取生成 的    密钥码
	
	static{ 
		System.loadLibrary("fang_key_MakeKey"); 
	}

}
