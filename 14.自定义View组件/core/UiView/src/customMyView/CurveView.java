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
 * �Զ��� �ؼ���    �̳�ViewGroup
 * ���ܣ� ������  
 * ʹ�ó����� �¶Ȳ���  pue���ߡ�
 * made by��fjw0312  date:2017.11.16
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
	

	
	//��ȡ  �Զ��� �ؼ�������    �����ʹ��layout.xml���벼�ֲ��� �����ڴ��Զ�������� һЩ.setTextSize()�������������á�
	private void init_Parmas(Context context, AttributeSet attrs){
		
		TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.CurveView);//��ȡ  �Զ��� �ؼ�������
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
        mPaint.setAntiAlias(true); // ���û��ʵľ��Ч��
        mPaint.setStyle(Style.STROKE);  //���� ���ʿ���
        mPaint.setTextSize(textSize);  //�������� ��С       
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
		  //����ؼ�   Ĭ�ϻ����ߴ�
	    private static final int DEFAULT_WIDTH = 500;   //Ĭ�� �ؼ� ���200
	    private static final int DEFAULT_HEIGHT = 400;  //Ĭ�� �ؼ� �߶�150
	    
	    private  int  groundColor = Color.TRANSPARENT; //Ĭ��  �ؼ��װ�  ��ɫ
	    private  int  lineColor   = Color.GRAY;  //Ĭ��  �ؼ�  �����  ��ɫ
	    private  float  lineTextSize = 14;     //Ĭ��  �ؼ�  �����������С
	    private  int  dotColor = Color.CYAN;    //Ĭ��  �ؼ�СԲ����ɫ
	    private  int  fillColor = Color.LTGRAY;  ////Ĭ��  �ؼ������·�������ɫ
	    private  int  fillendColor = Color.DKGRAY;  ////Ĭ��  �ؼ������·�������ɫ
	    private  float  textSize = 20;            //Ĭ��  �ؼ�  �м������С
	    private  boolean  IsShowText = false;    //Ĭ��  �ؼ�  �м�������ʾ
	    private  boolean  IsFillShadow = true;   //Ĭ��  �ؼ�   �Ƿ������Ӱ
	    
	    private  int  pad = 4;    //Ĭ�Ͽؼ��߾� ����
	    private  int  xNumber = 12;  //Ĭ��  x��̶�����״ͼ   4�� ������
	    private  int  yNumber = 10;  //Ĭ��  y��̶���	   	  
	    private  float  yMaxValue = 100; //Ĭ�� Y�����ֵ
	    private  float  xMaxValue = 24; //Ĭ�� X�����ֵ

	  //  private  int[]  viewColors = {0xFFF84C4C, 0xFFB666C7,0xFF398D17,0xFF33DF8B,
	  //  		                     0xFFF6D532,0xFFFeA934,0xFFF933AA,0xFF398DE7};   //Ĭ��  �ؼ� ��״ͼ  ��ɫ����
	    
	    //ע�� ����ֵ���� һ��Ҫ��x����С��������  ��������ߴ���
	    private  List<String> value_lst = null;  //�ַ���ʽ     10-27 ����   x-y ��Բ��Ϊ0
	    private int minRadus = 2;  //СԲ��ֱ��
	    
		private Paint mPaint;
		private Axis  mAxis;
		
		/* ���߻���  ����  �����������㷨   ---Ŀǰδʵ��
		 * 	path.reset(); //����·��	
		 * path.quadTo(k_node_x, k_node_y, node_x, node_y); //or path.cubicTo();
		 * canvas.drawPath(path, mPaint);
		 * @see android.view.ViewGroup#dispatchDraw(android.graphics.Canvas)
		 */
	    @Override 
		protected void dispatchDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.dispatchDraw(canvas);			
			canvas.drawColor(groundColor);
	        //��ȡ  �ؼ��� ����  �˴���ͼ�� һ��Ҫע�� �ؼ�ʹ��ʱʵ�ʵĴ�С   ���Զ���Ŀؼ���С�ߴ�Ĺ�ϵ��
	        int width = getWidth();
	        int height = getHeight();
	       // Log.i("RingPerView", "�ؼ���͸ߣ�"+String.valueOf(width)+"   "+String.valueOf(height));
			float pre_x = 0;
			float pre_y = 0;
			//�� ������
			Path path = new Path();
			path.reset(); //����·��
			path.moveTo(mAxis.x_start, mAxis.y_start);
					
	        if(value_lst ==null || value_lst.size()==0) return;  
	        //���Կ����޶�value_lst������������С
	        for(int i=0;i<value_lst.size();i++){
	        	String str[] = value_lst.get(i).split("-");
	        	if(str.length != 2) continue;	
	        	float x_value = Float.parseFloat(str[0]);
	        	float y_value = Float.parseFloat(str[1]);
	        	//����Բ��
	        	float node_x = mAxis.x_start+mAxis.x_per_unit*(x_value);
				float node_y = mAxis.y_start-mAxis.y_per_unit*y_value;		
				canvas.drawCircle(node_x, node_y,minRadus, mPaint); // ������ֵ��
				
				path.lineTo(node_x, node_y); //�������
				
				//���� ����
				if(i!=0){
					canvas.drawLine(pre_x,pre_y,node_x,node_y,mPaint);			
				}									
				
				pre_x = node_x;
				pre_y = node_y;
				
	        	//�Ƿ� ����  �м�������ʾ
	        	if(IsShowText){
	        			canvas.drawText(str[1], node_x, node_y, mPaint);
	        	}       	
	        }
	        if(IsFillShadow){
	        	path.lineTo(pre_x, mAxis.y_start); //�������
		        path.lineTo(mAxis.x_start, mAxis.y_start); //���߻�ԭ��
		        Paint paint = new Paint();
		        mPaint.setStyle(Style.FILL);  //���� ���ʿ���  
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
	    		mAxis.upDataValue(right-left,bottom-top,xNumber,yNumber, xMaxValue,yMaxValue); //������ʱ�����
	    	}
	//	    Log.i("Tag>>CurveView->onLayout","left:"+String.valueOf(left)+" top:"+String.valueOf(top)
	//	        		+" right:"+String.valueOf(right)+" bottom:"+String.valueOf(bottom));
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
		
}
