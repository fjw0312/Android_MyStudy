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
 * Intent  目的/动作  
 * @author fjw 2017 5 22
 *   相关属性有：Action Data Category Type compent Extra
 */

// test Intent  隐式Intent用法  调用浏览器活动

public class MainActivity extends Activity {
	
	@Override//图片选择器 点击选择后返回的 图片选择Uri
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

	
	//实例化将听器      Intent的5种基本用法
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button1){        //退出桌面
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.setAction("android.intent.action.MAIN");
				intent.addCategory("android.intent.category.HOME");
				startActivity(intent); 
			}else if(arg0==button2){           //跳转页面
				Intent intent = new Intent(MainActivity.this, Page2Activity.class);
				startActivity(intent);
			}else if(arg0==button3){             //进入图片查看器
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(new File("/mnt/sdcard/fjw_photo/8.jpg")), "image/*");
				startActivity(intent);			
			}else if(arg0==button4){             //进入浏览器---（需要选择浏览器）
				Intent intent = new Intent(Intent.ACTION_VIEW); 
				intent.setData(Uri.parse("http://www.baidu.com"));	
				intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");//添加指定默认 浏览器
				startActivity(intent); 
			}else if(arg0==button5){              //间接拨打电话---- 到最后一步，需要按拨打键
				   Intent intent = new Intent(Intent.ACTION_DIAL);
				   intent.setData(Uri.parse("tel:13590156707")); 
				   startActivity(intent);  
			}else if(arg0==button6){              //直接拨打电话   记得开拨打电话权限
				   Intent intent = new Intent(Intent.ACTION_CALL);
				   intent.setData(Uri.parse("tel:13590156707"));
				   startActivity(intent);  
			} else if(arg0==button7){              //发送邮件---需要选择邮箱app
				   Intent emailIntent = new Intent(Intent.ACTION_SEND) ;// 实例化 
				   emailIntent.setType("plain/text") ;// 设置类型
				   emailIntent.putExtra(Intent.EXTRA_EMAIL, "fjw0312@163.com") ;	// 设置首件人					
				   emailIntent.putExtra(Intent.EXTRA_SUBJECT, "fangjiongwen-TEST") ;// 设置主题				
				   emailIntent.putExtra(Intent.EXTRA_TEXT, "fjw TEST") ;	// 设置内容 
				   //emailIntent.setClassName("com.android.email", "com.android.email.activity.MessageCompose");
				   startActivity(emailIntent);   //发送邮件	
			} else if(arg0==button8){              //图片播放选择器
				Intent intent = new Intent();						// 定义Intent
				intent.setAction(Intent.ACTION_GET_CONTENT) ;		// 指定Action
				intent.setType("image/*");							// 定义操作类型
				startActivityForResult(Intent.createChooser(intent,"选择图片浏览工具"), 1);	//应用选择器
			}else if(arg0==button9){              //分享应用选择
				Intent intent = new Intent(Intent.ACTION_SEND);						// 定义Intent
				//	intent.setPackage("com.tencent.mm");        //指定微信 应用包
				//	intent.setPackage("com.tencent.mobileqq");  //指定qq  应用包
				//分享文本
		//		intent.setType("plain/text") ;// 设置类型
		//		intent.putExtra(Intent.EXTRA_TITLE, "NB XXXX") ;	        //设置标题
		//		intent.putExtra(Intent.EXTRA_SUBJECT, "fangjiongwen-TEST") ;// 设置主题
		//		intent.putExtra(Intent.EXTRA_TEXT, "fjw TEST") ;	        // 设置内容 
				//分享 图片
		//		intent.setType("image/*");						    	// 定义操作类型
		//		intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File("/mnt/sdcard/MagazineUnlock/31757.jpg")));   //传输图片 文件 采用流方式
			//	intent.putExtra(Intent.EXTRA_TEXT, "fjw TEST") ;	 // 设置内容 
				//分享 apk
		//		intent.setType("application/*") ;// 设置类型
		//		intent.putExtra(Intent.EXTRA_SUBJECT, "fangjiongwen-TEST") ;// 设置主题
		//		intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File("/mnt/sdcard/fjw_work/10SQLite.apk"))) ; // 设置内容 		
		//		intent.setPackage("com.tencent.mm");        //指定微信 应用包 
				
				//分享 动态图
				intent.setType("image/*");						    	// 定义操作类型
				intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File("/mnt/sdcard/fjw_work/99.gif")));   //传输图片 文件 采用流方式
				intent.putExtra(Intent.EXTRA_TEXT, "fjw TEST") ;	 // 设置内容 	
				intent.setPackage("com.tencent.mm");        //指定微信 应用包
				startActivity(Intent.createChooser(intent,"分享工具"));	//应用选择器
			}  
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//获取控件
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
		
		//设置监听器
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
	
	
	//通过获取包名，调用第三方app
		private void doStartApplicationWithPackageName(String packagename) {  
			  
		    // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等  
		    PackageInfo packageinfo = null;  
		    try {  
		        packageinfo = MainActivity.this.getPackageManager().getPackageInfo(packagename, 0);  
		    } catch (NameNotFoundException e) {  
		        e.printStackTrace();  
		    }  
		    if (packageinfo == null) {  
		        return;  
		    }  
		  
		    // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent  
		    Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);  
		    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
		    resolveIntent.setPackage(packageinfo.packageName);  
		  
		    // 通过getPackageManager()的queryIntentActivities方法遍历  
		    List<ResolveInfo> resolveinfoList = MainActivity.this.getPackageManager()  
		            .queryIntentActivities(resolveIntent, 0);  
		  
		    ResolveInfo resolveinfo = resolveinfoList.iterator().next();  
		    if (resolveinfo != null) {  
		        // packagename = 参数packname  
		        String packageName = resolveinfo.activityInfo.packageName;  
		        // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]  
		        String className = resolveinfo.activityInfo.name;  
		        // LAUNCHER Intent  
		        Intent intent = new Intent(Intent.ACTION_MAIN);  
		        intent.addCategory(Intent.CATEGORY_LAUNCHER);  
		  
		        // 设置ComponentName参数1:packagename参数2:MainActivity路径  
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
