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
 * �Զ��� �ؼ���    �̳�View
 * ���ܣ� ������״ͼ  
 * ʹ�ó����� ÿ�鹦�ʶԱ�ͼ�ȡ� ÿ���������������������� 
 * made by��fjw0312  date:2017.11.18
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
	

	
	//��ȡ  �Զ��� �ؼ�������    �����ʹ��layout.xml���벼�ֲ��� �����ڴ��Զ�������� һЩ.setTextSize()�������������á�
	private void init_Parmas(Context context, AttributeSet attrs){
		
		TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.HorizontalBarView);//��ȡ  �Զ��� �ؼ�������
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
        mPaint.setStyle(Style.FILL);  //���� ����  ʵ��
        mPaint.setTextSize(textSize);  //�������� ��С
        value_lst = new ArrayList<String>();    
        
       // value_lst.add("12-38-26-34-54-35-61");
       // value_lst.add("45-63-80-49-59-50-32");
       // value_lst.add("60-39-50-41-52-30-52");    
       // value_lst.add("40-59-55-29-49-30-52"); 

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
	    private  int  pad = 10;    //Ĭ�Ͽ�s���߾� ����
	    private  int  pad_tabX = 20;  //Ĭ�� x�� ������С

	    private  float  xMaxValue = 100; //Ĭ�� X�����ֵ

	    private  int[]  viewColors = {0xFFF84C4C, 0xFFB666C7,0xFF398D17,0xFF33DF8B,
	    		                     0xFFF6D532,0xFFFeA934,0xFFF933AA,0xFF398DE7};   //Ĭ��  �ؼ� ��״ͼ  ��ɫ����
	    private  List<String> value_lst = null;  //�ַ���ʽ     45-57-39 ����
	    
		private Paint mPaint;
		private RectF mRectf;
			
		@Override
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);	
	        canvas.drawColor(groundColor);
	       
	        //��ȡ  �ؼ��� ����  �˴���ͼ�� һ��Ҫע�� �ؼ�ʹ��ʱʵ�ʵĴ�С   ���Զ���Ŀؼ���С�ߴ�Ĺ�ϵ��
	        int width = getWidth() - pad_tabX ;
	        int height = getHeight() - pad;
	       // Log.i("RingPerView", "�ؼ���͸ߣ�"+String.valueOf(width)+"   "+String.valueOf(height));
	            
	        if(value_lst ==null || value_lst.size()==0) return;
	       int xNumber = value_lst.size();
	        float x_start = pad_tabX;
	        float x_end =   x_start + width;      
	        float y_end =  pad/2;
	        float y_start = height+y_end;
	        float y_unit = height/(xNumber+1);
	        canvas.drawLine(x_start, y_start, x_start, y_end, mPaint); //y��
	        for(int i=1;i<xNumber+1;i++){  //y��̶� 
				float y = y_start - y_unit*i;
				canvas.drawLine(x_start, y, x_start+5, y, mPaint); //5  �̶ȳ���
			}
	        //���� ���� 
	        RectF rectf1 = new RectF();
	        float x_dataUnit = width/xMaxValue;
	        for(int i=0;i<xNumber;i++){
	        	String str[] = value_lst.get(i).split("-");
	        	if(str.length < 1) continue;	
	        	int yItem_Num = str.length;   //ÿ��  ����  С���Ӹ���
	        	float yItem_width = y_unit/(yItem_Num+2); //ÿ��  ����С���� ��Ч���
	        	float yBag_width = yItem_width * yItem_Num;  //ÿ�� ������Ч���
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
	        		
	        	//�Ƿ� ����  �м�������ʾ 
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

	    @Override //Mode: EXACTLY ȷ����С    AT_MOST wrap_content    UNSPECIFIED û���ƴ�С
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	      
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
