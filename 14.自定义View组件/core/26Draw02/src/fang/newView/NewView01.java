package fang.newView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;



/**    �Զ���ؼ�---�����̳����пؼ� ����չ
 *   ͨ���̳����пؼ�TextView ʵ�ֶ������κ�������ɫ��Ⱦ������Ⱦ�ƶ�
 *   �������裺����û�иı�layout�Ĵ�С�����Կ��Բ���Ҫ��дonMeasure  onlayout
 *   1.��дonDraw() �����Ϳ����ˣ�  ������û��ʹ�� ���ֲ��������ļ�����  ������
 * 
 *   ������ ʹ������Ⱦ��LinearGradient   �ƶ���Matrix   ��ע�⡣
 * 
 * */
public class NewView01 extends TextView{

	public NewView01(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init_view();
	}

	public NewView01(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_view();
	}

	public NewView01(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init_view();
	}

	Paint mPaint;
	int mTranslate = 0;
	LinearGradient linearGradient; //���� ��Ⱦ��
	Matrix mMatrix;
	
	private void init_view(){
		mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
		mPaint.setTextSize(20);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
	
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		Log.i("NewView01>onSizeChanged","into -1");
		super.onSizeChanged(w, h, oldw, oldh);
		Log.i("NewView01>onSizeChanged","into -2");
		// ���� ��Щ���÷���onDraw()�Ϳ��ԣ����� ��ȾҪ���沢ѭ����������������ǰ����
		int width = getWidth(); 
		int height = getHeight();
		Log.i("NewView01>onDraw","into width="+width+"   height"+height);
		//������Ⱦ
		linearGradient = new LinearGradient(0,0,width,0,
				new int[]{Color.BLACK,Color.RED,Color.GREEN,Color.BLUE,Color.WHITE},
		        null, Shader.TileMode.CLAMP);
		mMatrix = new Matrix();
	}
    
	@Override  //��д onDraw()
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawColor(Color.YELLOW); //���õװ���ɫ  Ӧ����ԭ���ؼ� ǰ
		int width = getWidth(); 
		int height = getHeight();

		getPaint().setShader(linearGradient);  //�� ����Ļ���  �����Ⱦ��
		super.onDraw(canvas); 
		

		RectF recf = new RectF(0.0f,0.0f,(float)width,(float)height);
		canvas.drawRoundRect(recf, width/4.0f, height/4.0f, mPaint); 
		
		mTranslate += 20;
		if(mTranslate > width) mTranslate = 0;		
		mMatrix.setTranslate(mTranslate, 0);  //����ƽ��
		linearGradient.setLocalMatrix(mMatrix); //������Ⱦ��ƽ��
		postInvalidateDelayed(300);   //��ʱ200ms    �ٴ�ˢ��ui  ����onDraw()
		Log.i("NewView01>onDraw","into -2"); 
	}
	
	@Override  //���ı�layout ���Բ�����д
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override  //�ڲ�ʹ��wrap_content ���ı�layout ʱ  ���Բ�����д
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
