package MyXml;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import android.util.Xml;

//���� ��Ϣ�澯���� ��
public class ParseSmsXml {

	//������� �澯����Ϣ����
	public class Person{
		public String name;
		public String phoneNumber;
		public String  enable;
		public String type;
		public String alarm_level;
	}
	
	File file = null;  //�ļ���
	InputStream fis = null;  //��ȡ�ֽ���
	public HashMap<String, Person> smsPerson_map = new HashMap<String, Person>();
	
	public ParseSmsXml(String filename){
		// TODO Auto-generated constructor stub
		file = new File(filename);
		try{
		parseFlie();
		}catch(Exception e){
			Log.e("ParseSmsXml>>","�������Ÿ澯�����ļ��쳣�׳���");
		}
	}
	public void parseFlie() throws FileNotFoundException{
		//��ȡ�ֽ���
		fis = new BufferedInputStream(new FileInputStream(file));
		if(fis==null){
			Log.e("ParseXml->getXmlStream","���ļ����ֽ�����");
			return;
		}
		//����Xml�ֽ���
		try{ 
			parseStream(fis);
			fis.close(); 
		
		}catch(Exception e){
			Log.e("ParseXml->getXmlStream","�����ֽ����쳣�׳���");
		}
		fis = null;
	}
	public void parseStream(InputStream inStream) throws Exception{
		smsPerson_map.clear();
		
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
					Person person = new Person();
					//���� <rule type="4" alarm_level="1" />�ڵ� ������ʽ
					person.name = pullParser.getAttributeValue("", "name");
					person.phoneNumber = pullParser.getAttributeValue("", "tel_number");
					person.enable = pullParser.getAttributeValue("", "enable");
					person.type = pullParser.getAttributeValue("", "rule_type");
					smsPerson_map.put(person.name, person);
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

}
