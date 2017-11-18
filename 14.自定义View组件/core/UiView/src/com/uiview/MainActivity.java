package com.uiview;

import java.util.ArrayList;
import java.util.List;

import customMyView.BarGraphView;
import customMyView.HorizontalBarView;
import customMyView.RingPerBarView;
import customMyView.RingPerView;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	BarGraphView barGraphView;
	RingPerView ringPerView;
	RingPerBarView ringPerBarView;
	HorizontalBarView horizontalView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);  
		
		barGraphView = (BarGraphView)findViewById(R.id.myView02);
		ringPerView = (RingPerView)findViewById(R.id.myView01);
//		ringPerBarView = (RingPerBarView)findViewById(R.id.myView06);
		horizontalView = (HorizontalBarView)findViewById(R.id.myView07);
		List<String> lst = new ArrayList<String>(); 
		lst.add("34-56-47-38");
		lst.add("44-46-32-56");   
		lst.add("38-52-43-38"); 
		lst.add("54-36-36-48"); 
	//	barGraphView.setValue_lst(lst); 
	//	barGraphView.doInvalidate();
		
		horizontalView.setValue_lst(lst);		
		horizontalView.postInvalidate();
		
		List<Float> lst_f = new ArrayList<Float>();
		lst_f.add(50f);
		lst_f.add(25f);
		lst_f.add(25f);
	//	lst_f.add(25f);
	//	lst_f.add(25f);
		ringPerView.setValue_lst(lst_f);
		ringPerView.invalidate();
		List<Float> lst_f2 = new ArrayList<Float>();
		lst_f2.add(54f);
		lst_f2.add(65f);
		lst_f2.add(75f);
		lst_f2.add(68f);
		lst_f2.add(59f);
	//	ringPerBarView.setValue_lst(lst_f2);
	//	ringPerBarView.invalidate();
	}
}
