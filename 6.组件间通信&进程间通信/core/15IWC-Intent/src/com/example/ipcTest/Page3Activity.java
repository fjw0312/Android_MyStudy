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

public class Page3Activity extends Activity {

	TextView textview;
	Button button01;
	String getStr = "....";
	
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page3);
		
		//获取 启动该Activity 的intent   接收Bundle数据   Bundle数据只包含一个String
		Intent intent = this.getIntent(); 
		//Bundle myBundle = intent.getExtras();
		//String getStr = myBundle.getString("fjw");
		Bundle myBundle = intent.getBundleExtra("fjw_Bundle3");				
		if(myBundle != null){
			getStr = myBundle.getString("fjw");
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
				Intent intent = new Intent(Page3Activity.this, MainActivity.class);
				startActivity(intent);
			}
		}
	};
}
