package main.mypulltorefresh;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/***
 * ���� ��ʽ����
 * android-UItra-Pull-To-Refresh  
 * 
 *  
 * */

public class NextActivity extends Activity {

	
	String[] str_s = {"΢��","QQ","İİ","����","̽̽",
			          "������","�ſ�","��Ѷ��Ƶ","����","bilibili",
			          "���","ͷ��","����","����","����"};
	 
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page2); 
		
	
	}
}
