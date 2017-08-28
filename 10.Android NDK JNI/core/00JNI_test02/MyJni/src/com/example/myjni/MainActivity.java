package com.example.myjni;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	MyJni myJni = new MyJni();  
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		

		myJni.MyJniTestA();
		int ret1 = myJni.MyJniTestB("into MyJni");  
		Log.e("MainActivity>>-1-", "  "+String.valueOf(ret1));
		int ret2 = myJni.MyJniTestB("X"); 
		Log.e("MainActivity>>-11-", "  "+String.valueOf(ret2));
		int ret3 = myJni.MyJniTestB("Y"); 
		Log.e("MainActivity>>-22-", "  "+String.valueOf(ret3));
		int ret4 = myJni.MyJniTestB("VB"); 
		Log.e("MainActivity>>-33-", "  "+String.valueOf(ret4));
		
		int ret5 = myJni.MyJniTestB("V1"); 
		Log.e("MainActivity>>-V1-", "  "+String.valueOf(ret5));
		int ret6 = myJni.MyJniTestB("V2"); 
		Log.e("MainActivity>>-V2-", "  "+String.valueOf(ret6));
		int ret7 = myJni.MyJniTestB("V3"); 
		Log.e("MainActivity>>-V3-", "  "+String.valueOf(ret7));
		int ret8 = myJni.MyJniTestB("V4"); 
		Log.e("MainActivity>>-V4-", "  "+String.valueOf(ret8));
		int ret9 = myJni.MyJniTestB("V5"); 
		Log.e("MainActivity>>-V5-", "  "+String.valueOf(ret9));
		int ret10 = myJni.MyJniTestB("V6"); 
		Log.e("MainActivity>>-V6-", "  "+String.valueOf(ret10));
		int ret11 = myJni.MyJniTestB("V7"); 
		Log.e("MainActivity>>-V7-", "  "+String.valueOf(ret11));
		int ret12 = myJni.MyJniTestB("V8"); 
		Log.e("MainActivity>>-V8-", "  "+String.valueOf(ret12));
		int ret13 = myJni.MyJniTestB("V9"); 
		Log.e("MainActivity>>-V9-", "  "+String.valueOf(ret13));
		
		
		String str = myJni.MyJniTestC("send str into Jni");
		Log.e("MainActivity>>-2222-1-", str);
		String str1 = myJni.MyJniTestC("strcpy");
		Log.e("MainActivity>>-2222-2-", str1);
		String str2 = myJni.MyJniTestC("strcat");
		Log.e("MainActivity>>-2222-3-", str2);
//		String fs = "strcat000";
//		String str3 = myJni.MyJniTestC(fs);
//		Log.e("MainActivity>>-2222-4-", str3+"   "+fs);
		
	//	myJni.MyJniFunA(3, 5f, (byte)10);
	//	String str_s = myJni.MyJniFunB("aaaaa", "bbbbbbb");
	//	Log.e("MainActivity>>-2-", str_s);
	//	int ret = myJni.MyJniFunC(10, 26, 34);
	//	Log.e("MainActivity>>-3-", String.valueOf(ret));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { 
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
