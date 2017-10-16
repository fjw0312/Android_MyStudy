package myCustomView;

import main.mypulltorefresh.R;
import mybroadcast.MyBroadcastReceiver;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPullToHeadView extends FrameLayout implements PtrUIHandler{

	public MyPullToHeadView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initViews();
	}

	public MyPullToHeadView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initViews();
	}

	public MyPullToHeadView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initViews();
	}

	
	TextView textView;
	ImageView imageView;
	int i = 0;
	AnimationDrawable animationDrawable;
	
	// ��ʼ��  �ؼ�
	private void initViews(){
		View ly = View.inflate(getContext(), R.layout.pullto_headview, this); 
		textView = (TextView)ly.findViewById(R.id.Tx_id);
		imageView = (ImageView)ly.findViewById(R.id.img_id);
	}
	
	
	//֡ ����     ע�� ��setBackgroundResource ��������ImageView 
	private void myFrameAnimation(View view){
		if(animationDrawable!=null && animationDrawable.isRunning()){
			animationDrawable.stop();
		}
		view.setBackgroundResource(R.drawable.mypullto_refresh_animationlist);  
		animationDrawable = (AnimationDrawable)view.getBackground();
		animationDrawable.start();
	}


	@Override   //��ʼ����֮ǰ�Ľӿڻص� 
	public void onUIRefreshPrepare(PtrFrameLayout frame) {
		// TODO Auto-generated method stub
		textView.setText("Prepare");
		//imageView.setVisibility(View.GONE);
		MyBroadcastReceiver.sendBroad_MSG_HAL("ˢ��-Prepare");
	}

	@Override  //��ʼˢ�µĽӿڻص���
	public void onUIRefreshBegin(PtrFrameLayout frame) {
		// TODO Auto-generated method stub
		textView.setText("Begin");
		//imageView.setVisibility(View.VISIBLE);   
		myFrameAnimation(imageView);  //���� ˢ�� ����
		MyBroadcastReceiver.sendBroad_MSG_HAL("ˢ��-Begin");
	}

	@Override  //ˢ����ɵĽӿڻص���
	public void onUIRefreshComplete(PtrFrameLayout frame) {
		// TODO Auto-generated method stub
		textView.setText("Complete");
	//	imageView.setVisibility(View.GONE);
		MyBroadcastReceiver.sendBroad_MSG_HAL("ˢ��-Complete");
	}
	
	@Override  //ˢ�����֮��UI��ʧ֮��Ľӿڻص���
	public void onUIReset(PtrFrameLayout frame) {
		// TODO Auto-generated method stub
		textView.setText("Reset");
		MyBroadcastReceiver.sendBroad_MSG_HAL("ˢ��-Reset");
	}
	
	@Override  //���������Ľӿڻص�����ε��á� 
	public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch,
			byte status, PtrIndicator ptrIndicator) {
		// TODO Auto-generated method stub
		i++;
		if(i>200000) i = 0;
//		textView.setText("Change "+String.valueOf(i));
	}

}
