package com.example.c002;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setTitle("查看图片");						// 设置标题
		
		Intent intent = this.getIntent();
		//Bitmap bm = intent.getData();
		
		ImageView img = new ImageView(this);			// 实例化ImageView
		//img.setImageBitmap(bm);
		img.setImageResource(R.drawable.gg0);		// 定义显示图片
		super.setContentView(img);						// 设置组件
	}

}
