package com.example.c019_baidumap2;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiSearch;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

// baiduMap key:NXplwvXMO43Q2rsUrePAG6Lnogqjzz0L
// ��ͼmapView ���� ok   Ŀǰ����location�Լ���ȡ��λ�� ���������ͼ�ܶ�λ��һ����
//��Ŀǰ���ڵ������� ��λ���޷�ȷ����ͷ����
public class MainActivity extends Activity {
	
	//����ٶȵ�ͼ����
	MapView mapView;
	BaiduMap mBaiduMap;
//	PoiSearch mPoiSearch = PoiSearch.newInstance(); //baiduMap  Poi������
	
	//���嶨λ��Ϣ����
	String provider;
	LocationManager locationManager;
	Location location;
	double lat; //����
	double lon; //γ��
	private LocationListener locationListener = new LocationListener() {	
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
			location = arg0;
			myThread thread = new myThread();
			thread.start();	
		}
	};
	
	//ʵ���� handler ˢ��ui
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			switch(msg.what){ 
			case 1:			
				lat = location.getLatitude(); 
				lon = location.getLongitude();
				break;	
			default: break;
			}
			String text1 = "��λ��γ�ȣ�"+lat+" "+lon;
			String text2 = "��λλ�ã�";
			Log.e("MainActivity->handler>>text1=",text1);
		}
		
	};
	//����һ�� ���� λ����Ϣ ������ ���߳�     ��������Ϣ��������ʧ�� 
	//Ŀǰ�����ж�Ϊgoogle��ǽ�ˣ�����ֻ�ܿ��������������磺�������߰� �� �����������
	private class myThread extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub 
			
			
			handler.sendEmptyMessage(1);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��ʼ�� �ٶȵ�ͼSDK
		SDKInitializer.initialize(getApplicationContext());
		
		setContentView(R.layout.activity_main);
		//��ȡ ��ͼ�ؼ�
		mapView = (MapView)findViewById(R.id.mapView);
		//��ȡ��ͼ��
		mBaiduMap = mapView.getMap();
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);    //��ͨ��ͼ���ͷ��
	//	mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE); //���ǵ�ͼ���ͷ��
		
	//	mBaiduMap.setTrafficEnabled(true);//������ͨͼ
		
		initLoaction();
		initMap(); 
		
	}
	
	//��ʼ�� ��λ
	private void initLoaction(){
		Log.e("MainActivity->initLoaction","into!");
		//��ȡ ��λ��Ϣ ������ 
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//��ȡ ��λ�ṩ��
		List<String> providerList = locationManager.getProviders(true);
		if(providerList.contains(LocationManager.GPS_PROVIDER)){
				provider = LocationManager.GPS_PROVIDER;
		}else if(providerList.contains(LocationManager.NETWORK_PROVIDER)){
				provider = LocationManager.NETWORK_PROVIDER;
		}else{ 
				Toast.makeText(this, "�޶�λ����", Toast.LENGTH_SHORT).show();		
				return;
		}
		//��� ��λ��Ϣ �� 
		location = locationManager.getLastKnownLocation(provider);
		if(location != null){
				//ʵ���� λ����Ϣ ���� �߳�
				myThread thread = new myThread();
				thread.start();		
		}
		//���� ע�� ��λ���� ������
		locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
	}
	//��ʼ�� ��ͼ��ʾ�� �������ó�ʼ��
	private void initMap(){
		Log.e("MainActivity->initMap","into!");
		// ������λͼ��  
		mBaiduMap.setMyLocationEnabled(true);  
		// ���춨λ����  
		MyLocationData locData = new MyLocationData.Builder()  
//		    .accuracy(location.getRadius())  
		    // �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360  
		    .direction(100).latitude(location.getLatitude())  
		    .longitude(location.getLongitude()).build();  
		// ���ö�λ����  
		mBaiduMap.setMyLocationData(locData);  
		// ���ö�λͼ������ã���λģʽ���Ƿ���������Ϣ���û��Զ��嶨λͼ�꣩  
//		mCurrentMarker = BitmapDescriptorFactory  
//		    .fromResource(R.drawable.icon_geo);  
//		MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);  
//		mBaiduMap.setMyLocationConfiguration();  
		// ������Ҫ��λͼ��ʱ�رն�λͼ��  
//		mBaiduMap.setMyLocationEnabled(false);
		
		//���� baiduMap ����λ
//		LocationClient mLocClient = new LocationClient(this);
//		LocationClientOption option = new LocationClientOption();
//		option.setOpenGps(true);// ��gps
//		option.setCoorType("bd09ll"); // ������������
//		option.setScanSpan(1000);
//		mLocClient.setLocOption(option);
//		mLocClient.start();
		
		//���� ��λ λ����ʾ ��ͼ
		LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
		mBaiduMap.animateMapStatus(u);
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mapView.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mapView.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
