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
	private ListView alldata = null; 							// ListView组件
	private LocationManager locationManager = null; 			// 位置管理
	private ListAdapter adapter = null; 						// 适配器组件
	private List<String> all = new ArrayList<String>();			// 保存信息
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);					// 默认布局管理器
		this.locationManager = (LocationManager) super
				.getSystemService(Context.LOCATION_SERVICE); 	// 取得位置服务
		this.alldata = (ListView) super.findViewById(R.id.alldata); // 获得组件
		this.listProviders() ;									// 列出数据
	}
	public void listProviders() {
		List<String> allProviders = this.locationManager.getAllProviders() ;	// 取得所有的Provider信息
		Iterator<String> iter = allProviders.iterator() ;		// 取得Iterator对象
		while (iter.hasNext()) {								// 迭代输出
			String provider = iter.next();						// 每一个Provider
			this.all.add(provider);								// 追加数据
		}
		this.adapter = new ArrayAdapter<String>(this,			// 实例化ArrayAdapter
				android.R.layout.simple_list_item_1,			// 定义布局文件 
				MyGPSDemo.this.all);							// 定义显示数据
		this.alldata.setAdapter(MyGPSDemo.this.adapter);		// 设置数据
	}
}

