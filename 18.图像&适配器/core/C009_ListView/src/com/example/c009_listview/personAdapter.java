package com.example.c009_listview;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class personAdapter extends ArrayAdapter<person>{


	private int item_layout_id;
	
	public personAdapter(Context context,int id,ArrayList<person> list) {
		// TODO Auto-generated constructor stub
		super(context,id,list);
		item_layout_id = id;
//		lst = list;
	}
	
//	public int getCount(){
//		return lst.size();
//	}
	
//	public person getItem(int arg0){
//		return lst.get(arg0);
//	}
	
//	public long getIemID(int arg0){
//		return arg0;
//	}
	
	//����ÿ�λ���view ��ʹ�������������䣬Ϊ��������ʿ������ж�view arg1�Ƿ�Ϊnull
	public View getView(int arg0, View arg1, ViewGroup arg2){
		person p = getItem(arg0);
		View view;
		ViewHolder viewHolder;
		if(arg1==null){
//			view = LayoutInflater.from(getContext()).inflate(item_layout_id, null); 
			view = View.inflate(getContext(), item_layout_id, null);//����д��һ��
			
			//���ViewHolder
			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView)view.findViewById(R.id.imageview);
			viewHolder.text = (TextView)view.findViewById(R.id.name);
			view.setTag(viewHolder);
		}else{
			view = arg1;
			viewHolder = (ViewHolder)view.getTag();  //���»�ȡ ViewHolder
		}


		
		viewHolder.image.setImageResource(p.getImageId());
		viewHolder.text.setText(p.getName());
		return view;
	}
	
	//����һ�� ����������
	class ViewHolder{
		ImageView image;
		TextView text;
	}
	
}
