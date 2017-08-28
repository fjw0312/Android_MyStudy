package com.example.IWC_Broadcast;

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
	Button button02;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page4);
		
		textview = (TextView)findViewById(R.id.TextView01);
		button01 = (Button)findViewById(R.id.button01);		
		button02 = (Button)findViewById(R.id.button02);	
		//…Ë÷√º‡Ã˝ 
		button01.setOnClickListener(l);	
		button02.setOnClickListener(l);	
	}
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){  //∑µªÿ
				Intent intent = new Intent(Page4Activity.this, MainActivity.class);
				startActivity(intent);
			}else if(arg0==button02){
				Intent intent = new Intent("fjw_Broadcast_Action");
				sendBroadcast(intent);
			}
		}
	};
}
