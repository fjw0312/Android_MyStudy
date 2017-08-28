package com.example.mydraw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textView01;
	TextView textView02;
	Button button01;
	Button button02;
	 
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){
			  
			}else if(arg0==button02){  
				Intent intent = new Intent(MainActivity.this, NextActivity.class);
				startActivity(intent);
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);    
		
		textView01 = (TextView)findViewById(R.id.textView01); 
		textView02 = (TextView)findViewById(R.id.textView02);
		button01 = (Button)findViewById(R.id.button01);
		button02 = (Button)findViewById(R.id.button02);
		 
		button01.setOnClickListener(l);
		button02.setOnClickListener(l); 
		
		Log.e("MainActivity>>onCreate","into!");
	}
}
