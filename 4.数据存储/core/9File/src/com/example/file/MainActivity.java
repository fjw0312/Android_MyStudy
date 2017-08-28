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
 * File 文件存储操作
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
				// 是否读取到了末尾
		        if ((line = reader.readLine()) == null) break;
			}
		}catch(Exception e){
			
		}
	}
}
