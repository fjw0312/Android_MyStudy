package fang.key;


//makeKey.so��������  
public class MakeKey {

	public MakeKey() {
		// TODO Auto-generated constructor stub
	}
	public native int makeKeyFile(String path);  //������Կ  ����    �ɹ�����0
	public native String getCpuSerial();         //��ȡcpu���к�   ��Կ��׼��
	public native String getAPPKey(); //��ȡ���� ��    ��Կ��
	
	static{ 
		System.loadLibrary("fang_key_MakeKey"); 
	}

}
