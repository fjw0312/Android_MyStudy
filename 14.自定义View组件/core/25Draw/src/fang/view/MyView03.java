package fang.view;

import com.example.mydraw.R;

import android.R.color;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/****    ���Զ���View ���ڲ��� �Զ���ؼ�    �������� & ������д����
 * �̳�View �Զ���ؼ�   �������Եó���  OK   ����
 * ���Զ���ؼ� ֧�ֲ��ֿ��� wrap/match ���ֲ�������  ���ؼ���Ҫ����һ�����������ļ�/res/values/myview03attr.xml
 * ��� �Զ���Ŀؼ� ����Ҫ���ֲ������� �������趨��һ�����������ļ�,��� ���ֲ�ʹ��wrap_content��������дonMeasure();
 * androidԴ�� �����ؼ��Զ���������˳��
ViewRoot��.performTraversals(){
  final View host = mView;
  ....
  host.measure(childWidthMeasureSpec, childHeightMeasureSpec);
  ....
  host.layout(0, 0 , host.getMeasureWidth(), host.MeasureHeight());
  ....
  draw(fullRedrawNeed);
}
 �����п���д�ķ����У�
 onMeasure()
 layout() --onlayout()  ����androidԴ���ʵ��
 draw()   --onDraw()    ����androidԴ���ʵ��
 
 
 ����Measure������д ����5������
 ����onlayout()  onDraw() ��androidԴ���ʵ�֡��� �������Զ��� ��д�÷�����
 �������Զ��� ��д�ķ������ã�
   onMeasure();(�����ڲ�super.onMeasure(widthMeasureSpec, heightMeasureSpec);����)
   onlayout(); 
   onDraw();
 ���У� 
 layout()ͨ�� ����ԭ�й��췽��super.layout(l, t, r, b); ����->onlayout(); 
 draw()ͨ�� ����ԭ�й��췽��super.onDraw(Canvas canvas); ����->onDraw(); 
 * 
 * onMeasure()��д�ķ����� ֻ�����ԭ�еĹ���super.onMeasure(widthMeasureSpec, heightMeasureSpec);
 * ����ʹ�� ��view�Զ���Ŀؼ� ���ֲ���  ֻ������match_content
 * ���Ҫ������ wrap_content Ӧ�����.setMeasureDimension()
 * 
 * 
 * */
public class MyView03 extends View{
	//����1
	public MyView03(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		Log.i("MyView01>MyView03","into-1");
		init_Parmas(context, attrs);
	}
	//����2
	public MyView03(Context context,  AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		Log.i("MyView01>MyView03","into-2");
		init_Parmas(context, attrs);

	}
	//����3
	public MyView03(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Log.i("MyView01>MyView03","into-3");
		
	}
	
	//��ȡ  �Զ��� �ؼ�������    �����ʹ��layout.xml���벼�ֲ��� �����ڴ��Զ�������� һЩ.setTextSize()�������������á�
	private void init_Parmas(Context context, AttributeSet attrs){
		TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.myView03);//��ȡ  �Զ��� �ؼ�������
		if(arr != null){
			textSize = arr.getDimension(R.styleable.myView03_textSize, textSize);
			textColor = arr.getColor(R.styleable.myView03_textColor, textColor);
			viewColor = arr.getColor(R.styleable.myView03_viewColor, viewColor);
			arr.recycle();
		}
	}

	//����ؼ�   Ĭ�ϻ����ߴ�
	private static final int DEFAULT_WIDTH = 130;   //Ĭ�� �ؼ� ���200 
	private static final int DEFAULT_HEIGHT = 80;  //Ĭ�� �ؼ� �߶�150  
	
	private  float textSize = 15;  //Ĭ�� �ؼ� �����С
	private   int  textColor = Color.RED;  //Ĭ��  �ؼ� ������ɫ
	private   int  viewColor = Color.BLUE;   //Ĭ��  �ؼ�  ��ɫ 
	private   int  groundColor = Color.GREEN; //Ĭ��  �ؼ��װ�  ��ɫ 
	private   int   padding = 10;  //Ĭ��  �ؼ� ���ؼ�϶
//	private   int groundColor = 0x00000000; //Ĭ��  �ؼ��װ�  ��ɫ 
	
	

	   @Override   //���� ������д
		public void draw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.draw(canvas);
		}
		@Override  //���� ������д
		public void layout(int l, int t, int r, int b) {
			// TODO Auto-generated method stub
			super.layout(l, t, r, b);
		}
		
		
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			
			canvas.drawColor(groundColor); 
			//��ȡ  �ؼ��� ����  �˴���ͼ�� һ��Ҫע�� �ؼ�ʹ��ʱʵ�ʵĴ�С   ���Զ���Ŀؼ���С�ߴ�Ĺ�ϵ��
			int width = getWidth();
			int height = getHeight();
		
			//���廭��
			Paint mPaint = new Paint();
			mPaint.setAntiAlias(true);
			
			mPaint.setColor(viewColor);
			canvas.drawRect(padding, padding, width-padding, height-padding, mPaint);    //������ 
			
			mPaint.setColor(textColor);
			mPaint.setTextSize(textSize);
			mPaint.setTextAlign(Paint.Align.CENTER); //���û�����Ļ�׼��  Ϊ�����׼�»����е㡣
			canvas.drawText("MyView03", width/2.0f, height/2.0f+textSize*0.6f/2.0f, mPaint);  //λ�� �ߴ粻��д��  Ҫ�ۺ� �ؼ�ʵ��ʹ�ô�С�Ĺ�ϵ��
		}
		@Override
		protected void onLayout(boolean changed, int left, int top, int right,
				int bottom) {
			// TODO Auto-generated method stub
			super.onLayout(changed, left, top, right, bottom);
//			Log.i("MyView02>onLayout","f-2");
		}
		@Override  //Mode: EXACTLY ȷ����С    AT_MOST wrap_content    UNSPECIFIED û���ƴ�С
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// TODO Auto-generated method stub			          //���ȷ����С200dp  Ҳ��ʵ��
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);//�� wrap or fill/match ʱ��������
			    //���� ����Ӧ��  ����wrap_content �������Ե����ʹ��  ����   this.setMeasuredDimension(x, y);                                              
			int widthMode = MeasureSpec.getMode(widthMeasureSpec);		
			int heightMode = MeasureSpec.getMode(heightMeasureSpec);
			int widthSize = MeasureSpec.getSize(widthMeasureSpec); 
			int heightSize = MeasureSpec.getSize(heightMeasureSpec);
			
			//�ж�  ����ģʽ  �Ƿ���wrap_content �ؼ�Ĭ�ϳߴ�
			if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
				setMeasuredDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);	
			}else if(widthMode == MeasureSpec.AT_MOST){
				setMeasuredDimension(DEFAULT_WIDTH, heightSize);	
			}else if(heightMode == MeasureSpec.AT_MOST){
				setMeasuredDimension(widthSize, DEFAULT_HEIGHT);
			}	
		}

}
