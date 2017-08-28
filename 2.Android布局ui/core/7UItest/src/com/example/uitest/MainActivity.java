package com.example.uitest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * UiTest  
 * 获取 屏幕像素   分辨率
 * 
 * */

public class MainActivity extends Activity {

	//
	TextView textView03;
	TextView textView04;
	TextView textView05;
	TextView textView06;
	
	String str1 = "";
	String str2 = "";

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		float xPx = this.getResources().getDisplayMetrics().widthPixels;
		float yPx = this.getResources().getDisplayMetrics().heightPixels;
		float xdpi = this.getResources().getDisplayMetrics().xdpi;
		float ydpi = this.getResources().getDisplayMetrics().ydpi;
		
		float density = this.getResources().getDisplayMetrics().density;
		float densityDpi = this.getResources().getDisplayMetrics().densityDpi;
		
		textView03 = (TextView)findViewById(R.id.textView03);
		textView04 = (TextView)findViewById(R.id.textView04);
		textView05 = (TextView)findViewById(R.id.textView05);
		textView06 = (TextView)findViewById(R.id.textView06);
		textView03.setOnClickListener(l); 
		textView04.setOnClickListener(l);
		
		str1 = "像素："+String.valueOf(xPx)+"*"+String.valueOf(yPx);
		str2 = "分辨率："+String.valueOf(xdpi)+"*"+String.valueOf(ydpi);
		textView05.setText(str1);
		textView06.setText(str2);
		
		
//		setContentView(R.layout.frame_layout1);
//		setContentView(R.layout.page1);
//		setContentView(R.layout.page2);
//		setContentView(R.layout.relative_layout1);
//		setContentView(R.layout.relative_layout2);
//		setContentView(R.layout.tabel_layout1);
	}
	
	
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0 == textView03){
				Toast.makeText(MainActivity.this, str1, Toast.LENGTH_LONG).show();
			}else if(arg0 == textView04){
				Toast.makeText(MainActivity.this, str2, Toast.LENGTH_LONG).show();
			}
		}
	};

}
