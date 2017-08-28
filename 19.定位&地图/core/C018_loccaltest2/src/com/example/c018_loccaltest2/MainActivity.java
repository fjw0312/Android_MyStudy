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

// ע����ַ ���󹦷���ʧ�� ���ɱ�ǽ��
public class MainActivity extends Activity {

	TextView textView1;
	TextView textView2;
	
	double lat; //����
	double lon; //γ��
	//���嶨λ��Ϣ����
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
			textView1.setText(text1);
			textView2.setText(text2);
		}
		
	};
	//����һ�� ���� λ����Ϣ ������ ���߳�     ��������Ϣ��������ʧ�� 
	//Ŀǰ�����ж�Ϊgoogle��ǽ�ˣ�����ֻ�ܿ��������������磺�������߰� �� �����������
	private class myThread extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub 
			// ��װ����������Ľӿڵ�ַ 
			StringBuilder url = new StringBuilder();
			url.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
			url.append(location.getLatitude()).append(",");
			url.append(location.getLongitude());
			url.append("&sensor=false");
			Log.e("MainActivity->myThread>>url=", url.toString());
			//����httpClient ����ͻ���
			try{ 
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url.toString());	
			Log.e("MainActivity->myThread>>","flag-0-0");
//			httpGet.addHeader("Accept-Language", "zh-CN");// ��������Ϣͷ��ָ�����ԣ���֤�������᷵����������
			HttpResponse httpResponse = httpClient.execute(httpGet);
			Log.e("MainActivity->myThread>>","flag-0");
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = httpResponse.getEntity();
					String response = EntityUtils.toString(entity,"utf-8");
					JSONObject jsonObject = new JSONObject(response);
					Log.e("MainActivity->myThread>>","flag-1");
					// ��ȡresults�ڵ��µ�λ����Ϣ
					JSONArray resultArray = jsonObject.getJSONArray("results");
					Log.e("MainActivity->myThread>>","flag-2");
					if (resultArray.length() > 0) {
						JSONObject subObject = resultArray.getJSONObject(0);
						// ȡ����ʽ�����λ����Ϣ
						String address = subObject.getString("formatted_address");
						Log.e("��Ϣ��", address);
					}
				}
			}catch(Exception e){
				Log.e("MainActivity->myThread>>","��������쳣��");
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
