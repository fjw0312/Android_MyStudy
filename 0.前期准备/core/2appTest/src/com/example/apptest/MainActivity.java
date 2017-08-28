package com.example.apptest;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 获取 工程目录文件
 * */
public class MainActivity extends Activity {

	TextView textview;
	Button button;
	
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button){
				Toast.makeText(MainActivity.this, "点击！", Toast.LENGTH_LONG).show();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textview = (TextView)findViewById(R.id.textView);
		button = (Button)findViewById(R.id.button);
		
		button.setOnClickListener(l);
		
		//获取系统文件  路径
		String system_Path = Environment.getRootDirectory().getAbsolutePath(); // /system
		String data_Path = Environment.getDataDirectory().getAbsolutePath(); // /data
		String cache_Path = Environment.getDownloadCacheDirectory().getAbsolutePath();// /cache
		String sdcard_Path = Environment.getExternalStorageDirectory().getAbsolutePath(); // /storage/emulated/0/...
	//	String sdcard_Path1 = Environment.getExternalStorageDirectory().getPath(); // /storage/emulated/0
//		String outSdcardPath = System.getenv("SECONDARY_STORAGE");  //外置 sdcard 没有外置sdcard的系统会报错

		//获取app 相关路径
		String app_cache_Path = this.getCacheDir().getAbsolutePath(); //获取app的cache目录
		String app_sdcardCache_Path = this.getExternalCacheDir().getAbsolutePath(); //获取app的sdcard下cache目录
		String app_files_Path = this.getFilesDir().getAbsolutePath(); //获取app files目录
		String app_package_Path = this.getPackageName();  //获取app所在包 目录     com.example.apptest
		String app_apk_Path = this.getPackageCodePath();  //获取app所对应apk 目录
		
		
		Log.e("system_Path>", system_Path);
		Log.e("data_Path>", data_Path);
		Log.e("cache_Path>", cache_Path);
		Log.e("sdcard_Path>", sdcard_Path); 
//		Log.e("outSdcardPath>", outSdcardPath);
		Log.e("app_cache_Path>", app_cache_Path);
		Log.e("app_sdcardCache_Path>", app_sdcardCache_Path);
		Log.e("app_files_Path>", app_files_Path);
		Log.e("app_package_Path>", app_package_Path);
		Log.e("app_apk_Path>", app_apk_Path);
		
		//获取项目工程目录文件
		try {
		//1.获取asset目录下文件（可以新建子目录、文件大小不能超过1M）apk打包不会被压缩  不会在R文件上映射id  只能读
		AssetManager am = this.getResources().getAssets();
	    InputStream in = am.open("ui/Up.png");
			
		//2.获取res目录下文件（文件没限定大小） apk打包会被压缩优化 但不会被编译成二进制文件  会在R文件上映射id  可读写
	    //res/raw
	    InputStream in1 = this.getResources().openRawResource(R.raw.cc);
	    //res/drawable 
	    BitmapDrawable a = (BitmapDrawable)this.getResources().getDrawable(R.drawable.ic_launcher);
	    Bitmap bitmap = a.getBitmap();
	    //res/xml
	    XmlResourceParser xmlResourceParser = this.getResources().getXml(R.xml.test);
	    
	    //3.获取src目录下文件
	    InputStream inn = getClass().getClassLoader().getResourceAsStream("xxx.xml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//=========获取工程项目资源文件==========	
		//方式一：获取sdcard中图片文件     内置sdcard   外置sdcard

		//方式二：获取系统路劲下图片文件
		//方式三：获取软件项目下src目录下图片文件
		//方式四：获取软件项目下res目录下图片文件
		//方式五：获取软件项目下assets目录下图片文件

	}
}
