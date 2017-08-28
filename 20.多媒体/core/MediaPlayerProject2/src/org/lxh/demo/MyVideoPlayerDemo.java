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
		super.setContentView(R.layout.main);					// ���ò����ļ�
		this.play = (ImageButton) super.findViewById(R.id.play);
		this.stop = (ImageButton) super.findViewById(R.id.stop);
		this.surfaceView = (SurfaceView) super.findViewById(R.id.surfaceView);
		this.surfaceHolder = this.surfaceView.getHolder();		// ȡ��SurfaceHolder
		this.surfaceHolder.setType(
				SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);		// ����SurfaceView������
		this.media = new MediaPlayer();							// ����MediaPlayer����
		try {
			this.media.setDataSource("/sdcard/mldn.3gp");		// ���ò����ļ���·��
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.play.setOnClickListener(new PlayOnClickListenerImpl());// �����¼�
		this.stop.setOnClickListener(new StopOnClickListenerImpl());// �����¼�
	}
	private class PlayOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			MyVideoPlayerDemo.this.media.setAudioStreamType(
					AudioManager.STREAM_MUSIC);				// ������Ƶ����
			MyVideoPlayerDemo.this.media.setDisplay(
					MyVideoPlayerDemo.this.surfaceHolder);	// ������ʾ������
			try {
				MyVideoPlayerDemo.this.media.prepare();		// Ԥ��״̬
				MyVideoPlayerDemo.this.media.start();		// ������Ƶ
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private class StopOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			MyVideoPlayerDemo.this.media.stop();			// ֹͣ����
		}
	}
}