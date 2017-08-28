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
	private Camera cam = null;									// �������
	private Button but = null;									// ��ť���
	private boolean previewRunning = true;						// Ԥ�������ı��

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);	// ����ʾ����
		super.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);	// ȫ����ʾ
		super.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // ������ʾ
		super.setContentView(R.layout.main);					// ���ֹ�����
		this.but = (Button) super.findViewById(R.id.but);		// ȡ�����
		this.surface = (SurfaceView) findViewById(R.id.surface);// ȡ�����
		this.holder = surface.getHolder();						// ����Holder
		this.holder.addCallback(new MySurfaceViewCallback());	// ����ص�
		this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);	// ���û�������
		this.holder.setFixedSize(500, 350);						// ���÷ֱ���
		this.but.setOnClickListener(new OnClickListenerImpl());	// �����¼�

	}

	// �ӿ�SurfaceHolder.Callback��������������ͷԤ������仯����Ϣ��
	private class MySurfaceViewCallback implements SurfaceHolder.Callback {
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) { 								// ��Ԥ������ĸ�ʽ�ʹ�С�����ı�ʱ���÷���������
		}

		public void surfaceCreated(SurfaceHolder holder) { 	// ����ʵ������Ԥ�����汻����ʱ���÷���������
			MyCameraDemo.this.cam = Camera.open(0);			// ȡ������ͷ
			WindowManager manager = (WindowManager) MyCameraDemo.this
					.getSystemService(Context.WINDOW_SERVICE); // ȡ�ô��ڷ���
			Display display = manager.getDefaultDisplay(); // ȡ��Display����
			Parameters param = MyCameraDemo.this.cam.getParameters(); // ȡ�����������
			param.setPreviewSize(display.getWidth(), display.getHeight()); // ����Ԥ����С
			param.setPreviewFrameRate(5); 					// ÿ����ʾ5֡������
			param.setPictureFormat(PixelFormat.JPEG); 		// ����ͼƬ��ʽ
			param.set("jpeg-quality", 85);					// ����ͼƬ���������Ϊ100
			MyCameraDemo.this.cam.setParameters(param); 	// ���ò���
			try { 											// ͨ��SurfaceView��ʾ
				MyCameraDemo.this.cam
						.setPreviewDisplay(MyCameraDemo.this.holder);
			} catch (IOException e) {
				e.printStackTrace();
			}
			MyCameraDemo.this.cam.startPreview(); 			// ��ʼԤ��
			MyCameraDemo.this.previewRunning = true; 		// �޸�Ԥ�����

		}

		public void surfaceDestroyed(SurfaceHolder holder) { // ��Ԥ�����汻�ر�ʱ���÷���������
			if (MyCameraDemo.this.cam != null) {
				if (MyCameraDemo.this.previewRunning) {		// �������Ԥ��
					MyCameraDemo.this.cam.stopPreview(); 	// ֹͣԤ��
					MyCameraDemo.this.previewRunning = false; // �޸ı��
				}
				// ����ͷֻ�ܱ�һ��Activity����ʹ�ã�����Ҫ�ͷ�����ͷ��
				MyCameraDemo.this.cam.release(); 			// �ͷ�����ͷ
			}
		}
	}
	private PictureCallback jpgcall = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			try {
				Bitmap bmp = BitmapFactory
						.decodeByteArray(data, 0, data.length);	// ����BitMap
				String fileName = Environment.getExternalStorageDirectory()
						.toString()
						+ File.separator
						+ "mldnphoto"
						+ File.separator
						+ "MLDN_"
						+ System.currentTimeMillis()
						+ ".jpg";								// ����ļ�����
				File file = new File(fileName);					// ����File����
				if (!file.getParentFile().exists()) { 			// ���ļ��в�����
					file.getParentFile().mkdirs(); 				// �������ļ���
				}
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(file));			// ʹ���ֽڻ�����
				bmp.compress(Bitmap.CompressFormat.JPEG, 80, bos);	// ͼƬѹ��
				bos.flush();									// ��ջ���
				bos.close();									// �ر�
				Toast.makeText(MyCameraDemo.this,
						"���ճɹ�����Ƭ�ѱ�����" + fileName + "�ļ�֮��", Toast.LENGTH_SHORT)
						.show(); 								// ��ʾToast
				MyCameraDemo.this.cam.stopPreview(); 			// ֹͣԤ��
				MyCameraDemo.this.cam.startPreview(); 			// ��ʼԤ��
			} catch (Exception e) {
			}
		}
	};
	private class OnClickListenerImpl implements OnClickListener {
		public void onClick(View v) {
			if (MyCameraDemo.this.cam != null) {				// ����Camera����
				MyCameraDemo.this.cam.autoFocus(new AutoFocusCallbackImpl());	// �Զ��Խ�
			}
		}
	}

	private class AutoFocusCallbackImpl implements AutoFocusCallback {

		public void onAutoFocus(boolean success, Camera cam) {
			if (success) { 										// ����Խ��ɹ�
				MyCameraDemo.this.cam.takePicture(sc, pc, jpgcall);	// ��ȡͼƬ
				MyCameraDemo.this.cam.stopPreview();			// ֹͣԤ��			
			}
		}

	}
	private ShutterCallback sc = new ShutterCallback() {

		public void onShutter() {
			// ���¿��ź�Ļص�����
		}

	};
	private PictureCallback pc = new PictureCallback() {

		public void onPictureTaken(byte[] arg0, Camera arg1) {
			// �����ԴͼƬ����
		}
	};
}