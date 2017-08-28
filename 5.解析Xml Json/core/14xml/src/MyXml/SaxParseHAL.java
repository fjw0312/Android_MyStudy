package MyXml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * SAX 解析   接口
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
	public class MySAX extends DefaultHandler {					// 继承DefaultHandler
		private List<LinkMan> all = null;						// 保存全部元素
		private String elementName = null; 						// 保存元素名称
		private LinkMan man = null;								// 定义封装对象

		@Override
		public void startDocument() throws SAXException { 		// 文档开始
			this.all = new ArrayList<LinkMan>();				// 实例化集合
		}

		@Override
		public void startElement(String uri, String localName, String name,
				Attributes attributes) throws SAXException { 	// 元素开始
			if ("linkman".equals(localName)) { 					// 表示是linkman节点
				this.man = new LinkMan(); 						// 实例化LinkMan对象
			}
			this.elementName = localName; 						// 保存元素名称
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException { 							// 取得元素内容
			if (this.elementName != null) {		 				// 表示有元素
				String data = new String(ch, start, length); 	// 取得文字信息
				if ("name".equals(this.elementName)) { 			// 是否是name节点
					this.man.setName(data); 					// 设置name属性
				} else if ("email".equals(this.elementName)) { 	// 是否是email节点
					this.man.setEmail(data); 					// 设置email属性
				}
			}
		}

		@Override
		public void endElement(String uri, String localName, String name)
				throws SAXException { 							// 元素结束
			if ("linkman".equals(localName)) { 					// 结尾标记是否是linkman
				this.all.add(this.man); 						// 向集合保存数据
				this.man = null; 								// 清空对象
			}
			this.elementName = null; 							// 清空元素标记
		}

		public List<LinkMan> getAll() { 						// 取得全部集合
			return this.all;
		}
	}
	
	//DOM　文件解析　读取
	public  void xml_DomParseHAL(String xmlFile){
		// 1、建立SAX解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null ;	// 2、构造解析器
		MySAX sax = new MySAX();							// SAX解析器
		try {
			parser = factory.newSAXParser();				// 取得SAXParser对象
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {	
			parser.parse(xmlFile, sax);						// 3、解析XML使用HANDLER
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<LinkMan> all = sax.getAll() ;					// 取得联系人信息
		String str1 = all.get(0).getName();	    // 设置文本
		String str2 = all.get(0).getEmail();	// 设置文本
	}
	
	
	//DOM  文件创建   写入
	public  void xml_DOMSaveHAL(){
		
	}
}
