package com.example.c013_broadcast;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


//使用本地广播
public class MainActivity extends Activity {
	
	//Fields
	private IntentFilter intentFilter;
	private LocalBroadcastManager localBroadcastManager;
	private LocalReceiver localReceiver;
	
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent("com.example.C013_Broadcast.LOCAL_BROADCAST");
			localBroadcastManager.sendBroadcast(intent);  //本地广播接收
//			sendBroadcast(intent);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//获取控件
		Button button = (Button)findViewById(R.id.button1);
		//设置监听器
		button.setOnClickListener(l);
		
		
		//注册 普通 广播 接收器
        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON); //亮屏广播   
        filter.addAction(Intent.ACTION_SCREEN_OFF); //灭屏广播  
        BroadcastReceiver BroastcastScreenOn = new BroadcastReceiver(){
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				// TODO Auto-generated method stub
				if(arg1.getAction().equals(Intent.ACTION_SCREEN_ON)){
					Log.e("Activity->BroastcastScreenOn","into Intent.ACTION_SCREEN_ON");
				}
			}
        };
        registerReceiver(BroastcastScreenOn, filter);
		

        //注册 本地 广播接收器
		localBroadcastManager = LocalBroadcastManager.getInstance(this); //获取本地广播的管理类			
		//动态注册  广播接收器
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.example.C013_Broadcast.LOCAL_BROADCAST");
		localReceiver = new LocalReceiver();
		localBroadcastManager.registerReceiver(localReceiver,intentFilter);//本地广播注册
//		registerReceiver(localReceiver, intentFilter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		localBroadcastManager.unregisterReceiver(localReceiver);  //本地广播清除接收
//		unregisterReceiver(localReceiver);  //本地广播清除接收
	}
	
	public class LocalReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Toast.makeText(arg0, "receved local broadcast!", Toast.LENGTH_SHORT).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
