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
		super.setTitle("�鿴ͼƬ");						// ���ñ���
		
		Intent intent = this.getIntent();
		//Bitmap bm = intent.getData();
		
		ImageView img = new ImageView(this);			// ʵ����ImageView
		//img.setImageBitmap(bm);
		img.setImageResource(R.drawable.gg0);		// ������ʾͼƬ
		super.setContentView(img);						// �������
	}

}
