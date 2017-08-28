package com.example.c009_listview;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import javax.net.ssl.ManagerFactoryParameters;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;


public class MainActivity extends Activity {

	//Fields
	private ArrayList<person> lst = new ArrayList<person>();
	private String[] data = {"data1","data2","data3","data4","data5","data6",
							  "data7", "data8", "data9","data10","data11","data12",
							  "data13", "data14", "data15","data16","data17","data18"};
	private int[] imageId = {R.drawable.f1, R.drawable.f2, R.drawable.f3, R.drawable.f4,R.drawable.f5};
	LinearLayout layout;
	ListView listview;
	
	//主线程 handler
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 1:
				personAdapter p_adapter = new personAdapter(MainActivity.this,
						android.R.layout.simple_list_item_1,lst);  
				if(lst==null) break;
				listview.setAdapter(p_adapter);				
				break;
			case 2:
				break;
			default: break;
			}
		};
	};
	//线程
	Thread mythread = new Thread(new Runnable() {
		public void run() {
			init_lst();
			handler.sendEmptyMessage(1);
		}
	});
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		layout = (LinearLayout)findViewById(R.id.main_layoutID);
			
		//获取控件
		listview = (ListView)findViewById(R.id.listview1);
		//设置控件参数
		init_lst(); //定义一个参数数组链表
		personAdapter p_adapter = new personAdapter(MainActivity.this,
				R.layout.item,lst);  
		listview.setAdapter(p_adapter); //设置适配器
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				person p = lst.get(arg2);
				Toast.makeText(MainActivity.this, p.getName(), Toast.LENGTH_SHORT).show();
			}
		});
//		mythread.start();
	}
	
	//初始化人物的对象链表
	private void init_lst(){ 
		
		//获取字符
		//获取图片资源
		for(int i=0;i<5;i++){
			person p = new person(data[i], imageId[i]);
			lst.add(p);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
