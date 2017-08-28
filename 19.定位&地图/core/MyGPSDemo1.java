package org.lxh.demo;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MyGPSDemo extends Activity {
	private TextView msg = null; 								// ��ʾ������Ϣ
	private LocationManager locationManager = null; 			// λ�ù���

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);					// Ĭ�ϲ��ֹ�����
		this.locationManager = (LocationManager) super
				.getSystemService(Context.LOCATION_SERVICE); 	// ȡ��λ�÷���
		this.msg = (TextView) super.findViewById(R.id.msg); 	// ������
		this.locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 					// GPS��λ�ṩ��
				1000, 											// ʱ��������Ϊ1��
				1, 												// λ�ü������Ϊ1��
				new LocationListenerImpl()); 					// ����λ�ü���
	}
	private class LocationListenerImpl implements LocationListener {

		@Override
		public void onLocationChanged(Location location) { 		// �豸λ�÷����ı�ʱ����
			MyGPSDemo.this.msg.setText("�û�λ�÷����ı䣬�µ�λ�����ݣ�\n" 
					+ "���ȣ�" + location.getLongitude() + "\n" 
					+ "γ�ȣ�" + location.getLatitude() + "\n"
					+ "���ݾ�ȷ�ȣ�" + location.getAccuracy() + "\n"
					+ "ʱ�䣺" + location.getTime() + "\n"
					+ "�ٶȣ�" + location.getSpeed() + "\n"
					+ "��λ��" + location.getBearing()) ; 		// �����ı���Ϣ
		}

		@Override
		public void onProviderDisabled(String provider) { 		// �������ṩ�߹ر�ʱ����

		}

		@Override
		public void onProviderEnabled(String provider) { 		// �������ṩ������ʱ����
		}

		@Override
		public void onStatusChanged(String provider, 
				int status, Bundle extras) {					// ��λ����Ϣ״̬����ʱ����
		}

	}
}

