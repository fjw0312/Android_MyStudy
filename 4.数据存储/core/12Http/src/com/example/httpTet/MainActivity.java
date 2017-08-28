package com.example.httpTet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import Myhttp.URLConnectionHAL;
import Myhttp.UrlHAL;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * http　测试
 * */
public class MainActivity extends Activity {

	Button button01;
	Button button02;
	Button button03;
	Button button04;
	Button button05;
	Button button06;
	String strfile = "/mnt/sdcard/video/fang.txt";
	String url = "http://192.168.0.102:8080/Users/Administrator/Desktop/gg.txt";
	
   private class MyThread extends Thread{	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	//	File file = new File(strfile);			
		try {
			Log.e("MainActivity>OnClickListener","线程into ");
			UrlHAL.downLoadFormUrl(url, "/mnt/sdcard/video/", "fang.txt");
			Log.e("MainActivity>OnClickListener","线程 -flag！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("MainActivity>OnClickListener","异常抛出！");
			e.printStackTrace();
		}
		
	}
  }
   private class MyThread02 extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			String strUrl = "https://www.baidu.com"; 
			Reader reader = UrlHAL.getReader_URL(strUrl);
			if(reader==null){
				Log.e("MainActivity>MyThread02","网络返回为null");
				return;
			} 
			Log.e("MainActivity>MyThread02","has done net");
			try {
				Writer writer = new FileWriter("/mnt/sdcard/fjw_work/load_f2.txt");
				char[] buffer = new char[2048];
				int count = 0;
				while((count=reader.read(buffer))>0){
					writer.write(buffer,0,count);
				}
				reader.close();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	   
   }
  private class MyThread03 extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		String strUrl = "http://apicloud.mob.com/appstore/health/search?key=1c76fb84e8d88&name=玉米";
//		String strUrl = "https://www.baidu.com";
		String strFile = "/mnt/sdcard/fjw_work/load_f3.txt";
		
		InputStream in = URLConnectionHAL.getInputStream_URL(strUrl);
		if(in==null){
			Log.e("MainActivity>MyThread03","网络返回为null");
			return;
		}
		try {
			byte[] buffer = new byte[2048];
			int count = 0;
			OutputStream out = new FileOutputStream(strFile);

				while((count=in.read(buffer))>0){
					out.write(buffer,0,count);
				}
				out.close();
				in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
  }
  private class MyThread04 extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();

			String strFile = "/mnt/sdcard/fjw_work/load_f4.txt";
			String strUrl = "https://www.baidu.com"; 
			Reader reader = URLConnectionHAL.getReader_URL(strUrl);
			if(reader==null){
				Log.e("MainActivity>MyThread04","网络返回为null");
				return;
			} 
			Log.e("MainActivity>MyThread04","has done net");
			try {
				Writer writer = new FileWriter(strFile);
				char[] buffer = new char[2048];
				int count = 0;
				while((count=reader.read(buffer))>0){
					writer.write(buffer,0,count);
				}
				reader.close();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	   
  } 
  private class MyThread05 extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		String strUrl = "http://mail.163.com";
		String strUrlPara = "name=fjw0312@163.com&password=w876023";
		String strFile = "/mnt/sdcard/fjw_work/load_f5.txt"; 
		
		InputStream in = URLConnectionHAL.setInputStream_URL(strUrl, strUrlPara.getBytes());
		if(in==null){
			Log.e("MainActivity>MyThread05","网络返回为null");
			return;
		}
		try {
			byte[] buffer = new byte[2048];
			int count = 0;
			OutputStream out = new FileOutputStream(strFile);

				while((count=in.read(buffer))>0){
					out.write(buffer,0,count);
				}
				out.close();
				in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
  }
	private OnClickListener l = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){		
				MyThread thread = new MyThread();
				thread.start();
			}else if(arg0==button02){
				new MyThread02().start();
			}else if(arg0==button03){
				new MyThread03().start();
			}else if(arg0==button04){
				new MyThread04().start();
			}else if(arg0==button05){
				
			}else if(arg0==button06){
				
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button01 = (Button)findViewById(R.id.button01);
		button02 = (Button)findViewById(R.id.button02);
		button03 = (Button)findViewById(R.id.button03);
		button04 = (Button)findViewById(R.id.button04);
		button05 = (Button)findViewById(R.id.button05);
		button06 = (Button)findViewById(R.id.button06);
		
		button01.setOnClickListener(l);
		button02.setOnClickListener(l);
		button03.setOnClickListener(l);
		button04.setOnClickListener(l);
		button05.setOnClickListener(l);
		button06.setOnClickListener(l);
	}
}
