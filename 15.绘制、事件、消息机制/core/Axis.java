package view;

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
		paint.setTextSize(10); //���û���������С
		paint.setColor(color); //���û�����ɫ
		paint.setAntiAlias(false); //���÷Ǿ�ݷ��
		paint.setStyle(Paint.Style.STROKE); //���û��ʷ��
	}
	//Fileds
	//************�� ��ֵ����********
	 public int color = 0xFF008000;
	 public int BackgroundColor = 0xFFFFFFFF;
	 public	float x_pad=40,y_pad=30;    //�ؼ��ı߽�
	 public int y_density = 1; // y�� ��ǩ �ܶ�
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
//		Log.e("Aixs>>onDraw()","into!");
		//���õװ���ɫ
		canvas.drawColor(BackgroundColor);
		
		//���û���
		paint.setTextSize(10);
		paint.setColor(color);
	//	paint.setStrokeWidth(1);
		
//		try{
		//����������
		canvas.drawLine(x_start, y_start, x_end, y_start, paint); //x��
		canvas.drawLine(x_start, y_start, x_start, y_end, paint); //y��
		//�����̶�
		for(int i=0;i<x_num+1;i++){  //x��̶�
			float x = x_start + x_unit*i;
			canvas.drawLine(x, y_start, x, y_start-5, paint); 
		}
		for(int i=0;i<y_num+1;i++){  //y��̶� 
			float y = y_start - y_unit*i;
			canvas.drawLine(x_start, y, x_start+5, y, paint); 
		}
		//���û���
		paint.setTextSize(10);
		paint.setColor(color);

		if(x_markValue==null) return;
		if(y_markValue==null) return;
		//������ǩ
		for(int i=0;i<x_num+1;i++){  //x���ǩ
			float x = x_start + x_unit*i;
			if(enable_y_label)
				canvas.drawText(x_markValue.get(i), x, y_start+10, paint); 
		}
		for(int i=0;i<y_num+1;i++){  //y���ǩ
			if(i%y_density != 0) continue;
			float y = y_start - y_unit*i;
			if(i>y_markValue.size()-1) break;
			canvas.drawText(y_markValue.get(i), x_start-30, y, paint); 
			canvas.drawLine(x_start, y, x_start+8, y, paint); 
		}
//		}catch(Exception e){
//			Log.e("Axis>>onDraw>>>","�쳣�׳���");
//		}
		
		
	}
	//����layout ���� ��������layout
	public void doLayout(boolean bool,int l,int t, int r,int b){
		this.layout(l, t, r, b);
	}
	//����invalidate()���� �����ػ�view��->view.onDraw()�Զ�����
	public void doInvalidate(){
		this.invalidate();
	}
	//����addView()���� ������ͼ��ӵ�����ͼ��
	public boolean doAddToView(ViewGroup view){
		view.addView(this);
		return true;
	}
	//�ؼ����ݸ��� ������ں���
	//�����ֱ�Ϊ  �ؼ��Ŀ�� �߶�  x��Ŀ̶���  y��Ŀ̶���  x��ı�ǩ���ֵ
	public boolean upDataValue(float width, float height,int xNum,int yNum,float x_maxVlaue,float y_maxVlaue){
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
