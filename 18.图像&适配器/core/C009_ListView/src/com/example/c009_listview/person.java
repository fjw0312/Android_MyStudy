package com.example.c009_listview;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;


//»ÀŒÔ¿‡
public class person {
	
	//Fields
	private String name; 
	private int ImageId;


	public person(String name, int id) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.ImageId = id;
	}
	public String getName(){
		return name;
	}
	public int getImageId(){
		return ImageId;
	}

}
