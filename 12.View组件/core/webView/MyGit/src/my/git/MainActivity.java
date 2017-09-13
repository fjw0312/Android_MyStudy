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
		//���� ������
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		setContentView(R.layout.activity_main);
		
		//��ȡ ������
		myNavigationBar = (MyNavigationBar)findViewById(R.id.MyNavigationBar);
		myNavigationBar.setActivity(this);
		//��ȡ  ������
		progressBarPage = (LinearLayout)findViewById(R.id.progressBar_page);
		progressBar = (ProgressBar)progressBarPage.findViewById(R.id.progressBar);
		
		
		webView = (WebView)findViewById(R.id.webView_id);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.canGoBack(); //���� ���Է���ҳ��
		webView.setWebViewClient(new WebViewClient(){
			@Override   //��ʼ����
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
				progressBar.setVisibility(View.VISIBLE);
				
			}

			@Override  //���ؽ���
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				progressBar.setVisibility(View.GONE);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) { //���Ի�� ҳ��������url
				view.loadUrl(url);
				// TODO Auto-generated method stub
				//return super.shouldOverrideUrlLoading(view, url);  

				return true;
			}			
		});
		
		//��web 
		webView.loadUrl(myGit_strUrl);	

	}
	
	
	@Override  //������� webView ������� �� activity.finish  �� �ر���һ��
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) { 
			webView.goBack();
		      return true;
		  }
		return super.onKeyDown(keyCode, event);
	}
}
