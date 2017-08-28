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
 * MediaRecoder ¼��    (AudioRecord Ҳ����¼���������Ա�¼�߲�)
 * MediaRecoder ¼��Ƶ
 * camera  ����
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
	
	//��ʼ��   ¼��  
	private void init_MediaRecorder_Audio(){       //����   OK
		mediaRecorder = new MediaRecorder();
		//1.����  ¼��������Դ
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		//2.����  ���������ʽ
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		//3.����  ���������ʽ
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		//4.����  �����ļ�·��
		mediaRecorder.setOutputFile("/mnt/sdcard/video/my_recorder.amr"); //¼���ļ���ʽ�У�aac amr  3gp
		//5.׼�� ¼��
		try {
			mediaRecorder.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			Log.e("init_MediaRecorder_Audio>>IllegalStateException","�쳣�׳���");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("init_MediaRecorder_Audio>>IOException","�쳣�׳���");
			e.printStackTrace();  
		}
	}
	
	//��ʼ��   ¼������                ���� ʧ��   ԭ�����
	@SuppressLint("SdCardPath")
	private void init_MediaRecorder_Video(){
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		surfaceHolder.setFixedSize(320, 280);  //Ӧ����  ����Ԥ���ֱ���
		surfaceHolder.setKeepScreenOn(true);  //���� ��������Ļ
		mediaRecorder = new MediaRecorder();
		mediaRecorder.reset();
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);  //���� ������Դ
	//	mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);  //���� ������Դ
		mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);  //����  ͼ����Դ
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); //����  �����ʽ
	//	mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);  //����  �������
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);  //����  �������
		mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);  //����  �������
//		mediaRecorder.setVideoSize(320, 280);
		mediaRecorder.setVideoFrameRate(30);                      //����  ֡����
		mediaRecorder.setOutputFile("/mnt/sdcard/video/my_recorderVideo.mp4");                        //����  ����ļ�
		mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());         //����Ԥ��
		try {
			mediaRecorder.prepare();               //׼�� ¼��
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			Log.e("init_MediaRecorder_Video>>IllegalStateException","�쳣�׳���");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("init_MediaRecorder_Video>>IOException","�쳣�׳���");
		}  
	}
	
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){
				init_MediaRecorder_Audio();
				mediaRecorder.start(); 
				Toast.makeText(MainActivity.this, "��ʼ¼����", Toast.LENGTH_LONG).show();
			}else if(arg0==button02){
				mediaRecorder.stop(); //ֹͣ ¼�� 
				mediaRecorder.release(); //�ͷ� ��Դ
				mediaRecorder = null;
				Toast.makeText(MainActivity.this, "¼������     end��", Toast.LENGTH_LONG).show();
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
				}               //׼�� ¼��
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
