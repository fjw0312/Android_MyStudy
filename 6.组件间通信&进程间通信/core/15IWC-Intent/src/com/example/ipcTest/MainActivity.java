package com.example.ipcTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


/**
 * android IPC&组件间通信
 * 1.intent     目的意图、组件间媒介    a.普通putExtra()  b.Bundle      (本质：Intent+Bundle)
 * 2.Broadcast  广播
 * 3.IBinder   a.直接继承Binder   b.Messenger     c.ADIL
 * 4.数据存储技术   a.file  b.sqlite  c.sharedPerferences  d.contentProvider  e.网络socket
 * 5.组件间通信还有：第三方开源包EventBus
 * */

public class MainActivity  extends Activity {
	
	TextView textview;
	Button button01;
	Button button02;
	Button button03;
	Button button04;
	Button button05;

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textview = (TextView)findViewById(R.id.TextView01);
		button01 = (Button)findViewById(R.id.button01);
		button02 = (Button)findViewById(R.id.button02);
		button03 = (Button)findViewById(R.id.button03);
		button04 = (Button)findViewById(R.id.button04);
		button05 = (Button)findViewById(R.id.button05);
		//设置监听 
		button01.setOnClickListener(l);		
		button02.setOnClickListener(l);		
		button03.setOnClickListener(l);		
		button04.setOnClickListener(l);	
		button05.setOnClickListener(l);	
	}
	
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){   
				Intent intent = new Intent(MainActivity.this, Page2Activity.class);
				intent.putExtra("fjw", "intent 发送putExtra普通数据");
				startActivity(intent);
			}else if(arg0==button02){
				Intent intent = new Intent(MainActivity.this, Page3Activity.class);
				Bundle myBundle = new Bundle();
				myBundle.putString("fjw", "intent 发送Bundle(String)数据");
				//intent.putExtras(myBundle);
				intent.putExtra("fjw_Bundle3", myBundle);
				startActivity(intent);
			}else if(arg0==button03){
				Intent intent = new Intent(MainActivity.this, Page4Activity.class);
				Bundle myBundle = new Bundle();
				String str = "人生苦短";
				myBundle.putString("fjw-string", "intent 发送Bundle(xxxx)数据");
				myBundle.putInt("fjw-int", 6666);
				myBundle.putFloat("fjw-float", 999.999f);
				myBundle.putCharArray("fjw-CharArray", str.toCharArray());
				intent.putExtra("fjw_Bundle4", myBundle);
				startActivity(intent);
			}else if(arg0==button04){   
				person per = new person();
				per.id = 1;
				per.f = 666.666f;
				per.name ="jjyy"; 
				
				Intent intent = new Intent(MainActivity.this, Page5Activity.class);
				Bundle myBundle = new Bundle();
				myBundle.putParcelable("fjw-Parcelable", per);
				intent.putExtra("fjw_Bundle5", myBundle);
				startActivity(intent);
			}else if(arg0==button05){
				Book per = new Book();
				per.id = 2;
				per.f = 888.888f;
				per.name ="nbnbnb";  
				
				Intent intent = new Intent(MainActivity.this, Page6Activity.class);
				Bundle myBundle = new Bundle();
				myBundle.putSerializable("fjw-Serializable", per);
				intent.putExtra("fjw_Bundle6", myBundle);
				startActivity(intent);
			}
		}
	};

}



