package main.mypulltorefresh;

import myCustomView.MyPullToRefreshLayout;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/***
 * 下拉 方式二：
 * android-UItra-Pull-To-Refresh 自定义 表头
 * 
 *  
 * */

public class ThreeActivity extends Activity {

	
	String[] str_s = {"微信","QQ","陌陌","来往","探探",
			          "爱奇艺","优酷","腾讯视频","乐视","bilibili",
			          "凤凰","头条","网易","虎扑","天行"};  
	MyPullToRefreshLayout ptrFrameLayout;
	TextView textView;
	
	Button Bn_Pre;
	Button Bn_Next;
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v==Bn_Pre){
				Intent intent = new Intent(ThreeActivity.this, NextActivity.class);
				startActivity(intent);
			}else if(v==Bn_Next){
				//Intent intent = new Intent(ThreeActivity.this, MainActivity.class);
				//startActivity(intent);
				
				myFrameAnimation(textView);
			}
		}
	};
	
	//帧 动画 
	AnimationDrawable animationDrawable;
	private void myFrameAnimation(View view){
			if(animationDrawable!=null && animationDrawable.isRunning()){
				animationDrawable.stop();
			}
			view.setBackgroundResource(R.drawable.mypullto_refresh_animationlist);  
			animationDrawable = (AnimationDrawable)view.getBackground();
			animationDrawable.start();
	}
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page3); 
		
		Bn_Pre = (Button)findViewById(R.id.Bn_pre);
		Bn_Next = (Button)findViewById(R.id.Bn_next);
		Bn_Pre.setOnClickListener(l);
		Bn_Next.setOnClickListener(l);
		
		textView = (TextView)findViewById(R.id.Tx_id);
		ptrFrameLayout = (MyPullToRefreshLayout)findViewById(R.id.MyPullToRefreshLayout);
		ptrFrameLayout.setPtrHandler(new PtrHandler() {  //处理刷新		
			@Override
			public void onRefreshBegin(PtrFrameLayout frame) {  //开始刷新
				// TODO Auto-generated method stub
				//处理包裹的content刷新业务。
				
				//延时2s后  结束刷新	
				ptrFrameLayout.postDelayed( new Runnable() {  		
					@Override
					public void run() {
						// TODO Auto-generated method stub
						ptrFrameLayout.refreshComplete();	//结束刷新
					}
				}, 2000);
			}		
			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame, View content,
					View header) {
				// TODO Auto-generated method stub//返回true  表示可以刷新 。
				return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
			}
		});
	}
}
