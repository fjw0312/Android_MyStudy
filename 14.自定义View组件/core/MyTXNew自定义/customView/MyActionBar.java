package TXNews.customView;

import mybroadcast.MyBroadcastReceiver;
import TXNews.Main.R;
import android.app.ActionBar;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 自定义  标题栏
 * author:fjw0312
 * date: 2017.8.2
 * 
 * */
public class MyActionBar {

	ImageView imageView1;
	ImageView imageView2;
	ImageView imageView3;
	
	public MyActionBar(Activity activity) {
		// TODO Auto-generated constructor stub
		
		//对标题栏进行 自定义修改
		ActionBar actionBar = activity.getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);//使能自定义
		actionBar.setCustomView(R.layout.my_actionbar);       //设置自定义布局
		actionBar.setDisplayShowCustomEnabled(true);
		
		imageView1 = (ImageView)actionBar.getCustomView().findViewById(R.id.img_TitleLogo);
		imageView2 = (ImageView)actionBar.getCustomView().findViewById(R.id.img_findLogo);
		imageView3 = (ImageView)actionBar.getCustomView().findViewById(R.id.img_shareLogo);
		
		imageView1.setOnClickListener(l);
		imageView2.setOnClickListener(l);
		imageView3.setOnClickListener(l);
	}
	
	private OnClickListener l = new OnClickListener() {
		 
		 
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==imageView1){
				MyBroadcastReceiver.sendBroad_MSG_HAL("点击标题栏>> Title logo");
			}else if(arg0==imageView2){
				MyBroadcastReceiver.sendBroad_MSG_HAL("点击标题栏>> Find logo");
			}else if(arg0==imageView3){
				MyBroadcastReceiver.sendBroad_MSG_HAL("点击标题栏>> Share logo");
			}
		}
	};

}
