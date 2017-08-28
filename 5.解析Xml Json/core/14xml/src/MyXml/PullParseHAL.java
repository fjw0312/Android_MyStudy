package MyXml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

/**
 * pull ������  �����
 * 
 * */
public class PullParseHAL {

	public PullParseHAL() {
		// TODO Auto-generated constructor stub
	}
	
	public static void xml_pullParseHAL(InputStream inStream)throws Exception{
		//1.����xml������
		XmlPullParser pullParser = Xml.newPullParser();
		//2.���ý���������
		pullParser.setInput(inStream, "UTF-8");
		//3.��ȡXml�����¼�����
		int event = pullParser.getEventType();
		//4.�жϽڵ�����  ����ȡ��Ϣ
		while(event != XmlPullParser.END_DOCUMENT){
			switch(event){

			//a.�ļ�ͷ
			case XmlPullParser.START_DOCUMENT:
							break;
			//b.�ڵ��ͷ
			case XmlPullParser.START_TAG:
				//��ȡ�ڵ�����
				String name = pullParser.getName();
				//	Log.e("ParseXml->parseStream","XmlPullParser name="+name);
				//�жϽڵ�����
				if("user".equals(name)){
					//���� <rule type="4" alarm_level="1" />�ڵ� ������ʽ
					//person.name = pullParser.getAttributeValue("", "name");
					//����<name>12345</name>�ڵ�   ������ʽ
					//String strname = pullParser.nextText();
				}				
				break;
			//c.�ڵ��β
			case XmlPullParser.END_TAG:
				break;
			}	
			//5.ָ����λ��һ���ڵ�
			event = pullParser.next();
		}				
	}
	
	//pull  д�� һ��xml�ļ�
	public static void xml_pullSaveHAL(OutputStream output,List<Object> all) throws Exception{
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlSerializer xs = factory.newSerializer(); 			// ȡ��XmlSerializer�ӿڶ���
		xs.setOutput(output, "UTF-8");						// �����������
		xs.startDocument("UTF-8", true); 						// �ĵ���ʼ������ΪUTF-8���Ҷ�������
		xs.startTag(null, "addresslist");						// �����Ԫ�ؿ�ʼ
		Iterator<Object> iter = all.iterator();			// ȡ��Iterator�ӿڶ���
		while (iter.hasNext()) {								// �������
			Object man = iter.next();							// ȡ��ÿһ��LinkMan
			xs.startTag(null, "linkman"); 						// ����linkmanԪ�ؿ�ʼ
			xs.startTag(null, "name"); 							// ����nameԪ�ؿ�ʼ
			xs.text("man.getName()"); 							// ����nameԪ������
			xs.endTag(null, "name"); 							// ����nameԪ�ؽ���
			xs.startTag(null, "email"); 						// ����nameԪ�ؿ�ʼ
			xs.text("man.getEmail()"); 							// ����nameԪ������
			xs.endTag(null, "email"); 							// ����nameԪ�ؽ���
			xs.endTag(null, "linkman"); 						// ����linkmanԪ�ؽ���
		}
		xs.endTag(null, "addresslist");							// �����Ԫ�����
		xs.endDocument(); 										// �����ĵ�����
		xs.flush(); 
	}
}
