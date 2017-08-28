package com.example.c017_servicetest;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * 1.android 4大组件---Service
 * Service 
 * @author fjw 2017 5 19 
 * 知识点：#SerVice启动    #SerVice杀死    #SerVice与activity间通信binder   #生命周期
 *
 */

public class MainActivity extends Activity {

	Button button1; 
	Button button2;
	Button button3;
	Button button4;
	
	myService.downLoadBinder serBinder;
	
	//实例化 serviceConnection 
	private ServiceConnection serviceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			Log.d("Activity->ServiceConnection->onServiceDisconnected","into!");
		}
		 
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			// TODO Auto-generated method stub
			Log.d("Activity->ServiceConnection->onServiceConnected","into!");
			serBinder = (myService.downLoadBinder)arg1;
			serBinder.startDownLoad();
			serBinder.getProgress();
		}
	};
	//实例化监听器
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button1){
				Intent intent = new Intent(MainActivity.this, myService.class);
				startService(intent);
			}else if(arg0==button2){
				Intent intent = new Intent(MainActivity.this, myService.class);
				stopService(intent);
			}else if(arg0==button3){
				Intent intent = new Intent(MainActivity.this, myService.class);
				bindService(intent, serviceConnection, BIND_AUTO_CREATE);
				
			}else if(arg0==button4){
			//	Intent intent = new Intent(MainActivity.this, myService.class);
				unbindService(serviceConnection);
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		button4 = (Button)findViewById(R.id.button4);
		
		button1.setOnClickListener(l);
		button2.setOnClickListener(l);
		button3.setOnClickListener(l);
		button4.setOnClickListener(l);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
