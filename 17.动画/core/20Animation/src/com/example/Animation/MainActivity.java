package com.example.Animation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button button01;
	Button button02;
	Button button03;
	Button button04;
	Button button05;
	Button button06;
	Button button07;
	Button button08;
	TextView textView01;
	ImageView imageview;
	AnimationDrawable animationDrawable;
	
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
		button07 = (Button)findViewById(R.id.button07);
		button08 = (Button)findViewById(R.id.button08);
		textView01 = (TextView)findViewById(R.id.textView01);
		imageview = (ImageView)findViewById(R.id.iamgeView);
		
		button01.setOnClickListener(l);
		button02.setOnClickListener(l);
		button03.setOnClickListener(l);
		button04.setOnClickListener(l);	
		button05.setOnClickListener(l);	
		button06.setOnClickListener(l);	
		button07.setOnClickListener(l);
		button08.setOnClickListener(l);
	}
	private OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub      
			if(arg0==button01){   
				Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.my_anim);
				textView01.startAnimation(animation);
			}else if(arg0==button02){  
				Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_anim);
				textView01.startAnimation(animation);     
			}else if(arg0==button03){  
				Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate_anim);
				textView01.startAnimation(animation); 
			}else if(arg0==button04){  
				Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_anim);
				textView01.startAnimation(animation);
			}else if(arg0==button05){ 
				Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.set_anim);
				textView01.setAnimation(animation); 		
			}else if(arg0==button06){   //注意 AnimationDrawable.start()不能再oncreate()方法内执行   
				imageview.setBackgroundResource(R.drawable.drawable_anim); 
				animationDrawable = (AnimationDrawable)imageview.getBackground();
				animationDrawable.start();  //animationDrawable.stop();
				textView01.setBackgroundResource(R.drawable.drawable_anim);
				AnimationDrawable animationDrawable2 = (AnimationDrawable)textView01.getBackground();
				animationDrawable2.start();  //animationDrawable.stop(); 
			}else if(arg0==button07){  //animationDrawable.start()启动后会一直运行  需要stop才之后能重运行
				animationDrawable.stop();  
			}else if(arg0==button08){
				Intent intent = new Intent(MainActivity.this, NextActivity.class);
				startActivity(intent);
			}
		}
	};
}
