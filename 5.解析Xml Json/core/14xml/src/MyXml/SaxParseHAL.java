package MyXml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * SAX ����   �ӿ�
 * 
 * */
public class SaxParseHAL {

	public SaxParseHAL() {
		// TODO Auto-generated constructor stub
	}
	
	public class LinkMan {
		private String name;
		private String email;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
	public class MySAX extends DefaultHandler {					// �̳�DefaultHandler
		private List<LinkMan> all = null;						// ����ȫ��Ԫ��
		private String elementName = null; 						// ����Ԫ������
		private LinkMan man = null;								// �����װ����

		@Override
		public void startDocument() throws SAXException { 		// �ĵ���ʼ
			this.all = new ArrayList<LinkMan>();				// ʵ��������
		}

		@Override
		public void startElement(String uri, String localName, String name,
				Attributes attributes) throws SAXException { 	// Ԫ�ؿ�ʼ
			if ("linkman".equals(localName)) { 					// ��ʾ��linkman�ڵ�
				this.man = new LinkMan(); 						// ʵ����LinkMan����
			}
			this.elementName = localName; 						// ����Ԫ������
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException { 							// ȡ��Ԫ������
			if (this.elementName != null) {		 				// ��ʾ��Ԫ��
				String data = new String(ch, start, length); 	// ȡ��������Ϣ
				if ("name".equals(this.elementName)) { 			// �Ƿ���name�ڵ�
					this.man.setName(data); 					// ����name����
				} else if ("email".equals(this.elementName)) { 	// �Ƿ���email�ڵ�
					this.man.setEmail(data); 					// ����email����
				}
			}
		}

		@Override
		public void endElement(String uri, String localName, String name)
				throws SAXException { 							// Ԫ�ؽ���
			if ("linkman".equals(localName)) { 					// ��β����Ƿ���linkman
				this.all.add(this.man); 						// �򼯺ϱ�������
				this.man = null; 								// ��ն���
			}
			this.elementName = null; 							// ���Ԫ�ر��
		}

		public List<LinkMan> getAll() { 						// ȡ��ȫ������
			return this.all;
		}
	}
	
	//DOM���ļ���������ȡ
	public  void xml_DomParseHAL(String xmlFile){
		// 1������SAX��������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null ;	// 2�����������
		MySAX sax = new MySAX();							// SAX������
		try {
			parser = factory.newSAXParser();				// ȡ��SAXParser����
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {	
			parser.parse(xmlFile, sax);						// 3������XMLʹ��HANDLER
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<LinkMan> all = sax.getAll() ;					// ȡ����ϵ����Ϣ
		String str1 = all.get(0).getName();	    // �����ı�
		String str2 = all.get(0).getEmail();	// �����ı�
	}
	
	
	//DOM  �ļ�����   д��
	public  void xml_DOMSaveHAL(){
		
	}
}
