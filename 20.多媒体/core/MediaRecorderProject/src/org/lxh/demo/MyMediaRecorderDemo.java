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
	private ImageButton record = null; 						// 按钮信息
	private ImageButton stop = null; 						// 按钮信息
	private ImageButton browser = null; 					// 按钮信息
	private TextView info = null; 							// 提示信息
	private MediaRecorder mediaRecorder = null; 			// MediaRecorder对象
	private File recordVideoSaveFile = null; 				// 文件保存目录路径
	private File recordVideoSaveFileDir = null; 			// 文件保存目录
	private String recordVideoSaveFileName = null; 			// 音频文件保存名称
	private String recDir = "mldnvideo";					// 保存目录
	private boolean sdcardExists = false;					// 判断SD卡是否存在
	private boolean isRecord = false;						// 判断是否正在录音
	private SurfaceView surface = null;						// SurfaceView

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);// 不显示标题
		super.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 全屏显示
		super.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 高亮显示
		super.setContentView(R.layout.main);				// 调用布局管理器
		this.record = (ImageButton) super.findViewById(R.id.record);	// 查找组件
		this.stop = (ImageButton) super.findViewById(R.id.stop);		// 查找组件
		this.browser = (ImageButton) super.findViewById(R.id.browser);	// 查找组件
		this.info = (TextView) super.findViewById(R.id.info);			// 查找组件
		this.surface = (SurfaceView) super.findViewById(R.id.surface) ;	// 查找组件
		this.surface.getHolder().setType(
				SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);	// 设置缓冲类型
		this.surface.getHolder().setFixedSize(350, 500) ;	// 设置分辨率
		if (this.sdcardExists = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) { 				// 判断SD卡是否存在
			this.recordVideoSaveFileDir = new File(Environment
					.getExternalStorageDirectory().toString()
					+ File.separator
					+ MyMediaRecorderDemo.this.recDir + File.separator); // 保存录音目录
			if (!this.recordVideoSaveFileDir.exists()) { 	// 父文件夹不存在
				this.recordVideoSaveFileDir.mkdirs(); 		// 创建新的文件夹
			}
		}
		this.stop.setOnClickListener(new StopOnClickListenerImpl()); // 设置单击事件
		this.record.setOnClickListener(new RecordOnClickListenerImpl()); // 设置单击事件
		this.browser.setOnClickListener(new BrowserOnClickListenerImpl()); // 设置单击事件
		this.stop.setEnabled(false); 						// 暂停钮暂时不可用
	}
	private class RecordOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (MyMediaRecorderDemo.this.sdcardExists) {			// 如果SD卡存在
				MyMediaRecorderDemo.this.recordVideoSaveFileName = 
					MyMediaRecorderDemo.this.recordVideoSaveFileDir
						.toString()
						+ File.separator
						+ "MLDNVideo_" 
						+ System.currentTimeMillis() + ".3gp"; 		// 文件保存名称
				MyMediaRecorderDemo.this.recordVideoSaveFile = new File(
					MyMediaRecorderDemo.this.recordVideoSaveFileName); // 取得音频文件保存路径
				MyMediaRecorderDemo.this.mediaRecorder = new MediaRecorder(); // 实例化MediaRecorder
				MyMediaRecorderDemo.this.mediaRecorder.reset() ;
				MyMediaRecorderDemo.this.mediaRecorder
					.setAudioSource(
							MediaRecorder.AudioSource.MIC);		// 设置录音源为MIC
				MyMediaRecorderDemo.this.mediaRecorder
					.setVideoSource(
						MediaRecorder.VideoSource.CAMERA);		// 摄像头为视频源
				MyMediaRecorderDemo.this.mediaRecorder
					.setOutputFormat(
							MediaRecorder.OutputFormat.THREE_GPP);// 定义输出格式
				MyMediaRecorderDemo.this.mediaRecorder
					.setVideoEncoder(
						MediaRecorder.VideoEncoder.H263);		// 定义视频编码
				MyMediaRecorderDemo.this.mediaRecorder
					.setAudioEncoder(
						MediaRecorder.AudioEncoder.AMR_NB);		// 定义音频编码
				MyMediaRecorderDemo.this.mediaRecorder
					.setOutputFile(MyMediaRecorderDemo.
						this.recordVideoSaveFileName); 			// 定义输出文件
				MyMediaRecorderDemo.this.mediaRecorder
					.setVideoSize(320, 240); 					// 定义视频大小
				MyMediaRecorderDemo.this.mediaRecorder
					.setVideoFrameRate(10); 					// 每秒10帧
				MyMediaRecorderDemo.this.mediaRecorder
					.setPreviewDisplay(MyMediaRecorderDemo.this.surface
							.getHolder().getSurface());			// 定义视频显示
				try {
					MyMediaRecorderDemo.this.mediaRecorder.prepare();// 准备录象
				} catch (Exception e) {
					e.printStackTrace();
				}
				MyMediaRecorderDemo.this.mediaRecorder.start();		// 开始录象
				MyMediaRecorderDemo.this.info.setText("正在录象中...");	// 提示信息
				MyMediaRecorderDemo.this.stop.setEnabled(true); 	// 停止按钮可用
				MyMediaRecorderDemo.this.record.setEnabled(false); 	// 录象按钮禁用
				MyMediaRecorderDemo.this.isRecord = true; 			// 正在录音
			}
		}
	}
	private class StopOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (MyMediaRecorderDemo.this.isRecord) { 			// 已经开始录音，则停止
				MyMediaRecorderDemo.this.mediaRecorder.stop(); 	// 停止录音
				MyMediaRecorderDemo.this.mediaRecorder.release(); // 释放资源
				MyMediaRecorderDemo.this.record.setEnabled(true); // 录音按钮启用
				MyMediaRecorderDemo.this.info.setText("录像结束，文件路径为："
						+ MyMediaRecorderDemo.this.recordVideoSaveFile);
			}
		}
	}

	private class BrowserOnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent it = new Intent(MyMediaRecorderDemo.this,
					BrowserActivity.class);						// 指定Intent
			MyMediaRecorderDemo.this.startActivity(it) ;		// 跳转Intent
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) { 				// 如果是手机上的返回键
			this.finish(); 										// 程序结束
		}
		return false;
	}
}