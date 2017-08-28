package com.example.Handle;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textView;
	Button button01;
	Button button02;
	Button button03;
	Button button04;	
	Button button05;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView)findViewById(R.id.textView01);
		button01 = (Button)findViewById(R.id.button01); 
		button02 = (Button)findViewById(R.id.button02); 
		button03 = (Button)findViewById(R.id.button03);
		button04 = (Button)findViewById(R.id.button04);
		button05 = (Button)findViewById(R.id.button05);
		
		button01.setOnClickListener(l);
		button02.setOnClickListener(l);
		button03.setOnClickListener(l); 
		button04.setOnClickListener(l); 
		button05.setOnClickListener(l); 
		
		Log.i("MainActivity", "。。。。。。");
		Locale locale = getResources().getConfiguration().locale; 
		String language = locale.getLanguage(); 
		Log.i("MainActivity>>get language", language);  //获取到中文  "zh"  美国英语"en" 
	}
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){
				new myThread1().start();
		//		myhandler5.sendEmptyMessage(4);
			}else if(arg0==button02){
				new myThread2().start(); 
			}else if(arg0==button03){
				new myThread3().start();
			}else if(arg0==button04){
				new myThread4().start();
			}else if(arg0==button05){
				new myThread5().start();
				
			}
		}
	};
	
	// Handler实例化  不带参数， 表示获取当前线程的looper(UI MianThread启动会自创建MainLooper)   当前线程looper为 MainLooper
	private Handler myHandler1 = new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case 1:
				textView.setText("myHandler1>>get msg-1");
				Log.i("myHandler1", "get msg-1");
				break;
			case 2:
				textView.setText("myHandler1>>get msg-2");
				Log.i("myHandler1", "get msg-2");
				break;
			case 3:
				textView.setText("myHandler1>>get msg-3");
				Log.i("myHandler1", "get msg-3");
				break;
			case 4:
				textView.setText("myHandler1>>get msg-4");
				Log.i("myHandler1", "get msg-4");
				break;
			default: break;
			}
		}
	};
	private class myThread1 extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			myHandler1.sendEmptyMessage(1);
		}		
	}
	private class myThread2 extends Thread{
		//(UI MianThread启动会自创建MainLooper)
		// Handler实例化  不带参数， 表示获取当前线程的looper 虽然在线程类内，但实例化还在父线程，只有进入run后才是子线程
		//当前线程looper为 MainLooper
		public Handler myHandler2 = new Handler(){
			public void handleMessage(Message msg){
				switch(msg.what){
				case 1:
					textView.setText("myHandler2>>get msg-1");
					Log.i("myHandler2", "get msg-1");
					break;
				case 2:
					textView.setText("myHandler2>>get msg-2");
					Log.i("myHandler2", "get msg-2");
					break;
				case 3:
					textView.setText("myHandler2>>get msg-3");
					Log.i("myHandler2", "get msg-3");
					break;
				case 4:
					textView.setText("myHandler2>>get msg-4");
					Log.i("myHandler2", "get msg-4");
					break;
				default: break;
				}
			}
		};
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			myHandler2.sendEmptyMessage(2);
		}		
	}
	public class myThread3 extends Thread{	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			// Handler实例化  不带参数， 表示获取当前线程的looper (如果子线程中没有创建looper会报错！)
			//当前线程looper为该线程的Looper
			//子线程 如果没指定现有looper就是默认该线程looper ，如果looper不存在会报错
			Handler myhandler = new Handler(){ 
				public void handleMessage(Message msg){
					switch(msg.what){
					case 1:
						Log.i("myHandler3", "get msg-1");
						textView.setText("myHandler3>>get msg-1");
						break;
					case 2:
						Log.i("myHandler3", "get msg-2");
						textView.setText("myHandler3>>get msg-2");
						break;
					case 3:
						Log.i("myHandler3", "get msg-3");
			//			textView.setText("myHandler3>>get msg-3");
						break;
					case 4:		
						Log.i("myHandler3", "get msg-4");
//						textView.setText("myHandler3>>get msg-4");
						break;
					default: break;
					}
				}
			};
			myhandler.sendEmptyMessage(3);
		}		
	}
	
	private class myThread4 extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			// Handler实例化  不带参数， 表示获取当前线程的looper (如果子线程中没有创建looper会报错！)
			//当前线程looper为该线程的Looper
			//子线程 如果没指定现有looper就是默认该线程looper ，如果looper不存在会报错
			Handler myhandler = new Handler(Looper.getMainLooper()){  //传入 mainThread looper 
				public void handleMessage(Message msg){
					switch(msg.what){
					case 1:
						Log.i("myHandler4", "get msg-1");
						textView.setText("myHandler4>>get msg-1");
						break;
					case 2:
						Log.i("myHandler4", "get msg-2");
						textView.setText("myHandler4>>get msg-2");
						break;
					case 3:
						Log.i("myHandler4", "get msg-3");
						textView.setText("myHandler4>>get msg-3");
						break;
					case 4:		
						Log.i("myHandler4", "get msg-4");
						textView.setText("myHandler4>>get msg-4");
						break;
					default: break;
					}
				}
			};
			myhandler.sendEmptyMessage(4);
		}
		
	}

	
	    Handler myhandler5 = null;
		private class myThread5 extends Thread{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				// Handler实例化  不带参数， 表示获取当前线程的looper (如果子线程中没有创建looper会报错！)
				//当前线程looper为该线程的Looper
				//子线程 如果没指定现有looper就是默认该线程looper ，如果looper不存在会报错
				Looper.prepare(); //创建 该线程的looper和message.
				//Looper myLooper = Looper.myLooper();
				myhandler5 = new Handler(){  //传入该线程looper 非ui线程  不能刷新ui
					public void handleMessage(Message msg){
						Log.i("myHandler5", "xxxxxxxx");
						switch(msg.what){
						case 1:
							Log.i("myHandler5", "get msg-1");
				//			textView.setText("myHandler5>>get msg-1");  子线程不能刷新 ui
							break;
						case 2:
							Log.i("myHandler5", "get msg-2");
				//			textView.setText("myHandler5>>get msg-2");
							break;
						case 3:
							Log.i("myHandler5", "get msg-3");
				//			textView.setText("myHandler5>>get msg-3");
							break;
						case 4:		
							Log.i("myHandler5", "get msg-4");
				//			textView.setText("myHandler5>>get msg-4"); 
							break;
						default: break;
						}
					}
				};
				
				myhandler5.sendEmptyMessage(1);
				
				Looper.loop();  //此处应该阻塞了        开始 消息循环   退出消息循环可以Looper.quit()
				//不会执行Looper.loop()  后面的程序了
				Log.i("myHandler5", "不会执行！");

			}
	}

}
