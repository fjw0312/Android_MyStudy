package com.example.c018_localtest;


import java.util.List;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;

import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	TextView textView;
	//定义位置 变量
	private String provider;
	private LocationManager locationManager;
	//实例化定位信息更新监听器
	LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
			showLocation(arg0); //显示 更新的位置信息
		}
	};
	
	//显示当前 位置信息 函数
	private void showLocation(Location location){
		String text = "当前位置：\n Latitude="+ location.getLatitude()+
				      "\n Longitude="+location.getLongitude();
		textView.setText(text);
	} 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main); 

		textView = (TextView)findViewById(R.id.textView1); 
				 
		//获取本地位置管理类    
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//获取可用的位置提供器
		List<String> providerList = locationManager.getProviders(true);   
		//按优先顺序选择 位置提供器
		if(providerList==null)  return;
		if(providerList.contains(LocationManager.GPS_PROVIDER)){
			provider = LocationManager.GPS_PROVIDER; 
		}else if(providerList.contains(LocationManager.NETWORK_PROVIDER)){
			provider = LocationManager.NETWORK_PROVIDER;
		}else{
			Toast.makeText(this, "没有好的定位服务！", Toast.LENGTH_SHORT).show();
			return;
		}
	 
		//获取定位信息类
		Location location = locationManager.getLastKnownLocation(provider);
		if(location != null){
			//显示当前位置信息
			showLocation(location);
		}
		//申请定时请求 位置信息 监听   定时5000ms 距离1m
		locationManager.requestLocationUpdates(provider,5000,1,locationListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
