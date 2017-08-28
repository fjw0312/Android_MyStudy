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
	//ʵ����������
	private OnClickListener l = new OnClickListener() {

		@Override 
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==saveData){
				//ʹ��shareperferences ������������    //Ĭ�����ݱ�����/data/data/����/shared_prefs/
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
				String content = "������"+name+" ���䣺"+String.valueOf(age)+" ���"+String.valueOf(married);
				Toast.makeText(MainActivity.this, content, Toast.LENGTH_LONG).show();
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		//��ȡ�ؼ�
		saveData = (Button)findViewById(R.id.save_data);
		restoreData = (Button)findViewById(R.id.restore_data);
		
		//���ü�����
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
