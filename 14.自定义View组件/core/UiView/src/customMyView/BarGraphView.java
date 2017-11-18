package customMyView;

import java.util.ArrayList;
import java.util.List;

import com.uiview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


/**
 * 自定义 控件类    继承ViewGroup
 * 功能： 柱状图   直方图
 * 使用场景： 月份用电量 分布图。 每组柱体数量可随数据数量 
 * made by：fjw0312  date:2017.11.15
 * */
public class BarGraphView extends ViewGroup{

	public BarGraphView(Context context) {		
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}
	
	public BarGraphView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_Parmas(context, attrs); 
		init(context);
		 
	}
	
	public BarGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub 
		init_Parmas(context, attrs); 
		init(context);		
	}
	

	
	//获取  自定义 控件的属性    如果不使用layout.xml参入布局参数 可以在此自定义类添加 一些.setTextSize()方法给调用设置。
	private void init_Parmas(Context context, AttributeSet attrs){
		
		TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.BarGraghView);//获取  自定义 控件的属性
		if(arr != null){
			groundColor = arr.getColor(R.styleable.BarGraghView_groundColor, groundColor);	
			textSize = arr.getDimension(R.styleable.BarGraghView_textSize, textSize);
			IsShowText = arr.getBoolean(R.styleable.BarGraghView_IsShowText, IsShowText);
			lineTextSize = arr.getDimension(R.styleable.BarGraghView_lineTextSize, lineTextSize);
			lineColor = arr.getColor(R.styleable.BarGraghView_lineColor, lineColor);
			xNumber = arr.getInteger(R.styleable.BarGraghView_xNumber, xNumber);
			yNumber = arr.getInteger(R.styleable.BarGraghView_yNumber, yNumber);
			xMaxValue = arr.getFloat(R.styleable.BarGraghView_xMaxValue, xMaxValue);
			yMaxValue = arr.getFloat(R.styleable.BarGraghView_yMaxValue, yMaxValue);
			arr.recycle();
		}
	
	}
	
	private void init(Context context){
        mRectf = new RectF();
        mAxis = new Axis(context);
        mAxis.textSize = (int)lineTextSize;
        mAxis.color = lineColor;
        mPaint = new Paint(); 
        mPaint.setStyle(Style.FILL);  //设置 画笔  实心
        mPaint.setTextSize(textSize);  //设置字体 大小
        value_lst = new ArrayList<String>();    
        
       // value_lst.add("12-38-26-34-54-35-61");
       // value_lst.add("45-63-80-49-59-50-32");
       // value_lst.add("60-39-50-41-52-30-52");    
       // value_lst.add("40-59-55-29-49-30-52"); 

        
//        ViewGroup.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.addView(mAxis);
	}
		  //定义控件   默认基本尺寸
	    private static final int DEFAULT_WIDTH = 500;   //默认 控件 宽度200
	    private static final int DEFAULT_HEIGHT = 400;  //默认 控件 高度150
	    
	    private  int  groundColor = Color.TRANSPARENT; //默认  控件底板  颜色
	    private  int  lineColor   = Color.GRAY;  //默认  控件  最标轴  颜色
	    private  float  lineTextSize = 14;     //默认  控件  做标轴字体大小
	    //private  int  textColor = Color.BLUE;    //默认  控件 中间字体颜色
	    private  float  textSize = 20;            //默认  控件  中间字体大小
	    private  boolean  IsShowText = true;    //默认  控件  中间字体显示
	    private  int  pad = 4;    //默认控件边距 冗余
	 // private  int  xItem_Num = 3;  //默认 每组柱状图 的柱子 个数
	    private  int  xNumber = 4;  //默认  x轴刻度数柱状图   4组 子柱体
	    private  int  yNumber = 10;  //默认  y轴刻度数	   	  
	    private  float  yMaxValue = 100; //默认 Y轴最大值
	    private  float  xMaxValue = 4; //默认 X轴最大值

	    private  int[]  viewColors = {0xFFF84C4C, 0xFFB666C7,0xFF398D17,0xFF33DF8B,
	    		                     0xFFF6D532,0xFFFeA934,0xFFF933AA,0xFF398DE7};   //默认  控件 柱状图  颜色数组
	    private  List<String> value_lst = null;  //字符格式     45-57-39 数组
	    
		private Paint mPaint;
		private RectF mRectf;
		private Axis  mAxis;
			
	    @Override
		protected void dispatchDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.dispatchDraw(canvas);			
			canvas.drawColor(groundColor);
	        //获取  控件的 长宽  此处画图形 一定要注意 控件使用时实际的大小   与自定义的控件最小尺寸的关系。
	        int width = getWidth();
	        int height = getHeight();
	       // Log.i("RingPerView", "控件宽和高："+String.valueOf(width)+"   "+String.valueOf(height));
	             
	        if(value_lst ==null || value_lst.size()==0) return;
	        int num = xNumber;
	        if(value_lst.size()<xNumber) num = value_lst.size();	      
	        for(int i=0;i<num;i++){
	        	String str[] = value_lst.get(i).split("-");
	        	if(str.length < 1) continue;	
	        	int xItem_Num = str.length;   //每组  柱体  小柱子个数
	        	float xItem_lenth = mAxis.x_unit/(xItem_Num+2); //每组  柱体小柱子 有效长度
	        	float xBag_lenth = xItem_lenth * xItem_Num;  //每组 整体有效长度
	        	for(int j=0;j<str.length;j++){        		
	        		float f_value = Float.parseFloat(str[j]);
	        		float node_x = mAxis.x_start+mAxis.x_unit*(i+1);
	        		float node_y = mAxis.y_start-mAxis.y_per_unit*f_value;
	        		RectF rectf1 = new RectF();
	        		rectf1.left = node_x-xBag_lenth/2+xItem_lenth*j;
	        		rectf1.right = node_x-xBag_lenth/2+xItem_lenth*(j+1);
	        		rectf1.top = node_y;
	        		rectf1.bottom = mAxis.y_start;
	        		mPaint.setColor(viewColors[j]);
	        		canvas.drawRect(rectf1, mPaint);
	        	//是否 绘制  中间字体显示
	        		if(IsShowText){
	        			canvas.drawText(str[j], rectf1.left, rectf1.top-2, mPaint);
	        		}
	        	}
	        }
	        Log.i("Tag>>BarGraphView->dispatchDraw","into -2!"); 
		}

		@Override
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);	         
	    }

	    @Override
	    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
	    //    super.onLayout(changed, left, top, right, bottom);
	    	if(changed){
	    		mAxis.layout(0, 0, right-left, bottom-top); 
	    		mAxis.upDataValue(right-left,bottom-top,xNumber,yNumber, xMaxValue,yMaxValue); //坐标轴时间更新
	    	}
		//    Log.i("Tag>>BarGraphView->onLayout","left:"+String.valueOf(left)+" top:"+String.valueOf(top)
		//        		+" right:"+String.valueOf(right)+" bottom:"+String.valueOf(bottom));
	    }

	    @Override //Mode: EXACTLY 确定大小    AT_MOST wrap_content    UNSPECIFIED 没限制大小
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	      //遍历 子控件 mesaure()
			int childCount = getChildCount();
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				measureChild(children, widthMeasureSpec, heightMeasureSpec);
			}
	      
	        //所以 以下应该  处理wrap_content 这种属性的情况使用  方法   this.setMeasuredDimension(x, y);
	        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//	        Log.i("Tag>>BarGraphView->onMeasure",String.valueOf(widthSize)+"   "+String.valueOf(heightSize));

	        //判断  布局模式  是否有wrap_content 控件默认尺寸
	        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
	            setMeasuredDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	        }else if(widthMode == MeasureSpec.AT_MOST){
	            setMeasuredDimension(DEFAULT_WIDTH, heightSize);
	        }else if(heightMode == MeasureSpec.AT_MOST){
	            setMeasuredDimension(widthSize, DEFAULT_HEIGHT);
	        }
	    }
		//调用invalidate() 控件更新->onDraw()调用函数
		public void doInvalidate(){
				this.invalidate();
				mAxis.invalidate();
		}
		//调用requestLayout() 底板更新->onLayout()调用函数
		public void doRequestLayout(){
				this.requestLayout();
		}
	    
	    //设置 柱状图 数据
		public void setValue_lst(List<String> lst){
			if(lst == null) return;
			if(value_lst!=null)  value_lst.clear();
			value_lst = lst;
		}
		//设置 柱状图 柱体颜色
		public void setViewColors(int[] colors){
			if(colors==null) return;
			int num = viewColors.length>colors.length?colors.length:viewColors.length;
			for(int i=0;i<num;i++){
				viewColors[i] = colors[i];
			}
		}
}
