package fang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/****    ���Զ���View ���ڲ��� �Զ���ؼ�(����ʹ��)  �������� & ������д����
 * �̳�View �Զ���ؼ�   �������Եó���
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
public class MyView02 extends View{
	//����1
	public MyView02(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		Log.i("MyView01>MyView02","into-1");
	}
	//����2
	public MyView02(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		Log.i("MyView01>MyView02","into-2");
	}
	//����3
	public MyView02(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Log.i("MyView01>MyView02","into-3");
	}

	//����ؼ�   Ĭ�ϻ����ߴ�
	private static final int DEFAULT_WIDTH = 130;   //Ĭ�� �ؼ� ���200
	private static final int DEFAULT_HEIGHT = 80;  //Ĭ�� �ؼ� �߶�150

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
			
			canvas.drawColor(Color.BLUE); 
			//��ȡ  �ؼ��� ����  �˴���ͼ�� һ��Ҫע�� �ؼ�ʹ��ʱʵ�ʵĴ�С   ���Զ���Ŀؼ���С�ߴ�Ĺ�ϵ��
			int width = getWidth();
			int height = getHeight();
			int pad = 10;
			//���廭��
			Paint mPaint = new Paint();
			mPaint.setColor(Color.RED);
			mPaint.setTextSize(15); 			
			canvas.drawRect(pad, pad, width-pad, height-pad, mPaint);    //������ 
			mPaint.setColor(Color.YELLOW);
			canvas.drawText("MyView02", 30, 30, mPaint);  //λ�� �ߴ粻��д��  Ҫ�ۺ� �ؼ�ʵ��ʹ�ô�С�Ĺ�ϵ��
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
				Log.i("MyView02>onMeasure", widthSize+"---"+heightSize);
				setMeasuredDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);	
			}else if(widthMode == MeasureSpec.AT_MOST){
				setMeasuredDimension(DEFAULT_WIDTH, heightSize);	
			}else if(heightMode == MeasureSpec.AT_MOST){
				setMeasuredDimension(widthSize, DEFAULT_HEIGHT);
			}	
		}

}
