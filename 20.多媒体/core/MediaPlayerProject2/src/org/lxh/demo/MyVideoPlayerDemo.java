package org.lxh.demo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MyVideoPlayerDemo extends Activity {
	private ImageButton play = null;
	private ImageButton stop = null;
	private MediaPlayer media = null;
	private SurfaceView surfaceView = null;
	private SurfaceHolder surfaceHolder = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);					// 调用布局文件
		this.play = (ImageButton) super.findViewById(R.id.play);
		this.stop = (ImageButton) super.findViewById(R.id.stop);
		this.surfaceView = (SurfaceView) super.findViewById(R.id.surfaceView);
		this.surfaceHolder = this.surfaceView.getHolder();		// 取得SurfaceHolder
		this.surfaceHolder.setType(
				SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);		// 设置SurfaceView的类型
		this.media = new MediaPlayer();							// 创建MediaPlayer对象
		try {
			this.media.setDataSource("/sdcard/mldn.3gp");		// 设置播放文件的路径
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.play.setOnClickListener(new PlayOnClickListenerImpl());// 单击事件
		this.stop.setOnClickListener(new StopOnClickListenerImpl());// 单击事件
	}
	private class PlayOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			MyVideoPlayerDemo.this.media.setAudioStreamType(
					AudioManager.STREAM_MUSIC);				// 设置音频类型
			MyVideoPlayerDemo.this.media.setDisplay(
					MyVideoPlayerDemo.this.surfaceHolder);	// 设置显示的区域
			try {
				MyVideoPlayerDemo.this.media.prepare();		// 预备状态
				MyVideoPlayerDemo.this.media.start();		// 播放视频
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private class StopOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			MyVideoPlayerDemo.this.media.stop();			// 停止播放
		}
	}
}