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
 * 下拉 方式二：
 * android-UItra-Pull-To-Refresh  
 * 
 *  
 * */

public class NextActivity extends Activity {

	
	String[] str_s = {"微信","QQ","陌陌","来往","探探",
			          "爱奇艺","优酷","腾讯视频","乐视","bilibili",
			          "凤凰","头条","网易","虎扑","天行"};
	 
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page2); 
		
	
	}
}
