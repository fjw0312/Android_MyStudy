package com.example.Thread;

import java.util.Timer;
import java.util.TimerTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textView01;
	Button button01;
	Button button02;
	Button button03;
	Button button04;
	Button button05;
	Button button06;
	Button button07;
	Button button08;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView01 = (TextView)findViewById(R.id.textView01);
		button01 = (Button)findViewById(R.id.button01);
		button02 = (Button)findViewById(R.id.button02);
		button03 = (Button)findViewById(R.id.button03);
		button04 = (Button)findViewById(R.id.button04);
		button05 = (Button)findViewById(R.id.button05);
		button06 = (Button)findViewById(R.id.button06);
		button07 = (Button)findViewById(R.id.button07);
		button08 = (Button)findViewById(R.id.button08);
		
		button01.setOnClickListener(l);
		button02.setOnClickListener(l);
		button03.setOnClickListener(l);
		button04.setOnClickListener(l);
		button05.setOnClickListener(l);
		button06.setOnClickListener(l);
		button07.setOnClickListener(l);
		button08.setOnClickListener(l);
		
	}
	
	private OnClickListener l = new OnClickListener() { 
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){
				myThread.start();
			}else if(arg0==button02){
				MyThread thread = new MyThread();
				thread.start(); //new MyThread().start();			
			}else if(arg0==button03){
				Thread thread = new Thread(new MyRunnable()); //��Runnable()���������Thread
				thread.start(); //new Thread(new MyRunnable()).start();
				//����  
				new Handler().post(new MyRunnable());  //��Runnable post �������߳�mianThread ��Handler
			}else if(arg0==button04){
				Thread newThread = new Thread(new Runnable() {				
					@Override
					public void run() {
						// TODO Auto-generated method stub
						VPost();
					}
				});		
				newThread.start();
			}else if(arg0==button05){
				Thread newThread = new Thread(new Runnable() {				
					@Override
					public void run() {
						// TODO Auto-generated method stub
						HPost();
					}
				});
				newThread.start();
			}else if(arg0==button06){
			//	timer.schedule(task, 1000*1); //1s ��ʱ
				timer.schedule(task, 1000*5, 1000*2); //5s��ʼ ��ʱ 2s����
			}else if(arg0==button07){

				asyncTask.execute("�������");
			//	asyncTask.cancel(true);  �����첽����
			}else if(arg0==button08){
				asyncTask.cancel(true);
			}
		}
	};
	
	//�߳� ʵ�ֵ�һ����ʽ   �˷�ʽ�߳�������ʱ �����ظ����ã�ֻ����һ�����̴߳���
	private Thread myThread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.i("myThread-1","�߳�-1");
			try {
				Thread.sleep(1000*5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	//�߳� ʵ�ֵ�2����ʽ   �˷�ʽ���ظ�����  ���������̴߳���
	private class MyThread extends Thread{ 
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			Log.i("myThread-2","�߳�-2");
			try {
				Thread.sleep(1000*5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	//�߳� ʵ�ֵ�3����ʽ   ����һ��ʵ��Ruannable�ӿڵ���
	private class MyRunnable implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String threadName = Thread.currentThread().getName();
			Log.i("myThread-3","�߳�-3--name:"+threadName);
			try {
				Thread.sleep(1000*5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	private class MyRunnable2 implements Runnable{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String threadName = Thread.currentThread().getName();
				Log.i("myThread-4","�߳�-4--name:"+threadName);
	//			textView01.setText("����View.post(Runnable)");
			}	
	}
	 
	//view.post
	private void VPost(){
		String threadName = Thread.currentThread().getName();
		Log.i("myThread-5","�߳�-5-VPost-name:"+threadName);
		textView01.post(new MyRunnable2()); //post �����̵߳�handler->messageQuece
	}
	
	//Handler.post
	private void HPost(){
		String threadName = Thread.currentThread().getName();
		Log.i("myThread-6","�߳�-6-HPost-name:"+threadName);
//		Looper.prepare();
	//	Handler handler = new Handler();  //�����߳� ��Ҫ�󶨴��ڵ�looper
		Handler handler = new Handler(getMainLooper());  //�����߳�looper
		handler.post(new MyRunnable2());
//		Looper.loop();
	}
	
	//timer-task
	Timer timer = new Timer();
	TimerTask task = new TimerTask(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String threadName = Thread.currentThread().getName();
			Log.i("myThread-7-TimerTask","�߳�-7--name:"+threadName);
		}
			
	};
	
	myAsyncTask asyncTask = new myAsyncTask();
	//�������첽��    �첽���� extends AsyncTask<Params, Progress, Result>
	private class myAsyncTask extends AsyncTask<String, Integer, String>{

		@Override  //ֻ�� �첽������ֹ   �������mainThreadִ��
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			String threadName = Thread.currentThread().getName();
			Log.i("myAsyncTask��onCancelled","into "+threadName);
		}

		@Override  //ֻ�� �첽������ֹ   �������mainThreadִ��
		protected void onCancelled(String result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
			String threadName = Thread.currentThread().getName();
			Log.i("myAsyncTask��onCancelled(String)=="+result,"into "+threadName);
		}

		@Override //��ִ̨�� ����  ��mainThreadִ��
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			String threadName = Thread.currentThread().getName();
			Log.i("myAsyncTask��onPostExecute=="+result,"into  "+threadName);
		}

		@Override //ǰ��ִ��   ��mainThreadִ��
		protected void onPreExecute() { 
			// TODO Auto-generated method stub
			super.onPreExecute();
			String threadName = Thread.currentThread().getName();
			Log.i("myAsyncTask��onPreExecute","into  "+threadName);
		}

		@Override //���� ��̨����
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			String threadName = Thread.currentThread().getName();
			Log.i("myAsyncTask��onProgressUpdate","into"+threadName+""+"���ȣ�"+String.valueOf(values[0]));
		}

		@Override //��ִ̨��   ��̨���߳�ִ��
		protected String doInBackground(String[] params) {
			// TODO Auto-generated method stub
			//��ȡ����
			String url = params[0];
			String threadName = Thread.currentThread().getName();
			Log.i("myAsyncTask��doInBackground-"+url,"into"+threadName);
			for(int i=0;i<50;i++){
				if(isCancelled()) break;
				publishProgress(i);
				try {
					Thread.sleep(1000*1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "doInBackground-end";
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
