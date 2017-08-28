package com.example.c003;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * 1.android 4大组件---activity
 * activity  生命周期&Dialog test ok  but 活动的保存参数 未测试出来
 * @author fjw 2017 5 19 
 * 知识点：#多个acticity跳转    #跳转返回     #后台回收activity数据保存   #生命周期
 *
 */

public class MainActivity extends Activity {

	//Fileds
	Button button1;
	Button button2;
	Button button3;
	TextView textview;
	
	
	@Override   //oncreate 的参数为该acticity活动的保存参数恢复类
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		//获取该活动的保存数据
		if(savedInstanceState!=null){
			String tempData = savedInstanceState.getString("fjw_key");
			textview.setText(tempData);
		}
		
		//获取控件
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		textview = (TextView) findViewById(R.id.textView1);
		//设置监听器
		button1.setOnClickListener(l);
		button2.setOnClickListener(l);
		button3.setOnClickListener(l);
	}
	
	//实例化监听器 
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button1){
				Intent intent = new Intent(MainActivity.this,NormalActivity.class);
				startActivity(intent);
			}else if(arg0==button2){ 
				Log.d("Tag","yyyyyyyyyy"); 
				Intent intent = new Intent(MainActivity.this,DailogActivity.class);
				startActivity(intent);
			}else if(arg0==button3){
				Intent intent = new Intent(MainActivity.this,NormalActivity.class);
				startActivityForResult(intent, 1);
			}
		}
	}; 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("Tag","onActivityResult"); 
		textview.setText("返回");
	}

	protected void onStart(){ 
		super.onStart();
		Log.d("Tag","onStart"); 
	}
	protected void onResume(){
		super.onResume();
		Log.d("Tag","onResume");
	}
	protected void onPause(){
		super.onPause();
		Log.d("Tag","onPause");
	}
	protected void onStop(){
		super.onStop();
		Log.d("Tag","onStop");
	}
	protected void onDestory(){
		super.onDestroy();
		Log.d("Tag","onDestory");
	}
	protected void onRestart(){
		super.onRestart();
		Log.d("Tag","onRestart");
	}
	//将活动页面的数据进行保存
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		String tempData = "save my data";
		outState.putString("fjw_key", tempData);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
