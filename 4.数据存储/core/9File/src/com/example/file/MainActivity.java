package com.example.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;


/**
 * File �ļ��洢����
 * 
 * */
public class MainActivity extends Activity {
	
	BufferedReader reader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		try{
			reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(Environment.getExternalStorageDirectory().getPath() + "pagelist"), 
							"gb2312"));
		    

			for (int i = 0; i < 1024; i++)
			{
				String line = "";
				// �Ƿ��ȡ����ĩβ
		        if ((line = reader.readLine()) == null) break;
			}
		}catch(Exception e){
			
		}
	}
}
