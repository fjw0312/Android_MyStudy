package com.example.c015_sharedpreferencetest;

import android.os.Bundle;
import android.app.Activity;

import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


//sharePrerence
public class MainActivity extends Activity {

	private Button saveData;
	private Button restoreData;
	//实例化监听器
	private OnClickListener l = new OnClickListener() {

		@Override 
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==saveData){
				//使用shareperferences 保存永久数据    //默认数据保存在/data/data/包名/shared_prefs/
				SharedPreferences.Editor editor = getSharedPreferences("data",  
						MODE_PRIVATE).edit();
				editor.putString("name", "fang");
				editor.putInt("age", 25);
				editor.putBoolean("married", false);
				editor.commit();
			}else if(arg0==restoreData){
				SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
				String name = pref.getString("name", "");
				int age = pref.getInt("age", 0);
				boolean married = pref.getBoolean("married", false);
				String content = "姓名："+name+" 年龄："+String.valueOf(age)+" 婚否："+String.valueOf(married);
				Toast.makeText(MainActivity.this, content, Toast.LENGTH_LONG).show();
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		//获取控件
		saveData = (Button)findViewById(R.id.save_data);
		restoreData = (Button)findViewById(R.id.restore_data);
		
		//设置监听器
		saveData.setOnClickListener(l);
		restoreData.setOnClickListener(l);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
