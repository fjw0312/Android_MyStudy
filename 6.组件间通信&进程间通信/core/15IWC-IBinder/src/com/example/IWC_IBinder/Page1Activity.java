package com.example.IWC_IBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Page1Activity extends Activity {

	TextView textview;
	Button button01;
	Button button02;
	MyService1.myIBinder myiBinder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page2); 
		
		textview = (TextView)findViewById(R.id.TextView01);
		button01 = (Button)findViewById(R.id.button01);		
		button02 = (Button)findViewById(R.id.button02);	
		//ÉèÖÃ¼àÌý 
		button01.setOnClickListener(l);	
		button02.setOnClickListener(l);	
		
	}
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){  //·µ»Ø
				Intent intent = new Intent(Page1Activity.this, MainActivity.class);
				startActivity(intent);
			}else if(arg0==button02){
				Intent intent = new Intent(Page1Activity.this, MyService1.class);
				bindService(intent, serviceConnection, BIND_AUTO_CREATE);
			}
		}
	};
	
	private ServiceConnection serviceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			Log.i("Page1Activity->ServiceConnection->onServiceDisconnected","into!");
		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder iBinder) {
			// TODO Auto-generated method stub
			Log.i("Page1Activity->ServiceConnection->onServiceConnected","into!");
			myiBinder = (MyService1.myIBinder)iBinder;
			myiBinder.startLoad();
			myiBinder.endLoad();
		}
	};
}
