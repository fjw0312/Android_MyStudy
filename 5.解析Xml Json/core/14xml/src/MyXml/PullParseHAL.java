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
 * pull 解析器  借口类
 * 
 * */
public class PullParseHAL {

	public PullParseHAL() {
		// TODO Auto-generated constructor stub
	}
	
	public static void xml_pullParseHAL(InputStream inStream)throws Exception{
		//1.定义xml解析器
		XmlPullParser pullParser = Xml.newPullParser();
		//2.设置解析器参数
		pullParser.setInput(inStream, "UTF-8");
		//3.获取Xml解析事件类型
		int event = pullParser.getEventType();
		//4.判断节点类型  并获取信息
		while(event != XmlPullParser.END_DOCUMENT){
			switch(event){

			//a.文件头
			case XmlPullParser.START_DOCUMENT:
							break;
			//b.节点的头
			case XmlPullParser.START_TAG:
				//获取节点名称
				String name = pullParser.getName();
				//	Log.e("ParseXml->parseStream","XmlPullParser name="+name);
				//判断节点类型
				if("user".equals(name)){
					//此类 <rule type="4" alarm_level="1" />节点 解析方式
					//person.name = pullParser.getAttributeValue("", "name");
					//此类<name>12345</name>节点   解析方式
					//String strname = pullParser.nextText();
				}				
				break;
			//c.节点的尾
			case XmlPullParser.END_TAG:
				break;
			}	
			//5.指针移位下一个节点
			event = pullParser.next();
		}				
	}
	
	//pull  写入 一个xml文件
	public static void xml_pullSaveHAL(OutputStream output,List<Object> all) throws Exception{
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlSerializer xs = factory.newSerializer(); 			// 取得XmlSerializer接口对象
		xs.setOutput(output, "UTF-8");						// 设置输出编码
		xs.startDocument("UTF-8", true); 						// 文档开始，编码为UTF-8，且独立运行
		xs.startTag(null, "addresslist");						// 定义根元素开始
		Iterator<Object> iter = all.iterator();			// 取得Iterator接口对象
		while (iter.hasNext()) {								// 迭代输出
			Object man = iter.next();							// 取出每一个LinkMan
			xs.startTag(null, "linkman"); 						// 定义linkman元素开始
			xs.startTag(null, "name"); 							// 定义name元素开始
			xs.text("man.getName()"); 							// 设置name元素内容
			xs.endTag(null, "name"); 							// 定义name元素结束
			xs.startTag(null, "email"); 						// 定义name元素开始
			xs.text("man.getEmail()"); 							// 设置name元素内容
			xs.endTag(null, "email"); 							// 定义name元素结束
			xs.endTag(null, "linkman"); 						// 定义linkman元素结束
		}
		xs.endTag(null, "addresslist");							// 定义根元素完结
		xs.endDocument(); 										// 定义文档结束
		xs.flush(); 
	}
}
