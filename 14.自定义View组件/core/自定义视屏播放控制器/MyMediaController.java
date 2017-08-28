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
 * �Զ��� ���ſ�����
 * author:fjw0312@163.com
 * date:2017.8.22
 * ���� �Զ���ؼ�     �Զ��� ViewGroup����(������ϲ���) �ؼ�
 * �Զ��岼������ ��Ҫ��д  onMeasure  onLayout 
 * �Զ��� ��Ͽؼ� ��Ҫ��ȡ����
 * �Զ���  �̳���չ���пؼ�
 * 
 * onSizeChanged() ���ڵ�һ�� onMeasure�����
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
		// �� ������ӵ� �ؼ�	
		init_view(myContext);	 
	}

	public MyMediaController(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		myContext = context;
		Log.e("MyMediaController","���빹�캯��3");
	}
	public MyMediaController(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		myContext = context;
		Log.e("MyMediaController","���빹�캯��2");
	}
	public MyMediaController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		myContext = context;
		Log.e("MyMediaController","���빹�캯��1");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.drawColor(Color.GREEN);
		Log.w("MyMediaController"," >>onDraw");
	}
	private View ly_view;   //�Զ������ ����
	private LinearLayout ly_contrlBar;//�Զ������ ����  ������ 
	private LinearLayout ly_content;  //��Ƶ�ڲ�����  ���ڵ����ͣ��
	private TextView Tx_title;        //�Զ������ ���� ����
	private ImageView img_play;      //�Զ������ ����
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
	
	int widthSize = 0;   //this���ֿؼ�  ���
	int heightSize = 0;
	
	String strTx_TimeStart = "";
	
	
	public static int URI_LOADCATION = 1;  //���ű�����Դ  ��ʶ
	public static int URI_NET = 2;         //����������Դ  ��ʶ
	int Localtion_OR_Net = 0; //����/����
	
	public static int PLAY_STATE_WILL = 1;      //���ž��� ״̬
	public static int PLAY_STATE_PLAYING = 2;   //���Ž��� ״̬
	public static int PLAY_STATE_PAUSE = 3;     //������ͣ ״̬
	public static int PLAY_STATE_END = 4;      //���Ž��� ״̬
	public static int PLAY_STATE_ReView = 5;      //���½������ ״̬
	public static int PLAY_STATE_NETWAIT = 6;    //����ȴ�
	int HasPrepared = 0;   //Ԥ���� ������ʶ
	int PlayState = 0;   //����״̬
	int oldPlayState = 0; //�ϵĲ���״̬
	
	MediaPlayer MyMediaPlayer;  
	VideoView videoView;
	int DurationTime = 0;      //��Ƶʱ��  ms
	int CurrentPosition = 0;   //���Ž���  ms
	int OldCurrentPosition = 0;   //ǰ1s���Ž���  ms
	int BufferPercentage = 0;  //����ٷֱ�
	int pause_position = 0 ;  //��ͣʱ���Ž���
	int seek_To = 0;    //������ �϶�λ��

	Handler myHandler;
	Timer timer;
	
	//�ṩ---*************************-  �ⲿ�������˿�    ��*************************
		public void start(){  //��ʼ����
			if( (videoView != null) && (HasPrepared==PLAY_STATE_WILL) ){ //����  ����׼��
				videoView.start();			
			}
		}
		public void pause(){
			if( (videoView != null) && (HasPrepared==PLAY_STATE_WILL) ){ //����  ����׼�� 
				videoView.pause();
			}
		}
		public void resume(){
			if( videoView != null ){
				OnResume();
			}
		}
		/**
		 * ����  ֧�ֽ������½���
		 * */
		private void OnStart(){ //��ʼ����	������˸����  ------seekBar ��������  ��λ����
			if(videoView != null){
				cancelTimerTask();
				videoView.resume();
				videoView.seekTo(CurrentPosition);
				videoView.start();
			}
		}
		/**
		 * ����  ֧�ֽ������½���
		 * */
		private void OnPause(){ //��ͣ����                          -----seekBar ����ʱ ���� ����
			if(videoView != null){
				cancelTimerTask();
				videoView.pause(); 
				updateSeekBar();
			}
		}
		/**
		 * ��λ ���²���
		 * */
		private void OnResume(){ //���²���                         ------resume ����
			if(videoView != null){
				cancelTimerTask();
				videoView.resume();
				videoView.start();
			}
		} 
		public void OnDestory(){ //���ٲ�����
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
	//==========================���� �ӿ�
	/**
	 * �Զ��� ������ ���ýӿ�
	 * ����Object�� VideoView or MediaPlayer  mode ���ز���ģʽ ���� ���粥��ģʽ 
	 * */
	public boolean setMediaPlayer(Object object){
		if(object == null) return false;
		//Localtion_OR_Net = mode;
		if(object instanceof VideoView){   //ֻ֧�� VideoView
			videoView = (VideoView)object;
			videoView.setOnPreparedListener(reparedListener);  //���ü���		
		}else{
			return false;
		} 
		return true;
	}
	// ���� ����׼��������  ��ʼ�� ����׼��  ֻ��Ҫ���嵽 ���Բ��žͻᴥ��  Ҳֻ�ᴥ��һ�� 
	private OnPreparedListener reparedListener = new OnPreparedListener(){
		@Override
		public void onPrepared(MediaPlayer mediaPlayer) {
			// TODO Auto-generated method stub
			MyMediaPlayer = mediaPlayer;
			DurationTime = mediaPlayer.getDuration();  //��ȡ ��Ƶ��ʱ��
			seekBar.setMax(DurationTime);
			String str = Num_sec_TurnTo_Date(DurationTime);
			Tx_TimeEnd.setText(str);				
			
			Log.i("MyMediaController>>reparedListener", "into!");		
		//	if(Localtion_OR_Net== URI_NET){//�ж�  ��Դģʽ
			//	mediaPlayer.setOnBufferingUpdateListener(bufferingUpdateListener);//���û������ ������
			
			//����  ����������  ��ʱ��
			if(myHandler==null)  myHandler = new Handler();
			CurrentPosition = 0;  //��ʼ�� ���Ž���
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
				}, 0, 500); //��ʱ 0s  ����1s
			}
			
			HasPrepared = PLAY_STATE_WILL;
			//�ж� Ϊ�����뿪���½���
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
	// ����   ���� ������¼�����      //Լ1/s �����һ��ֱ�����Ž���   �ʣ�ʹ��������Դ �����øü��� ��� ��ʱ��
	private OnBufferingUpdateListener bufferingUpdateListener = new OnBufferingUpdateListener(){
		@Override
		public void onBufferingUpdate(MediaPlayer mediaPlayer, int arg1) {  //arg1 Ϊ���绺��ٷֱ�
			// TODO Auto-generated method stub
			
			BufferPercentage = arg1; //��ȡ ��Ƶ������Ȱٷֱ�  videoView.getBufferPercentage();
			seekBar.setSecondaryProgress( (BufferPercentage*100)/DurationTime );
			updateSeekBar();
			Log.i("MyMediaController>>bufferingUpdateListener","BufferPercentage��"+String.valueOf(BufferPercentage));
		}	
	};
	*/	
	//--------------------- ----------�ڲ����ò���-------------------------
	//���� ���ſ�����
	private void updateSeekBar(){  
		if(videoView==null) return;
		try{
			OldCurrentPosition = CurrentPosition;
			CurrentPosition = videoView.getCurrentPosition(); //��ȡ���Ž��� 
			seekBar.setProgress(CurrentPosition);			
			seekBar.setSecondaryProgress( videoView.getBufferPercentage() ); //�������绺��s
			strTx_TimeStart = Num_sec_TurnTo_Date(CurrentPosition);
			Tx_TimeStart.setText(strTx_TimeStart);

			
			//�ж�  ����״̬
			if(PlayState != PLAY_STATE_ReView){
				if(OldCurrentPosition == DurationTime){ //���Ž���
					Log.d("MyMediaController>>����״̬", "���Ž���--"+"���ȣ�"+String.valueOf(CurrentPosition));
					PlayState =  PLAY_STATE_END;
				}else if(videoView.isPlaying()){		
					Log.d("MyMediaController>>����״̬", "���ڲ���--"+"���ȣ�"+String.valueOf(CurrentPosition));
					PlayState =  PLAY_STATE_PLAYING;
				}else{
					if(CurrentPosition==0){
						Log.w("MyMediaController>>����״̬", "׼������--"+"���ȣ�"+String.valueOf(CurrentPosition));
						PlayState =  PLAY_STATE_WILL;
					}else{
						if(CurrentPosition+1000<DurationTime){
							Log.w("MyMediaController>>����״̬", "��ͣ����--"+"���ȣ�"+String.valueOf(CurrentPosition));
							PlayState =  PLAY_STATE_PAUSE;
						}
					}
				}
			}else{
				Log.d("MyMediaController>>����״̬", "���ɼ�--"+"���ȣ�"+String.valueOf(CurrentPosition));
			}
			if(PlayState != oldPlayState){
				Log.e("MyMediaController>>����״̬", "����״̬�����ı�");
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
	//�� ms ʱ�� ת��Ϊʱ��00:00:00   ������� ms
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
	//����״̬  ��ʶ��������
	private void setStateVisibility(int state){
		if(state==PLAY_STATE_WILL || state==PLAY_STATE_END){ //׼������
			img_c_play.setVisibility(VISIBLE);
			img_c_pause.setVisibility(GONE);
			progressBar.setVisibility(GONE);
		}else if(state==PLAY_STATE_PAUSE){   //��ͣ����
			img_c_play.setVisibility(GONE);
			img_c_pause.setVisibility(VISIBLE);
			progressBar.setVisibility(GONE);
		}else if(state==PLAY_STATE_PLAYING){  //���ڲ���
			img_c_play.setVisibility(GONE);
			img_c_pause.setVisibility(GONE);
			progressBar.setVisibility(GONE);
		}else if(state==PLAY_STATE_NETWAIT){  //���绺��
			img_c_play.setVisibility(GONE);
			img_c_pause.setVisibility(GONE);
			progressBar.setVisibility(VISIBLE);
		}else{
			img_c_play.setVisibility(GONE);
			img_c_pause.setVisibility(GONE);
			progressBar.setVisibility(GONE);
		}
	}
	//======================================�ؼ� ��������===================================
	private OnClickListener l = new OnClickListener() {		 
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0 == img_play){
				Log.e("OnClickListener>>img_play","���");
				 //��ʼ����
				start();
			}else if(arg0 == img_pause){
				Log.e("OnClickListener>>img_next","���");
				//��ͣ����
				pause();
			}else if(arg0 == img_pgUp){ 
				Log.e("OnClickListener>>img_pgUp","���");
				//ȫ�� ����  ----δʵ��
				
			}else if(arg0==img_c_play){
				Log.e("OnClickListener>>img_c_play","��� �м��ʶ");
				 //���²���
				resume();
			}else if(arg0==img_c_pause){
				Log.e("OnClickListener>>img_c_pause","��� �м��ʶ");
				//����
				start();
			}
		}
	};
	private OnTouchListener myOnTouchListenner = new OnTouchListener(){
		@Override
		public boolean onTouch(View arg0, MotionEvent event) {
			// TODO Auto-generated method stub
			//event.getAction() Ϊ��8λ�������ͣ���8λ+1λ��������� 
			//�ú���&0xff ��&MotionEvent.ACTION_MASK = event.getActionMasked()
			switch(event.getActionMasked()){      //�жϴ���������getActionMasked() 
				case MotionEvent.ACTION_DOWN: //����ָ����
					Log.e("onTouch>>ACTION_DOWN","����ָ����");
					pause();
					break;
				case MotionEvent.ACTION_UP : //����ָ����
					Log.e("onTouch>>ACTION_DOWN","����ָ����");
					break;
				case MotionEvent.ACTION_MOVE : //����ָ�ƶ�  
					Log.e("onTouch>>ACTION_DOWN","����ָ�ƶ�");
					break;
				default: break; 
			}
			return true;  //ֻ�з���true ���ܼ�����ACTION_MOVE  ACTION_UP
		}
		
	};
	// ���� seekBar �϶� �ı����   notice�������϶����ò��Ž��� ��bug �޷���ʱ��Ӧ
	private OnSeekBarChangeListener seekBar_l = new OnSeekBarChangeListener() {	 
		@Override
		public void onStopTrackingTouch(SeekBar arg0) { //ֹͣ����
			// TODO Auto-generated method stub
			//Log.i("OnSeekBarChangeListener>>SeekBar","SeekBarֹͣ����");
			CurrentPosition = seek_To;
			OnStart();
		} 
		
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {//��ʼ����
			// TODO Auto-generated method stub 
			//Log.i("OnSeekBarChangeListener>>SeekBar","SeekBar��ʼ����");
			OnPause();		
		}
		
		@Override
		public void onProgressChanged(SeekBar arg0, int progresss, boolean fromUser) {  //����
			// TODO Auto-generated method stub 
			if(fromUser){
				seek_To = progresss;
			}else{
				//������  fromUser = false
			//	Log.i("OnSeekBarChangeListener>>SeekBar�����϶�-false",String.valueOf(progresss)+"  "+String.valueOf(fromUser));
			}		 
		}
	};

	//---------------------------------------�Զ��� �ؼ� ����----------------------------------
    //��ʼ�� ����������ӵ��ؼ���    ���ֿ����� ��wrap_content�����»���� ��View(VideoView)�Ĵ�С 
	private void init_view(Context context){ 
		ly_view = LayoutInflater.from(context).inflate(R.layout.my_mediacontroller, this); //��ҳ����ص� ��View
		android.view.ViewGroup.LayoutParams layoutParams = ly_view.getLayoutParams();
		layoutParams.height = heightSize;
		layoutParams.width = widthSize;
		ly_view.setLayoutParams(layoutParams);
		
		//��ȡ�ؼ�   
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
		
		//���ü���
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
		//���� �ӿؼ� mesaure() 
		int childCount = getChildCount();
		Log.i("MyMediaController>>onMeasure","childCount="+String.valueOf(childCount));
		for(int i=0;i<childCount;i++){   
			View children = getChildAt(i); 
			measureChild(children, widthMeasureSpec, heightMeasureSpec);
		}
		
		//�ж� ����ģʽ   ֧��wrap_content
		int mMaxWidth = 0;
		int mMaxHeight = 0;
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);	 //this���ֿؼ�  ���ģʽ 
		int heightMode = MeasureSpec.getMode(heightMeasureSpec); //this���ֿؼ�  �߶�ģʽ 
		widthSize = MeasureSpec.getSize(widthMeasureSpec);   //this���ֿؼ�  ���
		heightSize = MeasureSpec.getSize(heightMeasureSpec); //this���ֿؼ�  �߶�
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
		setMeasuredDimension(widthSize, heightSize);  //���� Ϊ��һ �ӿؼ������ �� ��	
	}
}
