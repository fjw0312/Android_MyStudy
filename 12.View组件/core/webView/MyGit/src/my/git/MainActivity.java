package my.git;


import my.customView.MyNavigationBar;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


public class MainActivity extends Activity {

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		webView.clearHistory();
		webView.clearCache(true);
		webView.destroy();
		super.onDestroy();
	}

	public WebView webView;
	MyNavigationBar myNavigationBar;
	LinearLayout progressBarPage;
	ProgressBar progressBar;
	
	public final static String myGit_strUrl = "https://github.com/fjw0312";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		//隐藏 标题栏
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		setContentView(R.layout.activity_main);
		
		//获取 导航栏
		myNavigationBar = (MyNavigationBar)findViewById(R.id.MyNavigationBar);
		myNavigationBar.setActivity(this);
		//获取  缓冲条
		progressBarPage = (LinearLayout)findViewById(R.id.progressBar_page);
		progressBar = (ProgressBar)progressBarPage.findViewById(R.id.progressBar);
		
		
		webView = (WebView)findViewById(R.id.webView_id);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.canGoBack(); //设置 可以返回页面
		webView.setWebViewClient(new WebViewClient(){
			@Override   //开始加载
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
				progressBar.setVisibility(View.VISIBLE);
				
			}

			@Override  //加载结束
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				progressBar.setVisibility(View.GONE);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) { //可以获得 页面点击链接url
				view.loadUrl(url);
				// TODO Auto-generated method stub
				//return super.shouldOverrideUrlLoading(view, url);  

				return true;
			}			
		});
		
		//打开web 
		webView.loadUrl(myGit_strUrl);	

	}
	
	
	@Override  //有浏览器 webView 点击返回 会 activity.finish  故 特别处理一下
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) { 
			webView.goBack();
		      return true;
		  }
		return super.onKeyDown(keyCode, event);
	}
}
