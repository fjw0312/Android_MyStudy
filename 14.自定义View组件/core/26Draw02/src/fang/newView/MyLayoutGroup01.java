package fang.newView;

import com.example.mydraw.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



/*** �Զ���ؼ�---������϶���ؼ����� �Զ���ؼ�
 *   //��Ҫ ʵ�ֽ�layout ������ӵ� �Զ����view �ؼ���
 * 		LayoutInflater.from(context).inflate(R.layout.view_layout, this);
		//��ȡ�ؼ�
		leftButton = (Button)findViewById(R.id.title_bar_left);
		rightButton = (Button)findViewById(R.id.title_bar_right);
		textView = (Button)findViewById(R.id.title_bar_title);
		//���ü���
		leftButton.setOnClickListener(l);
		rightButton.setOnClickListener(l);
 * 
 * 
 * */
public class MyLayoutGroup01 extends LinearLayout{ 

	public MyLayoutGroup01(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub 
		init_view(context);
		init_Parmas(context, attrs);  
		init_value();
	}

	public MyLayoutGroup01(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_view(context);
		init_Parmas(context, attrs);
		init_value();
	}

	public MyLayoutGroup01(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init_view(context);
	}

	//���� �����ӿؼ�
	Button leftButton;
	Button rightButton;
	TextView textView;
	//����  �ؼ�����
    int backgroundColor; 
    String text = "";
    int textColor;
    int textDrawable;
    boolean leftButtonVisible;
    boolean rightButtonVisible;
    String leftButton_text ="Left";
    int leftButton_textColor;
    int leftButton_Drawable;
    String rightButton_text ="Right";
    int rightButton_textColor;
    int rightButton_Drawable;
	
	//��ȡ�������� ����
	private void init_Parmas(Context context, AttributeSet attrs){
		TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.myTitleView);//��ȡ  �Զ��� �ؼ�������
		if(arr != null){ 

		   backgroundColor = arr.getColor(R.styleable.myTitleView_backgroundColor, Color.GREEN); 
		   text = arr.getString(R.styleable.myTitleView_text); 
		   textColor = arr.getColor(R.styleable.myTitleView_textColor, Color.WHITE);
		   textDrawable = arr.getResourceId(R.styleable.myTitleView_textDrawable, -1);
		   leftButtonVisible = arr.getBoolean(R.styleable.myTitleView_leftButtonVisible, true);
		   rightButtonVisible = arr.getBoolean(R.styleable.myTitleView_rightButtonVisible, true);
		   leftButton_text = arr.getString(R.styleable.myTitleView_leftButton_text);
		   leftButton_textColor = arr.getColor(R.styleable.myTitleView_leftButton_textColor, Color.WHITE);
		   leftButton_Drawable = arr.getResourceId(R.styleable.myTitleView_leftButton_Drawable, -1);
		   rightButton_text = arr.getString(R.styleable.myTitleView_rightButton_text);
		   rightButton_textColor = arr.getColor(R.styleable.myTitleView_rightButton_textColor, Color.WHITE);
		   rightButton_Drawable = arr.getResourceId(R.styleable.myTitleView_rightButton_Drawable, -1);
		     
			arr.recycle(); 
		}
	}
	//��ʼ��  ��ֵ
	private void init_value(){
	//	setBackgroundResource(backgroundColor);
		setBackgroundColor(backgroundColor);
		textView.setText(text);
		textView.setTextColor(textColor);
		if(textDrawable != -1){
			textView.setBackgroundResource(textDrawable);
		}	
		leftButton.setText(leftButton_text);
		leftButton.setTextColor(leftButton_textColor);
		if(leftButton_Drawable != -1){
			leftButton.setBackgroundResource(leftButton_Drawable);
		}
		rightButton.setText(rightButton_text);
		rightButton.setTextColor(rightButton_textColor);
		if(rightButton_Drawable != -1){
			rightButton.setBackgroundResource(rightButton_Drawable);
		}
		if(leftButtonVisible){
			leftButton.setVisibility(View.VISIBLE);
		}else{
			leftButton.setVisibility(View.GONE);
		}
		if(rightButtonVisible){
			rightButton.setVisibility(View.VISIBLE);
		}else{
			rightButton.setVisibility(View.GONE);
		}
	}
	
    //��ʼ�� ����������ӵ��ؼ���
	private void init_view(Context context){
		LayoutInflater.from(context).inflate(R.layout.view_layout, this);
		//��ȡ�ؼ�
		leftButton = (Button)findViewById(R.id.title_bar_left);
		rightButton = (Button)findViewById(R.id.title_bar_right);
		textView = (TextView)findViewById(R.id.title_bar_title);
		//���ü���
		leftButton.setOnClickListener(l);
		rightButton.setOnClickListener(l);
	}
	private OnClickListener l = new OnClickListener() {		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==leftButton){
				Log.i("MyLayoutGroup01>>OnClickListener","into leftButton");
			}else if(arg0==rightButton){
				Log.i("MyLayoutGroup01>>OnClickListener","into rightButton");
			}
		}
	}; 
	
	//����  �ؼ� ����ⲿ api����
	public TextView getTitleText(){
		return textView;
	}
	public Button getLeftButton(){
		return leftButton;
	}
	public Button getRightButton(){
		return rightButton;
	}

}
