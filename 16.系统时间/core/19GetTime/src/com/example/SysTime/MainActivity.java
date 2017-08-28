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
	
	//获取系统时间
	private long getSysTime(){
		long time = System.currentTimeMillis();
		return time;
	}
	//转化时间字符
	private String timeToString(long time){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式转换
		Date date = new Date(time);
		String strTime = formatter.format(date);
		return strTime;
	}
	
	//转化时间字符
	private long stringTotime(String strtime){
		//获取strtime 00:00:00的时间秒数	  将昨天凌晨 作为初始时刻时间。	
		long time = 0;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式转换
			time = formatter.parse(strtime).getTime(); //单位ms
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		return time;
	}
}
