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



/**    自定义控件---二、继承现有控件 ，拓展
 *   通过继承现有控件TextView 实现对其外形和字体颜色渲染，并渲染移动
 *   方法步骤：由于没有改变layout的大小，所以可以不需要重写onMeasure  onlayout
 *   1.重写onDraw() 基本就可以了，  本案例没有使用 布局参数属性文件传入  参数。
 * 
 *   本案例 使用了渲染器LinearGradient   移动器Matrix   可注意。
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
	LinearGradient linearGradient; //线性 渲染器
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
		// 本来 这些设置放在onDraw()就可以，但是 渲染要保存并循环滑动，所以需提前定义
		int width = getWidth(); 
		int height = getHeight();
		Log.i("NewView01>onDraw","into width="+width+"   height"+height);
		//设置渲染
		linearGradient = new LinearGradient(0,0,width,0,
				new int[]{Color.BLACK,Color.RED,Color.GREEN,Color.BLUE,Color.WHITE},
		        null, Shader.TileMode.CLAMP);
		mMatrix = new Matrix();
	}
    
	@Override  //重写 onDraw()
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawColor(Color.YELLOW); //设置底板颜色  应该在原生控件 前
		int width = getWidth(); 
		int height = getHeight();

		getPaint().setShader(linearGradient);  //将 父类的画笔  添加渲染器
		super.onDraw(canvas); 
		

		RectF recf = new RectF(0.0f,0.0f,(float)width,(float)height);
		canvas.drawRoundRect(recf, width/4.0f, height/4.0f, mPaint); 
		
		mTranslate += 20;
		if(mTranslate > width) mTranslate = 0;		
		mMatrix.setTranslate(mTranslate, 0);  //设置平移
		linearGradient.setLocalMatrix(mMatrix); //设置渲染器平移
		postInvalidateDelayed(300);   //延时200ms    再次刷新ui  进入onDraw()
		Log.i("NewView01>onDraw","into -2"); 
	}
	
	@Override  //不改变layout 可以不用重写
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override  //在不使用wrap_content 并改变layout 时  可以不用重写
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
