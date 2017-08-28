package com.example.IWC_IBinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


/**
 * ʵ�� �����ͨ��   Activity��󶨵�Serviceͨ��   
 * ��ʽ�� ����һ���̳�Binder��IBinder ֱ�ӷ���
 * 
 * */
public class MyService1 extends Service{

	public MyService1() {
		// TODO Auto-generated constructor stub
	}
	
	@Override //ʵ���� service ʱ���봴��
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	@Override  //��ͨ��������ʱ ����
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("MyService1>>onStartCommand","into onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override  //�󶨷���ʱ  ����
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.i("MyService1>>onBind","into onBind");
		return myIBinder;
	}

	@Override  //stop���� �� �Ӵ���ʱ  ����
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("MyService1>>onDestroy","into onDestroy");
	}

	public myIBinder myIBinder = new myIBinder();
	public class myIBinder extends Binder{
		public void startLoad(){
			Log.i("MyService1>>myIBinder","into startLoad");
		}
		public void endLoad(){
			Log.i("MyService1>>myIBinder","into endLoad");
		}
	}

}
