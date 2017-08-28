package UIs;

import java.text.DecimalFormat;
import java.util.List;

import utils.BindExpression;
import utils.Calculator;
import utils.Expression;
import utils.RealTimeValue;
import app.main.idu.MainActivity;
import app.main.idu.Page;
import app.main.idu.VObject;
//import data.pool.DataPoolModel;
//import data.pool_model.Signal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//�Զ���ؼ�Label  ʹ��new TextView��ʽ
public class Ks_Label extends ViewGroup implements VObject{

	public Ks_Label(Context context) {
		super(context);
		//ʵ�����ÿؼ������Ԫ�ؿؼ�
		textview = new TextView(context);
		//��Ԫ����ӵ���������
		addView(textview);
	}
	//Fields
	String v_strID = "";                 //�ؼ�id
	String v_strType = "Label";           //�ؼ�����
	int v_iZIndex = 1;                    //�ؼ�ͼ��
	String v_strExpression = "Binding{[Value[Equip:114-Temp:173-Signal:1]]}";//�ؼ��󶨱��ʽ
	                      //��֧�ְ��źŸ澯�ȼ�Binding{[EventSeverity[Equip:2-Temp:175-Signal:1]]}
	int v_iPosX = 100,v_iPosY = 100;       //�ؼ�����
	int v_iWidth = 50,v_iHeight = 50;       //�ؼ���С
	int v_iBackgroundColor = 0x00000000;    //�ؼ��װ���ɫ
	float v_fAlpha = 1.0f;                 //�ؼ���λ
	float v_fRotateAngle = 0.0f;           //�ؼ���ת�Ƕ�
	float v_fFontSize = 12.0f;              //�ؼ�������С
	int  v_iFontColor = 0xFF008000;         //�ؼ���������ɫ
	int  v_iStartFontColor = 0xFF008000;         //�ؼ���������ɫ
	String v_strContent = "��������";        //�ؼ��ַ�����
	String v_strFontFamily = "΢���ź�";      //�ؼ��������� 
	boolean v_bIsBold = false;               //�ؼ������Ƿ�Ӵ�
	String v_strHorizontalContentAlignment = "Center"; //�ؼ����ݵĺ���װ���䷽ʽ
	String v_strVerticalContentAlignment = "Center";  //�ؼ����ݵ�����װ���䷽ʽ
	String v_strColorExpression = ">20[#FF009090]>30[#FF0000FF]>50[#FFFF0000]"; //������ɫ�仯���ʽ
	String v_strCmdExpression = "";             //�ؼ�����������ʽ
	String v_strUrl = "www.hao123.com";          //�ؼ���ҳ��ַ������ʽ
	String v_strClickEvent = "��ҳ.xml";           //�ؼ�����¼���ת����
	
	//����Ԫ�ض������
	int v_iHorizontalGravity = Gravity.CENTER_HORIZONTAL;
	int v_iVerticalGravity = Gravity.CENTER_VERTICAL;
	
	boolean v_bNeedUpdateFlag = false;            //�ؼ������ֵ���±�ʶ
	Page m_Page = null;         //��ҳ����
	//����ؼ�ʹ�õ�Ԫ��
	TextView textview;
	//��������
	BindExpression bindExpression = null;  //�󶨴�����
	int bindExpressionItem_num = 0;     //������ �ĸ���
	Expression expression = null; //���ʽ������
	int times = 0;
		
	//��дdispatchDraw() ����������view ��������drawChild()����	
	protected void dispatchDraw(Canvas canvas)  //����viewGroup�б�������ӿؼ�    
	{		
		super.dispatchDraw(canvas);	

//		canvas.drawColor(Color.LTGRAY);   //����viewgroup�ĵװ���ɫ   
		//�����ӿؼ�Ԫ�ز���                                                                             //���ڸ���Ļԭ��Ϊʹ��ǰ�������һ��*1.2��
		textview.setTextSize(v_fFontSize/MainActivity.densityPer*1.2f); //�����岻ͬ��Ļ������
		textview.setTextColor(v_iFontColor);		
		textview.setGravity(v_iHorizontalGravity|v_iVerticalGravity);
		textview.setText(v_strContent);
		textview.getPaint().setStrokeWidth(1);
		textview.getPaint().setFakeBoldText(v_bIsBold);	 
//		textview.getPaint().setAntiAlias(true);
		textview.setTypeface(Typeface.SERIF);
	
		//������view
		drawChild(canvas, textview, getDrawingTime());
//		Log.e("Label-dispatchDraw","into"); 
	}
	//��дonLayout() ����viewGroup�����е�view�װ�layout
	protected void onLayout(boolean bool, int l, int t, int r, int b) { 
//		Log.e("Label-onLayout","into"); 
	//	if(bool)
			textview.layout(0, 0, v_iWidth, v_iHeight);  //�����Ը����淶	
	}
	//��д�����¼�onTouchEvent()  ��Ҫ����view add�������ϲ���ʹ�ã�����Ҫ��ͼ���ϲ�
	public boolean onTouchEvent(MotionEvent event){
		super.onTouchEvent(event);
	//	Log.e("Ks_Label->onTouchEvent","into");
		//invalidate();   //֪ͨ��ǰview �ػ����Լ�
		return false;  //����false  ��page �ܲ���onTouch();
	}
	//����Layout() ����ؼ��װ�Layout��Сλ�û��ƺ���     
	public void doLayout(boolean bool, int l, int t, int r, int b){
//		Log.e("Label-doLayout","into");
		this.layout(v_iPosX, v_iPosY, v_iPosX+v_iWidth, v_iPosY+v_iHeight); //���Ƹ�view�װ�layout		

	}
	
