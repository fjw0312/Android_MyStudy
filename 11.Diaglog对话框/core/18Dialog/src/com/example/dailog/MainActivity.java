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
	ProgressDialog progressDialog1;//����һ�� ProgressDialog
	ProgressDialog progressDialog2;//����һ�� ProgressDialog
	
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
				//����һ�� ProgressDialog
				progressDialog1 = new ProgressDialog(MainActivity.this);//����һ�� ProgressDialog
				progressDialog1.setTitle("This is ProgressDialog");
				progressDialog1.setMessage("Loading...");
				progressDialog1.setCancelable(true); //�Ƿ����ͨ��back��ȡ�����Ի���	 			
				progressDialog1.show();
				new Handler().postDelayed(myRunnable, 5000);  //5s ��֪ͨȡ��
			}else if(arg0==button02){
				progressDialog2 = new ProgressDialog(MainActivity.this);//����һ�� ProgressDialog
				progressDialog2.setTitle("������");
				progressDialog2.setProgress(0);
				progressDialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressDialog2.setMax(100);
				myThread.start();
				progressDialog2.show();
			}else if(arg0==button03){
				//new AlertDialog.Builder(MainActivity.this).setMessage("����...").show();
				new AlertDialog.Builder(MainActivity.this).setTitle("����").setMessage("����...").show();			
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

    //���� �����������ݶԻ���
	private void showDialog(){ //����Զ����layout
	    View view = View.inflate(MainActivity.this, R.layout.my_dailg, null);
	//	view = LayoutInflater.from(getContext()).inflate(item_layout_id, null); 
	//	view = View.inflate(getContext(), item_layout_id, null);//����д��һ��
	    
		new AlertDialog.Builder(MainActivity.this)
		.setView(view)
		.setTitle("����2")
		.setMessage("����")  
		.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "ȷ��", Toast.LENGTH_LONG).show();
			}
		})
		.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "ȡ��", Toast.LENGTH_LONG).show();
			}
		})
		.create()
		.show();
	}
	//ѡ������dialog
	private void myDialog(){
		final String[] cities = {"shenzhen","guangzhou","shanghai","beijing","chongqing"};
		new AlertDialog.Builder(MainActivity.this)
		.setIcon(R.drawable.ic_launcher)
		.setTitle("ѡ�����")
		.setItems(cities, new DialogInterface.OnClickListener() {		
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, cities[arg1], Toast.LENGTH_LONG).show();
			}
		})
		.show();
	}
	
	//����ѡ��Dialog
	private void myDialog_1(){
		final String[] Sexs = {"��","Ů","���в�Ů"};
		new AlertDialog.Builder(MainActivity.this)
		.setIcon(R.drawable.ic_launcher)
		.setTitle("ѡ���Ա�")  //0ΪĬ��ѡ���0��ѡ��
		.setSingleChoiceItems(Sexs, 0, new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, Sexs[arg1], Toast.LENGTH_LONG).show();
			}
		})
		.show();
	}
	//����ѡ�� Dialog
	private void myDialog_2(){
		final String[] changes = {"������ͥ","������ʦ","�߿���ѧ","��ҵ����", "����ͬѧ","������","�ɵ�����"};
		new AlertDialog.Builder(MainActivity.this)
		.setIcon(R.drawable.ic_launcher)
		.setTitle("��ѡ�������ж��ٴλ���")  //0ΪĬ��ѡ���0��ѡ��
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
