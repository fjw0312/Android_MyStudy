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
 * 自定义圆形 图片
 * author:fjw0312@163.com
 * date:2017.8.10
 * 
 * 目前 还存在问题： 不能将图片 缩小适应到圆形  并布局gravity 
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
		
		//1.获得原始图片
		Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap(); //获得位图		
		if(bitmap==null){
			super.onDraw(canvas);
			return;
		}
				
		//2.新建图片罩层-获得圆形图片 
		Bitmap CutBitmap = Bitmap.createBitmap(min, min, Config.ARGB_8888); //定义一个 空的bitmap
		Canvas bitmapCanvas = new Canvas(CutBitmap);  //new 一个bitmap大小的  Mycanvas	
	//	bitmapCanvas.drawCircle(min / 2, min / 2, min / 2, mPaint);  //绘制 Mycanvas 图形 
		bitmapCanvas.drawOval(new RectF(0, 0, min, min), mPaint);  
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); //将图形 与下面设置的图片组合剪切
		bitmapCanvas.drawBitmap(bitmap, 0, 0, mPaint); //将 bitmap图 放入Mycanvas  
	
		 //将剪切好的图片 放入canvas
		canvas.drawBitmap(CutBitmap, 0, 0, mPaint);               //将剪切好的图片 放入canvas
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
