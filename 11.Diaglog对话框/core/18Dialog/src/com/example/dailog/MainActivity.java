package com.example.dailog;

import java.util.zip.Inflater;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button button01;
	Button button02;
	Button button03;
	Button button04;
	Button button05;
	Button button06;
	Button button07;
	ProgressDialog progressDialog1;//定义一个 ProgressDialog
	ProgressDialog progressDialog2;//定义一个 ProgressDialog
	
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
		button07 = (Button)findViewById(R.id.button07);
		
		button01.setOnClickListener(l);
		button02.setOnClickListener(l);
		button03.setOnClickListener(l);
		button04.setOnClickListener(l);
		button05.setOnClickListener(l);
		button06.setOnClickListener(l);
		button07.setOnClickListener(l);
		
	}
	private Runnable myRunnable = new Runnable() {
		public void run() {
			progressDialog1.cancel();
		}
	};
	private Thread myThread = new Thread(new Runnable() {	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i<100;i++){
				progressDialog2.setProgress(i); 
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			progressDialog2.setProgress(100);
	//		progressDialog1.cancel();
		}
	});
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){  
				//定义一个 ProgressDialog
				progressDialog1 = new ProgressDialog(MainActivity.this);//定义一个 ProgressDialog
				progressDialog1.setTitle("This is ProgressDialog");
				progressDialog1.setMessage("Loading...");
				progressDialog1.setCancelable(true); //是否可以通过back键取消掉对话框	 			
				progressDialog1.show();
				new Handler().postDelayed(myRunnable, 5000);  //5s 后通知取消
			}else if(arg0==button02){
				progressDialog2 = new ProgressDialog(MainActivity.this);//定义一个 ProgressDialog
				progressDialog2.setTitle("进度条");
				progressDialog2.setProgress(0);
				progressDialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressDialog2.setMax(100);
				myThread.start();
				progressDialog2.show();
			}else if(arg0==button03){
				//new AlertDialog.Builder(MainActivity.this).setMessage("内容...").show();
				new AlertDialog.Builder(MainActivity.this).setTitle("标题").setMessage("内容...").show();			
			}else if(arg0==button04){
				showDialog();
			}else if(arg0==button05){
				myDialog();
			}else if(arg0==button06){
				myDialog_1();
			}else if(arg0==button07){
				myDialog_2();
			}
		}
	};

    //弹出 基本输入内容对话框
	private void showDialog(){ //添加自定义的layout
	    View view = View.inflate(MainActivity.this, R.layout.my_dailg, null);
	//	view = LayoutInflater.from(getContext()).inflate(item_layout_id, null); 
	//	view = View.inflate(getContext(), item_layout_id, null);//两种写法一样
	    
		new AlertDialog.Builder(MainActivity.this)
		.setView(view)
		.setTitle("标题2")
		.setMessage("内容")  
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_LONG).show();
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_LONG).show();
			}
		})
		.create()
		.show();
	}
	//选择内容dialog
	private void myDialog(){
		final String[] cities = {"shenzhen","guangzhou","shanghai","beijing","chongqing"};
		new AlertDialog.Builder(MainActivity.this)
		.setIcon(R.drawable.ic_launcher)
		.setTitle("选择城市")
		.setItems(cities, new DialogInterface.OnClickListener() {		
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, cities[arg1], Toast.LENGTH_LONG).show();
			}
		})
		.show();
	}
	
	//单项选择Dialog
	private void myDialog_1(){
		final String[] Sexs = {"男","女","不男不女"};
		new AlertDialog.Builder(MainActivity.this)
		.setIcon(R.drawable.ic_launcher)
		.setTitle("选择性别")  //0为默认选择第0个选择
		.setSingleChoiceItems(Sexs, 0, new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, Sexs[arg1], Toast.LENGTH_LONG).show();
			}
		})
		.show();
	}
	//多项选择 Dialog
	private void myDialog_2(){
		final String[] changes = {"出生家庭","启蒙老师","高考大学","行业工作", "朋友同学","结婚对象","干爹干妈"};
		new AlertDialog.Builder(MainActivity.this)
		.setIcon(R.drawable.ic_launcher)
		.setTitle("请选择人生有多少次机会")  //0为默认选择第0个选择
		.setMultiChoiceItems(changes, null, new DialogInterface.OnMultiChoiceClickListener() {			
			@Override
			public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, changes[arg1], Toast.LENGTH_LONG).show();
			}
		})
		.show();
	}
}
