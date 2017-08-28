package fang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/****    本自定义View 用于测试 自定义控件(基本使用)  加载流程 & 基本重写方法
 * 继承View 自定义控件   经过测试得出：
 * android源码 ：（控件自动加载运行顺序）
ViewRoot类.performTraversals(){
  final View host = mView;
  ....
  host.measure(childWidthMeasureSpec, childHeightMeasureSpec);
  ....
  host.layout(0, 0 , host.getMeasureWidth(), host.MeasureHeight());
  ....
  draw(fullRedrawNeed);
}
 代码中可重写的方法有：
 onMeasure()
 layout() --onlayout()  》》android源码空实现
 draw()   --onDraw()    》》android源码空实现
 
 
 由于Measure不能重写 故有5个方法
 其中onlayout()  onDraw() 》android源码空实现》》 让我们自定义 重写该方法。
 让我们自定义 重写的方法常用：
   onMeasure();(其中内部super.onMeasure(widthMeasureSpec, heightMeasureSpec);复杂)
   onlayout(); 
   onDraw();
 其中： 
 layout()通过 自身原有构造方法super.layout(l, t, r, b); 调用->onlayout(); 
 draw()通过 自身原有构造方法super.onDraw(Canvas canvas); 调用->onDraw(); 
 * 
 * onMeasure()重写的方法中 只添加了原有的构造super.onMeasure(widthMeasureSpec, heightMeasureSpec);
 * 这样使得 该view自定义的控件 布局参数  只适用于 wrap_content
 * 如果要适用于 wrap_content 应该添加.setMeasureDimension()
 * 
 * 
 * */
public class MyView01 extends View{
	//构造1
	public MyView01(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		Log.v("MyView01>MyView01","into-1");
	}
	//构造2
	public MyView01(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		Log.v("MyView01>MyView01","into-2");
	}
	//构造3
	public MyView01(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Log.v("MyView01>MyView01","into-3");
	}

		
	   @Override
		public void draw(Canvas canvas) {
			// TODO Auto-generated method stub
		    Log.v("MyView01>draw","f-1");
			super.draw(canvas);
		    Log.v("MyView01>draw","f-2");
		}
		@Override
		public void layout(int l, int t, int r, int b) {
			// TODO Auto-generated method stub
			Log.v("MyView01>layout","f-1");
			super.layout(l, t, r, b);
			Log.v("MyView01>layout","f-2");
		} 
		
			
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub 
			Log.v("MyView01>onDraw","f-1");
			super.onDraw(canvas);
			Log.v("MyView01>onDraw","f-2");
			//定义画笔
			Paint mPaint = new Paint();
			mPaint.setColor(Color.RED);
			mPaint.setTextSize(20);
			canvas.drawText("MyView01", 20, 20, mPaint);
		}
		@Override
		protected void onLayout(boolean changed, int left, int top, int right,
				int bottom) {
			// TODO Auto-generated method stub 
			Log.v("MyView01>onLayout","f-1");
			super.onLayout(changed, left, top, right, bottom);
			Log.v("MyView01>onLayout","f-2");
		}
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// TODO Auto-generated method stub
			Log.v("MyView01>onMeasure","f-1");
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			Log.v("MyView01>onMeasure","f-2");
		}

}
