package com.example.multimedia;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


/***
 * SoundPool VideoView
 * SoundPool:���ڲ��Ŷ�ʱ����Ч��֧��ͬʱ���Ŷ������������1M��С�ռ�
 * ʹ�÷�����
 *    SoundPool soundPool = new SoundPool(10,Audiomanager.STREAM_MUSIC, 5);//����1�����������   ����3��Ʒ�ʵȼ�
 *    int source_id = soundPool.load(context,R.raw.move_sound, 1); //���Դ�apk����ϵͳ�ļ�����
 *    soundPool.play(source_id, 2, 2, 0, 0, 1); //����2.3:������������   ����4�����ȼ�   ����5��ѭ������     ����6������
 *    ����5��ѭ������    0:��ѭ��     -1:����ѭ��              ����6������    1.0��Ϊԭʼ����
 *    
 * VideoView �� MediaController ���ʹ��
 * ʹ�ã� VideoView  .setVideoPath()  ����  .setVideoURI()  ������Դ
 *      start()    stop()    pause()   ���Ʋ��Ų���
 * */

public class NextActivity extends Activity{
	public NextActivity() {
		// TODO Auto-generated constructor stub
	}

	Button playSoundPool;
	Button button01;
	Button button02;
	Button button03;
	Button button04;
	Button button05;
	String Sound_src = "/mnt/sdcard/video/�����޵�.mp3";
	int source_id;
	SoundPool soundPool;
	
	
	Button video_init;
	Button video_player;
	Button video_pause; 
	Button video_stop;
	Button video_end;
	VideoView videoView;
	MediaController mediaController;
	String video_src = "/mnt/sdcard/video/mm.mp4"; 
	
	private void loadSoundsrc(){
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 1);
		source_id = soundPool.load( Sound_src, 1);
	}
	
	private void init_videoView(){
		mediaController = new MediaController(NextActivity.this);   //�����Զ� ��Ӳ��ſ�����  ��ȥ��Ҳ�ܲ��ţ������Ƶ�ܿ���������
		videoView.setVideoPath(video_src);
		videoView.setMediaController(mediaController);
		mediaController.setMediaPlayer(videoView);
		videoView.requestFocus();
	}
	
	private OnClickListener l = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==playSoundPool){
				soundPool.play(source_id, 2, 2, 0, 0, 1.0f);
				 
			}else if(arg0 == button01){
				soundPool.pause(1);
			}else if(arg0 == button02){
				soundPool.resume(1);
			}else if(arg0 == button03){
				
			}else if(arg0 == button04){
				
			}else if(arg0==video_init){
				init_videoView();
			}else if(arg0==video_player){
				videoView.start();
			}else if(arg0==video_pause){
				videoView.pause();
			}else if(arg0==video_stop){
				videoView.stopPlayback();
			}else if(arg0==video_end){
			
			}
		}
	};
	
	private void init_findView(){
		playSoundPool = (Button)findViewById(R.id.soundPool_play);
		button01 = (Button)findViewById(R.id.button01);
		button02 = (Button)findViewById(R.id.button02);
		button03 = (Button)findViewById(R.id.button03);
		button04 = (Button)findViewById(R.id.button04);
		videoView = (VideoView)findViewById(R.id.videoView);
		
		video_init = (Button)findViewById(R.id.video_init);
		video_player = (Button)findViewById(R.id.video_player);
		video_pause = (Button)findViewById(R.id.video_pause);
		video_stop = (Button)findViewById(R.id.video_stop);
		video_end = (Button)findViewById(R.id.video_release);
		
		playSoundPool.setOnClickListener(l);
		button01.setOnClickListener(l);
		button02.setOnClickListener(l);
		button03.setOnClickListener(l);
		button04.setOnClickListener(l);
		video_init.setOnClickListener(l);
		video_player.setOnClickListener(l);
		video_pause.setOnClickListener(l);
		video_stop.setOnClickListener(l);
		video_end.setOnClickListener(l);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next);
		
		init_findView();
		
		//load SoundPool src
		loadSoundsrc();
	}


}
