package com.example.c003;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * activity  生命周期&Dialog
 * @author fjw 2016 5 26 
 *
 */

public class NormalActivity extends Activity {

	//Filed 
	Button button1;
	Button button2;
	OnClickListener l = new OnClickListener() {  
		
		@Override  
		public void onClick(View arg0) {    
			// TODO Auto-generated method stub 
			if(arg0==button1){ 
				Intent intent = new Intent(NormalActivity.this, MainActivity.class);
				startActivity(intent); 
			}else if(arg0==button2){
				NormalActivity.this.finish();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//请求应用无标题
		setContentView(R.layout.normal_layout);
		
		//获取控件
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
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
