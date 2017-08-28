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


/**
 * android IPC&�����ͨ��
 * 1.intent     Ŀ����ͼ�������ý��    a.��ͨputExtra()  b.Bundle      (���ʣ�Intent+Bundle)
 * 2.Broadcast  �㲥
 * 3.IBinder   a.ֱ�Ӽ̳�Binder   b.Messenger     c.ADIL
 * 4.���ݴ洢����   a.file  b.sqlite  c.sharedPerferences  d.contentProvider  e.����socket
 * 5.�����ͨ�Ż��У���������Դ��EventBus
 * */

public class MainActivity  extends Activity {
	
	TextView textview;
	Button button01;
	Button button02;
	Button button03;
	Button button04;
	Button button05;

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textview = (TextView)findViewById(R.id.TextView01);
		button01 = (Button)findViewById(R.id.button01);
		button02 = (Button)findViewById(R.id.button02);
		button03 = (Button)findViewById(R.id.button03);
		button04 = (Button)findViewById(R.id.button04);
		button05 = (Button)findViewById(R.id.button05);
		//���ü��� 
		button01.setOnClickListener(l);		
		button02.setOnClickListener(l);		
		button03.setOnClickListener(l);		
		button04.setOnClickListener(l);	
		button05.setOnClickListener(l);	
	}
	
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){   
				Intent intent = new Intent(MainActivity.this, Page2Activity.class);
				intent.putExtra("fjw", "intent ����putExtra��ͨ����");
				startActivity(intent);
			}else if(arg0==button02){
				Intent intent = new Intent(MainActivity.this, Page3Activity.class);
				Bundle myBundle = new Bundle();
				myBundle.putString("fjw", "intent ����Bundle(String)����");
				//intent.putExtras(myBundle);
				intent.putExtra("fjw_Bundle3", myBundle);
				startActivity(intent);
			}else if(arg0==button03){
				Intent intent = new Intent(MainActivity.this, Page4Activity.class);
				Bundle myBundle = new Bundle();
				String str = "�������";
				myBundle.putString("fjw-string", "intent ����Bundle(xxxx)����");
				myBundle.putInt("fjw-int", 6666);
				myBundle.putFloat("fjw-float", 999.999f);
				myBundle.putCharArray("fjw-CharArray", str.toCharArray());
				intent.putExtra("fjw_Bundle4", myBundle);
				startActivity(intent);
			}else if(arg0==button04){   
				person per = new person();
				per.id = 1;
				per.f = 666.666f;
				per.name ="jjyy"; 
				
				Intent intent = new Intent(MainActivity.this, Page5Activity.class);
				Bundle myBundle = new Bundle();
				myBundle.putParcelable("fjw-Parcelable", per);
				intent.putExtra("fjw_Bundle5", myBundle);
				startActivity(intent);
			}else if(arg0==button05){
				Book per = new Book();
				per.id = 2;
				per.f = 888.888f;
				per.name ="nbnbnb";  
				
				Intent intent = new Intent(MainActivity.this, Page6Activity.class);
				Bundle myBundle = new Bundle();
				myBundle.putSerializable("fjw-Serializable", per);
				intent.putExtra("fjw_Bundle6", myBundle);
				startActivity(intent);
			}
		}
	};

}



