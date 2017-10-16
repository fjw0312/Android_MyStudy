package main.mypulltorefresh;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import android.app.Activity;
import android.content.Intent;
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


/***
 * 下拉 方式二：
 * android-UItra-Pull-To-Refresh  使用源码库 demo PtrClassicFrameLayout
 * 
 *  
 * */

public class NextActivity extends Activity {

	
	String[] str_s = {"微信","QQ","陌陌","来往","探探",
			          "爱奇艺","优酷","腾讯视频","乐视","bilibili",
			          "凤凰","头条","网易","虎扑","天行"};  
	PtrClassicFrameLayout ptrFrameLayout;
	 
	
	Button Bn_Pre;
	Button Bn_Next;
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v==Bn_Pre){
				Intent intent = new Intent(NextActivity.this, MainActivity.class);
				startActivity(intent);
			}else if(v==Bn_Next){
				Intent intent = new Intent(NextActivity.this, Next2Activity.class);
				startActivity(intent);
			}
		}
	};
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page2); 
		
		
		Bn_Pre = (Button)findViewById(R.id.Bn_pre);
		Bn_Next = (Button)findViewById(R.id.Bn_next);
		Bn_Pre.setOnClickListener(l);
		Bn_Next.setOnClickListener(l);
		
		ptrFrameLayout = (PtrClassicFrameLayout)findViewById(R.id.ptrClassicFrameLayout);
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
