package customMyView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;

/*ʹ�� ������
 * new class �Կɸ�ֵ������ֵ  
 *  ����upDataValue()����
 *  �����������ֵ��ʹ��x_per_unit,y_per_unit 
 *  ���֪ͨˢ��view
**/
//�Զ���view ������  ���ڿؼ���Ӧ��Ԫ��   upDataValue
public class Axis extends View{

	public Axis(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		//��������
		paint = new Paint();
		paint.setTextSize(textSize); //���û���������С
		paint.setColor(color); //���û�����ɫ
		paint.setAntiAlias(false); //���÷Ǿ�ݷ��
		paint.setStyle(Paint.Style.STROKE); //���û��ʷ��
	}
	//Fileds
	//************�� ��ֵ����********
	 public int color = Color.GRAY;
	 public int BackgroundColor = Color.TRANSPARENT;
	 public	float x_pad=40,y_pad=30;    //�ؼ��ı߽�
	 public int y_density = 1; // y�� ��ǩ �ܶ�
	 public int textSize = 10; //�����С
	 public int textPad_x = 30; //�������x������
	 public int textPad_y = 20; //�������y������
	//************���� ֵ************
	 public	float x_per_unit,y_per_unit;     //�������/��ֵ ������λ
	//************���� ����**********
	 public boolean enable_y_label = true;  //ʹ�� ��ʾ y�� ��ǩ
	 
 public int x_num,y_num;  //����Ŀ̶���
 public	float x_start,y_start; //������ԭ�����������
 public	float x_end,y_end;     //�������߽��������������
 public	float x_lenth,y_lenth;  //�������ߵ����س���
 public	float x_unit,y_unit;    //�������߿̶ȵ�λ����������

 public ArrayList<String> x_markValue = new ArrayList<String>(); //x���ǩ ����
 public ArrayList<String> y_markValue = new ArrayList<String>(); //y���ǩ ����
 
 private Paint paint;  //���廭��
	
	//��д onDraw ���� ����canvas���� 
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		//���õװ���ɫ
		canvas.drawColor(BackgroundColor);
		//canvas.drawColor(Color.YELLOW);
		
		//���û���
		paint.setTextSize(textSize);
		paint.setColor(color);
	//	paint.setStrokeWidth(1);
		
//		try{
		//����������
		canvas.drawLine(x_start, y_start, x_end, y_start, paint); //x��
		canvas.drawLine(x_start, y_start, x_start, y_end, paint); //y��
		//�����̶�
		for(int i=0;i<x_num+1;i++){  //x��̶�
			float x = x_start + x_unit*i;
			canvas.drawLine(x, y_start, x, y_start-5, paint); //5  �̶ȳ���
		}
		for(int i=0;i<y_num+1;i++){  //y��̶� 
			float y = y_start - y_unit*i;
			canvas.drawLine(x_start, y, x_start+5, y, paint); //5  �̶ȳ���
		}

		if(x_markValue==null) return;
		if(y_markValue==null) return;
		//������ǩ
		for(int i=0;i<x_num+1;i++){  //x���ǩ
			float x = x_start + x_unit*i;
			if(enable_y_label)
				canvas.drawText(x_markValue.get(i), x, y_start+textPad_y, paint); 
		}
		for(int i=0;i<y_num+1;i++){  //y���ǩ
			if(i%y_density != 0) continue;
			float y = y_start - y_unit*i;
			if(i>y_markValue.size()-1) break;
			canvas.drawText(y_markValue.get(i), x_start-textPad_x, y, paint); 
			canvas.drawLine(x_start, y, x_start+8, y, paint);  //8  �̶ȳ��� 
		}
//		}catch(Exception e){
//			Log.e("Axis>>onDraw>>>","�쳣�׳���");
//		}
		
		Log.i("Tag>>Axis->onDraw","into!"); 
	}

	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub		
		super.onLayout(changed, left, top, right, bottom);
		
	//	Log.i("Tag>>Axis->onLayout","left:"+String.valueOf(left)+" top:"+String.valueOf(top)
    //    		+" right:"+String.valueOf(right)+" bottom:"+String.valueOf(bottom));
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	//	Log.i("Tag>>Axis->onMeasure","into -2");
	}


	//����invalidate()���� �����ػ�view��->view.onDraw()�Զ�����
	public void doInvalidate(){
		this.invalidate();
	}

	//�ؼ����ݸ��� ������ں���
	//�����ֱ�Ϊ  �ؼ��Ŀ�� �߶�  x��Ŀ̶���  y��Ŀ̶���  x��ı�ǩ���ֵ
	public boolean upDataValue(float width, float height,int xNum,int yNum,float x_maxVlaue,float y_maxVlaue){
	//	Log.i("Tag>>Axis->upDataValue","width:"+String.valueOf(width)+" height:"+String.valueOf(height));
		x_start = x_pad;
		y_start = height-y_pad;
		x_end = width;
		y_end = 0;
		x_num = xNum;
		y_num = yNum;
		x_lenth = x_end - x_start;
		y_lenth = y_start - y_end;
		x_unit = x_lenth/(x_num+1);
		y_unit = y_lenth/(y_num+1);
		x_per_unit = (x_lenth-x_unit)/x_maxVlaue;
		y_per_unit = (y_lenth-y_unit)/y_maxVlaue; 
		dealMarkVlaue(x_maxVlaue,y_maxVlaue);
		return true;
	}
	//�������ֵ����x y���ǩ������
	public void dealMarkVlaue(float x_maxValue,float y_maxValue){
		float xUnit = x_maxValue/x_num;
		x_markValue.clear();
		for(int i=0;i<x_num+1;i++){
			x_markValue.add(String.valueOf((int)xUnit*i)); //�Ա�ǩ��ʽתΪint
		}
		float yUnit = y_maxValue/y_num;
		y_markValue.clear();
		for(int i=0;i<y_num+1;i++){
    //		DecimalFormat decimalFloat = new DecimalFormat("0.00"); //floatС���㾫�ȴ���
    		DecimalFormat decimalFloat = new DecimalFormat("0"); //floatС���㾫�ȴ���
    		String strValue= decimalFloat.format(yUnit*i);
			y_markValue.add(strValue);			
		}
	}
	

}
