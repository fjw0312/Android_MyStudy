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
 * �Զ��� �ؼ���    �̳�ViewGroup
 * ���ܣ� ��״ͼ   ֱ��ͼ
 * ʹ�ó����� �·��õ��� �ֲ�ͼ�� ÿ���������������������� 
 * made by��fjw0312  date:2017.11.15
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
	

	
	//��ȡ  �Զ��� �ؼ�������    �����ʹ��layout.xml���벼�ֲ��� �����ڴ��Զ�������� һЩ.setTextSize()�������������á�
	private void init_Parmas(Context context, AttributeSet attrs){
		
		TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.BarGraghView);//��ȡ  �Զ��� �ؼ�������
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
        mPaint.setStyle(Style.FILL);  //���� ����  ʵ��
        mPaint.setTextSize(textSize);  //�������� ��С
        value_lst = new ArrayList<String>();    
        
       // value_lst.add("12-38-26-34-54-35-61");
       // value_lst.add("45-63-80-49-59-50-32");
       // value_lst.add("60-39-50-41-52-30-52");    
       // value_lst.add("40-59-55-29-49-30-52"); 

        
//        ViewGroup.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.addView(mAxis);
	}
		  //����ؼ�   Ĭ�ϻ����ߴ�
	    private static final int DEFAULT_WIDTH = 500;   //Ĭ�� �ؼ� ���200
	    private static final int DEFAULT_HEIGHT = 400;  //Ĭ�� �ؼ� �߶�150
	    
	    private  int  groundColor = Color.TRANSPARENT; //Ĭ��  �ؼ��װ�  ��ɫ
	    private  int  lineColor   = Color.GRAY;  //Ĭ��  �ؼ�  �����  ��ɫ
	    private  float  lineTextSize = 14;     //Ĭ��  �ؼ�  �����������С
	    //private  int  textColor = Color.BLUE;    //Ĭ��  �ؼ� �м�������ɫ
	    private  float  textSize = 20;            //Ĭ��  �ؼ�  �м������С
	    private  boolean  IsShowText = true;    //Ĭ��  �ؼ�  �м�������ʾ
	    private  int  pad = 4;    //Ĭ�Ͽؼ��߾� ����
	 // private  int  xItem_Num = 3;  //Ĭ�� ÿ����״ͼ ������ ����
	    private  int  xNumber = 4;  //Ĭ��  x��̶�����״ͼ   4�� ������
	    private  int  yNumber = 10;  //Ĭ��  y��̶���	   	  
	    private  float  yMaxValue = 100; //Ĭ�� Y�����ֵ
	    private  float  xMaxValue = 4; //Ĭ�� X�����ֵ

	    private  int[]  viewColors = {0xFFF84C4C, 0xFFB666C7,0xFF398D17,0xFF33DF8B,
	    		                     0xFFF6D532,0xFFFeA934,0xFFF933AA,0xFF398DE7};   //Ĭ��  �ؼ� ��״ͼ  ��ɫ����
	    private  List<String> value_lst = null;  //�ַ���ʽ     45-57-39 ����
	    
		private Paint mPaint;
		private RectF mRectf;
		private Axis  mAxis;
			
	    @Override
		protected void dispatchDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.dispatchDraw(canvas);			
			canvas.drawColor(groundColor);
	        //��ȡ  �ؼ��� ����  �˴���ͼ�� һ��Ҫע�� �ؼ�ʹ��ʱʵ�ʵĴ�С   ���Զ���Ŀؼ���С�ߴ�Ĺ�ϵ��
	        int width = getWidth();
	        int height = getHeight();
	       // Log.i("RingPerView", "�ؼ���͸ߣ�"+String.valueOf(width)+"   "+String.valueOf(height));
	             
	        if(value_lst ==null || value_lst.size()==0) return;
	        int num = xNumber;
	        if(value_lst.size()<xNumber) num = value_lst.size();	      
	        for(int i=0;i<num;i++){
	        	String str[] = value_lst.get(i).split("-");
	        	if(str.length < 1) continue;	
	        	int xItem_Num = str.length;   //ÿ��  ����  С���Ӹ���
	        	float xItem_lenth = mAxis.x_unit/(xItem_Num+2); //ÿ��  ����С���� ��Ч����
	        	float xBag_lenth = xItem_lenth * xItem_Num;  //ÿ�� ������Ч����
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
	        	//�Ƿ� ����  �м�������ʾ
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
	    		mAxis.upDataValue(right-left,bottom-top,xNumber,yNumber, xMaxValue,yMaxValue); //������ʱ�����
	    	}
		//    Log.i("Tag>>BarGraphView->onLayout","left:"+String.valueOf(left)+" top:"+String.valueOf(top)
		//        		+" right:"+String.valueOf(right)+" bottom:"+String.valueOf(bottom));
	    }

	    @Override //Mode: EXACTLY ȷ����С    AT_MOST wrap_content    UNSPECIFIED û���ƴ�С
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	      //���� �ӿؼ� mesaure()
			int childCount = getChildCount();
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				measureChild(children, widthMeasureSpec, heightMeasureSpec);
			}
	      
	        //���� ����Ӧ��  ����wrap_content �������Ե����ʹ��  ����   this.setMeasuredDimension(x, y);
	        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//	        Log.i("Tag>>BarGraphView->onMeasure",String.valueOf(widthSize)+"   "+String.valueOf(heightSize));

	        //�ж�  ����ģʽ  �Ƿ���wrap_content �ؼ�Ĭ�ϳߴ�
	        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
	            setMeasuredDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	        }else if(widthMode == MeasureSpec.AT_MOST){
	            setMeasuredDimension(DEFAULT_WIDTH, heightSize);
	        }else if(heightMode == MeasureSpec.AT_MOST){
	            setMeasuredDimension(widthSize, DEFAULT_HEIGHT);
	        }
	    }
		//����invalidate() �ؼ�����->onDraw()���ú���
		public void doInvalidate(){
				this.invalidate();
				mAxis.invalidate();
		}
		//����requestLayout() �װ����->onLayout()���ú���
		public void doRequestLayout(){
				this.requestLayout();
		}
	    
	    //���� ��״ͼ ����
		public void setValue_lst(List<String> lst){
			if(lst == null) return;
			if(value_lst!=null)  value_lst.clear();
			value_lst = lst;
		}
		//���� ��״ͼ ������ɫ
		public void setViewColors(int[] colors){
			if(colors==null) return;
			int num = viewColors.length>colors.length?colors.length:viewColors.length;
			for(int i=0;i<num;i++){
				viewColors[i] = colors[i];
			}
		}
}
