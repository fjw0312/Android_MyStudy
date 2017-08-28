package TXNews.Main;

import TXNews.customView.MyMediaController;
import android.app.ActionBar;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;


public class TestActivity extends Activity{ 

	String video_src = "/mnt/sdcard/video/mm.mp4"; 
	String video_src2 = "/mnt/sdcard/video/SVID.mp4"; 
	String str_url = "http://flv.bn.netease.com/videolib3/1411/24/Eqwbg0292/SD/movie_index.m3u8";
	
	 VideoView videoView1;
	 MyMediaController myMediaController1;
	 VideoView videoView2;
	 MyMediaController myMediaController2;
	 
	 Button init1;
	 Button play1;
	 Button pause1;
	 Button stop1;
	 Button destory1;
	 Button init2;
	 Button play2;
	 Button pause2;
	 Button stop2;
	 Button destory2;
	 
	 
	@Override  
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar(); 
		actionBar.hide();
		setContentView(R.layout.text);
		 
		//»ñÈ¡¿Ø¼þ
		videoView1 = (VideoView)findViewById(R.id.Video_id1);
		myMediaController1 = (MyMediaController)findViewById(R.id.MyMediaController1);
		
		init1 = (Button)findViewById(R.id.Bn_init1);
		play1 = (Button)findViewById(R.id.Bn_play1);
		pause1 = (Button)findViewById(R.id.Bn_pause1);
		stop1 = (Button)findViewById(R.id.Bn_stop1);
		destory1 = (Button)findViewById(R.id.Bn_destory1); 
   
		init1.setOnClickListener(l);
		play1.setOnClickListener(l);
		pause1.setOnClickListener(l);
		stop1.setOnClickListener(l);
		destory1.setOnClickListener(l);
		
		videoView2 = (VideoView)findViewById(R.id.Video_id2);
		myMediaController2 = (MyMediaController)findViewById(R.id.MyMediaController2);
		
		init2 = (Button)findViewById(R.id.Bn_init2);
		play2 = (Button)findViewById(R.id.Bn_play2);
		pause2 = (Button)findViewById(R.id.Bn_pause2);
		stop2 = (Button)findViewById(R.id.Bn_stop2);
		destory2 = (Button)findViewById(R.id.Bn_destory2);
   
		init2.setOnClickListener(ll);
		play2.setOnClickListener(ll);
		pause2.setOnClickListener(ll);
		stop2.setOnClickListener(ll);
		destory2.setOnClickListener(ll);
	}
	private OnClickListener l = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub 
			
			if(arg0==init1){
				init_videoView();
				//videoView.setVideoURI( Uri.parse(str_url) );//or 
				videoView1.setVideoPath(video_src);	
			}else if(arg0==play1){
				videoView1.start(); 
			}else if(arg0==pause1){
				videoView1.pause();      //ÔÝÍ£²¥·Å   videoView.resume()¼ÌÐø²¥·Å
			}else if(arg0==stop1){
				videoView1.resume(); 
			}else if(arg0==destory1){
				videoView1.stopPlayback();
			}
		}
		
	};	
	//³õÊ¼»¯ ²¥·ÅÆ÷
	private void init_videoView(){ 						
		myMediaController1.setMediaPlayer(videoView1); 
		videoView1.requestFocus();
	
		videoView1.setOnCompletionListener(new OnCompletionListener() { //¼àÌý²¥·Å½áÊø		
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
			//	videoView.stopPlayback();
			}
		});
		videoView1.setOnErrorListener(new OnErrorListener() { //¼àÌý²¥·Å´íÎó			
			@Override
			public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}
	
	
	
	private OnClickListener ll = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub 
			
			if(arg0==init2){
				init_videoView2();
				//videoView.setVideoURI( Uri.parse(str_url) );//or 
				videoView2.setVideoPath(video_src);	
			}else if(arg0==play2){
				videoView2.start(); 
			}else if(arg0==pause2){
				videoView2.pause();      //ÔÝÍ£²¥·Å   videoView.resume()¼ÌÐø²¥·Å
			}else if(arg0==stop2){
				videoView2.resume(); 
			}else if(arg0==destory2){
				videoView2.stopPlayback();
			}
		}
		
	};	
	//³õÊ¼»¯ ²¥·ÅÆ÷
	private void init_videoView2(){ 						
		myMediaController2.setMediaPlayer(videoView2); 
		videoView2.requestFocus();
	
		videoView2.setOnCompletionListener(new OnCompletionListener() { //¼àÌý²¥·Å½áÊø		
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
			//	videoView.stopPlayback();
			}
		});
		videoView2.setOnErrorListener(new OnErrorListener() { //¼àÌý²¥·Å´íÎó			
			@Override
			public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}


}
