package com.example.ipcTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Page2Activity extends Activity {

	TextView textview;
	Button button01;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page2);
		
		//获取 启动该Activity 的intent  只接收intent 一个附加数据
		Intent intent = this.getIntent(); 
		String getStr = intent.getStringExtra("fjw");
		
		textview = (TextView)findViewById(R.id.TextView01);
		button01 = (Button)findViewById(R.id.button01);		
		//设置监听 
		button01.setOnClickListener(l);	
		
		textview.setText(getStr);
	}
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){
				Intent intent = new Intent(Page2Activity.this, MainActivity.class);
				startActivity(intent);
			}
		}
	};
}
