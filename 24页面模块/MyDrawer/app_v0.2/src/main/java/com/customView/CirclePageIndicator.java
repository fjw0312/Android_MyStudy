package com.customView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by fjw0312 on 2017/10/18.
 * ViewPage Indicator
 */

public class CirclePageIndicator extends View {
    public CirclePageIndicator(Context context) {
        super(context);
        init_view(context);
    }

    public CirclePageIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init_view(context);
    }

    public CirclePageIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init_view(context);
    }
    //初始化
    private void init_view(Context context){
        mContext = context;
        mPaint = new Paint();
    //    mPaint.setColor(sideColor);
    //    mPaint.setStyle(Paint.Style.STROKE); //设置不填充模式
    //  mPaint.setStrokeWidth(sideSize); //设置线宽
    }


    //定义控件   默认基本尺寸
    private static  int DEFAULT_WIDTH = 100;   //默认 控件 宽度200
    private static  int DEFAULT_HEIGHT = 30;  //默认 控件 高度30

    Context mContext;
    Activity mActivity;
    Paint mPaint;
    public  int  CircleNum = 4;  //小圆点个数
    public  int  selectItem = 0; //选中的圆点
    public  int  sideColor = Color.GRAY;  //边沿颜色
    public  int  selectColor = Color.RED;  //选中圆点颜色
    public  int  backgroundColor = Color.WHITE;
    public  float  radius = 10;  //默认半径
    public  float   sideSize = 1.0f;  //边沿圆圈 线条大小

    private int oldState = -1;


    //----------对 外  API----------------
    public void setBackgroundColor(int color){
        backgroundColor = color;
    }
    public void setSideColor(int color){
        sideColor = color;
    }
    public void setSelectColor(int color){
        selectColor = color;
    }
    public void setCircleNum(int num){
        CircleNum = num;
    }
    public void setSelectItem(int selectId){
        if(selectId>CircleNum) return;
        selectItem = selectId;
        this.postInvalidate(); //请求刷新
    }
    public void setActivity(Activity activity){
        mActivity = activity;
    }

    //根据viewPager 获取页面数  和滑动页面监听
    public void setViewPager(ViewPager viewPager){
        if(viewPager == null) return;
        CircleNum = viewPager.getAdapter().getCount();
        this.invalidate();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.i("CirclePageIndicator",String.valueOf(position)+"  "+String.valueOf(positionOffset)+"  "+String.valueOf(positionOffsetPixels));
            }

            @Override
            public void onPageSelected(int position) {
                selectItem = position;
                CirclePageIndicator.this.invalidate();

            }

            @Override  //切页 1->2->0  最后一页 1->0
            public void onPageScrollStateChanged(int state) {
               // Log.i("CirclePageIndicator",String.valueOf(state));
                if(oldState==1 && state ==0 && selectItem == CircleNum-1){  //判断最后一页  滑不动
                    if(mActivity!=null){
                      //  Intent intent = new Intent(mActivity, MainActivity.class);
                      //  mActivity.startActivity(intent);
                      //  mActivity.finish();  //关闭 Activity
                    }
                }
                oldState  = state;
            }
        });
    }






    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
  //      canvas.drawColor(Color.BLACK);
        //获取  控件的 长宽  此处画图形 一定要注意 控件使用时实际的大小   与自定义的控件最小尺寸的关系。
        int width = getWidth();
        int height = getHeight();

        //画 圆点
        if(CircleNum==0) return;
        int x = width/CircleNum;
        mPaint.setStyle(Paint.Style.FILL); //设置不填充模式
        mPaint.setColor(backgroundColor);
        for(int i=0;i<CircleNum;i++){
            canvas.drawCircle(i*x+x/2,height/2, radius, mPaint);  //画空点底板
        }
        mPaint.setStyle(Paint.Style.FILL); //设置不填充模式
        mPaint.setColor(selectColor);
        canvas.drawCircle(selectItem*x+x/2,height/2, radius, mPaint);  //画空点
        mPaint.setStyle(Paint.Style.STROKE); //设置不填充模式
        mPaint.setColor(sideColor);
        for(int i=0;i<CircleNum;i++){
            canvas.drawCircle(i*x+x/2,height/2, radius, mPaint);  //画空心点
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /* *
     * 它有三种模式：UNSPECIFIED(未指定),父元素部队自元素施加任何束缚，子元素可以得到任意想要的大小;
     * EXACTLY(完全)，父元素决定自元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小；
     * AT_MOST(至多)，子元素至多达到指定大小的值。
     * */
    @Override  //Mode: EXACTLY 确定大小    AT_MOST wrap_content    UNSPECIFIED 没限制大小
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);  //父View建议的长宽.

        //所以 以下应该  处理wrap_content 这种属性的情况使用  方法   this.setMeasuredDimension(x, y);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        //判断  布局模式  是否有wrap_content 控件默认尺寸
        DEFAULT_WIDTH = CircleNum *50+50;
        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(DEFAULT_WIDTH, heightSize);
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize, DEFAULT_HEIGHT);
        }

    }
}
