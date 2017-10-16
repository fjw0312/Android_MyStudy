package myCustomView;

import android.content.Context;
import android.util.AttributeSet;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
/***
 * �Զ��� ����ˢ��  �ؼ�   extends UItra-Pull-To-Refresh
 * 
 * */
public class MyPullToRefreshLayout extends PtrFrameLayout{

	public MyPullToRefreshLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initViews();
	}

	public MyPullToRefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initViews();
	}

	public MyPullToRefreshLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initViews();
	}

     MyPullToHeadView headView;   //���� �Զ���header
//     MaterialHeader  headView;  // ��Դ��  ԭ�� ��תˢ��Բ��ͷ

     
     //��ʼ��   ��ͷ
     private void initViews() {
    	 headView = new MyPullToHeadView(getContext());
    //	 headView = new MaterialHeader(getContext());
    	 setHeaderView(headView);
    	 addPtrUIHandler(headView);
     }
     
//     public MyPullToHeadView  getHeader() {
//    	 return headView;
//     }

}