	//����invalidate() �ؼ�����->onDraw()���ú���
	public void doInvalidate(){
			this.invalidate();
	}
	//����requestLayout() �װ����->onLayout()���ú���
	public void doRequestLayout(){
			this.requestLayout();
	}
	//����addView()���� ������ͼ��ӽ��븸��ͼ
	public boolean doAddViewsToWindow(Page window){
		//��Ļ���䴦��
		v_iPosX = (int)((float)v_iPosX * window.w_screenPer);
		v_iPosY = (int)((float)v_iPosY * window.h_screenPer);
		v_iWidth = (int)((float)v_iWidth * window.w_screenPer);
		v_iHeight = (int)((float)v_iHeight * window.h_screenPer);
		
		m_Page = window;
		window.addView(this);
		return true;
	}
	
	//��ȡ�ÿؼ���
	public View getViews(){
		return this;
	}
	//��ȡ�ؼ�ID
	public String getViewsID() {
		return v_strID;
	}
	//��ȡ�ؼ�����
	public String getViewsType() {
		return v_strType;
	}
	//��ȡ�ؼ���ͼ�����
	public int getViewsZIndex(){
		return v_iZIndex;
	}
	//��ȡ�ؼ��󶨱��ʽ
	public String getViewsExpression() {
		return v_strExpression;
	}
	//��ȡ�Ƿ����view��ʶ
	public boolean getNeedUpdateFlag(){
		return v_bNeedUpdateFlag;
	}

	
	//���ÿؼ���id
	public boolean setViewsID(String id){
		v_strID = id;
		return true;
	}
	//���ÿؼ���type
	public boolean setViewsType(String type){
		v_strType = type;
		return true;
	}
	//���ÿؼ���ͼ�����
	public boolean setViewsZIndex(int n){
		v_iZIndex = n;
		return true;
	}
	//���ÿؼ��󶨱��ʽ
	public boolean setViewsExpression(String strExpression) {
		v_strExpression = strExpression;
		return true;
	}
	//�����Ƿ����view��ʶ
	public boolean setNeedUpdateFlag(boolean b_flag){
		v_bNeedUpdateFlag = b_flag;
		return true;
	}
	//���¿ؼ���ֵ����     �����ַ���  �����Ƿ���ֵд��ɹ�
	public boolean  updataValue(String strValue) {
		v_bNeedUpdateFlag = false;
		if(bindExpression==null) return false;
		//Label�ؼ� ֻ���ǵ� �󶨱��ʽ  ��ֵ  �� ֱ�ӻ�ȡget(0);  
		String str_bindItem = bindExpression.itemBindExpression_lst.get(0);
		List<Expression> expression_lst = bindExpression.itemExpression_ht.get(str_bindItem);		
		RealTimeValue realTimeValue = new RealTimeValue();	
		String newValue = realTimeValue.getRealTimeValue(expression_lst);
		Log.i("Ks_Label->updataValue-JKTAG",  newValue);
		if("".equals(newValue)) newValue = "0.0"; //��ʾδ��ȡ����Ч����
		if(v_strContent.equals(newValue) ) return false; //��ֵδ�ı� ������ 
		parseFontcolor(newValue);    //����������ɫ 
		if("".equals(realTimeValue.strResultMeaning)){  //�ж��� ģ����    
			v_strContent = newValue;
		}else{                                          //�ж��� ������
			v_strContent = realTimeValue.strResultMeaning;
		}		
		realTimeValue = null;
		return true;
	}
	//��ɫ��������  �����������ʾֵ   fang
	public int parseFontcolor(String strValue)
	{
		v_iFontColor = v_iStartFontColor;
		if( (v_strColorExpression == null)||("".equals(v_strColorExpression)) ) return -1;
		if( (strValue == null)||("".equals(strValue)) ) return -1;
		if("0.0".equals(strValue)) return -1;		
		if( (">".equals(v_strColorExpression.substring(0,1)))!=true ) return -1;
	
		String buf[] = v_strColorExpression.split(">"); //��ȡ���ʽ�е���������ɫ��Ԫ
		for(int i=1;i<buf.length;i++){
			String a[] = buf[i].split("\\[|\\]"); //����ָ���[ ]			
//			Log.e("Label-updataValue", "�Ƚ�ֵ"+a[0]+"+��ɫ��ֵ��"+a[1]);
			//�Ƚ���ֵ	
			float data = Float.parseFloat(a[0]); //��ñȽ�ֵ
			float value = Float.parseFloat(strValue); //����ֵ
			if(value > data){
				v_iFontColor = Color.parseColor(a[1]);
			}
		}	
		return v_iFontColor;
	}
	//����󶨱��ʽ
	public boolean parseExpression(String str_bindExpression){
		if("".equals(v_strExpression)) return false;
		bindExpression = new BindExpression();
		bindExpressionItem_num = bindExpression.getBindExpression_ItemLst(v_strExpression);
		if(bindExpressionItem_num == 0) return false;
		
		return true;
	}
	//�ؼ����ֲ���setGravity
	public boolean setGravity(){
		return true;
	}
	//�����ؼ�����ز���
	public boolean setProperties(String strName, String strValue, String path){
			 if ("ZIndex".equals(strName))
		       	 	v_iZIndex = Integer.parseInt(strValue);	       	  
		     else if ("Location".equals(strName)) {
			       	String[] arrStr = strValue.split(",");
			        v_iPosX = Integer.parseInt(arrStr[0]);
			       	v_iPosY = Integer.parseInt(arrStr[1]);
		      }
		      else if ("Size".equals(strName)) {
			       	String[] arrSize = strValue.split(",");
			        v_iWidth = Integer.parseInt(arrSize[0]);
			        v_iHeight = Integer.parseInt(arrSize[1]);
		      }
		     else if ("Alpha".equals(strName)) 
		       	 	v_fAlpha = Float.parseFloat(strValue);
		     else if ("RotateAngle".equals(strName)) 
		        	v_fRotateAngle = Float.parseFloat(strValue);
		     else if ("Content".equals(strName)) 
		        	v_strContent = strValue;
		     else if ("FontFamily".equals(strName))
		        	v_strFontFamily = strValue;
		     else if ("FontSize".equals(strName)) 	   
		        	v_fFontSize = Float.parseFloat(strValue);	    	
		     else if ("IsBold".equals(strName))
		       	 	v_bIsBold = Boolean.parseBoolean(strValue);
		     else if ("FontColor".equals(strName)){
		    	 v_iStartFontColor = Color.parseColor(strValue); 
		    	 v_iFontColor = v_iStartFontColor;
		     }		        	
		     else if ("BackgroundColor".equals(strName)) 
		        	v_iBackgroundColor = Color.parseColor(strValue); 
		     else if ("HorizontalContentAlignment".equals(strName)){
		    	 v_strHorizontalContentAlignment = strValue;
		    	 if("Center".equals(v_strHorizontalContentAlignment)){
		    		 v_iHorizontalGravity = Gravity.CENTER_HORIZONTAL;
		    	 }else if("Left".equals(v_strHorizontalContentAlignment)){
		    		 v_iHorizontalGravity = Gravity.LEFT;
		    	 }else if("Right".equals(v_strHorizontalContentAlignment)){
		    		 v_iHorizontalGravity = Gravity.RIGHT;
		    	 }
		     }	
		     else if ("VerticalContentAlignment".equals(strName)){
		       	 	v_strVerticalContentAlignment = strValue;
		       	 if("Center".equals(v_strVerticalContentAlignment)){
		       		v_iVerticalGravity = Gravity.CENTER_VERTICAL;
		    	 }else if("Top".equals(v_strVerticalContentAlignment)){
		    		 v_iVerticalGravity = Gravity.TOP;
		    	 }else if("Bottom".equals(v_strVerticalContentAlignment)){
		    		 v_iVerticalGravity = Gravity.BOTTOM;
		    	 }
		     }
		     else if ("Expression".equals(strName))
		    		v_strExpression = strValue;          //�������ݱ��ʽ	       	 
		     else if ("CmdExpression".equals(strName))  
		        	v_strCmdExpression = strValue;      //����������ʽ
		     else if("ColorExpression".equals(strName))
		        	v_strColorExpression = strValue;    //������ɫ�仯���ʽ	
		     else if("ClickEvent".equals(strName))
		        	v_strClickEvent = strValue;         //����¼����ʽ
		     else if("Url".equals(strName))
		        	v_strUrl = strValue;                //��ҳ���ӱ��ʽ��ַ
			 return true;
		}	

}
