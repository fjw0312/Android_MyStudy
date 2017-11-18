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
 * �Զ��� �ؼ���    �̳�View
 * ���ܣ� ���ΰٷֱ� 
 * ʹ�ó����� ʪ�Ȱٷֱ� �ȡ�
 * made by��fjw0312  date:2017.11.16
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
	
	
	//��ȡ  �Զ��� �ؼ�������    �����ʹ��layout.xml���벼�ֲ��� �����ڴ��Զ�������� һЩ.setTextSize()�������������á�
	private void init_Parmas(Context context, AttributeSet attrs){
		
		TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.ArchPerView);//��ȡ  �Զ��� �ؼ�������
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
	
		  //����ؼ�   Ĭ�ϻ����ߴ�
	    private static final int DEFAULT_WIDTH = 300;   //Ĭ�� �ؼ� ���200
	    private static final int DEFAULT_HEIGHT = 300;  //Ĭ�� �ؼ� �߶�150 

	    private  int  groundColor = Color.TRANSPARENT; //Ĭ��  �ؼ��װ�  ��ɫ
	    private  int  viewColor = Color.CYAN;   //Ĭ��  �ؼ�  Բ����ɫ
	    private  int  ringGroundColor = Color.GRAY;  //Ĭ�� �ؼ� Բ����ɫ 
	    private  int  textColor = Color.BLUE;    //Ĭ��  �ؼ� �м�������ɫ
	    private  float  textSize = 40;            //Ĭ��  �ؼ�  �м������С
	    private  boolean  IsShowText = true;    //Ĭ��  �ؼ�  �м�������ʾ
	    private  int  pad = 4;    //Ĭ�Ͽؼ��߾� ����
	    private  float  ringWith = 20;   //Ĭ�� �ؼ�  Բ���Ŀ�� 
	    private  float  per  = 60;      //Ĭ��  �ؼ�  Բ���ٷֱ�60%
	   // private  float startAngle = -90;  //��ʼ�Ƕ�  0
	    private  float allAngle = 300f;   //����Ƕ�
	    private  int startColor = Color.CYAN; //���� ��ʼ��ɫ
	    private  int endColor = Color.YELLOW; //���� ������ɫ

		private Paint mPaint ;

	    @Override
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);
	        canvas.drawColor(groundColor);
	        //��ȡ  �ؼ��� ����  �˴���ͼ�� һ��Ҫע�� �ؼ�ʹ��ʱʵ�ʵĴ�С   ���Զ���Ŀؼ���С�ߴ�Ĺ�ϵ��
	        int width = getWidth();
	        int height = getHeight();
	   //     Log.i("RingPerView", "�ؼ���͸ߣ�"+String.valueOf(width)+"   "+String.valueOf(height));
	        float radius = ((width>height?height:width)-ringWith)/2-pad;
    
	        mPaint = new Paint();    
	        mPaint.setStyle(Paint.Style.STROKE);
	        mPaint.setStrokeWidth(ringWith);
	        mPaint.setAntiAlias(true); // ���û��ʵľ��Ч��
	        mPaint.setStrokeCap(Paint.Cap.ROUND); //���û��� ͷ Բ����Բ
	        RectF rectF = new RectF(width/2-radius,height/2-radius,width/2+radius,height/2+radius);
	        
	        // ���� ��Բ��
	        mPaint.setColor(ringGroundColor);       
	        float startAngle = (360-allAngle)/2 + 90;	      
	        canvas.drawArc(rectF, //������ʹ�õľ��������С 
	        		startAngle,  //��ʼ�Ƕ� 
	                allAngle, //ɨ���ĽǶ�
	                false, //�Ƿ�ʹ����������
	                mPaint);
	       // canvas.drawCircle(width/2, height/2,radius,mPaint);
	        
	      //����  Բ������
	        SweepGradient sweepGradient = new SweepGradient(width/2,height/2,startColor,endColor); //�ǶȽ��� ��ɫ��        
	        mPaint.setShader(sweepGradient);
	        mPaint.setColor(viewColor); 	        
	        float data1 = per*allAngle/100f;
	        canvas.drawArc(rectF, //������ʹ�õľ��������С
	                startAngle,  //��ʼ�Ƕ� 
	                data1, //ɨ���ĽǶ�
	                false, //�Ƿ�ʹ����������
	                mPaint);
	       
	        //����  �ٷֱ�����
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

	    @Override //Mode: EXACTLY ȷ����С    AT_MOST wrap_content    UNSPECIFIED û���ƴ�С
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	 //       Log.i("RingPerView>>onMeasure","into!");
	        //���� ����Ӧ��  ����wrap_content �������Ե����ʹ��  ����   this.setMeasuredDimension(x, y);
	        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
	//        Log.i("RingPerView",String.valueOf(widthSize)+"   "+String.valueOf(heightSize));

	        //�ж�  ����ģʽ  �Ƿ���wrap_content �ؼ�Ĭ�ϳߴ� 
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
