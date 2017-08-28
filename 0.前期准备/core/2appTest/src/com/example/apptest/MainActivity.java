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
 * ��ȡ ����Ŀ¼�ļ�
 * */
public class MainActivity extends Activity {

	TextView textview;
	Button button;
	
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button){
				Toast.makeText(MainActivity.this, "�����", Toast.LENGTH_LONG).show();
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
		
		//��ȡϵͳ�ļ�  ·��
		String system_Path = Environment.getRootDirectory().getAbsolutePath(); // /system
		String data_Path = Environment.getDataDirectory().getAbsolutePath(); // /data
		String cache_Path = Environment.getDownloadCacheDirectory().getAbsolutePath();// /cache
		String sdcard_Path = Environment.getExternalStorageDirectory().getAbsolutePath(); // /storage/emulated/0/...
	//	String sdcard_Path1 = Environment.getExternalStorageDirectory().getPath(); // /storage/emulated/0
//		String outSdcardPath = System.getenv("SECONDARY_STORAGE");  //���� sdcard û������sdcard��ϵͳ�ᱨ��

		//��ȡapp ���·��
		String app_cache_Path = this.getCacheDir().getAbsolutePath(); //��ȡapp��cacheĿ¼
		String app_sdcardCache_Path = this.getExternalCacheDir().getAbsolutePath(); //��ȡapp��sdcard��cacheĿ¼
		String app_files_Path = this.getFilesDir().getAbsolutePath(); //��ȡapp filesĿ¼
		String app_package_Path = this.getPackageName();  //��ȡapp���ڰ� Ŀ¼     com.example.apptest
		String app_apk_Path = this.getPackageCodePath();  //��ȡapp����Ӧapk Ŀ¼
		
		
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
		
		//��ȡ��Ŀ����Ŀ¼�ļ�
		try {
		//1.��ȡassetĿ¼���ļ��������½���Ŀ¼���ļ���С���ܳ���1M��apk������ᱻѹ��  ������R�ļ���ӳ��id  ֻ�ܶ�
		AssetManager am = this.getResources().getAssets();
	    InputStream in = am.open("ui/Up.png");
			
		//2.��ȡresĿ¼���ļ����ļ�û�޶���С�� apk����ᱻѹ���Ż� �����ᱻ����ɶ������ļ�  ����R�ļ���ӳ��id  �ɶ�д
	    //res/raw
	    InputStream in1 = this.getResources().openRawResource(R.raw.cc);
	    //res/drawable 
	    BitmapDrawable a = (BitmapDrawable)this.getResources().getDrawable(R.drawable.ic_launcher);
	    Bitmap bitmap = a.getBitmap();
	    //res/xml
	    XmlResourceParser xmlResourceParser = this.getResources().getXml(R.xml.test);
	    
	    //3.��ȡsrcĿ¼���ļ�
	    InputStream inn = getClass().getClassLoader().getResourceAsStream("xxx.xml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//=========��ȡ������Ŀ��Դ�ļ�==========	
		//��ʽһ����ȡsdcard��ͼƬ�ļ�     ����sdcard   ����sdcard

		//��ʽ������ȡϵͳ·����ͼƬ�ļ�
		//��ʽ������ȡ�����Ŀ��srcĿ¼��ͼƬ�ļ�
		//��ʽ�ģ���ȡ�����Ŀ��resĿ¼��ͼƬ�ļ�
		//��ʽ�壺��ȡ�����Ŀ��assetsĿ¼��ͼƬ�ļ�

	}
}
