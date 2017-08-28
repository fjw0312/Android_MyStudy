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


//ʹ�ñ��ع㲥
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
			localBroadcastManager.sendBroadcast(intent);  //���ع㲥����
//			sendBroadcast(intent);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ȡ�ؼ�
		Button button = (Button)findViewById(R.id.button1);
		//���ü�����
		button.setOnClickListener(l);
		
		
		//ע�� ��ͨ �㲥 ������
        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON); //�����㲥   
        filter.addAction(Intent.ACTION_SCREEN_OFF); //�����㲥  
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
		

        //ע�� ���� �㲥������
		localBroadcastManager = LocalBroadcastManager.getInstance(this); //��ȡ���ع㲥�Ĺ�����			
		//��̬ע��  �㲥������
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.example.C013_Broadcast.LOCAL_BROADCAST");
		localReceiver = new LocalReceiver();
		localBroadcastManager.registerReceiver(localReceiver,intentFilter);//���ع㲥ע��
//		registerReceiver(localReceiver, intentFilter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		localBroadcastManager.unregisterReceiver(localReceiver);  //���ع㲥�������
//		unregisterReceiver(localReceiver);  //���ع㲥�������
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
