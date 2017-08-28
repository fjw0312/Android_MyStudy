package com.example.Animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class threeActivity extends Activity {

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

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.three_main);
		
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
		
		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub      
			if(arg0==button01){   //透明度
				AlphaAnimation alphanimation = new AlphaAnimation(1.0f, 0.0f);
				alphanimation.setDuration(2000);
			//	textView01.setAnimation(alphanimation);
				textView01.startAnimation(alphanimation);
			}else if(arg0==button02){   //拉伸
				ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 3.0f, 1.0f, 0.5f);
				scaleAnimation.setDuration(2000);
				textView01.startAnimation(scaleAnimation);
			}else if(arg0==button03){  //位移
				TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 200, 200);
				translateAnimation.setDuration(2000);
				textView01.startAnimation(translateAnimation);
			}else if(arg0==button04){  //旋转
				RotateAnimation rotateAnimation = new RotateAnimation(0, 360);
				rotateAnimation.setDuration(2000);
				textView01.startAnimation(rotateAnimation);
			}else if(arg0==button05){ //组合
				AlphaAnimation alphanimation = new AlphaAnimation(1.0f, 0.0f);
				alphanimation.setDuration(2000);
				ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 3.0f, 1.0f, 0.5f);
				scaleAnimation.setDuration(2000);
				AnimationSet animationSet = new AnimationSet(false);
				animationSet.addAnimation(alphanimation);
				animationSet.addAnimation(scaleAnimation);
				textView01.startAnimation(animationSet);
			}else if(arg0==button06){  //帧动画 
				Drawable drawble1 = getResources().getDrawable(R.drawable.p102);
				Drawable drawble2 = getResources().getDrawable(R.drawable.p103);
				Drawable drawble3 = getResources().getDrawable(R.drawable.p104);
				AnimationDrawable animationDraw = new AnimationDrawable();
				animationDraw.addFrame(drawble1, 800);
				animationDraw.addFrame(drawble2, 800);
				animationDraw.addFrame(drawble3, 800);
				animationDraw.setOneShot(false);
				textView01.setBackgroundDrawable(animationDraw);
				animationDraw.start();
			}else if(arg0==button07){  //属性 动画
				//方式 一：
				ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(textView01, "alpha", 0f, 1f);
			    alphaAnimation.setDuration(2000);
			    alphaAnimation.setRepeatCount(0);
			    alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);
			    alphaAnimation.setStartDelay(200);
			    alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
			    alphaAnimation.start();
				
			    //方式 二：
			    ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageview, "scaleX", 1f, 1.5f);
			    scaleXAnimator.setDuration(1000);
			    scaleXAnimator.setRepeatCount(1);
			    scaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);
			    scaleXAnimator.start();

			    ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageview, "scaleY", 1f, 1.5f);
			    scaleYAnimator.setDuration(1000);
			    scaleYAnimator.setRepeatCount(1);
			    scaleYAnimator.setRepeatMode(ValueAnimator.REVERSE);

			    AnimatorSet animatorSet = new AnimatorSet();
			    animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
			    animatorSet.start();
			    //方式 三
			    /*
			    PropertyValuesHolder scaleXValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.5f);
			    PropertyValuesHolder scaleYValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.5f);
			    ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(imageView, scaleXValuesHolder, scaleYValuesHolder);
			        objectAnimator.setDuration(500);
			        objectAnimator.setRepeatCount(1);
			        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
			        objectAnimator.start();
			        */
			    //方式 四
			  //  ViewPropertyAnimator viewPropertyAnimator=imageView.animate();
			  //  viewPropertyAnimator.scaleXBy(1.0f).scaleX(1.5f).scaleYBy(1.0f).scaleY(1.5f).setDuration(500).start();
			}else if(arg0==button08){
				Intent intent = new Intent(threeActivity.this, MainActivity.class);
				startActivity(intent);
			}
		}
	};
}
