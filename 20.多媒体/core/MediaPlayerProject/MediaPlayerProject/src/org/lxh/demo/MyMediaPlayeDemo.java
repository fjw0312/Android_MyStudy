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
	private ImageButton play = null;							// ͼƬ��ť
	private ImageButton pause = null;							// ͼƬ��ť
	private ImageButton stop = null;							// ͼƬ��ť
	private TextView info = null;								// �ı���ʾ���
	private MediaPlayer myMediaPlayer = null;					// ý�岥��
	private boolean pauseFlag = false; 							// ��ͣ���ű��
	private boolean playFlag = true ;							// �Ƿ񲥷ŵı��
	private SeekBar seekbar = null;								// �϶���
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);						// ���ò����ļ�
		this.info = (TextView) super.findViewById(R.id.info);		// ȡ�����
		this.play = (ImageButton) super.findViewById(R.id.play);	// ȡ�����
		this.pause = (ImageButton) super.findViewById(R.id.pause);	// ȡ�����
		this.stop = (ImageButton) super.findViewById(R.id.stop);	// ȡ�����
		this.seekbar = (SeekBar) super.findViewById(R.id.seekbar);	// ȡ�����
		this.play.setOnClickListener(new PlayOnClickListenerImpl()) ;	// ��ť�����¼�
		this.pause.setOnClickListener(new PauseOnClickListenerImpl());	// ��ť�����¼�
		this.stop.setOnClickListener(new StopOnClickListenerImpl());	// ��ť�����¼�
	}
	private class PlayOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View view) {
			MyMediaPlayeDemo.this.myMediaPlayer = MediaPlayer.create(
					MyMediaPlayeDemo.this, R.raw.mldn_ad); 		// �ҵ�ָ������Դ
			MyMediaPlayeDemo.this.myMediaPlayer
					.setOnCompletionListener(new OnCompletionListener() {
						@Override
						public void onCompletion(MediaPlayer media) {
							MyMediaPlayeDemo.this.playFlag = false ;	// �������
							media.release(); 					// �ͷ�����״̬
						}
					});											// ������ϼ���
			MyMediaPlayeDemo.this.seekbar.setMax(MyMediaPlayeDemo.this.myMediaPlayer
					.getDuration());							// �����϶�������Ϊý�峤��
			UpdateSeekBar update = new UpdateSeekBar() ;		// �������߳ɸ����϶���
			update.execute(1000) ;								// ����1��
			MyMediaPlayeDemo.this.seekbar.setOnSeekBarChangeListener(
					new OnSeekBarChangeListenerImpl());				// �϶����ı����ֲ���λ��
			if (MyMediaPlayeDemo.this.myMediaPlayer != null) { 
				MyMediaPlayeDemo.this.myMediaPlayer.stop();		// ֹͣ����
			}
			try {
				MyMediaPlayeDemo.this.myMediaPlayer.prepare();	// ���뵽Ԥ��״̬
				MyMediaPlayeDemo.this.myMediaPlayer.start();	// �����ļ�
				MyMediaPlayeDemo.this.info.setText("���ڲ�����Ƶ�ļ�...");	// ��������
			} catch (Exception e) {
				MyMediaPlayeDemo.this.info.setText("�ļ����ų����쳣��" + e);// ��������
			}
		}
	}
	private class UpdateSeekBar extends AsyncTask<Integer, Integer, String> {
		@Override
		protected void onPostExecute(String result) {			// ����ִ�����ִ��
		}
		@Override
		protected void onProgressUpdate(Integer... progress) {	// ÿ�θ���֮�����ֵ
			MyMediaPlayeDemo.this.seekbar.setProgress(progress[0]) ;// �����϶���
		}
		@Override
		protected String doInBackground(Integer... params) {	// �����̨����
			while (MyMediaPlayeDemo.this.playFlag) {			// �������ۼ�
				try {
					Thread.sleep(params[0]);					// �ӻ�ִ��
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.publishProgress(MyMediaPlayeDemo.this.myMediaPlayer
						.getCurrentPosition());					// �޸��϶���
			}
			return null;										// ����ִ�н��
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
		public void onStopTrackingTouch(SeekBar seekBar) {		// ������ֹͣ��ק
			MyMediaPlayeDemo.this.myMediaPlayer.seekTo(seekBar
					.getProgress());							// ���岥��λ��
		}
	}
	private class PauseOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View view) {
			if (MyMediaPlayeDemo.this.myMediaPlayer != null) {
				if (MyMediaPlayeDemo.this.pauseFlag) { 			// Ϊtrue��ʾ����ͣ��Ϊ����
					MyMediaPlayeDemo.this.myMediaPlayer.start(); // �����ļ�
					MyMediaPlayeDemo.this.pauseFlag = false; 	// �޸ı��λ
				} else { 										// false��ʾ�ɲ��ű�Ϊ��ͣ
					MyMediaPlayeDemo.this.myMediaPlayer.pause(); // ��ͣ����
					MyMediaPlayeDemo.this.pauseFlag = true; 	// �޸ı��λ
				}
			}
		}
	}

	private class StopOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View view) {
			if (MyMediaPlayeDemo.this.myMediaPlayer != null) {
				MyMediaPlayeDemo.this.myMediaPlayer.stop();		// ֹͣ����
				MyMediaPlayeDemo.this.info.setText("ֹͣ������Ƶ�ļ�...");
			}
		}
	}
}