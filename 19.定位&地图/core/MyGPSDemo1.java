package org.lxh.demo;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MyGPSDemo extends Activity {
	private TextView msg = null; 								// 显示坐标信息
	private LocationManager locationManager = null; 			// 位置管理

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);					// 默认布局管理器
		this.locationManager = (LocationManager) super
				.getSystemService(Context.LOCATION_SERVICE); 	// 取得位置服务
		this.msg = (TextView) super.findViewById(R.id.msg); 	// 获得组件
		this.locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 					// GPS定位提供者
				1000, 											// 时间间隔设置为1秒
				1, 												// 位置间隔设置为1米
				new LocationListenerImpl()); 					// 设置位置监听
	}
	private class LocationListenerImpl implements LocationListener {

		@Override
		public void onLocationChanged(Location location) { 		// 设备位置发生改变时触发
			MyGPSDemo.this.msg.setText("用户位置发生改变，新的位置数据：\n" 
					+ "经度：" + location.getLongitude() + "\n" 
					+ "纬度：" + location.getLatitude() + "\n"
					+ "数据精确度：" + location.getAccuracy() + "\n"
					+ "时间：" + location.getTime() + "\n"
					+ "速度：" + location.getSpeed() + "\n"
					+ "方位：" + location.getBearing()) ; 		// 设置文本信息
		}

		@Override
		public void onProviderDisabled(String provider) { 		// 当数据提供者关闭时触发

		}

		@Override
		public void onProviderEnabled(String provider) { 		// 当数据提供者启用时触发
		}

		@Override
		public void onStatusChanged(String provider, 
				int status, Bundle extras) {					// 当位置信息状态更新时触发
		}

	}
}

