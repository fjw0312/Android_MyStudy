package com.example.IWC_IBinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


/**
 * 实现 组件间通信   Activity与绑定的Service通信   
 * 方式： 定义一个继承Binder的IBinder 直接返回
 * 
 * */
public class MyService1 extends Service{

	public MyService1() {
		// TODO Auto-generated constructor stub
	}
	
	@Override //实例化 service 时进入创建
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	@Override  //普通启动服务时 进入
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("MyService1>>onStartCommand","into onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override  //绑定服务时  进入
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.i("MyService1>>onBind","into onBind");
		return myIBinder;
	}

	@Override  //stop服务 或 接触绑定时  进入
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
