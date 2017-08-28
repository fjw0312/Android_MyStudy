package org.lxh.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MyCameraDemo extends Activity {
	private SurfaceHolder holder = null;						// SurfaceHolder
	private SurfaceView surface = null;							// SurfaceView
	private Camera cam = null;									// 拍照组件
	private Button but = null;									// 按钮组件
	private boolean previewRunning = true;						// 预览结束的标记

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);	// 不显示标题
		super.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);	// 全屏显示
		super.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 高亮显示
		super.setContentView(R.layout.main);					// 布局管理器
		this.but = (Button) super.findViewById(R.id.but);		// 取得组件
		this.surface = (SurfaceView) findViewById(R.id.surface);// 取得组件
		this.holder = surface.getHolder();						// 设置Holder
		this.holder.addCallback(new MySurfaceViewCallback());	// 加入回调
		this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);	// 设置缓冲类型
		this.holder.setFixedSize(500, 350);						// 设置分辨率
		this.but.setOnClickListener(new OnClickListenerImpl());	// 单击事件

	}

	// 接口SurfaceHolder.Callback被用来接收摄像头预览界面变化的信息。
	private class MySurfaceViewCallback implements SurfaceHolder.Callback {
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) { 								// 当预览界面的格式和大小发生改变时，该方法被调用
		}

		public void surfaceCreated(SurfaceHolder holder) { 	// 初次实例化，预览界面被创建时，该方法被调用
			MyCameraDemo.this.cam = Camera.open(0);			// 取得摄像头
			WindowManager manager = (WindowManager) MyCameraDemo.this
					.getSystemService(Context.WINDOW_SERVICE); // 取得窗口服务
			Display display = manager.getDefaultDisplay(); // 取得Display对象
			Parameters param = MyCameraDemo.this.cam.getParameters(); // 取得照相机参数
			param.setPreviewSize(display.getWidth(), display.getHeight()); // 设置预览大小
			param.setPreviewFrameRate(5); 					// 每秒显示5帧的数据
			param.setPictureFormat(PixelFormat.JPEG); 		// 设置图片格式
			param.set("jpeg-quality", 85);					// 设置图片质量，最高为100
			MyCameraDemo.this.cam.setParameters(param); 	// 设置参数
			try { 											// 通过SurfaceView显示
				MyCameraDemo.this.cam
						.setPreviewDisplay(MyCameraDemo.this.holder);
			} catch (IOException e) {
				e.printStackTrace();
			}
			MyCameraDemo.this.cam.startPreview(); 			// 开始预览
			MyCameraDemo.this.previewRunning = true; 		// 修改预览标记

		}

		public void surfaceDestroyed(SurfaceHolder holder) { // 当预览界面被关闭时，该方法被调用
			if (MyCameraDemo.this.cam != null) {
				if (MyCameraDemo.this.previewRunning) {		// 如果正在预览
					MyCameraDemo.this.cam.stopPreview(); 	// 停止预览
					MyCameraDemo.this.previewRunning = false; // 修改标记
				}
				// 摄像头只能被一个Activity程序使用，所以要释放设像头。
				MyCameraDemo.this.cam.release(); 			// 释放摄像头
			}
		}
	}
	private PictureCallback jpgcall = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			try {
				Bitmap bmp = BitmapFactory
						.decodeByteArray(data, 0, data.length);	// 定义BitMap
				String fileName = Environment.getExternalStorageDirectory()
						.toString()
						+ File.separator
						+ "mldnphoto"
						+ File.separator
						+ "MLDN_"
						+ System.currentTimeMillis()
						+ ".jpg";								// 输出文件名称
				File file = new File(fileName);					// 定义File对象
				if (!file.getParentFile().exists()) { 			// 父文件夹不存在
					file.getParentFile().mkdirs(); 				// 创建父文件夹
				}
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(file));			// 使用字节缓存流
				bmp.compress(Bitmap.CompressFormat.JPEG, 80, bos);	// 图片压缩
				bos.flush();									// 清空缓冲
				bos.close();									// 关闭
				Toast.makeText(MyCameraDemo.this,
						"拍照成功，照片已保存在" + fileName + "文件之中", Toast.LENGTH_SHORT)
						.show(); 								// 显示Toast
				MyCameraDemo.this.cam.stopPreview(); 			// 停止预览
				MyCameraDemo.this.cam.startPreview(); 			// 开始预览
			} catch (Exception e) {
			}
		}
	};
	private class OnClickListenerImpl implements OnClickListener {
		public void onClick(View v) {
			if (MyCameraDemo.this.cam != null) {				// 存在Camera对象
				MyCameraDemo.this.cam.autoFocus(new AutoFocusCallbackImpl());	// 自动对焦
			}
		}
	}

	private class AutoFocusCallbackImpl implements AutoFocusCallback {

		public void onAutoFocus(boolean success, Camera cam) {
			if (success) { 										// 如果对焦成功
				MyCameraDemo.this.cam.takePicture(sc, pc, jpgcall);	// 获取图片
				MyCameraDemo.this.cam.stopPreview();			// 停止预览			
			}
		}

	}
	private ShutterCallback sc = new ShutterCallback() {

		public void onShutter() {
			// 按下快门后的回调函数
		}

	};
	private PictureCallback pc = new PictureCallback() {

		public void onPictureTaken(byte[] arg0, Camera arg1) {
			// 保存的源图片数据
		}
	};
}