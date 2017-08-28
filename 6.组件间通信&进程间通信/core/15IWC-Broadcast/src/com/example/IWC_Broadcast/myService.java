package com.example.IWC_Broadcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class myService extends Service{
	public myService() {
		// TODO Auto-generated constructor stub
	}
	private  BroadcastReceiver  myBroadcastReceiver = new  BroadcastReceiver() {	
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			Log.i("myService>>BroadcastReceiver", action);
			Toast.makeText(context, action, Toast.LENGTH_LONG).show();
		}
	};
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("myService>>BroadcastReceiver", "into onCreate");
		//×¢²á ¹ã²¥½ÓÊÕÆ÷
		IntentFilter filter = new IntentFilter();
		filter.addAction("fjw_Broadcast_Action");
		registerReceiver(myBroadcastReceiver, filter);
		Log.i("myService>>BroadcastReceiver", "into onCreate,has register myBroadcastReceiver");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(myBroadcastReceiver);
	}

}
