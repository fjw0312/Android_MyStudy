package myCustomView;

import android.content.Context;
import android.util.AttributeSet;
import in.srain.cube.views.ptr.PtrFrameLayout;
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

     MyPullToHeadView headView;
     
     //��ʼ��   ��ͷ
     private void initViews() {
    	 headView = new MyPullToHeadView(getContext());
    	 setHeaderView(headView);
    	 addPtrUIHandler(headView);
     }
     
     public MyPullToHeadView  getHeader() {
    	 return headView;
     }

}
