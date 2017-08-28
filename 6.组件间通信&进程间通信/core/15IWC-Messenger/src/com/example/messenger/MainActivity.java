package com.example.messenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
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
public class MainActivity extends Activity {

	TextView textView01;
	Button button01;
	Button button02;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView01 = (TextView)findViewById(R.id.textView01);
		button01 = (Button)findViewById(R.id.button01);
		button02 = (Button)findViewById(R.id.button02);
		
		button01.setOnClickListener(l); 
		button02.setOnClickListener(l);
		Log.e("MainActivity->onCreate","into");  
	}
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub 
			if(arg0==button01){  //绑定 服务
				Intent intent = new Intent(MainActivity.this, MyService.class);
				bindService(intent, serviceConnection, BIND_AUTO_CREATE);
			}else if(arg0==button02){
				Message msg = new Message();
				msg.what = 0;
				try {
					messenger.send(msg);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.i("MainActivity->onClick","has done send Message");
			}
		} 
	};
	private ServiceConnection serviceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			Log.i("MainActivity->serviceConnection","onServiceDisconnected");
		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder iBinder) {
			// TODO Auto-generated method stub
			Log.i("MainActivity->serviceConnection","onServiceConnected");
			//获取到  Server端的Messenger 
			Messenger ServiceMessenger = new Messenger(iBinder);
			//发送 message 给server端的 Messenger-handler
			Message msg = new Message();
		//	msg.what = 1;
		//	msg.replyTo = messenger; //将 Activity端的Messenger也发送过去			
			msg.what = 2;
			msg.replyTo = messenger; //将 Activity端的Messenger也发送过去
			
			try {
				ServiceMessenger.send(msg);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	//定义一个Messenger
	private Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what){
			case 0:
				Log.i("MainActivity->myHandler","get Message");
				break;
			case 3:
				Log.i("MainActivity->myHandler","get Message form Service by ActivityMessenger");
				break;	
			default: break;
			}
		}
	};
	public Messenger messenger = new Messenger(myHandler);
}
