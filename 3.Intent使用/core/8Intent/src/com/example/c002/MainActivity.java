package com.example.c002;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/***
 * Intent  Ŀ��/����  
 * @author fjw 2017 5 22
 *   ��������У�Action Data Category Type compent Extra
 */

// test Intent  ��ʽIntent�÷�  ����������

public class MainActivity extends Activity {
	
	@Override//ͼƬѡ���� ���ѡ��󷵻ص� ͼƬѡ��Uri
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Uri uri = data.getData();
		Log.e("Uri",uri.toString());
		try {
		ContentResolver cr = this.getContentResolver();
		Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
		imageView.setImageBitmap(bitmap);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Fields
	TextView textview1;
	Button button1;
	Button button2; 
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;
	Button button8;
	Button button9;
	ImageView imageView;

	
	//ʵ����������      Intent��5�ֻ����÷�
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button1){        //�˳�����
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.setAction("android.intent.action.MAIN");
				intent.addCategory("android.intent.category.HOME");
				startActivity(intent); 
			}else if(arg0==button2){           //��תҳ��
				Intent intent = new Intent(MainActivity.this, Page2Activity.class);
				startActivity(intent);
			}else if(arg0==button3){             //����ͼƬ�鿴��
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(new File("/mnt/sdcard/fjw_photo/8.jpg")), "image/*");
				startActivity(intent);			
			}else if(arg0==button4){             //���������---����Ҫѡ���������
				Intent intent = new Intent(Intent.ACTION_VIEW); 
				intent.setData(Uri.parse("http://www.baidu.com"));	
				intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");//���ָ��Ĭ�� �����
				startActivity(intent); 
			}else if(arg0==button5){              //��Ӳ���绰---- �����һ������Ҫ�������
				   Intent intent = new Intent(Intent.ACTION_DIAL);
				   intent.setData(Uri.parse("tel:13590156707")); 
				   startActivity(intent);  
			}else if(arg0==button6){              //ֱ�Ӳ���绰   �ǵÿ�����绰Ȩ��
				   Intent intent = new Intent(Intent.ACTION_CALL);
				   intent.setData(Uri.parse("tel:13590156707"));
				   startActivity(intent);  
			} else if(arg0==button7){              //�����ʼ�---��Ҫѡ������app
				   Intent emailIntent = new Intent(Intent.ACTION_SEND) ;// ʵ���� 
				   emailIntent.setType("plain/text") ;// ��������
				   emailIntent.putExtra(Intent.EXTRA_EMAIL, "fjw0312@163.com") ;	// �����׼���					
				   emailIntent.putExtra(Intent.EXTRA_SUBJECT, "fangjiongwen-TEST") ;// ��������				
				   emailIntent.putExtra(Intent.EXTRA_TEXT, "fjw TEST") ;	// �������� 
				   //emailIntent.setClassName("com.android.email", "com.android.email.activity.MessageCompose");
				   startActivity(emailIntent);   //�����ʼ�	
			} else if(arg0==button8){              //ͼƬ����ѡ����
				Intent intent = new Intent();						// ����Intent
				intent.setAction(Intent.ACTION_GET_CONTENT) ;		// ָ��Action
				intent.setType("image/*");							// �����������
				startActivityForResult(Intent.createChooser(intent,"ѡ��ͼƬ�������"), 1);	//Ӧ��ѡ����
			}else if(arg0==button9){              //����Ӧ��ѡ��
				Intent intent = new Intent(Intent.ACTION_SEND);						// ����Intent
				//	intent.setPackage("com.tencent.mm");        //ָ��΢�� Ӧ�ð�
				//	intent.setPackage("com.tencent.mobileqq");  //ָ��qq  Ӧ�ð�
				//�����ı�
		//		intent.setType("plain/text") ;// ��������
		//		intent.putExtra(Intent.EXTRA_TITLE, "NB XXXX") ;	        //���ñ���
		//		intent.putExtra(Intent.EXTRA_SUBJECT, "fangjiongwen-TEST") ;// ��������
		//		intent.putExtra(Intent.EXTRA_TEXT, "fjw TEST") ;	        // �������� 
				//���� ͼƬ
		//		intent.setType("image/*");						    	// �����������
		//		intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File("/mnt/sdcard/MagazineUnlock/31757.jpg")));   //����ͼƬ �ļ� ��������ʽ
			//	intent.putExtra(Intent.EXTRA_TEXT, "fjw TEST") ;	 // �������� 
				//���� apk
		//		intent.setType("application/*") ;// ��������
		//		intent.putExtra(Intent.EXTRA_SUBJECT, "fangjiongwen-TEST") ;// ��������
		//		intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File("/mnt/sdcard/fjw_work/10SQLite.apk"))) ; // �������� 		
		//		intent.setPackage("com.tencent.mm");        //ָ��΢�� Ӧ�ð� 
				
				//���� ��̬ͼ
				intent.setType("image/*");						    	// �����������
				intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File("/mnt/sdcard/fjw_work/99.gif")));   //����ͼƬ �ļ� ��������ʽ
				intent.putExtra(Intent.EXTRA_TEXT, "fjw TEST") ;	 // �������� 	
				intent.setPackage("com.tencent.mm");        //ָ��΢�� Ӧ�ð�
				startActivity(Intent.createChooser(intent,"������"));	//Ӧ��ѡ����
			}  
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ȡ�ؼ�
		textview1 = (TextView)findViewById(R.id.textView1);
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		button4 = (Button)findViewById(R.id.button4);
		button5 = (Button)findViewById(R.id.button5);
		button6 = (Button)findViewById(R.id.button6);
		button7 = (Button)findViewById(R.id.button7);
		button8 = (Button)findViewById(R.id.button8);
		button9 = (Button)findViewById(R.id.button9);
		imageView = (ImageView)findViewById(R.id.imageView01);
		
		//���ü�����
		button1.setOnClickListener(l);
		button2.setOnClickListener(l);
		button3.setOnClickListener(l);
		button4.setOnClickListener(l);
		button5.setOnClickListener(l);
		button6.setOnClickListener(l);
		button7.setOnClickListener(l);
		button8.setOnClickListener(l);
		button9.setOnClickListener(l);
	}
	
	
	//ͨ����ȡ���������õ�����app
		private void doStartApplicationWithPackageName(String packagename) {  
			  
		    // ͨ��������ȡ��APP��ϸ��Ϣ������Activities��services��versioncode��name�ȵ�  
		    PackageInfo packageinfo = null;  
		    try {  
		        packageinfo = MainActivity.this.getPackageManager().getPackageInfo(packagename, 0);  
		    } catch (NameNotFoundException e) {  
		        e.printStackTrace();  
		    }  
		    if (packageinfo == null) {  
		        return;  
		    }  
		  
		    // ����һ�����ΪCATEGORY_LAUNCHER�ĸð�����Intent  
		    Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);  
		    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
		    resolveIntent.setPackage(packageinfo.packageName);  
		  
		    // ͨ��getPackageManager()��queryIntentActivities��������  
		    List<ResolveInfo> resolveinfoList = MainActivity.this.getPackageManager()  
		            .queryIntentActivities(resolveIntent, 0);  
		  
		    ResolveInfo resolveinfo = resolveinfoList.iterator().next();  
		    if (resolveinfo != null) {  
		        // packagename = ����packname  
		        String packageName = resolveinfo.activityInfo.packageName;  
		        // �����������Ҫ�ҵĸ�APP��LAUNCHER��Activity[��֯��ʽ��packagename.mainActivityname]  
		        String className = resolveinfo.activityInfo.name;  
		        // LAUNCHER Intent  
		        Intent intent = new Intent(Intent.ACTION_MAIN);  
		        intent.addCategory(Intent.CATEGORY_LAUNCHER);  
		  
		        // ����ComponentName����1:packagename����2:MainActivity·��  
		        ComponentName cn = new ComponentName(packageName, className);  
		  
		        intent.setComponent(cn);  
		        MainActivity.this.startActivity(intent);  
		    }  
		} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
