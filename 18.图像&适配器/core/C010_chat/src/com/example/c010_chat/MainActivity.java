package com.example.c010_chat;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	//fields
	private ListView listview;
	private EditText edittext;
	private Button button;
	private MsgAdapter adapter;
	private List<Msg> lst = new ArrayList<Msg>();
	
	//ʵ����������
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) { 
			// TODO Auto-generated method stub
			if(arg0 == button){
				String text = edittext.getText().toString();
				if("".equals(text)==false){
					Msg msg = new Msg(text, Msg.TYPE_SENT);
					lst.add(msg);
					adapter.notifyDataSetChanged(); //�����µ���Ϣʱ ˢ��listview
					listview.setSelection(lst.size()); //��listview ��λ�����һ��
					edittext.setText(""); //���������ַ�
				}
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ʼ������
		initMsg();
		adapter = new MsgAdapter(this,R.layout.msg_item, lst);
		//��ȡlistview 
		listview = (ListView)findViewById(R.id.msg_lst_view);
		listview.setAdapter(adapter);
		//��ȡ�ؼ�
		edittext = (EditText)findViewById(R.id.input);
		button = (Button)findViewById(R.id.send);
		//���ü�����
		button.setOnClickListener(l);
	}
	
	private void initMsg(){
		Msg msg1 = new Msg("Hello fang", Msg.TYPE_RECEIVED);
		lst.add(msg1);
		Msg msg2 = new Msg("Hello what you say", Msg.TYPE_SENT);
		lst.add(msg2);
		Msg msg3 = new Msg("Hello ! Nice to talking to you", Msg.TYPE_RECEIVED);
		lst.add(msg3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
