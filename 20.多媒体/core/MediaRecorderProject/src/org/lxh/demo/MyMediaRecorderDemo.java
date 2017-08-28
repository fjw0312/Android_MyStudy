package org.lxh.demo;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyMediaRecorderDemo extends Activity {
	private ImageButton record = null; 						// ��ť��Ϣ
	private ImageButton stop = null; 						// ��ť��Ϣ
	private ImageButton browser = null; 					// ��ť��Ϣ
	private TextView info = null; 							// ��ʾ��Ϣ
	private MediaRecorder mediaRecorder = null; 			// MediaRecorder����
	private File recordVideoSaveFile = null; 				// �ļ�����Ŀ¼·��
	private File recordVideoSaveFileDir = null; 			// �ļ�����Ŀ¼
	private String recordVideoSaveFileName = null; 			// ��Ƶ�ļ���������
	private String recDir = "mldnvideo";					// ����Ŀ¼
	private boolean sdcardExists = false;					// �ж�SD���Ƿ����
	private boolean isRecord = false;						// �ж��Ƿ�����¼��
	private SurfaceView surface = null;						// SurfaceView

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);// ����ʾ����
		super.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// ȫ����ʾ
		super.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // ������ʾ
		super.setContentView(R.layout.main);				// ���ò��ֹ�����
		this.record = (ImageButton) super.findViewById(R.id.record);	// �������
		this.stop = (ImageButton) super.findViewById(R.id.stop);		// �������
		this.browser = (ImageButton) super.findViewById(R.id.browser);	// �������
		this.info = (TextView) super.findViewById(R.id.info);			// �������
		this.surface = (SurfaceView) super.findViewById(R.id.surface) ;	// �������
		this.surface.getHolder().setType(
				SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);	// ���û�������
		this.surface.getHolder().setFixedSize(350, 500) ;	// ���÷ֱ���
		if (this.sdcardExists = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) { 				// �ж�SD���Ƿ����
			this.recordVideoSaveFileDir = new File(Environment
					.getExternalStorageDirectory().toString()
					+ File.separator
					+ MyMediaRecorderDemo.this.recDir + File.separator); // ����¼��Ŀ¼
			if (!this.recordVideoSaveFileDir.exists()) { 	// ���ļ��в�����
				this.recordVideoSaveFileDir.mkdirs(); 		// �����µ��ļ���
			}
		}
		this.stop.setOnClickListener(new StopOnClickListenerImpl()); // ���õ����¼�
		this.record.setOnClickListener(new RecordOnClickListenerImpl()); // ���õ����¼�
		this.browser.setOnClickListener(new BrowserOnClickListenerImpl()); // ���õ����¼�
		this.stop.setEnabled(false); 						// ��ͣť��ʱ������
	}
	private class RecordOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (MyMediaRecorderDemo.this.sdcardExists) {			// ���SD������
				MyMediaRecorderDemo.this.recordVideoSaveFileName = 
					MyMediaRecorderDemo.this.recordVideoSaveFileDir
						.toString()
						+ File.separator
						+ "MLDNVideo_" 
						+ System.currentTimeMillis() + ".3gp"; 		// �ļ���������
				MyMediaRecorderDemo.this.recordVideoSaveFile = new File(
					MyMediaRecorderDemo.this.recordVideoSaveFileName); // ȡ����Ƶ�ļ�����·��
				MyMediaRecorderDemo.this.mediaRecorder = new MediaRecorder(); // ʵ����MediaRecorder
				MyMediaRecorderDemo.this.mediaRecorder.reset() ;
				MyMediaRecorderDemo.this.mediaRecorder
					.setAudioSource(
							MediaRecorder.AudioSource.MIC);		// ����¼��ԴΪMIC
				MyMediaRecorderDemo.this.mediaRecorder
					.setVideoSource(
						MediaRecorder.VideoSource.CAMERA);		// ����ͷΪ��ƵԴ
				MyMediaRecorderDemo.this.mediaRecorder
					.setOutputFormat(
							MediaRecorder.OutputFormat.THREE_GPP);// ���������ʽ
				MyMediaRecorderDemo.this.mediaRecorder
					.setVideoEncoder(
						MediaRecorder.VideoEncoder.H263);		// ������Ƶ����
				MyMediaRecorderDemo.this.mediaRecorder
					.setAudioEncoder(
						MediaRecorder.AudioEncoder.AMR_NB);		// ������Ƶ����
				MyMediaRecorderDemo.this.mediaRecorder
					.setOutputFile(MyMediaRecorderDemo.
						this.recordVideoSaveFileName); 			// ��������ļ�
				MyMediaRecorderDemo.this.mediaRecorder
					.setVideoSize(320, 240); 					// ������Ƶ��С
				MyMediaRecorderDemo.this.mediaRecorder
					.setVideoFrameRate(10); 					// ÿ��10֡
				MyMediaRecorderDemo.this.mediaRecorder
					.setPreviewDisplay(MyMediaRecorderDemo.this.surface
							.getHolder().getSurface());			// ������Ƶ��ʾ
				try {
					MyMediaRecorderDemo.this.mediaRecorder.prepare();// ׼��¼��
				} catch (Exception e) {
					e.printStackTrace();
				}
				MyMediaRecorderDemo.this.mediaRecorder.start();		// ��ʼ¼��
				MyMediaRecorderDemo.this.info.setText("����¼����...");	// ��ʾ��Ϣ
				MyMediaRecorderDemo.this.stop.setEnabled(true); 	// ֹͣ��ť����
				MyMediaRecorderDemo.this.record.setEnabled(false); 	// ¼��ť����
				MyMediaRecorderDemo.this.isRecord = true; 			// ����¼��
			}
		}
	}
	private class StopOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (MyMediaRecorderDemo.this.isRecord) { 			// �Ѿ���ʼ¼������ֹͣ
				MyMediaRecorderDemo.this.mediaRecorder.stop(); 	// ֹͣ¼��
				MyMediaRecorderDemo.this.mediaRecorder.release(); // �ͷ���Դ
				MyMediaRecorderDemo.this.record.setEnabled(true); // ¼����ť����
				MyMediaRecorderDemo.this.info.setText("¼��������ļ�·��Ϊ��"
						+ MyMediaRecorderDemo.this.recordVideoSaveFile);
			}
		}
	}

	private class BrowserOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent it = new Intent(MyMediaRecorderDemo.this,
					BrowserActivity.class);						// ָ��Intent
			MyMediaRecorderDemo.this.startActivity(it) ;		// ��תIntent
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) { 				// ������ֻ��ϵķ��ؼ�
			this.finish(); 										// �������
		}
		return false;
	}
}