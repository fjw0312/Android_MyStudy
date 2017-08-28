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


//拍照页面类     实现一个页面 加载- 然后自动调出摄像头 内部线程进行快门拍照  也可手动点击拍照！测试ok  fjw0312
public class CameraView extends SurfaceView implements SurfaceHolder.Callback,Camera.PictureCallback{
	//Fields
	private SurfaceHolder holder;
	private Camera camera;
	public boolean af;     //拍照结束变量
	public int hasPreview;  //记录进入相机预览的次数
	private boolean taked;
	Context thisContext;
	long time;
	private boolean thread_live = false;
	private MediaPlayer mediaPlayer;  //定义音频播放类
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
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); //指定push buffer
		
		mediaPlayer = new MediaPlayer(); //实例化音频播放器类
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		Log.e("CameraView-surfaceChanged","into!");  
		Camera.Parameters parameters = camera.getParameters(); //定义摄像头参数变量
//		List<Size> supportPreViewSize = parameters.getSupportedPreviewSizes();//获取所支持的大小
//		List<Size> supportPitureSize = parameters.getSupportedPictureSizes();
//		Log.e("CameraView-surfaceChanged-》supportPreViewSize.height：",String.valueOf( supportPreViewSize.get(0).height )); 
//		Log.e("CameraView-surfaceChanged-》supportPreViewSize.width：",String.valueOf( supportPreViewSize.get(0).width )); 
//		Log.e("CameraView-surfaceChanged-》supportPitureSize.height：",String.valueOf( supportPitureSize.get(0).height ));  
//		Log.e("CameraView-surfaceChanged-》supportPitureSize.width：",String.valueOf( supportPitureSize.get(0).width ));  
		parameters.setPreviewSize(200, 160); //设置预览大小
	//	parameters.setPictureSize(1024, 680);  //尺寸像素	由于照片出来近200K 相册无法显示 故应改小像素	
		parameters.setPictureSize(400, 320);  //尺寸像素		
		camera.setParameters(parameters);      //设置参数
		Log.e("CameraView-surfaceChanged","will startPreview()!"); 
		camera.startPreview();//开始预览 会有3~4s的阻塞时间
		hasPreview++;
		Log.e("CameraView-surfaceChanged>>has done startPreview!","startPreview!="+String.valueOf(hasPreview)+"---"+String.valueOf(af)); 
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		Log.e("CameraView-surfaceCreated","into!");  
		// TODO Auto-generated method stub  
		try{
		camera = Camera.open(0);//摄像头打开 根据不同硬件填入参数（有些也不用填） 会有1~2s的阻塞时间
		if(camera==null){
			Log.e("CameraView-surfaceCreated","camera==null");  
			return;
		}
		Log.e("CameraView-surfaceCreated","Camera.open");  
		camera.setPreviewDisplay(holder);
		
		}catch(Exception e){ 
			Log.e("CameraView-surfaceChanged","摄像头初始化失败！");
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

	@Override   //照片处理
	public void onPictureTaken(byte[] arg0, Camera arg1) {
		// TODO Auto-generated method stub
		try{
			time = System.currentTimeMillis();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式转换
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
			Log.e("CameraView-onPictureTaken","照片处理异常！");
		}
	}
	//按下快门处理程序  调用该函数会有系统默认拍照声
	private ShutterCallback shutterCallback = new ShutterCallback(){

		@Override
		public void onShutter() {
			Log.e("CameraView-shutterCallback","shutterCallback  into！");
			taked = true;
			// TODO Auto-generated method stub 
			//自定义播放声音    测试ok   记得退出时mediaPlayer.stop();
/*			try{
			mediaPlayer.setDataSource("/system/media/audio/ringtones/BirdLoop.ogg");
			mediaPlayer.prepare(); //缓冲
			}catch(Exception e){
				Log.e("CameraView-shutterCallback","读取文件失败 ！");
			}
			
			if(mediaPlayer != null){
				Log.e("CameraView-shutterCallback","will mediaPlay！");
				mediaPlayer.start();  
			}
*/			
		}
		
	};
	//外界触发传入参数 触发 拍照  记得把上面的mythread.start()去掉  
	public boolean takePhoto(boolean take){  
		if(take){
			mythread.start();
			return true;
		}
		return false;
	}
	//点击事件的触发 拍照
	public boolean onTouchEvent(MotionEvent event){
		try{
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			camera.autoFocus(null);
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			camera.takePicture(shutterCallback, null, this);   //第一个参数为shutterCallback拍照成功响声 null时没有声音
			af = true; //该返回不一定 是拍照结束 需要再一定延时  该条件可以在surfaceChanged 第2次开门拍下时 赋值
		}
		}catch(Exception e){
			Log.e("CameraView-onTouchEvent","异常！");
		}
		return true;
		
	}
	/****************
	 * camera 拍照的代码运行逻辑说明
	 * 当创建surfaceView -surfaceCreated执行为该类的设置
	 * 接着surfaceChanged 设置相机参数和启动预览（只有启动成功相机才能快门拍照takePicture）
	 * 接着我们可以camera.autoFocus(null); 去相机自动对焦
	 * 接着我们可以按下快门 camera.takePicture() ->会再调用surfaceChanged完成拍照
	 * **************/
	//新建 一个sleep 线程 自动拍照  
	private Thread mythread = new Thread(new Runnable(){  

		@Override
		public void run() {
			// TODO Auto-generated method stub
			long time = System.currentTimeMillis();	
			 while(hasPreview == 0); //预览成功后才可进行拍照   等待预览成功 执行下一步
			 try{
			 Thread.sleep(10); //0.001ms*1000
			 }catch(Exception e){
				 Log.e("CameraView-mythread-run"," sleep异常抛出！");
			 }		
			 camera.autoFocus(null); // 
			 while(CameraView.this.hasWindowFocus()==false){ //等待判断拍照界面显示于界面 才按下下面的快门
				 long d_time = System.currentTimeMillis()-time;	
				 if(d_time>10000){ //10s后未进入界面直接准备拍照
					 Log.e("CameraView-mythread-run","10s未进入界面直接拍照！");  
					 break;
				 }
			 }
			
			 try{
				 	Thread.sleep(10); //0.001ms*1000
			 }catch(Exception e){
					Log.e("CameraView-mythread-run"," sleep异常抛出！");
			 }
			 camera.takePicture(shutterCallback, null, CameraView.this); //传入外部静态类//会调用到surfaceChanged
			 Log.e("CameraView-mythread-run","has done takePicture!---"+String.valueOf(hasPreview));   							
			//等待拍照真正结束
			 while(true){	 		
				if(taked){
						Log.e("CameraView-mythread-run","拍照完成---"+String.valueOf(hasPreview));
						try{
						Thread.sleep(200);
						}catch(Exception e){  
							Log.e("CameraView-mythread-run"," sleep异常抛出！");
						}
						af = true;					
						break;
					}
			} 		
		}});
}
