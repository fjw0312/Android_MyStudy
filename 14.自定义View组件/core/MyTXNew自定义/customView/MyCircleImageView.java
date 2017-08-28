package TXNews.customView;

import mybroadcast.MyBroadcastReceiver;
import TXNews.Main.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;



/***
 * �Զ���Բ�� ͼƬ
 * author:fjw0312@163.com
 * date:2017.8.10
 * 
 * Ŀǰ ���������⣺ ���ܽ�ͼƬ ��С��Ӧ��Բ��  ������gravity 
 * */
public class MyCircleImageView extends ImageView{


	public MyCircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyCircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyCircleImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	Paint mPaint = new Paint();
	
	@Override
	protected void onDraw(Canvas canvas) { 
		// TODO Auto-generated method stub	
	//	canvas.drawColor(Color.GREEN);
		int W = getWidth(); 
		int H = getHeight();
		int min = W>H?H:W;
		
		//1.���ԭʼͼƬ
		Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap(); //���λͼ		
		if(bitmap==null){
			super.onDraw(canvas);
			return;
		}
				
		//2.�½�ͼƬ�ֲ�-���Բ��ͼƬ 
		Bitmap CutBitmap = Bitmap.createBitmap(min, min, Config.ARGB_8888); //����һ�� �յ�bitmap
		Canvas bitmapCanvas = new Canvas(CutBitmap);  //new һ��bitmap��С��  Mycanvas	
	//	bitmapCanvas.drawCircle(min / 2, min / 2, min / 2, mPaint);  //���� Mycanvas ͼ�� 
		bitmapCanvas.drawOval(new RectF(0, 0, min, min), mPaint);  
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); //��ͼ�� ���������õ�ͼƬ��ϼ���
		bitmapCanvas.drawBitmap(bitmap, 0, 0, mPaint); //�� bitmapͼ ����Mycanvas  
	
		 //�����кõ�ͼƬ ����canvas
		canvas.drawBitmap(CutBitmap, 0, 0, mPaint);               //�����кõ�ͼƬ ����canvas
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}


}
