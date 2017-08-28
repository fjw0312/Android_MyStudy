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
// 地图mapView 加载 ok   目前采用location自己获取定位后 参数传入地图能定位出一个点
//但目前存在的问题是 定位点无法确定箭头方向
public class MainActivity extends Activity {
	
	//定义百度地图变量
	MapView mapView;
	BaiduMap mBaiduMap;
//	PoiSearch mPoiSearch = PoiSearch.newInstance(); //baiduMap  Poi搜索类
	
	//定义定位信息变量
	String provider;
	LocationManager locationManager;
	Location location;
	double lat; //经度
	double lon; //纬度
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
	
	//实例化 handler 刷新ui
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
			String text1 = "定位经纬度："+lat+" "+lon;
			String text2 = "定位位置：";
			Log.e("MainActivity->handler>>text1=",text1);
		}
		
	};
	//定义一个 处理 位置信息 反编译 的线程     反编译信息请求连接失败 
	//目前初步判断为google被墙了，所以只能考虑其他方法：如：下载离线包 或 请求国内数据
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
		//初始化 百度地图SDK
		SDKInitializer.initialize(getApplicationContext());
		
		setContentView(R.layout.activity_main);
		//获取 地图控件
		mapView = (MapView)findViewById(R.id.mapView);
		//获取地图类
		mBaiduMap = mapView.getMap();
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);    //普通地图类型风格
	//	mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE); //卫星地图类型风格
		
	//	mBaiduMap.setTrafficEnabled(true);//开启交通图
		
		initLoaction();
		initMap(); 
		
	}
	
	//初始化 定位
	private void initLoaction(){
		Log.e("MainActivity->initLoaction","into!");
		//获取 定位信息 管理类 
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//获取 定位提供器
		List<String> providerList = locationManager.getProviders(true);
		if(providerList.contains(LocationManager.GPS_PROVIDER)){
				provider = LocationManager.GPS_PROVIDER;
		}else if(providerList.contains(LocationManager.NETWORK_PROVIDER)){
				provider = LocationManager.NETWORK_PROVIDER;
		}else{ 
				Toast.makeText(this, "无定位服务！", Toast.LENGTH_SHORT).show();		
				return;
		}
		//获得 定位信息 类 
		location = locationManager.getLastKnownLocation(provider);
		if(location != null){
				//实例化 位置信息 编译 线程
				myThread thread = new myThread();
				thread.start();		
		}
		//申请 注册 定位更新 监听器
		locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
	}
	//初始化 地图显示的 个人设置初始化
	private void initMap(){
		Log.e("MainActivity->initMap","into!");
		// 开启定位图层  
		mBaiduMap.setMyLocationEnabled(true);  
		// 构造定位数据  
		MyLocationData locData = new MyLocationData.Builder()  
//		    .accuracy(location.getRadius())  
		    // 此处设置开发者获取到的方向信息，顺时针0-360  
		    .direction(100).latitude(location.getLatitude())  
		    .longitude(location.getLongitude()).build();  
		// 设置定位数据  
		mBaiduMap.setMyLocationData(locData);  
		// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）  
//		mCurrentMarker = BitmapDescriptorFactory  
//		    .fromResource(R.drawable.icon_geo);  
//		MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);  
//		mBaiduMap.setMyLocationConfiguration();  
		// 当不需要定位图层时关闭定位图层  
//		mBaiduMap.setMyLocationEnabled(false);
		
		//调用 baiduMap 自身定位
//		LocationClient mLocClient = new LocationClient(this);
//		LocationClientOption option = new LocationClientOption();
//		option.setOpenGps(true);// 打开gps
//		option.setCoorType("bd09ll"); // 设置坐标类型
//		option.setScanSpan(1000);
//		mLocClient.setLocOption(option);
//		mLocClient.start();
		
		//设置 单位 位置显示 地图
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
