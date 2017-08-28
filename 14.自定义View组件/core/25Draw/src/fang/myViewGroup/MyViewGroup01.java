package fang.myViewGroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/****    ���Զ���ViewGroup Ŀǰû��ʹ�����Բ����ļ� ������ֵ���� 
 * ���ؼ�  ���֣� ����view ���ȿ�ȡ�������view��������ϵ�������������
 * 
 * ��Ҫ ��дonLayout()  onMeasure();
 */
public class MyViewGroup01 extends ViewGroup{ 

	public MyViewGroup01(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyViewGroup01(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyViewGroup01(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	private   int  groundColor = Color.GREEN; //Ĭ��  �ؼ��װ�  ��ɫ 
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Log.i("MyViewGroup01>dispatchDraw", "into-1" );
		canvas.drawColor(groundColor);  //���� �װ�  ������super.dispatchDraw(canvas); ֮ǰ�Ų�������view
		super.dispatchDraw(canvas); //���ڲ����� ���addView ��ӵ���view ����
		
		Log.i("MyViewGroup01>dispatchDraw", "into-2" );
	}
	
	@Override   //���� ���֤��  û�н���ú���
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Log.i("MyViewGroup01>onDraw", "into-1" );
		super.onDraw(canvas);
		canvas.drawColor(groundColor); 
		Log.i("MyViewGroup01>onDraw", "into-2" );
	}
	
	@Override  //������д
	protected void onLayout(boolean arg0, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int preHeight = 0;
		//���� �ӿؼ� 
		for(int i=0;i<getChildCount();i++){
			View children = getChildAt(i);
			int cHeight = children.getMeasuredHeight();
			if(children.getVisibility() != View.GONE){
				children.layout(l, preHeight, r, preHeight +=cHeight);//��view ���� ���߶ȵ�
			}
		}
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//���� �ӿؼ� mesaure()
		int childCount = getChildCount();
		for(int i=0;i<childCount;i++){
			View children = getChildAt(i);
			measureChild(children, widthMeasureSpec, heightMeasureSpec);
		}
		
		//�ж� ����ģʽ   ֧��wrap_content
		int mMaxWidth = 0;
		int mMaxHeight = 0;
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);		
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec); 
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
			for(int i=0;i<childCount;i++){  //Ҫ�ο����ַ�ʽ ����
				View children = getChildAt(i);	
				mMaxWidth = Math.max(mMaxWidth, children.getMeasuredWidth());
				mMaxHeight += children.getMeasuredHeight();
			}
			setMeasuredDimension(mMaxWidth, mMaxHeight);	
		}else if(widthMode == MeasureSpec.AT_MOST){
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				mMaxWidth = Math.max(mMaxWidth, children.getMeasuredWidth());
			}
			setMeasuredDimension(mMaxWidth, heightSize);	
		}else if(heightMode == MeasureSpec.AT_MOST){
			for(int i=0;i<childCount;i++){
				View children = getChildAt(i);
				mMaxHeight += children.getMeasuredHeight();
			}
			setMeasuredDimension(widthSize, mMaxHeight);	
		}
	}

}
