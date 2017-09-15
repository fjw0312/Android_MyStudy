package myCustomView;

import android.content.Context;
import android.util.AttributeSet;
import in.srain.cube.views.ptr.PtrFrameLayout;
/***
 * 自定义 下拉刷新  控件   extends UItra-Pull-To-Refresh
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
     
     //初始化   表头
     private void initViews() {
    	 headView = new MyPullToHeadView(getContext());
    	 setHeaderView(headView);
    	 addPtrUIHandler(headView);
     }
     
     public MyPullToHeadView  getHeader() {
    	 return headView;
     }

}
