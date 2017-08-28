package TXNews.customView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import TXNews.Main.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.opengl.Visibility;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.SeekBar.OnSeekBarChangeListener;


/**
 * 自定义 播放控制器
 * author:fjw0312@163.com
 * date:2017.8.22
 * 功能 自定义控件     自定义 ViewGroup容器(包含组合布局) 控件
 * 自定义布局容器 需要重写  onMeasure  onLayout 
 * 自定义 组合控件 需要获取布局
 * 自定义  继承拓展现有控件
 * 
 * onSizeChanged() 会在第一次 onMeasure后进入
 * */

public class MyMediaController extends FrameLayout{
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		// TODO Auto-generated method stub
		super.onVisibilityChanged(changedView, visibility);
		if(visibility == VISIBLE){
			Log.i("onVisibilityChanged>>",  "VISIBLE");
		}else if(visibility == INVISIBLE){
			Log.i("onVisibilityChanged>>",  "INVISIBLE");			
			pause_position = OldCurrentPosition;
			PlayState = PLAY_STATE_ReView;
		}else if(visibility == GONE){
			Log.i("onVisibilityChanged>>",  "GONE");
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		Log.i("MyMediaController>>onSizeChanged","into!");
		super.onSizeChanged(w, h, oldw, oldh);
		// 将 布局添加到 控件	
		init_view(myContext);	 
	}

