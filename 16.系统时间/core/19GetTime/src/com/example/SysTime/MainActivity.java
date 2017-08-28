package com.example.SysTime;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textView01;
	TextView textView02;
	TextView textView03;
	TextView textView04;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView01 = (TextView)findViewById(R.id.textView01);
		textView02 = (TextView)findViewById(R.id.textView02);
		textView03 = (TextView)findViewById(R.id.textView03);
		textView04 = (TextView)findViewById(R.id.textView04);
		
		long l = getSysTime();
		String str = timeToString(l);
		textView01.setText(String.valueOf(l));
		textView02.setText(str);
		
		long ll = stringTotime(str);
		String str2 = timeToString(ll);
		textView03.setText(String.valueOf(ll));
		textView04.setText(str2);
		
	}
	
	//��ȡϵͳʱ��
	private long getSysTime(){
		long time = System.currentTimeMillis();
		return time;
	}
	//ת��ʱ���ַ�
	private String timeToString(long time){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//ʱ���ʽת��
		Date date = new Date(time);
		String strTime = formatter.format(date);
		return strTime;
	}
	
	//ת��ʱ���ַ�
	private long stringTotime(String strtime){
		//��ȡstrtime 00:00:00��ʱ������	  �������賿 ��Ϊ��ʼʱ��ʱ�䡣	
		long time = 0;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//ʱ���ʽת��
			time = formatter.parse(strtime).getTime(); //��λms
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		return time;
	}
}
