package com.example.c011_fragmenttest;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button button;
	
	//实例化监听器
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button){
				button.setText("进入");
				AnotherRightFragment frag = new AnotherRightFragment();
				FragmentManager manager = getFragmentManager();
				FragmentTransaction transaction = manager.beginTransaction();
				transaction.replace(R.id.right_layout1, frag);
				transaction.addToBackStack(null);  //将碎片放入栈中
				transaction.commit();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		//获取控件
//		View frag = (View)findViewById(R.id.left_fragment);
//		button = (Button) frag.findViewById(R.id.button1);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(l);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu); 
		return true;
	}

}
