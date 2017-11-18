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


/**
 * 自定义 控件类    继承View
 * 功能： 横向柱状图  
 * 使用场景： 每组功率对比图等。 每组柱体数量可随数据数量 
 * made by：fjw0312  date:2017.11.18
 * */
public class HorizontalBarView extends View{

	public HorizontalBarView(Context context) {		
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}
	
	public HorizontalBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_Parmas(context, attrs); 
		init(context);
		 
	}
	
	public HorizontalBarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub 
		init_Parmas(context, attrs); 
		init(context);		
	}
	

	
	//获取  自定义 控件的属性    如果不使用layout.xml参入布局参数 可以在此自定义类添加 一些.setTextSize()方法给调用设置。
	private void init_Parmas(Context context, AttributeSet attrs){
		
		TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.HorizontalBarView);//获取  自定义 控件的属性
		if(arr != null){
			groundColor = arr.getColor(R.styleable.HorizontalBarView_groundColor, groundColor);	
			textSize = arr.getDimension(R.styleable.HorizontalBarView_textSize, textSize);
			IsShowText = arr.getBoolean(R.styleable.HorizontalBarView_IsShowText, IsShowText);
			lineTextSize = arr.getDimension(R.styleable.HorizontalBarView_lineTextSize, lineTextSize);
			lineColor = arr.getColor(R.styleable.HorizontalBarView_lineColor, lineColor);
			xMaxValue = arr.getFloat(R.styleable.HorizontalBarView_xMaxValue, xMaxValue);

			arr.recycle();
		}
	
	}
	
	private void init(Context context){
        mRectf = new RectF();
        mPaint = new Paint(); 
        mPaint.setStyle(Style.FILL);  //设置 画笔  实心
        mPaint.setTextSize(textSize);  //设置字体 大小
        value_lst = new ArrayList<String>();    
        
       // value_lst.add("12-38-26-34-54-35-61");
       // value_lst.add("45-63-80-49-59-50-32");
       // value_lst.add("60-39-50-41-52-30-52");    
       // value_lst.add("40-59-55-29-49-30-52"); 

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
	    private  int  pad = 10;    //默认控s件边距 冗余
	    private  int  pad_tabX = 20;  //默认 x轴 缩进大小

	    private  float  xMaxValue = 100; //默认 X轴最大值

	    private  int[]  viewColors = {0xFFF84C4C, 0xFFB666C7,0xFF398D17,0xFF33DF8B,
	    		                     0xFFF6D532,0xFFFeA934,0xFFF933AA,0xFF398DE7};   //默认  控件 柱状图  颜色数组
	    private  List<String> value_lst = null;  //字符格式     45-57-39 数组
	    
		private Paint mPaint;
		private RectF mRectf;
			
		@Override
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);	
	        canvas.drawColor(groundColor);
	       
	        //获取  控件的 长宽  此处画图形 一定要注意 控件使用时实际的大小   与自定义的控件最小尺寸的关系。
	        int width = getWidth() - pad_tabX ;
	        int height = getHeight() - pad;
	       // Log.i("RingPerView", "控件宽和高："+String.valueOf(width)+"   "+String.valueOf(height));
	            
	        if(value_lst ==null || value_lst.size()==0) return;
	       int xNumber = value_lst.size();
	        float x_start = pad_tabX;
	        float x_end =   x_start + width;      
	        float y_end =  pad/2;
	        float y_start = height+y_end;
	        float y_unit = height/(xNumber+1);
	        canvas.drawLine(x_start, y_start, x_start, y_end, mPaint); //y轴
	        for(int i=1;i<xNumber+1;i++){  //y轴刻度 
				float y = y_start - y_unit*i;
				canvas.drawLine(x_start, y, x_start+5, y, mPaint); //5  刻度长度
			}
	        //绘制 柱子 
	        RectF rectf1 = new RectF();
	        float x_dataUnit = width/xMaxValue;
	        for(int i=0;i<xNumber;i++){
	        	String str[] = value_lst.get(i).split("-");
	        	if(str.length < 1) continue;	
	        	int yItem_Num = str.length;   //每组  柱体  小柱子个数
	        	float yItem_width = y_unit/(yItem_Num+2); //每组  柱体小柱子 有效宽度
	        	float yBag_width = yItem_width * yItem_Num;  //每组 整体有效宽度
	        	for(int j=0;j<yItem_Num;j++){        		
	        		float f_value = Float.parseFloat(str[j]);
	        		float node_x = x_start + f_value*x_dataUnit;
	        		float node_y = y_start - y_unit*(i+1);	  
	        		  		
	        		rectf1.left = x_start;
	        		rectf1.right = node_x;
	        		rectf1.top = node_y - yBag_width/2 + yItem_width*j;
	        		rectf1.bottom = rectf1.top + yItem_width;
	        		Log.w("HorizontalBarView>>onDraw","left:"+String.valueOf(rectf1.left)
	        				+"  right:"+String.valueOf(rectf1.right)+"  top:"+String.valueOf(rectf1.top)
	        				+"  bottom:"+String.valueOf(rectf1.bottom));
	        		mPaint.setColor(viewColors[j]);
	        		canvas.drawRect(rectf1, mPaint);
	        		
	        	//是否 绘制  中间字体显示 
	        		if(IsShowText){
	        			canvas.drawText(str[j], node_x, rectf1.bottom+yItem_width/4, mPaint);
	        		}
	        	}       	
	        }
	        
	    }

	    @Override
	    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
	         super.onLayout(changed, left, top, right, bottom);

		//    Log.i("Tag>>BarGraphView->onLayout","left:"+String.valueOf(left)+" top:"+String.valueOf(top)
		//        		+" right:"+String.valueOf(right)+" bottom:"+String.valueOf(bottom));
	    }

	    @Override //Mode: EXACTLY 确定大小    AT_MOST wrap_content    UNSPECIFIED 没限制大小
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	      
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
