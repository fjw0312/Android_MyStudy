package com.example.c011_fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RightFragment extends Fragment{

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.right_fragment, container, false);
		return view;
	} 

	public RightFragment() {
		// TODO Auto-generated constructor stub
	}
	

}
