package main.mypulltorefresh;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


/***
 * ���� ��ʽ����
 * android-UItra-Pull-To-Refresh  ʹ��Դ��� demo   PtrFrameLayout + MaterialHeader
 *   ��ͷ��ʽ2�� PtrFrameLayout + MaterialHeader
 *   ��ͷ��ʽ3��PtrFrameLayout + StoreHouseHeader
 *  
 * */

public class Next2Activity extends Activity {

	
	String[] str_s = {"΢��","QQ","İİ","����","̽̽",
			          "������","�ſ�","��Ѷ��Ƶ","����","bilibili",
			          "���","ͷ��","����","����","����"};  
	PtrFrameLayout ptrFrameLayout;
	 
	
	Button Bn_Pre;
	Button Bn_Next;
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v==Bn_Pre){
				Intent intent = new Intent(Next2Activity.this, MainActivity.class);
				startActivity(intent);
			}else if(v==Bn_Next){
				Intent intent = new Intent(Next2Activity.this, ThreeActivity.class);
				startActivity(intent);
			}
		}
	};
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page22); 
		
		
		Bn_Pre = (Button)findViewById(R.id.Bn_pre); 
		Bn_Next = (Button)findViewById(R.id.Bn_next);
		Bn_Pre.setOnClickListener(l);
		Bn_Next.setOnClickListener(l);
		
		ptrFrameLayout = (PtrFrameLayout)findViewById(R.id.ptrFrameLayout);
		//��� ˢ�±�ͷ
//		MaterialHeader headView = new MaterialHeader(this);
//		ptrFrameLayout.setHeaderView(headView);
//		ptrFrameLayout.addPtrUIHandler(headView);
		StoreHouseHeader storeHouseHeader = new StoreHouseHeader(this);
		storeHouseHeader.setBackgroundColor(Color.BLACK);
	    storeHouseHeader.setTextColor(Color.WHITE);
	    storeHouseHeader.setLineWidth(5);
	    storeHouseHeader.initWithString("ENGLISH ONLY HAHA");    //ֻ��Ӣ�ģ����Ĳ�������(���ʱ��)
		ptrFrameLayout.setHeaderView(storeHouseHeader);
		ptrFrameLayout.addPtrUIHandler(storeHouseHeader);
		//����ˢ�´���
		ptrFrameLayout.setPtrHandler(new PtrHandler() {  //����ˢ��		
			@Override
			public void onRefreshBegin(PtrFrameLayout frame) {  //��ʼˢ��
				// TODO Auto-generated method stub
				//���������contentˢ��ҵ��
				//��ʱ2s��  ����ˢ��	
				ptrFrameLayout.postDelayed( new Runnable() {  		
					@Override
					public void run() {
						// TODO Auto-generated method stub
						ptrFrameLayout.refreshComplete();	//����ˢ��
					}
				}, 2000);
			}		
			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame, View content,
					View header) {
				// TODO Auto-generated method stub//����true  ��ʾ����ˢ�� ��
				return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
			}
		});
		
	}
}
