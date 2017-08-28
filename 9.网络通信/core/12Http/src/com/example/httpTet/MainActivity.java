package com.example.httpTet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Myhttp.UrlHAL;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * http������
 * */
public class MainActivity extends Activity {
	
	TextView textView;
	ImageView imageView;
	Button   button01;
	Button   button02;
	Button   button03;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main); 
		super.onCreate(savedInstanceState);  
		
		textView = (TextView)findViewById(R.id.textView01);
		imageView = (ImageView)findViewById(R.id.imageView);  
		button01 = (Button)findViewById(R.id.button01);
		button02 = (Button)findViewById(R.id.button02); 
		button03 = (Button)findViewById(R.id.button03);
		
		button01.setOnClickListener(l);
		button02.setOnClickListener(l);
		button03.setOnClickListener(l);
		Log.i("MainActivity>>","into");
	}   
	
	private OnClickListener l = new OnClickListener() { 
		 
		@Override
		public void onClick(View arg0) { 
			// TODO Auto-generated method stub 
			if(arg0==button01){
				new myThread().start();
				
			}else if(arg0==button02){ 
				new myThread02().start();
			}else if(arg0==button03){ 
				
			}
		}
	};
	//�½�  ��������ͼƬ�߳�   --���� OK
	private class myThread extends Thread{ 
		@Override
		public void run() { 
			// TODO Auto-generated method stub
			super.run();
			String strUrl = "http://p2.ifengimg.com/a/2017_23/5b84bed625b22d0.jpg";
			
			try {
				UrlHAL.downLoadFormUrl(strUrl,"/mnt/sdcard/tmp/", "save.jpg");
				Log.i("MainActivity>>myThread","has done downed");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
				Log.e("MainActivity>>myThread","�쳣�׳���");
			}
		}
	}
	//�½� ���������ļ��߳�   --����OK
	private class myThread02 extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			String strUrl = "http://vdisk.weibo.com/s/sPsMSxe2XbPiv";
			InputStream in = UrlHAL.getInputStream_URL(strUrl);
			if(in==null){
				Log.w("MainActivity>>myThread02","�����ȡ ��Ϊnull");
				return;
			}
			String strFlie = "/mnt/sdcard/tmp/myFile.txt";
			//File file = new File(strFlie);
			OutputStream out;
			try {
				out = new FileOutputStream(strFlie);
			
				byte[] buffer = new byte[2048];
				int count = 0;
				
				while( (count = in.read(buffer,0,2048))>0 ){
						out.write(buffer, 0, count);
				}
				
			}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("MainActivity>>myThread02","�쳣�׳���-1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("MainActivity>>myThread02","�쳣�׳���-2");
		   }
		}
		
	}
	private void mkAlertDialog(String str){
		new AlertDialog.Builder(this).setTitle("֪ͨ").setMessage(str).show();
	}
}
