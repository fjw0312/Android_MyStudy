package com.example.c002;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


// test Intent  隐式Intent用法  调用浏览器活动

public class Page2Activity extends Activity {
	
	//Fields
	TextView textview1;
	TextView textview2;
	Button button1;
	Button button2;
	
	Intent intent;
	

	//实例化将听器
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button1){
				intent = new Intent(Page2Activity.this, MainActivity.class);
				startActivity(intent);
			}else if(arg0==button2){

				String str = (String)intent.getSerializableExtra("fjw");
				textview2.setText(str);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page2);
		
		//获取Intent 传递的数据
		intent = getIntent();
		
		//获取控件
		textview1 = (TextView)findViewById(R.id.textView1);
		textview2 = (TextView)findViewById(R.id.textView2);
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		
		//设置监听器
		button1.setOnClickListener(l);
		button2.setOnClickListener(l);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
