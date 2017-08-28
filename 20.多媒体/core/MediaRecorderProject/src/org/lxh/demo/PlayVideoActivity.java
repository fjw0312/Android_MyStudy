package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PlayVideoActivity extends Activity {
	private SurfaceView surfaceView = null;
	private SurfaceHolder surfaceHolder = null;
	private MediaPlayer media = null;
	private ImageButton play = null;						// 定义按钮
	private ImageButton stop = null;						// 定义按钮
	private ImageButton back = null ;						// 定义按钮
	private String filepath = null ;						// 播放文件路径
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.play); 				// 设置布局管理器
		this.play = (ImageButton) super.findViewById(R.id.play);	// 取得组件
		this.stop = (ImageButton) super.findViewById(R.id.stop);	// 取得组件
		this.back = (ImageButton) super.findViewById(R.id.back);	// 取得组件
		this.filepath = super.getIntent().getStringExtra("filepath");
		this.surfaceView = (SurfaceView) super
				.findViewById(R.id.surfaceView);			// 取得组件
		this.surfaceView.getHolder().setType(
				SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 	// 设置SurfaceView的类型
		this.surfaceView = (SurfaceView) super.findViewById(R.id.surfaceView);
		this.surfaceHolder = this.surfaceView.getHolder(); 	// 取得SurfaceHolder
		this.surfaceHolder.setType(
				SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 	// 设置SurfaceView的类型
		this.media = new MediaPlayer(); 					// 创建MediaPlayer对象
		this.media.reset() ;								// 重置操作
		try {
			this.media.setDataSource(filepath); 			// 设置播放文件的路径
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.play.setOnClickListener(new PlayOnClickListenerImpl());// 单击事件
		this.stop.setOnClickListener(new StopOnClickListenerImpl());// 单击事件
		this.back.setOnClickListener(new BackOnClickListenerImpl());// 单击事件
	}

	private class PlayOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			PlayVideoActivity.this.media.setAudioStreamType(
					AudioManager.STREAM_MUSIC);				// 设置音频类型
			PlayVideoActivity.this.media.setDisplay(
					PlayVideoActivity.this.surfaceHolder);	// 设置显示的区域
			try {
				PlayVideoActivity.this.media.prepare();		// 预备状态
				PlayVideoActivity.this.media.start();		// 播放视频
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private class StopOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			PlayVideoActivity.this.media.stop();			// 停止播放
		}
	}
	private class BackOnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View view) {
			Intent it = new Intent(PlayVideoActivity.this,
					BrowserActivity.class);					// 指定Intent
			PlayVideoActivity.this.startActivity(it) ;		// 跳转Intent
		}
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) { 			// 如果是手机上的返回键
			this.media.stop();
			this.media.release();
			this.finish(); 									// 程序结束
		}
		return false;
	}
}
