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
	//����λ�� ����
	private String provider;
	private LocationManager locationManager;
	//ʵ������λ��Ϣ���¼�����
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
			showLocation(arg0); //��ʾ ���µ�λ����Ϣ
		}
	};
	
	//��ʾ��ǰ λ����Ϣ ����
	private void showLocation(Location location){
		String text = "��ǰλ�ã�\n Latitude="+ location.getLatitude()+
				      "\n Longitude="+location.getLongitude();
		textView.setText(text);
	} 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main); 

		textView = (TextView)findViewById(R.id.textView1); 
				 
		//��ȡ����λ�ù�����    
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//��ȡ���õ�λ���ṩ��
		List<String> providerList = locationManager.getProviders(true);   
		//������˳��ѡ�� λ���ṩ��
		if(providerList==null)  return;
		if(providerList.contains(LocationManager.GPS_PROVIDER)){
			provider = LocationManager.GPS_PROVIDER; 
		}else if(providerList.contains(LocationManager.NETWORK_PROVIDER)){
			provider = LocationManager.NETWORK_PROVIDER;
		}else{
			Toast.makeText(this, "û�кõĶ�λ����", Toast.LENGTH_SHORT).show();
			return;
		}
	 
		//��ȡ��λ��Ϣ��
		Location location = locationManager.getLastKnownLocation(provider);
		if(location != null){
			//��ʾ��ǰλ����Ϣ
			showLocation(location);
		}
		//���붨ʱ���� λ����Ϣ ����   ��ʱ5000ms ����1m
		locationManager.requestLocationUpdates(provider,5000,1,locationListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
