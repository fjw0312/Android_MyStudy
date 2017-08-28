package org.lxh.demo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MyGPSDemo extends Activity {
	private ListView alldata = null; 							// ListView���
	private LocationManager locationManager = null; 			// λ�ù���
	private ListAdapter adapter = null; 						// ���������
	private List<String> all = new ArrayList<String>();			// ������Ϣ
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);					// Ĭ�ϲ��ֹ�����
		this.locationManager = (LocationManager) super
				.getSystemService(Context.LOCATION_SERVICE); 	// ȡ��λ�÷���
		this.alldata = (ListView) super.findViewById(R.id.alldata); // ������
		this.listProviders() ;									// �г�����
	}
	public void listProviders() {
		List<String> allProviders = this.locationManager.getAllProviders() ;	// ȡ�����е�Provider��Ϣ
		Iterator<String> iter = allProviders.iterator() ;		// ȡ��Iterator����
		while (iter.hasNext()) {								// �������
			String provider = iter.next();						// ÿһ��Provider
			this.all.add(provider);								// ׷������
		}
		this.adapter = new ArrayAdapter<String>(this,			// ʵ����ArrayAdapter
				android.R.layout.simple_list_item_1,			// ���岼���ļ� 
				MyGPSDemo.this.all);							// ������ʾ����
		this.alldata.setAdapter(MyGPSDemo.this.adapter);		// ��������
	}
}

