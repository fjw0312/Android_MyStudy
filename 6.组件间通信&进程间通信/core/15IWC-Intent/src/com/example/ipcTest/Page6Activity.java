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

public class Page6Activity extends Activity {

	TextView textview;
	Button button01;
	String getStr = "....";
	
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page6);
		
		//获取 启动该Activity 的intent
		Intent intent = this.getIntent(); 
		Bundle myBundle = intent.getBundleExtra("fjw_Bundle6");				
		if(myBundle != null){
			Book book = (Book) myBundle.getSerializable("fjw-Serializable");
			int id = book.id; 
			float f = book.f;
			String name = book.name; 
			int a1 = book.add();
			int a2 = book.add2(5,8);
			getStr = String.valueOf(id)+">"+String.valueOf(f)+">"+name+">"
			+String.valueOf(a1)+">"+String.valueOf(a2);
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
				Intent intent = new Intent(Page6Activity.this, MainActivity.class);
				startActivity(intent);
			}
		}
	};
}
