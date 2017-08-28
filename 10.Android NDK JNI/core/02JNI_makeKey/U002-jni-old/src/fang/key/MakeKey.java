package fang.key;


//makeKey.soº¯ÊıÉùÃ÷  
public class MakeKey {

	public MakeKey() {
		// TODO Auto-generated constructor stub
	}
	public native int makeKeyFile(String path);
	
	static{ 
		System.loadLibrary("fang_key_MakeKey"); 
	}

}
