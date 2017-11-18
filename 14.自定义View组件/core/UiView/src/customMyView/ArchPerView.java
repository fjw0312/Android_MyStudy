package customMyView;

import com.uiview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * 自定义 控件类    继承View
 * 功能： 拱形百分比 
 * 使用场景： 湿度百分比 等。
 * made by：fjw0312  date:2017.11.16
 * */
public class ArchPerView extends View{

	public ArchPerView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public ArchPerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_Parmas(context, attrs);  
	}
	
	public ArchPerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub 
		init_Parmas(context, attrs); 
	}
	
	
	//获取  自定义 控件的属性    如果不使用layout.xml参入布局参数 可以在此自定义类添加 一些.setTextSize()方法给调用设置。
	private void init_Parmas(Context context, AttributeSet attrs){
		
		TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.ArchPerView);//获取  自定义 控件的属性
		if(arr != null){
			groundColor = arr.getColor(R.styleable.ArchPerView_groundColor, groundColor);
			ringGroundColor = arr.getColor(R.styleable.ArchPerView_ringGroundColor, ringGroundColor);
			viewColor = arr.getColor(R.styleable.ArchPerView_viewColor, viewColor);
			textColor = arr.getColor(R.styleable.ArchPerView_textColor, textColor);
			textSize = arr.getDimension(R.styleable.ArchPerView_ringWith, ringWith);
			IsShowText = arr.getBoolean(R.styleable.ArchPerView_IsShowText, IsShowText);
			per = arr.getFloat(R.styleable.ArchPerView_per, per); 
			allAngle = arr.getFloat(R.styleable.ArchPerView_allAngle, allAngle); 
			arr.recycle();
		}
	
	}
	
		  //定义控件   默认基本尺寸
	    private static final int DEFAULT_WIDTH = 300;   //默认 控件 宽度200
	    private static final int DEFAULT_HEIGHT = 300;  //默认 控件 高度150 

	    private  int  groundColor = Color.TRANSPARENT; //默认  控件底板  颜色
	    private  int  viewColor = Color.CYAN;   //默认  控件  圆环颜色
	    private  int  ringGroundColor = Color.GRAY;  //默认 控件 圆环底色 
	    private  int  textColor = Color.BLUE;    //默认  控件 中间字体颜色
	    private  float  textSize = 40;            //默认  控件  中间字体大小
	    private  boolean  IsShowText = true;    //默认  控件  中间字体显示
	    private  int  pad = 4;    //默认控件边距 冗余
	    private  float  ringWith = 20;   //默认 控件  圆环的宽度 
	    private  float  per  = 60;      //默认  控件  圆环百分比60%
	   // private  float startAngle = -90;  //开始角度  0
	    private  float allAngle = 300f;   //总体角度
	    private  int startColor = Color.CYAN; //渐变 开始颜色
	    private  int endColor = Color.YELLOW; //渐变 结束颜色

		private Paint mPaint ;

	    @Override
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);
	        canvas.drawColor(groundColor);
	        //获取  控件的 长宽  此处画图形 一定要注意 控件使用时实际的大小   与自定义的控件最小尺寸的关系。
	        int width = getWidth();
	        int height = getHeight();
	   //     Log.i("RingPerView", "控件宽和高："+String.valueOf(width)+"   "+String.valueOf(height));
	        float radius = ((width>height?height:width)-ringWith)/2-pad;
    
	        mPaint = new Paint();    
	        mPaint.setStyle(Paint.Style.STROKE);
	        mPaint.setStrokeWidth(ringWith);
	        mPaint.setAntiAlias(true); // 设置画笔的锯齿效果
	        mPaint.setStrokeCap(Paint.Cap.ROUND); //设置画笔 头 圆润椭圆
	        RectF rectF = new RectF(width/2-radius,height/2-radius,width/2+radius,height/2+radius);
	        
	        // 绘制 大圆环
	        mPaint.setColor(ringGroundColor);       
	        float startAngle = (360-allAngle)/2 + 90;	      
	        canvas.drawArc(rectF, //弧线所使用的矩形区域大小 
	        		startAngle,  //开始角度 
	                allAngle, //扫过的角度
	                false, //是否使用链接中心
	                mPaint);
	       // canvas.drawCircle(width/2, height/2,radius,mPaint);
	        
	      //绘制  圆环进度
	        SweepGradient sweepGradient = new SweepGradient(width/2,height/2,startColor,endColor); //角度渐变 颜色类        
	        mPaint.setShader(sweepGradient);
	        mPaint.setColor(viewColor); 	        
	        float data1 = per*allAngle/100f;
	        canvas.drawArc(rectF, //弧线所使用的矩形区域大小
	                startAngle,  //开始角度 
	                data1, //扫过的角度
	                false, //是否使用链接中心
	                mPaint);
	       
	        //绘制  百分比字体
	        if(IsShowText){
	        	mPaint.setShader(null);
	        	mPaint.setStrokeWidth(0);
	        	mPaint.setColor(textColor);
	        	mPaint.setTextSize(textSize);
	        	mPaint.setTypeface(Typeface.DEFAULT_BOLD);
	        	float textWidth = mPaint.measureText((int)per+"%");
	        	canvas.drawText((int)per+"%", width/2-textWidth/2, height/2+textSize/2, mPaint);
	        }
	        
	        
	  //      Log.i("RingPerView>>onDraw","into!");        
	    }

	    @Override
	    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
	        super.onLayout(changed, left, top, right, bottom);
//	        Log.i("RingPerView>>onLayout","into!");
	    }

	    @Override //Mode: EXACTLY 确定大小    AT_MOST wrap_content    UNSPECIFIED 没限制大小
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	 //       Log.i("RingPerView>>onMeasure","into!");
	        //所以 以下应该  处理wrap_content 这种属性的情况使用  方法   this.setMeasuredDimension(x, y);
	        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
	//        Log.i("RingPerView",String.valueOf(widthSize)+"   "+String.valueOf(heightSize));

	        //判断  布局模式  是否有wrap_content 控件默认尺寸 
	        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
	            setMeasuredDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	        }else if(widthMode == MeasureSpec.AT_MOST){ 
	            setMeasuredDimension(DEFAULT_WIDTH, heightSize);
	        }else if(heightMode == MeasureSpec.AT_MOST){
	            setMeasuredDimension(widthSize, DEFAULT_HEIGHT);
	        }
	    }

	    
	    public int getGroundColor() {
			return groundColor;
		}

		public void setGroundColor(int groundColor) {
			this.groundColor = groundColor;
		}

		public int getViewColor() {
			return viewColor;
		}

		public void setViewColor(int viewColor) {
			this.viewColor = viewColor;
		}

		public int getRingGroundColor() {
			return ringGroundColor;
		}

		public void setRingGroundColor(int ringGroundColor) {
			this.ringGroundColor = ringGroundColor;
		}

		public int getTextColor() {
			return textColor;
		}

		public void setTextColor(int textColor) {
			this.textColor = textColor;
		}

		public float getTextSize() {
			return textSize;
		}

		public void setTextSize(int textSize) {
			this.textSize = textSize;
		}

		public boolean isIsShowText() {
			return IsShowText;
		}

		public void setIsShowText(boolean isShowText) {
			IsShowText = isShowText;
		}

		public float getRingWith() { 
			return ringWith;
		}

		public void setRingWith(int ringWith) {
			this.ringWith = ringWith;
		}

		public float getPer() {
			return per;
		}

		public void setPer(float per) {
			this.per = per;
		}
}
