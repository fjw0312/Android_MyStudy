 package my.customView;

import my.git.MainActivity;
import my.git.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;



/**
 * 自定义 组合布局(获取布局文件自定义组件)  组件 
 * author:fjw0312 
 * Email:fjw0312@163.com  
 * date:2017.9.13   
 * 功能：导航栏       利用3个ImageView  
 * 
 * */ 
public class MyNavigationBar extends LinearLayout{  

	public MyNavigationBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init_view(context);
	}

	public MyNavigationBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub 
		init_view(context);
	}

	public MyNavigationBar(Context context) { 
		super(context);
		// TODO Auto-generated constructor stub
		init_view(context);
	}
	
	private ImageView v_view1; 
	private ImageView v_view2; 
	private ImageView v_view3;
	
	public int selectId = 0;
	Context mContext;
	AnimationDrawable animationDrawable;
	MainActivity mActivity;
	
	public void setActivity(Activity activity){
		mActivity = (MainActivity)activity; 
	}
	
	//点击动画
	private void myFrameAnimation(View view){
		if(animationDrawable!=null && animationDrawable.isRunning()){
			animationDrawable.stop();
		}
		view.setBackgroundResource(R.drawable.click_animation);  
		animationDrawable = (AnimationDrawable)view.getBackground();
		animationDrawable.start();  
	}
	
	
	//初始化 ，将布局添加到控件上
	private void init_view(Context context){
		mContext = context;
		LayoutInflater.from(context).inflate(R.layout.my_navigation_bar, this);
		//获取控件
		v_view1 = (ImageView)findViewById(R.id.img_category1);
		v_view2 = (ImageView)findViewById(R.id.img_category2);
		v_view3 = (ImageView)findViewById(R.id.img_category3); 

		v_view1.setOnClickListener(l);
		v_view2.setOnClickListener(l); 
		v_view2.setOnClickListener(l); 
		v_view3.setOnClickListener(l);

	}
	
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub   
			//动画
			myFrameAnimation(arg0);
			
			if(arg0==v_view1){ 
				mActivity.webView.goBack();
				
			}else if(arg0==v_view2){
				mActivity.webView.loadUrl(MainActivity.myGit_strUrl);
			//	mActivity.webView.goBackOrForward(2);
			}else if(arg0==v_view3){
				mActivity.finish();				
			}	
		}
	};


}
