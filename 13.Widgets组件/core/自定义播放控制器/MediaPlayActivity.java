package com.example.gethttp;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import MyFunction.UrlThread;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MediaPlayActivity extends Activity {

	Button Bn_Exit;
	Button Bn_Per;
	EditText Ed_strUrl;
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	MediaPlayer mediaPlayer;
	SeekBar seekBar;
	Button Bn_start;
	Button Bn_stop;
	Button Bn_pause;
	Button Bn_Load;
	Button bn_release;
	
	int video_allTime;      //��Ƶ ����ʱ
	int video_currentTime;  //��Ƶ ��ǰʱ��
	Timer timer;
	
	public static String str_url = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
			
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==Bn_Exit){
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory("android.intent.category.HOME");
				startActivity(intent);
			}else if(arg0==Bn_Per){
				Intent intent = new Intent(MediaPlayActivity.this, VideoActivity.class);
				startActivity(intent);
			}else if(arg0==Bn_Load){  //��ʼ������Ƶ
				String str = Ed_strUrl.getText().toString();
				if("".equals(str)==false){ 
					str_url = str;				
				}
				init_MediaPlay();  //��ʼ�� ������
			}else if(arg0==bn_release){ //�ͷ� ��Ƶ			
				mediaPlayer.release();
			}else if(arg0==Bn_start){
				mediaPlayer.start();
			}else if(arg0==Bn_stop){
				mediaPlayer.stop();
			}else if(arg0==Bn_pause){
				mediaPlayer.pause();
			}
		}
	};
	
	
	//��ʼ�� ������
	private void init_MediaPlay(){
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);		
		mediaPlayer.reset();
		mediaPlayer.setDisplay(surfaceHolder);
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);  //������������
		mediaPlayer.setLooping(true); //���� �Ƿ�ѭ������
	
		try {
			mediaPlayer.setDataSource(str_url);   //���ñ��ز�����Դ
			mediaPlayer.setDataSource(MediaPlayActivity.this, Uri.parse(str_url) );  //�������粥����Դ
			mediaPlayer.prepareAsync();      //�첽  ���������Դ

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
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MediaPlayActivity.this, "video�������", Toast.LENGTH_LONG).show();
				video_allTime = mediaPlayer.getDuration();  //��ȡ���� ʱ��  ��λ��ms  0.001s
				seekBar.setMax(video_allTime);
				//�ж� ������ʱ��
				if(timer == null){
					timer = new Timer();
					timer.schedule(new TimerTask() {			
						@Override
						public void run() {  //��ʱ����   ��ȡ��ǰ ���Ž���
							// TODO Auto-generated method stub
							video_currentTime = mediaPlayer.getCurrentPosition();
							seekBar.setProgress(video_currentTime);
							seekBar.postInvalidate();
							Log.i("timerTask02>>"+Thread.currentThread().getName()+"��ǰʱ�䣺",  String.valueOf(video_currentTime));
						}
					}, 0, 1000);
				}
			} 
		});
		//���� ���Ž��� ����
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MediaPlayActivity.this, "video���Ž���", Toast.LENGTH_LONG).show();
				//���Կ��� ���ò����α�
				timer.cancel(); 
				timer = null;
			}
		});
	}
	// ���� seekBar �϶� �ı����   notice�������϶����ò��Ž��� ��bug �޷���ʱ��Ӧ
	private OnSeekBarChangeListener seekOnclick = new OnSeekBarChangeListener() {	 
		int seekTo = 0;
		@Override
		public void onStopTrackingTouch(SeekBar arg0) { //ֹͣ����
			// TODO Auto-generated method stub
			mediaPlayer.seekTo(seekTo);
			//mediaPlayer.start();
			Log.e("OnSeekBarChangeListener>>SeekBar","SeekBarֹͣ����");
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {//��ʼ����
			// TODO Auto-generated method stub 
			
			Log.e("OnSeekBarChangeListener>>SeekBar","SeekBar��ʼ����");
		}
		
		@Override
		public void onProgressChanged(SeekBar arg0, int progresss, boolean fromUser) {  //����
			// TODO Auto-generated method stub
			if(fromUser){
				Log.e("OnSeekBarChangeListener>>SeekBar�����϶�-true",String.valueOf(progresss)+"  "+String.valueOf(fromUser));
				mediaPlayer.pause();
				seekTo = progresss;
			}else{
				Log.e("OnSeekBarChangeListener>>SeekBar�����϶�-false",String.valueOf(progresss)+"  "+String.valueOf(fromUser));
			}
			
		}
	};
	//Mediapalyer.toSeek()��λ��λ�ü���
	private OnSeekCompleteListener seekComlete = new OnSeekCompleteListener() {
		
		@Override
		public void onSeekComplete(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			mediaPlayer.start();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		requestWindowFeature(Window.FEATURE_NO_TITLE); //���س�app������ ���setContentView֮ǰ
		setContentView(R.layout.activity_mediaplay); 
		
		Bn_Exit = (Button)findViewById(R.id.Bn_Exit);
		Bn_Per = (Button)findViewById(R.id.Bn_Pre);
		Ed_strUrl = (EditText)findViewById(R.id.Ed_Url);
		surfaceView = (SurfaceView)findViewById(R.id.surface_video);
		seekBar = (SeekBar)findViewById(R.id.seekBar);
		Bn_start = (Button)findViewById(R.id.Bn_start);
		Bn_stop = (Button)findViewById(R.id.Bn_stop);
		Bn_pause = (Button)findViewById(R.id.Bn_pause);
		Bn_Load = (Button)findViewById(R.id.Bn_load);
		bn_release = (Button)findViewById(R.id.Bn_release);
		
		Bn_Exit.setOnClickListener(l);
		Bn_Per.setOnClickListener(l);
		Bn_start.setOnClickListener(l);
		Bn_stop.setOnClickListener(l);
		Bn_pause.setOnClickListener(l);
		Bn_Load.setOnClickListener(l);
		bn_release.setOnClickListener(l);
		
		mediaPlayer = new MediaPlayer();
		
		//seekBar.setOnClickListener(ll);
		
		Ed_strUrl.setHint(str_url);
		seekBar.setOnSeekBarChangeListener(seekOnclick);
		mediaPlayer.setOnSeekCompleteListener(seekComlete);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mediaPlayer.release();
	}
}
