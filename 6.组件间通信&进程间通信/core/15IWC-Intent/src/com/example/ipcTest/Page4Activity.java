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

public class Page4Activity extends Activity {

	TextView textview;
	Button button01;
	String getStr = "....";
	int getInt = 0;
	float getFloat = 0f;
	char[] bs = new char[20];
	
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page4);
		
		//获取 启动该Activity 的intent  接收Bundle数据   Bundle数据  包含多个数据   int float string
		Intent intent = this.getIntent(); 
		Bundle myBundle = intent.getBundleExtra("fjw_Bundle4");				
		if(myBundle != null){
			getStr = myBundle.getString("fjw-string");
			getInt = myBundle.getInt("fjw-int");
			getFloat = myBundle.getFloat("fjw-float");
			bs = myBundle.getCharArray("fjw-CharArray");
			
			getStr = getStr +">" +String.valueOf(getInt)+">" +String.valueOf(getFloat)+">"+new String(bs);
		}
		

		
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
				Intent intent = new Intent(Page4Activity.this, MainActivity.class);
				startActivity(intent);
			}
		}
	};
}
