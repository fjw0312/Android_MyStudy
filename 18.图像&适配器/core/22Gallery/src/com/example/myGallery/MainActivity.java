package com.example.myGallery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button button01;

	Gallery myGrallery; 
	List<Map<String,Integer>> myList = new ArrayList<Map<String,Integer>>(); //画廊 适配 表
	SimpleAdapter simpleAdap;  //简易适配器  
	String[] items = {"item1","item2","item3","item4"};   //
	int[] imag1_ids = {R.drawable.p101,R.drawable.p102,R.drawable.p103,R.drawable.p104};
	int[] imag2_ids = {R.drawable.p011,R.drawable.p012,R.drawable.p013,R.drawable.p014};
	
	private void init_simpleAdapter(){	    
		//初始化 链表
		for(int i=0;i<4;i++){
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("item1", imag1_ids[i]);
			map.put("item2", imag2_ids[i]);
			myList.add(map);
		}
		//初始化 适配器                                             R.layout.my_image
		//参数1：context  2.list<map<>>  3.layout文件   4.list数组中map中key数组(表的 列数组（可以只有一列）) 5.表选中元素的放置layout id
		simpleAdap = new SimpleAdapter(this, myList, R.layout.my_image, 
				new String[]{"item1"}, new int[]{R.id.image});  
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button01 = (Button)findViewById(R.id.button01);
		myGrallery = (Gallery)findViewById(R.id.rallery);
		
		init_simpleAdapter();
		myGrallery.setAdapter(simpleAdap); 
		myGrallery.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "点击图片：" + position, Toast.LENGTH_SHORT).show(); 										// 显示图片编号
			}
		});
		
		button01.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(arg0==button01){
					Intent intent = new Intent(MainActivity.this, NextActivity.class);
					startActivity(intent);
				}
			}
		});
	}
}
