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
 * ���� ��ʽһ��
 * SwipeRefreshLayout  google ԭ��v4 ����ˢ��   ����֧������ˢ�£�
 * ˢ����ʽ �̶��������õ� ˢ��ͷ ���� ���塣���Ҫ��ʽ�ḻʹ�� ��Ҫ �޸ĸ��࣬��дˢ��ͷ��
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
	
	String[] str_s = {"΢��","QQ","İİ","����","̽̽",
			          "������","�ſ�","��Ѷ��Ƶ","����","bilibili",
			          "���","ͷ��","����","����","����"};
	 
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
				swipeRefreshLayout.setRefreshing(false);  //һ��Ҫ���� ˢ�½���
			}
		});
	}
}
