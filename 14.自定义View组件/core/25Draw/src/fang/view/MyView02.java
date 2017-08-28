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
 * 这样使得 该view自定义的控件 布局参数  只适用于match_content
 * 如果要适用于 wrap_content 应该添加.setMeasureDimension()
 * 
 * 
 * */
public class MyView02 extends View{
	//构造1
	public MyView02(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		Log.i("MyView01>MyView02","into-1");
	}
	//构造2
	public MyView02(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		Log.i("MyView01>MyView02","into-2");
	}
	//构造3
	public MyView02(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Log.i("MyView01>MyView02","into-3");
	}

	//定义控件   默认基本尺寸
	private static final int DEFAULT_WIDTH = 130;   //默认 控件 宽度200
	private static final int DEFAULT_HEIGHT = 80;  //默认 控件 高度150

	   @Override   //可以 不用重写
		public void draw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.draw(canvas);
		}
		@Override  //可以 不用重写
		public void layout(int l, int t, int r, int b) {
			// TODO Auto-generated method stub
			super.layout(l, t, r, b);
		}
		
		
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			
			canvas.drawColor(Color.BLUE); 
			//获取  控件的 长宽  此处画图形 一定要注意 控件使用时实际的大小   与自定义的控件最小尺寸的关系。
			int width = getWidth();
			int height = getHeight();
			int pad = 10;
			//定义画笔
			Paint mPaint = new Paint();
			mPaint.setColor(Color.RED);
			mPaint.setTextSize(15); 			
			canvas.drawRect(pad, pad, width-pad, height-pad, mPaint);    //画矩形 
			mPaint.setColor(Color.YELLOW);
			canvas.drawText("MyView02", 30, 30, mPaint);  //位置 尺寸不能写死  要综合 控件实际使用大小的关系。
		}
		@Override
		protected void onLayout(boolean changed, int left, int top, int right,
				int bottom) {
			// TODO Auto-generated method stub
			super.onLayout(changed, left, top, right, bottom);
//			Log.i("MyView02>onLayout","f-2");
		}
		@Override  //Mode: EXACTLY 确定大小    AT_MOST wrap_content    UNSPECIFIED 没限制大小
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// TODO Auto-generated method stub			          //如果确定大小200dp  也能实现 
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);//当 wrap or fill/match 时都是铺满
			    //所以 以下应该  处理wrap_content 这种属性的情况使用  方法   this.setMeasuredDimension(x, y);                                              
			int widthMode = MeasureSpec.getMode(widthMeasureSpec);		
			int heightMode = MeasureSpec.getMode(heightMeasureSpec);
			int widthSize = MeasureSpec.getSize(widthMeasureSpec); 
			int heightSize = MeasureSpec.getSize(heightMeasureSpec);
			
					
			//判断  布局模式  是否有wrap_content 控件默认尺寸
			if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
				Log.i("MyView02>onMeasure", widthSize+"---"+heightSize);
				setMeasuredDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);	
			}else if(widthMode == MeasureSpec.AT_MOST){
				setMeasuredDimension(DEFAULT_WIDTH, heightSize);	
			}else if(heightMode == MeasureSpec.AT_MOST){
				setMeasuredDimension(widthSize, DEFAULT_HEIGHT);
			}	
		}

}
