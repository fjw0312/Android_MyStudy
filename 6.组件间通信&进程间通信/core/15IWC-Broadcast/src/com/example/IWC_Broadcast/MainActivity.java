package com.example.IWC_Broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * android IPC&组件间通信
 * 1.intent     目的意图、组件间媒介    a.普通putExtra()  b.Bundle      (本质：Intent+Bundle)
 * 2.Broadcast  广播
 * 3.IBinder   a.直接继承Binder   b.Messenger     c.ADIL
 * 4.数据存储技术   a.file  b.sqlite  c.sharedPerferences  d.contentProvider  e.网络socket
 * 5.组件间通信还有：第三方开源包EventBus
 * */
public class MainActivity extends Activity {
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(myBroadcastReceiver);
	}

	TextView textview; 
	Button button01;
	Button button02;
	Button button03;
	Button button04;
	Button button05;
	Button button06;

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
		button06 = (Button)findViewById(R.id.button06);
		//设置监听 
		button01.setOnClickListener(l);		
		button02.setOnClickListener(l);		
		button03.setOnClickListener(l);		
		button04.setOnClickListener(l);	
		button05.setOnClickListener(l);	
		button06.setOnClickListener(l);	
		
		//注册 广播接收器
		IntentFilter filter = new IntentFilter();
		filter.addAction("fjw_Broadcast_Action");
		registerReceiver(myBroadcastReceiver, filter);
	}
	
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){
				Intent intent = new Intent(MainActivity.this, Page2Activity.class);
				startActivity(intent);
			}else if(arg0==button02){
				Intent intent = new Intent(MainActivity.this, Page3Activity.class);
				startActivity(intent);
			}else if(arg0==button03){
				Intent intent = new Intent(MainActivity.this, Page4Activity.class);
				startActivity(intent);
			}else if(arg0==button04){
				Intent intent = new Intent(MainActivity.this, Page5Activity.class);
				startActivity(intent);
			}else if(arg0==button05){
				Intent intent = new Intent(MainActivity.this, Page6Activity.class);
				startActivity(intent);
			}else if(arg0==button06){
				Intent intent = new Intent(MainActivity.this, myService.class);
				startService(intent);
				Log.i("MainActivity>>onclick", "startService");
			}
		}
	};
	private  BroadcastReceiver  myBroadcastReceiver = new  BroadcastReceiver() {	
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			Log.i("MainActivity>>BroadcastReceiver", action);
			Toast.makeText(context, ">>>"+action+"<<<", Toast.LENGTH_LONG).show();
		}
	};
}
