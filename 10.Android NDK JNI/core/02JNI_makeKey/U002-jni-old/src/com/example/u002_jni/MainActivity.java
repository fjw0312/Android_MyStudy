package com.example.u002_jni;

import fang.key.MakeKey;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;




public class MainActivity extends Activity {

	
	private TextView textView;
	  
	MakeKey makeKey = new MakeKey();     

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);  
		
		textView = (TextView)findViewById(R.id.TextId);
		int ret = makeKey.makeKeyFile("/mnt/sdcard/tmp");


		Log.e("MainActivity>>","end £¡ "+String.valueOf(ret));
	}
}
