package com.example.myImage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;


//button  ImageView  ImageButton  ImageSwitcher  Gallery
//前瞻 ViewPager ViewFipper
//注意：关于图片的加载，退出一定要 释放图片资源
//ImageView :主要有4种 设置图片方式  
/**1.xml src=
 * 2.Image..setImageResource();
 * 3.imageView.setImageDrawable(drawable);
 * 4.imageView.setImageBitmap(bm);  Bitmap = BitmapFactory 1.decodeFile 2.decodeResource 3.decodeStream
 * */

//图片按钮  3种方式：  1.button.setBackGround  2.ImageButton set src  3.ImageButton set setBackGround
//ImageButton set src 会有一圈按钮覆盖。 功能基本一样。

//ImageSwitcher 常配合Gallery 使用，与ImageView 基本没啥区别，就是多了可以设置动画。

public class MainActivity extends Activity {

	Button button01;
	Button button02;
	Button button03;
	Button button04;
	Button button05;
	Button button06;
	ImageView imageView;
	ImageButton imageButton01;
	ImageButton imageButton02;
	Button button07;
	
	ImageSwitcher imageSwitcher;
	int[] pictures = {R.drawable.p101,R.drawable.p102,R.drawable.p103,R.drawable.p104};
	int pictures_index = 0;
	
	int i =0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button01 = (Button)findViewById(R.id.button01);
		button02 = (Button)findViewById(R.id.button02);
		button03 = (Button)findViewById(R.id.button03);
		button04 = (Button)findViewById(R.id.button04);
		button05 = (Button)findViewById(R.id.button05);
		button06 = (Button)findViewById(R.id.button06);
		imageView = (ImageView)findViewById(R.id.imagView);
		imageButton01 = (ImageButton)findViewById(R.id.imageButton01);
		imageButton02 = (ImageButton)findViewById(R.id.imageButton02);
		button07 = (Button)findViewById(R.id.button07);
		
		imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
		imageSwitcher.setFactory(factory);
		imageSwitcher.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
//		imageSwitcher.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
	
		
		button01.setOnClickListener(l);
		button02.setOnClickListener(l);
		button03.setOnClickListener(l);
		button04.setOnClickListener(l);
		button05.setOnClickListener(l);
		button06.setOnClickListener(l);
		imageButton01.setOnClickListener(l);
		imageButton02.setOnClickListener(l);
		button07.setOnClickListener(l);
		
	//	button05.setBackground(getResources().getDrawable(R.drawable.base));
	//	button05.setBackgroundResource(R.drawable.base);
	}
	private ViewFactory factory = new ViewFactory() {
		
		@Override
		public View makeView() { 
			// TODO Auto-generated method stub
			ImageView imageView = new ImageView(MainActivity.this);
		//	imageView.setImageResource(pictures[pictures_index]);
			i++;
		//	button06.setText(String.valueOf(i));
			return imageView; 
		}
	};

	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==button01){
				imageView.setImageResource(R.drawable.p103);
				
			//	Drawable drawable = getResources().getDrawable(R.drawable.p103);
			//	imageView.setImageDrawable(drawable);
				
				//BitmapFactory.decodeFile("pathName"); //直接加载文件
				//BitmapFactory.decodeStream(is); //直接加载文件流
			
				//加载资源文件
			//	Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.p103);
			//	imageView.setImageBitmap(bm);
				
			}else if(arg0==button02){
				Drawable drawable = getResources().getDrawable(R.drawable.p103);
				imageView.setImageDrawable(drawable);
			}else if(arg0==button03){
				Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.p103);
				imageView.setImageBitmap(bm);
			//	imageView.setScaleType(ScaleType.CENTER); //图片填充 模式
			}else if(arg0==button04){
				imageSwitcher.setImageResource(pictures[pictures_index++]);
			}else if(arg0==button05){ 
				imageSwitcher.setImageResource(pictures[pictures_index--]);
			}else if(arg0==button06){ 
				imageView.setImageResource(R.drawable.ic_launcher);
			}else if(arg0==imageButton01){
				button06.setText("复位1");
			}else if(arg0==imageButton02){
				button06.setText("复位2");
			}else if(arg0==button07){
				button06.setText("复位3");
			}
		}
	};
}
