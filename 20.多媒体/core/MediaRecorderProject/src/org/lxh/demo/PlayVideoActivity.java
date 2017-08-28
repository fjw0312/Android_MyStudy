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
	private ImageButton play = null;						// ���尴ť
	private ImageButton stop = null;						// ���尴ť
	private ImageButton back = null ;						// ���尴ť
	private String filepath = null ;						// �����ļ�·��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.play); 				// ���ò��ֹ�����
		this.play = (ImageButton) super.findViewById(R.id.play);	// ȡ�����
		this.stop = (ImageButton) super.findViewById(R.id.stop);	// ȡ�����
		this.back = (ImageButton) super.findViewById(R.id.back);	// ȡ�����
		this.filepath = super.getIntent().getStringExtra("filepath");
		this.surfaceView = (SurfaceView) super
				.findViewById(R.id.surfaceView);			// ȡ�����
		this.surfaceView.getHolder().setType(
				SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 	// ����SurfaceView������
		this.surfaceView = (SurfaceView) super.findViewById(R.id.surfaceView);
		this.surfaceHolder = this.surfaceView.getHolder(); 	// ȡ��SurfaceHolder
		this.surfaceHolder.setType(
				SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 	// ����SurfaceView������
		this.media = new MediaPlayer(); 					// ����MediaPlayer����
		this.media.reset() ;								// ���ò���
		try {
			this.media.setDataSource(filepath); 			// ���ò����ļ���·��
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.play.setOnClickListener(new PlayOnClickListenerImpl());// �����¼�
		this.stop.setOnClickListener(new StopOnClickListenerImpl());// �����¼�
		this.back.setOnClickListener(new BackOnClickListenerImpl());// �����¼�
	}

	private class PlayOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			PlayVideoActivity.this.media.setAudioStreamType(
					AudioManager.STREAM_MUSIC);				// ������Ƶ����
			PlayVideoActivity.this.media.setDisplay(
					PlayVideoActivity.this.surfaceHolder);	// ������ʾ������
			try {
				PlayVideoActivity.this.media.prepare();		// Ԥ��״̬
				PlayVideoActivity.this.media.start();		// ������Ƶ
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private class StopOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			PlayVideoActivity.this.media.stop();			// ֹͣ����
		}
	}
	private class BackOnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View view) {
			Intent it = new Intent(PlayVideoActivity.this,
					BrowserActivity.class);					// ָ��Intent
			PlayVideoActivity.this.startActivity(it) ;		// ��תIntent
		}
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) { 			// ������ֻ��ϵķ��ؼ�
			this.media.stop();
			this.media.release();
			this.finish(); 									// �������
		}
		return false;
	}
}
