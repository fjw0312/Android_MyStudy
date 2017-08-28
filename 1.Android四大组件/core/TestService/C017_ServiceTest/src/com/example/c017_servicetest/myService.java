package com.example.c017_servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class myService extends Service{ 

	public myService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e("myService->onCreate>>","into!");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e("myService->onStartCommand>>","into!");
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.e("myService->onBind>>","into!");
		return myBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.e("myService->onUnbind>>","into!");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("myService->onDestroy>>","into!");
		super.onDestroy();
	}

	
	public downLoadBinder myBinder = new downLoadBinder();
	//���binder��
	public class downLoadBinder extends Binder{
		//�Զ������ط���
		public void startDownLoad(){
			Log.d("myService->downLoadBinder->startDownLoad","into!");
		}
		public int getProgress(){
			Log.d("myService->downLoadBinder->getProgress","into!");
			return 0;
		}
	}


}
