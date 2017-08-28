package fang.myViewGroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/****    本自定义ViewGroup 目前没有使用属性参数文件 传参数值进来 
 * 本控件  布局： 将子view 按等宽（取最大宽度子view）逐个从上到下排序下来。
 * 
 * 需要 重写onLayout()  onMeasure();
 */
public class MyViewGroup01 extends ViewGroup{ 

	public MyViewGroup01(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyViewGroup01(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyViewGroup01(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	private   int  groundColor = Color.GREEN; //默认  控件底板  颜色 
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Log.i("MyViewGroup01>dispatchDraw", "into-1" );
		canvas.drawColor(groundColor);  //绘制 底板  必须在super.dispatchDraw(canvas); 之前才不覆盖子view
		super.dispatchDraw(canvas); //其内部自身 会对addView 添加的子view 绘制
		
		Log.i("MyViewGroup01>dispatchDraw", "into-2" );
	}
	
	@Override   //测试 结果证明  没有进入该函数
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Log.i("MyViewGroup01>onDraw", "into-1" );
		super.onDraw(canvas);
		canvas.drawColor(groundColor); 
		Log.i("MyViewGroup01>onDraw", "into-2" );
	}
	
	@Override  //必须重写
	protected void onLayout(boolean arg0, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int preHeight = 0;
		//遍历 子控件 
		for(int i=0;i<getChildCount();i++){
			View children = getChildAt(i);
			int cHeight = children.getMeasuredHeight();
			if(children.getVisibility() != View.GONE){
				children.layout(l, preHeight, r, preHeight +=cHeight);//子view 布局 按高度叠
			}
		}
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//遍历 子控件 mesaure()
		int childCount = getChildCount();
		for(int i=0;i<childCount;i++){
			View children = getChildAt(i);
			measureChild(children, widthMeasureSpec, heightMeasureSpec);
		}
		
		//判断 布局模式   支持wrap_content
		int mMaxWidth = 0;
		int mMaxHeight = 0;
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);		
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec); 
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
			for(int i=0;i<childCount;i++){  //要参考布局方式 计算
				View children = getChildAt(i);	
				mMaxWidth = Math.max(mMaxWidth, children.getMeasuredWidth());
				mMaxHeight += children.getMeasuredHeight();
			}
			setMeasuredDimension(mMaxWidth, mMaxHeight);	
		}else if(widthMode == MeasureSpec.AT_MOST){
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				mMaxWidth = Math.max(mMaxWidth, children.getMeasuredWidth());
			}
			setMeasuredDimension(mMaxWidth, heightSize);	
		}else if(heightMode == MeasureSpec.AT_MOST){
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				mMaxHeight += children.getMeasuredHeight();
			}
			setMeasuredDimension(widthSize, mMaxHeight);	
		}
	}

}
