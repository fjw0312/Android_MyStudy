package com.mgrid.fuction;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


//����ҳ����     ʵ��һ��ҳ�� ����- Ȼ���Զ���������ͷ �ڲ��߳̽��п�������  Ҳ���ֶ�������գ�����ok  fjw0312
public class CameraView extends SurfaceView implements SurfaceHolder.Callback,Camera.PictureCallback{
	//Fields
	private SurfaceHolder holder;
	private Camera camera;
	public boolean af;     //���ս�������
	public int hasPreview;  //��¼�������Ԥ���Ĵ���
	private boolean taked;
	Context thisContext;
	long time;
	private boolean thread_live = false;
	private MediaPlayer mediaPlayer;  //������Ƶ������
	String path = "/mgrid/log/vtu_camera/";

	public CameraView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub		
		af = false;
		hasPreview = 0;
		taked = false;
		thisContext = context;
		holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); //ָ��push buffer
		
		mediaPlayer = new MediaPlayer(); //ʵ������Ƶ��������
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		Log.e("CameraView-surfaceChanged","into!");  
		Camera.Parameters parameters = camera.getParameters(); //��������ͷ��������
//		List<Size> supportPreViewSize = parameters.getSupportedPreviewSizes();//��ȡ��֧�ֵĴ�С
//		List<Size> supportPitureSize = parameters.getSupportedPictureSizes();
//		Log.e("CameraView-surfaceChanged-��supportPreViewSize.height��",String.valueOf( supportPreViewSize.get(0).height )); 
//		Log.e("CameraView-surfaceChanged-��supportPreViewSize.width��",String.valueOf( supportPreViewSize.get(0).width )); 
//		Log.e("CameraView-surfaceChanged-��supportPitureSize.height��",String.valueOf( supportPitureSize.get(0).height ));  
//		Log.e("CameraView-surfaceChanged-��supportPitureSize.width��",String.valueOf( supportPitureSize.get(0).width ));  
		parameters.setPreviewSize(200, 160); //����Ԥ����С
	//	parameters.setPictureSize(1024, 680);  //�ߴ�����	������Ƭ������200K ����޷���ʾ ��Ӧ��С����	
		parameters.setPictureSize(400, 320);  //�ߴ�����		
		camera.setParameters(parameters);      //���ò���
		Log.e("CameraView-surfaceChanged","will startPreview()!"); 
		camera.startPreview();//��ʼԤ�� ����3~4s������ʱ��
		hasPreview++;
		Log.e("CameraView-surfaceChanged>>has done startPreview!","startPreview!="+String.valueOf(hasPreview)+"---"+String.valueOf(af)); 
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		Log.e("CameraView-surfaceCreated","into!");  
		// TODO Auto-generated method stub  
		try{
		camera = Camera.open(0);//����ͷ�� ���ݲ�ͬӲ�������������ЩҲ����� ����1~2s������ʱ��
		if(camera==null){
			Log.e("CameraView-surfaceCreated","camera==null");  
			return;
		}
		Log.e("CameraView-surfaceCreated","Camera.open");  
		camera.setPreviewDisplay(holder);
		
		}catch(Exception e){ 
			Log.e("CameraView-surfaceChanged","����ͷ��ʼ��ʧ�ܣ�");
		}
		Log.e("CameraView-surfaceCreated","end!");  
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.e("CameraView-surfaceDestroyed","into!");
		//mythread.destroy();
		// TODO Auto-generated method stub
		camera.setPreviewCallback(null);
		camera.stopPreview();   
		camera.release();
		camera = null;
		
