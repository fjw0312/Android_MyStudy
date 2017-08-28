package com.example.c010_chat;

import java.util.List;

import android.content.Context;
import android.graphics.YuvImage;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;



//������Ϣ ������
public class MsgAdapter extends ArrayAdapter<Msg>{
	//Fields
	private int resourceId;
	
	public MsgAdapter(Context context, int id, List<Msg> lst) {
		// TODO Auto-generated constructor stub
		super(context,id,lst);
		resourceId = id;
	}
	
	//���䷵��view
	public View getView(int position, View convertView,ViewGroup parent){
		View view;
		ViewHolder viewHolder;
		if(convertView == null){
			view = View.inflate(getContext(), resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.leftLayout = (LinearLayout)view.findViewById(R.id.letf_layout);
			viewHolder.rightLayout =(LinearLayout)view.findViewById(R.id.right_layout);
			viewHolder.leftMsg = (TextView)view.findViewById(R.id.letf_msg);
			viewHolder.rightMsg =(TextView)view.findViewById(R.id.right_msg);
			view.setTag(viewHolder);
			
		}else{
			view = convertView;
			viewHolder = (ViewHolder)view.getTag();
		}
		
		
		Msg msg = getItem(position);
		//�ж�һ�� ��Ϣ����
		if(msg.getType() == Msg.TYPE_RECEIVED){
			//���յ���Ϣ ��ʾ��ߵĲ���
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.rightLayout.setVisibility(View.GONE);
			viewHolder.leftMsg.setText(msg.getContent());
		}else if(msg.getType() == Msg.TYPE_SENT){
			//������Ϣ ��ʾ�ұߵĲ���
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.leftLayout.setVisibility(View.GONE);
			viewHolder.rightMsg.setText(msg.getContent());
		}
		
		return view;
	}

	//����һ��view �Ĳ���������
	private class ViewHolder{
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		TextView leftMsg;
		TextView rightMsg;
	}
}
