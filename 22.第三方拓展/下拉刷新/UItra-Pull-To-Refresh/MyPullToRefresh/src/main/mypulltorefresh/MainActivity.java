package main.mypulltorefresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


/***
 * 下拉 方式一：
 * SwipeRefreshLayout  google 原生v4 下拉刷新   （不支持上拉刷新）
 * 刷新样式 固定，可设置的 刷新头 单调 死板。如果要样式丰富使用 需要 修改该类，重写刷新头。
 * 
 * */

public class MainActivity extends Activity {

	SwipeRefreshLayout swipeRefreshLayout;
	ListView listView; 
	Button Bn_Pre;
	Button Bn_Next;
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v==Bn_Pre){
				MainActivity.this.finish();
			}else if(v==Bn_Next){
				Intent intent = new Intent(MainActivity.this, NextActivity.class);
				startActivity(intent);
			}
		}
	};
	
	String[] str_s = {"微信","QQ","陌陌","来往","探探",
			          "爱奇艺","优酷","腾讯视频","乐视","bilibili",
			          "凤凰","头条","网易","虎扑","天行"};
	 
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		
		Log.i("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu", "");
		Bn_Pre = (Button)findViewById(R.id.Bn_pre);
		Bn_Next = (Button)findViewById(R.id.Bn_next);
		Bn_Pre.setOnClickListener(l);
		Bn_Next.setOnClickListener(l); 
		
		swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.srl);
		listView = (ListView)findViewById(R.id.lv);
		 
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str_s);
		listView.setAdapter(adapter);
		
		
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() { //		
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(false);  //一定要设置 刷新结束
			}
		});
	}
}
