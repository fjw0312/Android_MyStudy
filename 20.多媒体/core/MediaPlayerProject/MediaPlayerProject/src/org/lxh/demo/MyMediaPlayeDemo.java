package org.lxh.demo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MyMediaPlayeDemo extends Activity {
	private ImageButton play = null;							// 图片按钮
	private ImageButton pause = null;							// 图片按钮
	private ImageButton stop = null;							// 图片按钮
	private TextView info = null;								// 文本显示组件
	private MediaPlayer myMediaPlayer = null;					// 媒体播放
	private boolean pauseFlag = false; 							// 暂停播放标记
	private boolean playFlag = true ;							// 是否播放的标记
	private SeekBar seekbar = null;								// 拖动条
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);						// 调用布局文件
		this.info = (TextView) super.findViewById(R.id.info);		// 取得组件
		this.play = (ImageButton) super.findViewById(R.id.play);	// 取得组件
		this.pause = (ImageButton) super.findViewById(R.id.pause);	// 取得组件
		this.stop = (ImageButton) super.findViewById(R.id.stop);	// 取得组件
		this.seekbar = (SeekBar) super.findViewById(R.id.seekbar);	// 取得组件
		this.play.setOnClickListener(new PlayOnClickListenerImpl()) ;	// 按钮单击事件
		this.pause.setOnClickListener(new PauseOnClickListenerImpl());	// 按钮单击事件
		this.stop.setOnClickListener(new StopOnClickListenerImpl());	// 按钮单击事件
	}
	private class PlayOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View view) {
			MyMediaPlayeDemo.this.myMediaPlayer = MediaPlayer.create(
					MyMediaPlayeDemo.this, R.raw.mldn_ad); 		// 找到指定的资源
			MyMediaPlayeDemo.this.myMediaPlayer
					.setOnCompletionListener(new OnCompletionListener() {
						@Override
						public void onCompletion(MediaPlayer media) {
							MyMediaPlayeDemo.this.playFlag = false ;	// 播放完毕
							media.release(); 					// 释放所有状态
						}
					});											// 播放完毕监听
			MyMediaPlayeDemo.this.seekbar.setMax(MyMediaPlayeDemo.this.myMediaPlayer
					.getDuration());							// 设置拖动条长度为媒体长度
			UpdateSeekBar update = new UpdateSeekBar() ;		// 启动子线成更新拖动条
			update.execute(1000) ;								// 休眠1秒
			MyMediaPlayeDemo.this.seekbar.setOnSeekBarChangeListener(
					new OnSeekBarChangeListenerImpl());				// 拖动条改变音乐播放位置
			if (MyMediaPlayeDemo.this.myMediaPlayer != null) { 
				MyMediaPlayeDemo.this.myMediaPlayer.stop();		// 停止播放
			}
			try {
				MyMediaPlayeDemo.this.myMediaPlayer.prepare();	// 进入到预备状态
				MyMediaPlayeDemo.this.myMediaPlayer.start();	// 播放文件
				MyMediaPlayeDemo.this.info.setText("正在播放音频文件...");	// 设置文字
			} catch (Exception e) {
				MyMediaPlayeDemo.this.info.setText("文件播放出现异常，" + e);// 设置文字
			}
		}
	}
	private class UpdateSeekBar extends AsyncTask<Integer, Integer, String> {
		@Override
		protected void onPostExecute(String result) {			// 任务执行完后执行
		}
		@Override
		protected void onProgressUpdate(Integer... progress) {	// 每次更新之后的数值
			MyMediaPlayeDemo.this.seekbar.setProgress(progress[0]) ;// 更新拖动条
		}
		@Override
		protected String doInBackground(Integer... params) {	// 处理后台任务
			while (MyMediaPlayeDemo.this.playFlag) {			// 进度条累加
				try {
					Thread.sleep(params[0]);					// 延缓执行
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.publishProgress(MyMediaPlayeDemo.this.myMediaPlayer
						.getCurrentPosition());					// 修改拖动条
			}
			return null;										// 返回执行结果
		}
	}
	private class OnSeekBarChangeListenerImpl implements OnSeekBarChangeListener {
		@Override
		public void onProgressChanged(SeekBar seekBar,
				int progress, boolean fromUser) {
		}
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {		// 进度条停止拖拽
			MyMediaPlayeDemo.this.myMediaPlayer.seekTo(seekBar
					.getProgress());							// 定义播放位置
		}
	}
	private class PauseOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View view) {
			if (MyMediaPlayeDemo.this.myMediaPlayer != null) {
				if (MyMediaPlayeDemo.this.pauseFlag) { 			// 为true表示由暂停变为播放
					MyMediaPlayeDemo.this.myMediaPlayer.start(); // 播放文件
					MyMediaPlayeDemo.this.pauseFlag = false; 	// 修改标记位
				} else { 										// false表示由播放变为暂停
					MyMediaPlayeDemo.this.myMediaPlayer.pause(); // 暂停播放
					MyMediaPlayeDemo.this.pauseFlag = true; 	// 修改标记位
				}
			}
		}
	}

	private class StopOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View view) {
			if (MyMediaPlayeDemo.this.myMediaPlayer != null) {
				MyMediaPlayeDemo.this.myMediaPlayer.stop();		// 停止播放
				MyMediaPlayeDemo.this.info.setText("停止播放音频文件...");
			}
		}
	}
}