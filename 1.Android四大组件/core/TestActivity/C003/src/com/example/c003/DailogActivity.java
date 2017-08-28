package com.example.c003;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

/**
 * activity  生命周期&Dialog
 * @author fjw 2016 5 26 
 *
 */

public class DailogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//请求应用无标题
		setContentView(R.layout.dialog_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