	public MyMediaController(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		myContext = context;
		Log.e("MyMediaController","进入构造函数3");
	}
	public MyMediaController(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		myContext = context;
		Log.e("MyMediaController","进入构造函数2");
	}
	public MyMediaController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		myContext = context;
		Log.e("MyMediaController","进入构造函数1");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.drawColor(Color.GREEN);
		Log.w("MyMediaController"," >>onDraw");
	}
	private View ly_view;   //自定义组合 布局
	private LinearLayout ly_contrlBar;//自定义组合 布局  控制条 
	private LinearLayout ly_content;  //视频内部布局  用于点击暂停等
	private TextView Tx_title;        //自定义组合 布局 标题
	private ImageView img_play;      //自定义组合 播放
	private ImageView img_pause;
	private ImageView img_pgUp;
	private TextView Tx_TimeStart;
	private TextView Tx_TimeEnd;
	private ImageView img_c_play;
	private ImageView img_c_pause;
	private ProgressBar progressBar;
	private SeekBar seekBar;
	private ViewGroup myViewGroup;
	private Context myContext;
	
	int widthSize = 0;   //this布局控件  宽度
	int heightSize = 0;
	
	String strTx_TimeStart = "";
	
	
	public static int URI_LOADCATION = 1;  //播放本地资源  标识
	public static int URI_NET = 2;         //播放网络资源  标识
	int Localtion_OR_Net = 0; //本地/网络
	
	public static int PLAY_STATE_WILL = 1;      //播放就绪 状态
	public static int PLAY_STATE_PLAYING = 2;   //播放进行 状态
	public static int PLAY_STATE_PAUSE = 3;     //播放暂停 状态
	public static int PLAY_STATE_END = 4;      //播放结束 状态
	public static int PLAY_STATE_ReView = 5;      //重新界面进入 状态
	public static int PLAY_STATE_NETWAIT = 6;    //网络等待
	int HasPrepared = 0;   //预加载 结束标识
	int PlayState = 0;   //播放状态
	int oldPlayState = 0; //老的播放状态
	
	MediaPlayer MyMediaPlayer;  
	VideoView videoView;
	int DurationTime = 0;      //视频时长  ms
	int CurrentPosition = 0;   //播放进度  ms
	int OldCurrentPosition = 0;   //前1s播放进度  ms
	int BufferPercentage = 0;  //缓存百分比
	int pause_position = 0 ;  //暂停时播放进度
	int seek_To = 0;    //播放条 拖动位置

	Handler myHandler;
	Timer timer;
	
	//提供---*************************-  外部控制器端口    —*************************
		public void start(){  //开始播放
			if( (videoView != null) && (HasPrepared==PLAY_STATE_WILL) ){ //经过  缓存准备
				videoView.start();			
			}
		}
		public void pause(){
			if( (videoView != null) && (HasPrepared==PLAY_STATE_WILL) ){ //经过  缓存准备 
				videoView.pause();
			}
		}
		public void resume(){
			if( videoView != null ){
				OnResume();
			}
		}
		/**
		 * 播放  支持界面重新进入
		 * */
		private void OnStart(){ //开始播放	出现闪烁现象  ------seekBar 滑动结束  定位播放
			if(videoView != null){
				cancelTimerTask();
				videoView.resume();
				videoView.seekTo(CurrentPosition);
				videoView.start();
			}
		}
		/**
		 * 播放  支持界面重新进入
		 * */
		private void OnPause(){ //暂停播放                          -----seekBar 滑动时 进入 调用
			if(videoView != null){
				cancelTimerTask();
				videoView.pause(); 
				updateSeekBar();
			}
		}
		/**
		 * 复位 重新播放
		 * */
		private void OnResume(){ //重新播放                         ------resume 调用
			if(videoView != null){
				cancelTimerTask();
				videoView.resume();
				videoView.start();
			}
		} 
		public void OnDestory(){ //销毁播放器
			if(videoView != null){
				HasPrepared = 0;
				PlayState =  0;
				cancelTimerTask();
				videoView.stopPlayback();
				videoView = null;
			}
		}
		private void cancelTimerTask(){
			if(timer != null){
				timer.cancel();
				timer = null;
			}
		}
	//==========================对外 接口
	/**
	 * 自定义 控制器 设置接口
	 * 参数Object： VideoView or MediaPlayer  mode 本地播放模式 还是 网络播放模式 
	 * */
	public boolean setMediaPlayer(Object object){
		if(object == null) return false;
		//Localtion_OR_Net = mode;
		if(object instanceof VideoView){   //只支持 VideoView
			videoView = (VideoView)object;
			videoView.setOnPreparedListener(reparedListener);  //设置监听		
		}else{
			return false;
		} 
		return true;
	}
	// 定义 缓存准备监听器  初始化 缓冲准备  只需要缓冲到 可以播放就会触发  也只会触发一次 
	private OnPreparedListener reparedListener = new OnPreparedListener(){
		@Override
		public void onPrepared(MediaPlayer mediaPlayer) {
			// TODO Auto-generated method stub
			MyMediaPlayer = mediaPlayer;
			DurationTime = mediaPlayer.getDuration();  //获取 视频总时间
			seekBar.setMax(DurationTime);
			String str = Num_sec_TurnTo_Date(DurationTime);
			Tx_TimeEnd.setText(str);				
			
			Log.i("MyMediaController>>reparedListener", "into!");		
		//	if(Localtion_OR_Net== URI_NET){//判断  资源模式
			//	mediaPlayer.setOnBufferingUpdateListener(bufferingUpdateListener);//设置缓存更新 监听器
			
			//定义  播放条更新  定时器
			if(myHandler==null)  myHandler = new Handler();
			CurrentPosition = 0;  //初始化 播放进度
			if(timer==null){
				timer = new Timer();			
				timer.schedule(new TimerTask() {				
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Runnable runnable = new Runnable() {		
							@Override
							public void run() {
								// TODO Auto-generated method stub
								updateSeekBar();
							}
						};
						myHandler.post(runnable);
						runnable = null;
						if(MyMediaController.this==null){
							cancelTimerTask();
						}
					}
				}, 0, 500); //延时 0s  周期1s
			}
			
			HasPrepared = PLAY_STATE_WILL;
			//判断 为界面离开重新进入
			if(PlayState == PLAY_STATE_ReView){
				videoView.seekTo(pause_position);
				PlayState =  PLAY_STATE_PAUSE;
				setStateVisibility(PlayState);
			}else{			
				PlayState =  PLAY_STATE_WILL;
				setStateVisibility(PlayState);
			}			
		}	
	};


	/*
	// 定义   网络 缓存更新监听器      //约1/s 会进入一次直到播放结束   故，使用网络资源 可以用该监听 替代 定时器
	private OnBufferingUpdateListener bufferingUpdateListener = new OnBufferingUpdateListener(){
		@Override
		public void onBufferingUpdate(MediaPlayer mediaPlayer, int arg1) {  //arg1 为网络缓冲百分比
			// TODO Auto-generated method stub
			
			BufferPercentage = arg1; //获取 视频缓存进度百分比  videoView.getBufferPercentage();
			seekBar.setSecondaryProgress( (BufferPercentage*100)/DurationTime );
			updateSeekBar();
			Log.i("MyMediaController>>bufferingUpdateListener","BufferPercentage："+String.valueOf(BufferPercentage));
		}	
	};
	*/	
	//--------------------- ----------内部调用部分-------------------------
	//更新 播放控制器
	private void updateSeekBar(){  
		if(videoView==null) return;
		try{
			OldCurrentPosition = CurrentPosition;
			CurrentPosition = videoView.getCurrentPosition(); //获取播放进度 
			seekBar.setProgress(CurrentPosition);			
			seekBar.setSecondaryProgress( videoView.getBufferPercentage() ); //设置网络缓冲s
			strTx_TimeStart = Num_sec_TurnTo_Date(CurrentPosition);
			Tx_TimeStart.setText(strTx_TimeStart);

			
			//判断  播放状态
			if(PlayState != PLAY_STATE_ReView){
				if(OldCurrentPosition == DurationTime){ //播放结束
					Log.d("MyMediaController>>播放状态", "播放结束--"+"进度："+String.valueOf(CurrentPosition));
					PlayState =  PLAY_STATE_END;
				}else if(videoView.isPlaying()){		
					Log.d("MyMediaController>>播放状态", "正在播放--"+"进度："+String.valueOf(CurrentPosition));
					PlayState =  PLAY_STATE_PLAYING;
				}else{
					if(CurrentPosition==0){
						Log.w("MyMediaController>>播放状态", "准备播放--"+"进度："+String.valueOf(CurrentPosition));
						PlayState =  PLAY_STATE_WILL;
					}else{
						if(CurrentPosition+1000<DurationTime){
							Log.w("MyMediaController>>播放状态", "暂停播放--"+"进度："+String.valueOf(CurrentPosition));
							PlayState =  PLAY_STATE_PAUSE;
						}
					}
				}
			}else{
				Log.d("MyMediaController>>播放状态", "不可见--"+"进度："+String.valueOf(CurrentPosition));
			}
			if(PlayState != oldPlayState){
				Log.e("MyMediaController>>播放状态", "播放状态发生改变");
				setStateVisibility(PlayState);
				oldPlayState = PlayState;
			}
			if(videoView.isPlaying()){
				setStateVisibility(PLAY_STATE_PLAYING);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	//将 ms 时间 转换为时间00:00:00   传入参数 ms
	private String Num_sec_TurnTo_Date(int secNum){
		int num  = secNum/1000;
		int h = num/3600;
		int m = (num%3600)/60;
		int s = (num%3600)%60;
		String str_h = String.valueOf(h)+":";
		String str_m = String.valueOf(m)+":";
		String str_s = String.valueOf(s);
		if(h == 0){ 
			str_h = "";
		}else if(h<10){
			str_h = "0"+str_h;
		}
		if(m == 0){ 
			str_m = "00:";
		}else if(m<10){
			str_m = "0"+str_m;
		}
		if(s == 0){
			str_s = "00";
		}else if(s<10){
			str_s = "0"+str_s;
		}		
		String str = str_h+str_m+str_s;
		return str;
	}	
	//播放状态  标识更换方法
	private void setStateVisibility(int state){
		if(state==PLAY_STATE_WILL || state==PLAY_STATE_END){ //准备播放
			img_c_play.setVisibility(VISIBLE);
			img_c_pause.setVisibility(GONE);
			progressBar.setVisibility(GONE);
		}else if(state==PLAY_STATE_PAUSE){   //暂停播放
			img_c_play.setVisibility(GONE);
			img_c_pause.setVisibility(VISIBLE);
			progressBar.setVisibility(GONE);
		}else if(state==PLAY_STATE_PLAYING){  //正在播放
			img_c_play.setVisibility(GONE);
			img_c_pause.setVisibility(GONE);
			progressBar.setVisibility(GONE);
		}else if(state==PLAY_STATE_NETWAIT){  //网络缓冲
			img_c_play.setVisibility(GONE);
			img_c_pause.setVisibility(GONE);
			progressBar.setVisibility(VISIBLE);
		}else{
			img_c_play.setVisibility(GONE);
			img_c_pause.setVisibility(GONE);
			progressBar.setVisibility(GONE);
		}
	}
	//======================================控件 监听部分===================================
	private OnClickListener l = new OnClickListener() {		 
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0 == img_play){
				Log.e("OnClickListener>>img_play","点击");
				 //开始播放
				start();
			}else if(arg0 == img_pause){
				Log.e("OnClickListener>>img_next","点击");
				//暂停播放
				pause();
			}else if(arg0 == img_pgUp){ 
				Log.e("OnClickListener>>img_pgUp","点击");
				//全屏 播放  ----未实现
				
			}else if(arg0==img_c_play){
				Log.e("OnClickListener>>img_c_play","点击 中间标识");
				 //重新播放
				resume();
			}else if(arg0==img_c_pause){
				Log.e("OnClickListener>>img_c_pause","点击 中间标识");
				//播放
				start();
			}
		}
	};
	private OnTouchListener myOnTouchListenner = new OnTouchListener(){
		@Override
		public boolean onTouch(View arg0, MotionEvent event) {
			// TODO Auto-generated method stub
			//event.getAction() 为低8位触摸类型，高8位+1位触摸点个数 
			//该函数&0xff 或&MotionEvent.ACTION_MASK = event.getActionMasked()
			switch(event.getActionMasked()){      //判断触摸的类型getActionMasked() 
				case MotionEvent.ACTION_DOWN: //有手指按下
					Log.e("onTouch>>ACTION_DOWN","有手指按下");
					pause();
					break;
				case MotionEvent.ACTION_UP : //有手指放起
					Log.e("onTouch>>ACTION_DOWN","有手指放起");
					break;
				case MotionEvent.ACTION_MOVE : //有手指移动  
					Log.e("onTouch>>ACTION_DOWN","有手指移动");
					break;
				default: break; 
			}
			return true;  //只有返回true 才能监听到ACTION_MOVE  ACTION_UP
		}
		
	};
	// 定义 seekBar 拖动 改变监听   notice：发现拖动设置播放进度 有bug 无法及时反应
	private OnSeekBarChangeListener seekBar_l = new OnSeekBarChangeListener() {	 
		@Override
		public void onStopTrackingTouch(SeekBar arg0) { //停止滚动
			// TODO Auto-generated method stub
			//Log.i("OnSeekBarChangeListener>>SeekBar","SeekBar停止滚动");
			CurrentPosition = seek_To;
			OnStart();
		} 
		
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {//开始滚动
			// TODO Auto-generated method stub 
			//Log.i("OnSeekBarChangeListener>>SeekBar","SeekBar开始滚动");
			OnPause();		
		}
		
		@Override
		public void onProgressChanged(SeekBar arg0, int progresss, boolean fromUser) {  //滚动
			// TODO Auto-generated method stub 
			if(fromUser){
				seek_To = progresss;
			}else{
				//播放中  fromUser = false
			//	Log.i("OnSeekBarChangeListener>>SeekBar真正拖动-false",String.valueOf(progresss)+"  "+String.valueOf(fromUser));
			}		 
		}
	};

	//---------------------------------------自定义 控件 部分----------------------------------
    //初始化 ，将布局添加到控件上    布局控制条 在wrap_content布局下会跟随 子View(VideoView)的大小 
	private void init_view(Context context){ 
		ly_view = LayoutInflater.from(context).inflate(R.layout.my_mediacontroller, this); //将页面加载到 该View
		android.view.ViewGroup.LayoutParams layoutParams = ly_view.getLayoutParams();
		layoutParams.height = heightSize;
		layoutParams.width = widthSize;
		ly_view.setLayoutParams(layoutParams);
		
		//获取控件   
		ly_contrlBar = (LinearLayout)ly_view.findViewById(R.id.ly_controlBar);
		Tx_title = (TextView)ly_view.findViewById(R.id.Tx_title);
		img_play = (ImageView)ly_view.findViewById(R.id.img_play);
		img_pause = (ImageView)ly_view.findViewById(R.id.img_pause);
		img_pgUp = (ImageView)ly_view.findViewById(R.id.img_upDown);
		Tx_TimeStart = (TextView)ly_view.findViewById(R.id.Tx_timeStart); 
		Tx_TimeEnd = (TextView)ly_view.findViewById(R.id.Tx_timeEnd);
		seekBar = (SeekBar)ly_view.findViewById(R.id.seekbar_id);
		
		ly_content = (LinearLayout)ly_view.findViewById(R.id.ly_content);
		img_c_play = (ImageView)ly_view.findViewById(R.id.img_c_play);
		img_c_pause = (ImageView)ly_view.findViewById(R.id.img_c_pause);
		progressBar = (ProgressBar)ly_view.findViewById(R.id.progreaaBar_id);
		
		//设置监听
		img_play.setOnClickListener(l);
		img_pause.setOnClickListener(l);
		img_pgUp.setOnClickListener(l);
		seekBar.setOnSeekBarChangeListener(seekBar_l);
		ly_content.setOnTouchListener(myOnTouchListenner);
		img_c_play.setOnClickListener(l);
		img_c_pause.setOnClickListener(l);
	}	
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate(); 
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		Log.i("MyMediaController>>onLayout","into!");
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//遍历 子控件 mesaure() 
		int childCount = getChildCount();
		Log.i("MyMediaController>>onMeasure","childCount="+String.valueOf(childCount));
		for(int i=0;i<childCount;i++){   
			View children = getChildAt(i); 
			measureChild(children, widthMeasureSpec, heightMeasureSpec);
		}
		
		//判断 布局模式   支持wrap_content
		int mMaxWidth = 0;
		int mMaxHeight = 0;
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);	 //this布局控件  宽度模式 
		int heightMode = MeasureSpec.getMode(heightMeasureSpec); //this布局控件  高度模式 
		widthSize = MeasureSpec.getSize(widthMeasureSpec);   //this布局控件  宽度
		heightSize = MeasureSpec.getSize(heightMeasureSpec); //this布局控件  高度
		if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				int h = children.getMeasuredHeight();
				int w = children.getMeasuredWidth();
				if(h > mMaxHeight)  mMaxHeight = h;
				if(w > mMaxWidth)  mMaxWidth = w;
			}
			widthSize = mMaxWidth;
			heightSize = mMaxHeight;			 
		}else if(widthMode == MeasureSpec.AT_MOST){ 
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				int w = children.getMeasuredWidth();
				if(w > mMaxWidth)  mMaxWidth = w;
			}
			widthSize = mMaxWidth;
		}else if(heightMode == MeasureSpec.AT_MOST){
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				int h = children.getMeasuredHeight();
				if(h > mMaxHeight)  mMaxHeight = h;
			}
			heightSize = mMaxHeight;		
		}
		setMeasuredDimension(widthSize, heightSize);  //设置 为任一 子控件的最大 宽 高	
	}
}
