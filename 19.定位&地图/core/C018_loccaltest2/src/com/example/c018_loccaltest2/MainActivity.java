package com.example.c018_loccaltest2;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

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
import android.widget.TextView;
import android.widget.Toast;

// 注：地址 请求功访问失败 怀疑被墙了
public class MainActivity extends Activity {

	TextView textView1;
	TextView textView2;
	
	double lat; //经度
	double lon; //纬度
	//定义定位信息变量
	String provider;
	LocationManager locationManager;
	Location location;
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
			textView1.setText(text1);
			textView2.setText(text2);
		}
		
	};
	//定义一个 处理 位置信息 反编译 的线程     反编译信息请求连接失败 
	//目前初步判断为google被墙了，所以只能考虑其他方法：如：下载离线包 或 请求国内数据
	private class myThread extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub 
			// 组装反向地理编码的接口地址 
			StringBuilder url = new StringBuilder();
			url.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
			url.append(location.getLatitude()).append(",");
			url.append(location.getLongitude());
			url.append("&sensor=false");
			Log.e("MainActivity->myThread>>url=", url.toString());
			//定义httpClient 网络客户端
			try{ 
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url.toString());	
			Log.e("MainActivity->myThread>>","flag-0-0");
//			httpGet.addHeader("Accept-Language", "zh-CN");// 在请求消息头中指定语言，保证服务器会返回中文数据
			HttpResponse httpResponse = httpClient.execute(httpGet);
			Log.e("MainActivity->myThread>>","flag-0");
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = httpResponse.getEntity();
					String response = EntityUtils.toString(entity,"utf-8");
					JSONObject jsonObject = new JSONObject(response);
					Log.e("MainActivity->myThread>>","flag-1");
					// 获取results节点下的位置信息
					JSONArray resultArray = jsonObject.getJSONArray("results");
					Log.e("MainActivity->myThread>>","flag-2");
					if (resultArray.length() > 0) {
						JSONObject subObject = resultArray.getJSONObject(0);
						// 取出格式化后的位置信息
						String address = subObject.getString("formatted_address");
						Log.e("信息：", address);
					}
				}
			}catch(Exception e){
				Log.e("MainActivity->myThread>>","网络访问异常！");
			}
			
			handler.sendEmptyMessage(1);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView1 = (TextView)findViewById(R.id.textview1);
		textView2 = (TextView)findViewById(R.id.textview2);
		
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
