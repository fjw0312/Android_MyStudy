package com.example.multimedia;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;


/**
 * MediaPlayer  ������Ƶ   ��  ��Ƶ
 * SeekBar ���� 
 * */

public class MainActivity extends Activity {

	SeekBar seekBar01;
	Button mp3_init;
	Button mp3_player;
	Button mp3_pause;
	Button mp3_stop;
	Button mp3_end;
	
	SeekBar seekBar02;
	Button video_init;
	Button video_player;
	Button video_pause; 
	Button video_stop;
	Button video_end;
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	MediaPlayer mediaPlayer01;
	MediaPlayer mediaPlayer02;
	Button nextpage;
	
	String mp3_src = "/mnt/sdcard/video/��С��.mp3";
	String video_src = "/mnt/sdcard/video/mm.mp4"; 
	int mp3_allTime;
	int video_allTime;
	int mp3_currentTime;
	int video_currentTime;
	Timer timer01;
	Timer timer02;

	
	private void init_music(){	
		mediaPlayer01.reset();  //Ϊʹ����MediaPlayer
		mediaPlayer01.setAudioStreamType(AudioManager.STREAM_MUSIC);  //������������

		try {
			mediaPlayer01.setDataSource(mp3_src);   //���ò�����Դ
			mediaPlayer01.prepare();      //�첽  ���������Դ

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			Log.e("init_music>>IllegalArgumentException","�쳣�׳���");
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			Log.e("init_music>>SecurityException","�쳣�׳���");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			Log.e("init_music>>IllegalStateException","�쳣�׳���");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("init_music>>IOException","�쳣�׳���");
			e.printStackTrace();
		}
		//���� �������
		mediaPlayer01.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "mp3�������", Toast.LENGTH_LONG).show();
				mp3_allTime = mediaPlayer01.getDuration();  //��ȡ����ʱ��    ��λ��ms  0.001s
				seekBar01.setMax(mp3_allTime);
				//�ж� ������ʱ��
				if(timer01 == null){
					timer01 = new Timer();				
					timer01.schedule(new TimerTask() {	//��ʱ����   ��ȡ��ǰ ���Ž���			
						@Override
						public void run() {
							// TODO Auto-generated method stub
							mp3_currentTime = mediaPlayer01.getCurrentPosition();
							seekBar01.setProgress(mp3_currentTime);
							seekBar01.postInvalidate();
							Log.i("timerTask01>>"+Thread.currentThread().getName()+"��ǰʱ�䣺",  String.valueOf(mp3_currentTime));
						}
					}, 0, 1000);
				}		
			}
		});
		
		//���� ���Ž��� ����
		mediaPlayer01.setOnCompletionListener(new OnCompletionListener() { 
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "mp3���Ž���", Toast.LENGTH_LONG).show();
				//���Կ��� ���ò����α�
				timer01.cancel();
				timer01 = null;
			}
		});
		
	}
	
	private void init_video(){		
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);		
		mediaPlayer02.reset();
		mediaPlayer02.setDisplay(surfaceHolder);
		mediaPlayer02.setAudioStreamType(AudioManager.STREAM_MUSIC);  //������������
		mediaPlayer02.setLooping(true); //���� �Ƿ�ѭ������
	
		try {
			mediaPlayer02.setDataSource(video_src);   //���ò�����Դ
			mediaPlayer02.prepareAsync();      //�첽  ���������Դ

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
			Log.e("init_video>>IllegalArgumentException","�쳣�׳���");
		} catch (SecurityException e) { 
			// TODO Auto-generated catch block
			Log.e("init_video>>SecurityException","�쳣�׳���");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			Log.e("init_video>>IllegalStateException","�쳣�׳���");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("init_video>>IOException","�쳣�׳���");
			e.printStackTrace();
		}
		//���� �������
		mediaPlayer02.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "video�������", Toast.LENGTH_LONG).show();
				video_allTime = mediaPlayer02.getDuration();  //��ȡ���� ʱ��  ��λ��ms  0.001s
				seekBar02.setMax(video_allTime);
				//�ж� ������ʱ��
				if(timer02 == null){
					timer02 = new Timer();
					timer02.schedule(new TimerTask() {			
						@Override
						public void run() {  //��ʱ����   ��ȡ��ǰ ���Ž���
							// TODO Auto-generated method stub
							video_currentTime = mediaPlayer02.getCurrentPosition();
							seekBar02.setProgress(video_currentTime);
							seekBar02.postInvalidate();
							Log.i("timerTask02>>"+Thread.currentThread().getName()+"��ǰʱ�䣺",  String.valueOf(video_currentTime));
						}
					}, 0, 1000);
				}
			} 
		});
		//���� ���Ž��� ����
		mediaPlayer02.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "video���Ž���", Toast.LENGTH_LONG).show();
				//���Կ��� ���ò����α�
				timer02.cancel(); 
				timer02 = null;
			}
		});
		surfaceHolder.addCallback(new SurfaceHolder.Callback() {		
			@Override //surfaceView ��С�����ı�
			public void surfaceDestroyed(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override //surfaceView ������
			public void surfaceCreated(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override //surfaceView ������
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
		}); 
	}
	
	private OnClickListener l = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==mp3_init){
				init_music();
			}else if(arg0==mp3_player){
				mediaPlayer01.start();
			}else if(arg0==mp3_pause){
				mediaPlayer01.pause();
			}else if(arg0==mp3_stop){
				mediaPlayer01.stop();
				//�ٴλ���  ׼���ٲ���
				init_music();
			}else if(arg0==mp3_end){
				mediaPlayer01.release();
			}else if(arg0==video_init){
				init_video();
			}else if(arg0==video_player){	 				
				mediaPlayer02.start();
			}else if(arg0==video_pause){
				mediaPlayer02.pause();
			}else if(arg0==video_stop){
				mediaPlayer02.stop();
				//�ٴλ���  ׼���ٲ���
				init_video();
			}else if(arg0==video_end){
				mediaPlayer02.release();
			}else if(arg0==nextpage){
				Intent intent = new Intent(MainActivity.this, NextActivity.class);
				startActivity(intent);
			}
		}
	};
	//findView
	private void init_findView(){
		seekBar01 = (SeekBar)findViewById(R.id.seekBar01);
		mp3_init = (Button)findViewById(R.id.mp3_init);
		mp3_player = (Button)findViewById(R.id.mp3_player);
		mp3_pause = (Button)findViewById(R.id.mp3_pause);
		mp3_stop = (Button)findViewById(R.id.mp3_stop);
		mp3_end = (Button)findViewById(R.id.mp3_release);
		
		seekBar02 = (SeekBar)findViewById(R.id.seekBar02);
		video_init = (Button)findViewById(R.id.video_init);
		video_player = (Button)findViewById(R.id.video_player);
		video_pause = (Button)findViewById(R.id.video_pause);
		video_stop = (Button)findViewById(R.id.video_stop);
		video_end = (Button)findViewById(R.id.video_release);
		surfaceView = (SurfaceView)findViewById(R.id.surfaceView01);
		
		nextpage = (Button)findViewById(R.id.next_page);
		
		mp3_init.setOnClickListener(l);
		mp3_player.setOnClickListener(l); 
		mp3_pause.setOnClickListener(l);
		mp3_stop.setOnClickListener(l);
		mp3_end.setOnClickListener(l);
		video_init.setOnClickListener(l);
		video_player.setOnClickListener(l);
		video_pause.setOnClickListener(l);
		video_stop.setOnClickListener(l);
		video_end.setOnClickListener(l);
		nextpage.setOnClickListener(l);
			
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mediaPlayer01 = new MediaPlayer();
		mediaPlayer02 = new MediaPlayer();
		
		init_findView(); 
	
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mediaPlayer01.release();
		mediaPlayer02.release();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
