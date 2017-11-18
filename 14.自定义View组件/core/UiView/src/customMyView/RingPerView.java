package customMyView;

import java.util.ArrayList;
import java.util.List;

import com.uiview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * �Զ��� �ؼ���    �̳�View
 * ���ܣ� Բ���ٷֱ�  ֧�� �������ݰٷֱ�  ��  ������ݰٷֱ�
 * ʹ�ó����� ������ �ȡ�
 * made by��fjw0312  date:2017.11.15
 * */
public class RingPerView extends View{

	public RingPerView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub 
		
	}
	
	public RingPerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_Parmas(context, attrs);  
	}
	
	public RingPerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub 
		init_Parmas(context, attrs); 
	}
	
	
	//��ȡ  �Զ��� �ؼ�������    �����ʹ��layout.xml���벼�ֲ��� �����ڴ��Զ�������� һЩ.setTextSize()�������������á�
	private void init_Parmas(Context context, AttributeSet attrs){
		
		TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.RingPerView);//��ȡ  �Զ��� �ؼ�������
		if(arr != null){
			groundColor = arr.getColor(R.styleable.RingPerView_groundColor, groundColor);
			ringGroundColor = arr.getColor(R.styleable.RingPerView_ringGroundColor, ringGroundColor);
			viewColor = arr.getColor(R.styleable.RingPerView_viewColor, viewColor);
			textColor = arr.getColor(R.styleable.RingPerView_textColor, textColor);
			textSize = arr.getDimension(R.styleable.RingPerView_textSize, textSize);
			ringWith = arr.getDimension(R.styleable.RingPerView_ringWith, ringWith);
			IsShowText = arr.getBoolean(R.styleable.RingPerView_IsShowText, IsShowText);
			per = arr.getFloat(R.styleable.RingPerView_per, per); 
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
	    private  float  ringWith = 60;   //Ĭ�� �ؼ�  Բ���Ŀ�� 
	    private  float  per  = 60;      //Ĭ��  �ؼ�  Բ���ٷֱ�60%
	    private  float startAngle = -90;  //��ʼ�Ƕ�  0
	    
	    private  int[]  viewColors = {0xFFF84C4C, 0xFFB666C7,0xFF398D17,0xFF33DF8B,
                0xFFF6D532,0xFFFeA934,0xFFF933AA,0xFF398DE7};   //Ĭ�� �ٷֱ�  ��ɫ����
	    private  List<Float> value_lst = null;  //�ַ���ʽ      �ٷֱ�����

		private Paint mPaint ;

	    @Override
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);
	        canvas.drawColor(groundColor);
	        //��ȡ  �ؼ��� ����  �˴���ͼ�� һ��Ҫע�� �ؼ�ʹ��ʱʵ�ʵĴ�С   ���Զ���Ŀؼ���С�ߴ�Ĺ�ϵ��
	        int width = getWidth();
	        int height = getHeight();
	   //     Log.i("RingPerView", "�ؼ���͸ߣ�"+String.valueOf(width)+"   "+String.valueOf(height));
	        float radius = ((width>height?height:width)-ringWith)/2f-pad;

	        mPaint = new Paint();    
	        mPaint.setStyle(Paint.Style.STROKE);
	        mPaint.setStrokeWidth(ringWith);
	        mPaint.setAntiAlias(true); // ���û��ʵľ��Ч��
	       
	   //   mPaint.setStrokeCap(Paint.Cap.ROUND); //���û��� ͷ Բ����Բ
	        // ���� ��Բ��
	        mPaint.setColor(ringGroundColor);
	        canvas.drawCircle(width/2f, height/2f,radius,mPaint);
	        
	        RectF rectF = new RectF(width/2f-radius,height/2f-radius,width/2f+radius,height/2f+radius);
	        //����  Բ������
	        if(value_lst==null || value_lst.size()==0){  //ֻ��һ���ٷֱ�����
	        	mPaint.setColor(viewColor);       
		        float data1 = per*360/100f;
		        canvas.drawArc(rectF, //������ʹ�õľ��������С
		                startAngle,  //��ʼ�Ƕ� 
		                data1, //ɨ���ĽǶ�
		                false, //�Ƿ�ʹ����������
		                mPaint);
	        }else{ //���� �ٷֱ�����
	        	for(int i=0;i<value_lst.size();i++){
	        		mPaint.setColor(viewColors[i]);
			        float data1 = value_lst.get(i)*360f/100f +1;		        
			        canvas.drawArc(rectF, //������ʹ�õľ��������С
			                startAngle,  //��ʼ�Ƕ� 
			                data1, //ɨ���ĽǶ�
			                false, //�Ƿ�ʹ����������
			                mPaint);
			      
			        startAngle = startAngle+data1 - 1;
	        	}      	
	        }
	        
	        
	        //����  �ٷֱ�����
	        if(IsShowText){
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
		//���ðٷֱ�����
		public void setValue_lst(List<Float> lst){
			if(lst == null) return;
			if(value_lst!=null)value_lst.clear();
			value_lst = lst;
			if(value_lst.size()>0)  IsShowText = false;
		}
		//���� �ٷֱ���ɫ
		public void setViewColors(int[] colors){
			if(colors==null) return;
			int num = viewColors.length>colors.length?colors.length:viewColors.length;
			for(int i=0;i<num;i++){
				viewColors[i] = colors[i];
			}
		}
}
