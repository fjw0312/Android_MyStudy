package customMyView;

import java.util.ArrayList;
import java.util.List;

import com.uiview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


/**
 * 自定义 控件类    继承ViewGroup
 * 功能： 单曲线  
 * 使用场景： 温度波动  pue曲线。
 * made by：fjw0312  date:2017.11.16
 * */
public class CurveView extends ViewGroup{

	public CurveView(Context context) {		
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}
	
	public CurveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_Parmas(context, attrs); 
		init(context);
		 
	}
	
	public CurveView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub 
		init_Parmas(context, attrs); 
		init(context);		
	}
	

	
	//获取  自定义 控件的属性    如果不使用layout.xml参入布局参数 可以在此自定义类添加 一些.setTextSize()方法给调用设置。
	private void init_Parmas(Context context, AttributeSet attrs){
		
		TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.CurveView);//获取  自定义 控件的属性
		if(arr != null){
			groundColor = arr.getColor(R.styleable.CurveView_groundColor, groundColor);	
			textSize = arr.getDimension(R.styleable.CurveView_textSize, textSize);
			IsShowText = arr.getBoolean(R.styleable.CurveView_IsShowText, IsShowText);
			lineTextSize = arr.getDimension(R.styleable.CurveView_lineTextSize, lineTextSize);
			lineColor = arr.getColor(R.styleable.CurveView_lineColor, lineColor);
			xNumber = arr.getInteger(R.styleable.CurveView_xNumber, xNumber);
			yNumber = arr.getInteger(R.styleable.CurveView_yNumber, yNumber);
			xMaxValue = arr.getFloat(R.styleable.CurveView_xMaxValue, xMaxValue);
			yMaxValue = arr.getFloat(R.styleable.CurveView_yMaxValue, yMaxValue);
			dotColor = arr.getColor(R.styleable.CurveView_dotColor, dotColor);
			fillColor = arr.getColor(R.styleable.CurveView_fillColor, fillColor);
			fillendColor = arr.getColor(R.styleable.CurveView_fillendColor, fillendColor);
			IsFillShadow = arr.getBoolean(R.styleable.CurveView_IsFillShadow, IsFillShadow);
		}
	
	}
	
	private void init(Context context){
        mAxis = new Axis(context);
        mAxis.textSize = (int)lineTextSize;
        mAxis.color = lineColor;
        mPaint = new Paint(); 
        mPaint.setAntiAlias(true); // 设置画笔的锯齿效果
        mPaint.setStyle(Style.STROKE);  //设置 画笔空心
        mPaint.setTextSize(textSize);  //设置字体 大小       
        mPaint.setColor(dotColor);
        mPaint.setStrokeWidth(2f);
        value_lst = new ArrayList<String>();    
        
        value_lst.add("0-21");
        value_lst.add("1-61");
        value_lst.add("2-32");
        value_lst.add("4-52");    
        value_lst.add("7-59"); 
        value_lst.add("8-49"); 
        value_lst.add("9-89"); 
        value_lst.add("13-69"); 
        value_lst.add("18-38"); 
        value_lst.add("21-49"); 
        
//        ViewGroup.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.addView(mAxis);
	}
		  //定义控件   默认基本尺寸
	    private static final int DEFAULT_WIDTH = 500;   //默认 控件 宽度200
	    private static final int DEFAULT_HEIGHT = 400;  //默认 控件 高度150
	    
	    private  int  groundColor = Color.TRANSPARENT; //默认  控件底板  颜色
	    private  int  lineColor   = Color.GRAY;  //默认  控件  最标轴  颜色
	    private  float  lineTextSize = 14;     //默认  控件  做标轴字体大小
	    private  int  dotColor = Color.CYAN;    //默认  控件小圆点颜色
	    private  int  fillColor = Color.LTGRAY;  ////默认  控件曲线下方区域颜色
	    private  int  fillendColor = Color.DKGRAY;  ////默认  控件曲线下方区域颜色
	    private  float  textSize = 20;            //默认  控件  中间字体大小
	    private  boolean  IsShowText = false;    //默认  控件  中间字体显示
	    private  boolean  IsFillShadow = true;   //默认  控件   是否填充阴影
	    
	    private  int  pad = 4;    //默认控件边距 冗余
	    private  int  xNumber = 12;  //默认  x轴刻度数柱状图   4组 子柱体
	    private  int  yNumber = 10;  //默认  y轴刻度数	   	  
	    private  float  yMaxValue = 100; //默认 Y轴最大值
	    private  float  xMaxValue = 24; //默认 X轴最大值

	  //  private  int[]  viewColors = {0xFFF84C4C, 0xFFB666C7,0xFF398D17,0xFF33DF8B,
	  //  		                     0xFFF6D532,0xFFFeA934,0xFFF933AA,0xFF398DE7};   //默认  控件 柱状图  颜色数组
	    
	    //注意 该数值链表 一定要按x量从小到大排序  否则会曲线错乱
	    private  List<String> value_lst = null;  //字符格式     10-27 数组   x-y 以圆点为0
	    private int minRadus = 2;  //小圆点直径
	    
		private Paint mPaint;
		private Axis  mAxis;
		
		/* 曲线绘制  可以  贝塞尔曲线算法   ---目前未实现
		 * 	path.reset(); //重置路径	
		 * path.quadTo(k_node_x, k_node_y, node_x, node_y); //or path.cubicTo();
		 * canvas.drawPath(path, mPaint);
		 * @see android.view.ViewGroup#dispatchDraw(android.graphics.Canvas)
		 */
	    @Override 
		protected void dispatchDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.dispatchDraw(canvas);			
			canvas.drawColor(groundColor);
	        //获取  控件的 长宽  此处画图形 一定要注意 控件使用时实际的大小   与自定义的控件最小尺寸的关系。
	        int width = getWidth();
	        int height = getHeight();
	       // Log.i("RingPerView", "控件宽和高："+String.valueOf(width)+"   "+String.valueOf(height));
			float pre_x = 0;
			float pre_y = 0;
			//做 面积填充
			Path path = new Path();
			path.reset(); //重置路径
			path.moveTo(mAxis.x_start, mAxis.y_start);
					
	        if(value_lst ==null || value_lst.size()==0) return;  
	        //可以考虑限定value_lst的数据数量大小
	        for(int i=0;i<value_lst.size();i++){
	        	String str[] = value_lst.get(i).split("-");
	        	if(str.length != 2) continue;	
	        	float x_value = Float.parseFloat(str[0]);
	        	float y_value = Float.parseFloat(str[1]);
	        	//绘制圆点
	        	float node_x = mAxis.x_start+mAxis.x_per_unit*(x_value);
				float node_y = mAxis.y_start-mAxis.y_per_unit*y_value;		
				canvas.drawCircle(node_x, node_y,minRadus, mPaint); // 画出数值点
				
				path.lineTo(node_x, node_y); //填充连线
				
				//链接 连线
				if(i!=0){
					canvas.drawLine(pre_x,pre_y,node_x,node_y,mPaint);			
				}									
				
				pre_x = node_x;
				pre_y = node_y;
				
	        	//是否 绘制  中间字体显示
	        	if(IsShowText){
	        			canvas.drawText(str[1], node_x, node_y, mPaint);
	        	}       	
	        }
	        if(IsFillShadow){
	        	path.lineTo(pre_x, mAxis.y_start); //填充连线
		        path.lineTo(mAxis.x_start, mAxis.y_start); //连线回原点
		        Paint paint = new Paint();
		        mPaint.setStyle(Style.FILL);  //设置 画笔空心  
		       // mPaint.setColor(fillColor);
		        LinearGradient gradient = new LinearGradient(mAxis.x_start, mAxis.y_start, mAxis.x_start, mAxis.y_end, 
		        		fillColor, fillendColor, Shader.TileMode.CLAMP);
		        mPaint.setShader(gradient);
		        canvas.drawPath(path, mPaint);
	        }
	        
	        
	        Log.i("Tag>>CurveView->dispatchDraw","into -2!"); 
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
	//	    Log.i("Tag>>CurveView->onLayout","left:"+String.valueOf(left)+" top:"+String.valueOf(top)
	//	        		+" right:"+String.valueOf(right)+" bottom:"+String.valueOf(bottom));
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
		
}