//		mediaPlayer.stop();
	}

	@Override   //��Ƭ����
	public void onPictureTaken(byte[] arg0, Camera arg1) {
		// TODO Auto-generated method stub
		try{
			time = System.currentTimeMillis();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//ʱ���ʽת��
			Date date = new Date(time);
			String nowTime = formatter.format(date);
			String f_time = nowTime.substring(0,10);
			String h = nowTime.substring(11,13);
			String m = nowTime.substring(14,16);
			String s = nowTime.substring(17,19);
			nowTime = f_time+"D"+h+"_"+m+"_"+s;
//			Log.e("CameraView-onPictureTaken>>nowTime",f_time+"D"+h+"_"+m+"_"+s);
			File jpgFile = new File(path+nowTime+".jpg");
			FileOutputStream out= new FileOutputStream(jpgFile);
			if(arg0==null){
				Log.e("CameraView-onPictureTaken","data==null");
			}
			out.write(arg0);
			out.close();
		}catch(Exception e){  
			Log.e("CameraView-onPictureTaken","��Ƭ�����쳣��");
		}
	}
	//���¿��Ŵ������  ���øú�������ϵͳĬ��������
	private ShutterCallback shutterCallback = new ShutterCallback(){

		@Override
		public void onShutter() {
			Log.e("CameraView-shutterCallback","shutterCallback  into��");
			taked = true;
			// TODO Auto-generated method stub 
			//�Զ��岥������    ����ok   �ǵ��˳�ʱmediaPlayer.stop();
/*			try{
			mediaPlayer.setDataSource("/system/media/audio/ringtones/BirdLoop.ogg");
			mediaPlayer.prepare(); //����
			}catch(Exception e){
				Log.e("CameraView-shutterCallback","��ȡ�ļ�ʧ�� ��");
			}
			
			if(mediaPlayer != null){
				Log.e("CameraView-shutterCallback","will mediaPlay��");
				mediaPlayer.start();  
			}
*/			
		}
		
	};
	//��紥��������� ���� ����  �ǵð������mythread.start()ȥ��  
	public boolean takePhoto(boolean take){  
		if(take){
			mythread.start();
			return true;
		}
		return false;
	}
	//����¼��Ĵ��� ����
	public boolean onTouchEvent(MotionEvent event){
		try{
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			camera.autoFocus(null);
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			camera.takePicture(shutterCallback, null, this);   //��һ������ΪshutterCallback���ճɹ����� nullʱû������
			af = true; //�÷��ز�һ�� �����ս��� ��Ҫ��һ����ʱ  ������������surfaceChanged ��2�ο�������ʱ ��ֵ
		}
		}catch(Exception e){
			Log.e("CameraView-onTouchEvent","�쳣��");
		}
		return true;
		
	}
	/****************
	 * camera ���յĴ��������߼�˵��
	 * ������surfaceView -surfaceCreatedִ��Ϊ���������
	 * ����surfaceChanged �����������������Ԥ����ֻ�������ɹ�������ܿ�������takePicture��
	 * �������ǿ���camera.autoFocus(null); ȥ����Զ��Խ�
	 * �������ǿ��԰��¿��� camera.takePicture() ->���ٵ���surfaceChanged�������
	 * **************/
	//�½� һ��sleep �߳� �Զ�����  
	private Thread mythread = new Thread(new Runnable(){  

		@Override
		public void run() {
			// TODO Auto-generated method stub
			long time = System.currentTimeMillis();	
			 while(hasPreview == 0); //Ԥ���ɹ���ſɽ�������   �ȴ�Ԥ���ɹ� ִ����һ��
			 try{
			 Thread.sleep(10); //0.001ms*1000
			 }catch(Exception e){
				 Log.e("CameraView-mythread-run"," sleep�쳣�׳���");
			 }		
			 camera.autoFocus(null); // 
			 while(CameraView.this.hasWindowFocus()==false){ //�ȴ��ж����ս�����ʾ�ڽ��� �Ű�������Ŀ���
				 long d_time = System.currentTimeMillis()-time;	
				 if(d_time>10000){ //10s��δ�������ֱ��׼������
					 Log.e("CameraView-mythread-run","10sδ�������ֱ�����գ�");  
					 break;
				 }
			 }
			
			 try{
				 	Thread.sleep(10); //0.001ms*1000
			 }catch(Exception e){
					Log.e("CameraView-mythread-run"," sleep�쳣�׳���");
			 }
			 camera.takePicture(shutterCallback, null, CameraView.this); //�����ⲿ��̬��//����õ�surfaceChanged
			 Log.e("CameraView-mythread-run","has done takePicture!---"+String.valueOf(hasPreview));   							
			//�ȴ�������������
			 while(true){	 		
				if(taked){
						Log.e("CameraView-mythread-run","�������---"+String.valueOf(hasPreview));
						try{
						Thread.sleep(200);
						}catch(Exception e){  
							Log.e("CameraView-mythread-run"," sleep�쳣�׳���");
						}
						af = true;					
						break;
					}
			} 		
		}});
}
