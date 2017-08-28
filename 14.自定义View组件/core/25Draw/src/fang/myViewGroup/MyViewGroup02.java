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
public class MyViewGroup02 extends ViewGroup{

	public MyViewGroup02(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyViewGroup02(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyViewGroup02(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	private   int  groundColor = Color.GREEN; //默认  控件底板  颜色 
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(groundColor); 
	}
	
	@Override  //必须重写
	protected void onLayout(boolean arg0, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		//获取  控件的 长宽  此处画图形 一定要注意 控件使用时实际的大小   与自定义的控件最小尺寸的关系。
		int width = getWidth();
		int height = getHeight();
		
		int pre_Height = 0;
		int pre_Width = 0;
		int next_Width = 0;
		int lenth_Height = 0;
		//遍历 子控件 
		for(int i=0;i<getChildCount();i++){
			View children = getChildAt(i); //获取子view 及 宽  高
			int cwidth = children.getMeasuredWidth();
			int cHeight = children.getMeasuredHeight();
			//判断 宽度起点是否 该换行
			if(pre_Width + cwidth > width){
				pre_Width = 0;
				pre_Height = pre_Height + lenth_Height;
				lenth_Height = cHeight; 
			}
			lenth_Height = Math.max(lenth_Height,cHeight); //获取最大高度
 
			if(children.getVisibility() != View.GONE){		    
				children.layout(pre_Width, pre_Height, pre_Width+cwidth, pre_Height+cHeight);//子view 布局 按高度叠
			}
			pre_Width = pre_Width + cwidth;
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
			int multi_line = 0;
			int line_MaxHeight = 0;
			int endLine_MaxHeight = 0;
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				line_MaxHeight = Math.max(line_MaxHeight, children.getMeasuredHeight());
				mMaxWidth = mMaxWidth + children.getMeasuredWidth();
				if(mMaxWidth > widthSize){
					mMaxWidth = children.getMeasuredWidth();
					multi_line++;
					mMaxHeight = mMaxHeight+line_MaxHeight;  
				//	Log.i("MyViewGroup02>onMeasure", "line_MaxHeight="+String.valueOf(line_MaxHeight) );
				}
				endLine_MaxHeight = line_MaxHeight;
			}
			mMaxHeight += endLine_MaxHeight;
			setMeasuredDimension(multi_line>0?widthSize:mMaxWidth, mMaxHeight);	 
	//		Log.i("MyViewGroup02>onMeasure", String.valueOf(multi_line>0?widthSize:mMaxWidth)+"---"+mMaxHeight);
		}else if(widthMode == MeasureSpec.AT_MOST){
			int multi_line = 0;
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				mMaxWidth += children.getMeasuredWidth();
				if(mMaxWidth > widthSize){
					mMaxWidth = children.getMeasuredWidth();
					multi_line++;
				}
			}
			setMeasuredDimension(multi_line>0?widthSize:mMaxWidth, heightSize);	
		}else if(heightMode == MeasureSpec.AT_MOST){
			int line_MaxHeight = 0;
            int endLine_MaxHeight = 0;
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				//判断 换行  高度增加
				line_MaxHeight = Math.max(line_MaxHeight, children.getMeasuredHeight());				
				mMaxWidth += children.getMeasuredWidth();
				if(mMaxWidth > widthSize){ 
					mMaxWidth = children.getMeasuredWidth();
					mMaxHeight += line_MaxHeight;
				}
				endLine_MaxHeight = line_MaxHeight;
			}
			mMaxHeight += endLine_MaxHeight;
			setMeasuredDimension(widthSize, mMaxHeight);	
		}
	}

}
