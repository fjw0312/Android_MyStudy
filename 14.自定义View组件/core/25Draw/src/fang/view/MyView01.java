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
 * ����ʹ�� ��view�Զ���Ŀؼ� ���ֲ���  ֻ������ wrap_content
 * ���Ҫ������ wrap_content Ӧ�����.setMeasureDimension()
 * 
 * 
 * */
public class MyView01 extends View{
	//����1
	public MyView01(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		Log.v("MyView01>MyView01","into-1");
	}
	//����2
	public MyView01(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		Log.v("MyView01>MyView01","into-2");
	}
	//����3
	public MyView01(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Log.v("MyView01>MyView01","into-3");
	}

		
	   @Override
		public void draw(Canvas canvas) {
			// TODO Auto-generated method stub
		    Log.v("MyView01>draw","f-1");
			super.draw(canvas);
		    Log.v("MyView01>draw","f-2");
		}
		@Override
		public void layout(int l, int t, int r, int b) {
			// TODO Auto-generated method stub
			Log.v("MyView01>layout","f-1");
			super.layout(l, t, r, b);
			Log.v("MyView01>layout","f-2");
		} 
		
			
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub 
			Log.v("MyView01>onDraw","f-1");
			super.onDraw(canvas);
			Log.v("MyView01>onDraw","f-2");
			//���廭��
			Paint mPaint = new Paint();
			mPaint.setColor(Color.RED);
			mPaint.setTextSize(20);
			canvas.drawText("MyView01", 20, 20, mPaint);
		}
		@Override
		protected void onLayout(boolean changed, int left, int top, int right,
				int bottom) {
			// TODO Auto-generated method stub 
			Log.v("MyView01>onLayout","f-1");
			super.onLayout(changed, left, top, right, bottom);
			Log.v("MyView01>onLayout","f-2");
		}
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// TODO Auto-generated method stub
			Log.v("MyView01>onMeasure","f-1");
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			Log.v("MyView01>onMeasure","f-2");
		}

}
