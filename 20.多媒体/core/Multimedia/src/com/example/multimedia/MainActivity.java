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
 * MediaPlayer  测试音频   和  视频
 * SeekBar 测试 
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
	
	String mp3_src = "/mnt/sdcard/video/笨小孩.mp3";
	String video_src = "/mnt/sdcard/video/mm.mp4"; 
	int mp3_allTime;
	int video_allTime;
	int mp3_currentTime;
	int video_currentTime;
	Timer timer01;
	Timer timer02;

	
	private void init_music(){	
		mediaPlayer01.reset();  //为使重用MediaPlayer
		mediaPlayer01.setAudioStreamType(AudioManager.STREAM_MUSIC);  //设置声音类型

		try {
			mediaPlayer01.setDataSource(mp3_src);   //设置播放资源
			mediaPlayer01.prepare();      //异步  缓冲加载资源

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			Log.e("init_music>>IllegalArgumentException","异常抛出！");
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			Log.e("init_music>>SecurityException","异常抛出！");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			Log.e("init_music>>IllegalStateException","异常抛出！");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("init_music>>IOException","异常抛出！");
			e.printStackTrace();
		}
		//监听 缓冲结束
		mediaPlayer01.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "mp3缓冲结束", Toast.LENGTH_LONG).show();
				mp3_allTime = mediaPlayer01.getDuration();  //获取播放时长    单位：ms  0.001s
				seekBar01.setMax(mp3_allTime);
				//判断 启动定时器
				if(timer01 == null){
					timer01 = new Timer();				
					timer01.schedule(new TimerTask() {	//定时任务   获取当前 播放进度			
						@Override
						public void run() {
							// TODO Auto-generated method stub
							mp3_currentTime = mediaPlayer01.getCurrentPosition();
							seekBar01.setProgress(mp3_currentTime);
							seekBar01.postInvalidate();
							Log.i("timerTask01>>"+Thread.currentThread().getName()+"当前时间：",  String.valueOf(mp3_currentTime));
						}
					}, 0, 1000);
				}		
			}
		});
		
		//设置 播放结束 监听
		mediaPlayer01.setOnCompletionListener(new OnCompletionListener() { 
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "mp3播放结束", Toast.LENGTH_LONG).show();
				//可以考虑 重置播放游标
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
		mediaPlayer02.setAudioStreamType(AudioManager.STREAM_MUSIC);  //设置声音类型
		mediaPlayer02.setLooping(true); //设置 是否循环播放
	
		try {
			mediaPlayer02.setDataSource(video_src);   //设置播放资源
			mediaPlayer02.prepareAsync();      //异步  缓冲加载资源

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
			Log.e("init_video>>IllegalArgumentException","异常抛出！");
		} catch (SecurityException e) { 
			// TODO Auto-generated catch block
			Log.e("init_video>>SecurityException","异常抛出！");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			Log.e("init_video>>IllegalStateException","异常抛出！");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("init_video>>IOException","异常抛出！");
			e.printStackTrace();
		}
		//监听 缓冲结束
		mediaPlayer02.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "video缓冲结束", Toast.LENGTH_LONG).show();
				video_allTime = mediaPlayer02.getDuration();  //获取播放 时长  单位：ms  0.001s
				seekBar02.setMax(video_allTime);
				//判断 启动定时器
				if(timer02 == null){
					timer02 = new Timer();
					timer02.schedule(new TimerTask() {			
						@Override
						public void run() {  //定时任务   获取当前 播放进度
							// TODO Auto-generated method stub
							video_currentTime = mediaPlayer02.getCurrentPosition();
							seekBar02.setProgress(video_currentTime);
							seekBar02.postInvalidate();
							Log.i("timerTask02>>"+Thread.currentThread().getName()+"当前时间：",  String.valueOf(video_currentTime));
						}
					}, 0, 1000);
				}
			} 
		});
		//设置 播放结束 监听
		mediaPlayer02.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "video播放结束", Toast.LENGTH_LONG).show();
				//可以考虑 重置播放游标
				timer02.cancel(); 
				timer02 = null;
			}
		});
		surfaceHolder.addCallback(new SurfaceHolder.Callback() {		
			@Override //surfaceView 大小发生改变
			public void surfaceDestroyed(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override //surfaceView 被创建
			public void surfaceCreated(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override //surfaceView 被销毁
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
				//再次缓冲  准备再播放
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
				//再次缓冲  准备再播放
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
