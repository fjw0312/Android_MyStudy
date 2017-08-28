package com.example.multimedia2;

import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * MediaRecoder 录音    (AudioRecord 也可以录音，还可以边录边播)
 * MediaRecoder 录视频
 * camera  拍照
 * 
 * 
 * */


public class MainActivity extends Activity {

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mediaRecorder != null){
			mediaRecorder.release();
			mediaRecorder = null;
		}
	}

	Button button01;
	Button button02;
	Button button03;
	Button button04;
	Button button05;
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	MediaRecorder mediaRecorder;
	
	//初始化   录音  
	private void init_MediaRecorder_Audio(){       //测试   OK
		mediaRecorder = new MediaRecorder();
		//1.设置  录音声音来源
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		//2.设置  声音输出格式
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		//3.设置  声音编码格式
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		//4.设置  声音文件路劲
		mediaRecorder.setOutputFile("/mnt/sdcard/video/my_recorder.amr"); //录音文件格式有：aac amr  3gp
		//5.准备 录制
		try {
			mediaRecorder.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			Log.e("init_MediaRecorder_Audio>>IllegalStateException","异常抛出！");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("init_MediaRecorder_Audio>>IOException","异常抛出！");
			e.printStackTrace();  
		}
	}
	
	//初始化   录制视屏                测试 失败   原因待查
	@SuppressLint("SdCardPath")
	private void init_MediaRecorder_Video(){
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		surfaceHolder.setFixedSize(320, 280);  //应该是  设置预览分辨率
		surfaceHolder.setKeepScreenOn(true);  //设置 保持亮屏幕
		mediaRecorder = new MediaRecorder();
		mediaRecorder.reset();
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);  //设置 声音来源
	//	mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);  //设置 声音来源
		mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);  //设置  图像来源
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); //设置  输出格式
	//	mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);  //设置  输出编码
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);  //设置  输出编码
		mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);  //设置  输出编码
//		mediaRecorder.setVideoSize(320, 280);
		mediaRecorder.setVideoFrameRate(30);                      //设置  帧速率
		mediaRecorder.setOutputFile("/mnt/sdcard/video/my_recorderVideo.mp4");                        //设置  输出文件
		mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());         //设置预览
		try {
			mediaRecorder.prepare();               //准备 录制
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			Log.e("init_MediaRecorder_Video>>IllegalStateException","异常抛出！");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("init_MediaRecorder_Video>>IOException","异常抛出！");
		}  
	}
	
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){
				init_MediaRecorder_Audio();
				mediaRecorder.start(); 
				Toast.makeText(MainActivity.this, "开始录音！", Toast.LENGTH_LONG).show();
			}else if(arg0==button02){
				mediaRecorder.stop(); //停止 录音 
				mediaRecorder.release(); //释放 资源
				mediaRecorder = null;
				Toast.makeText(MainActivity.this, "录音结束     end！", Toast.LENGTH_LONG).show();
			}else if(arg0==button03){
				init_MediaRecorder_Video();
				try {
					mediaRecorder.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}               //准备 录制
				mediaRecorder.start();
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
				//		mediaRecorder.start();
					}
				}).start();
				
			}else if(arg0==button04){					
				mediaRecorder.stop();
			}else if(arg0==button05){
				mediaRecorder.release();
				mediaRecorder = null;
			}
		}
	};
	
	private void init_findView(){
		button01 = (Button)findViewById(R.id.button01); 
		button02 = (Button)findViewById(R.id.button02);
		button03 = (Button)findViewById(R.id.button03);
		button04 = (Button)findViewById(R.id.button04);
		button05 = (Button)findViewById(R.id.button05); 
		surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
		
		button01.setOnClickListener(l);
		button02.setOnClickListener(l);
		button03.setOnClickListener(l); 
		button04.setOnClickListener(l);  
		button05.setOnClickListener(l);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		init_findView();
	//	init_MediaRecorder_Video();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
