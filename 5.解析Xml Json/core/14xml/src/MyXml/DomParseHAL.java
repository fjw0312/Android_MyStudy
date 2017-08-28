package MyXml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



/**
 * DOM  接口
 * */
public class DomParseHAL {

	public DomParseHAL() {
		// TODO Auto-generated constructor stub
	}
	
	//DOM 解析
	public static void xml_DomParseHAL(String xmlFile){
		// 1、建立DocumentBuilderFactory，以用于取得DocumentBuilder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 2、通过DocumentBuilderFactory取得DocumentBuilder
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		// 3、定义Document接口对象，通过DocumentBuilder类进行DOM树的转换操作
		Document doc = null;
		try {
			doc = builder.parse(xmlFile);	// 读取指定路径的xml文件
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 4、查找linkman的节点
		NodeList nl = doc.getElementsByTagName("linkman");
		// 5、输出NodeList中第一个子节点中文本节点的内容
		for (int x = 0; x < nl.getLength(); x++) {			// 循环输出节点内容
			Element e = (Element) nl.item(x) ;				// 取得每一个元素
		//	String str = e.getAttribute("name");
			String strName = e.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();	// 设置文本
			String strEmail = e.getElementsByTagName("email").item(0).getFirstChild().getNodeValue();	// 设置文本
		}
	}
	
	
	
	//DOM  写入
	public static void xml_DomSaveHAL(String xmlFile){
		// 1、建立DocumentBuilderFactory，以用于取得DocumentBuilder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 2、通过DocumentBuilderFactory取得DocumentBuilder
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		// 3、定义Document接口对象，通过DocumentBuilder类进行DOM树的转换操作
		 Document doc = builder.newDocument();						// 创建一个新的文档
		 
		// 4、建立各个操作节点
		Element addresslist = doc.createElement("addresslist") ;// 建立节点
		Element linkman = doc.createElement("linkman") ;	// 建立节点
		Element name = doc.createElement("name") ;			// 建立节点
		Element email = doc.createElement("email") ;		// 建立节点
		
		// 5、设置节点的文本内容，即：为每一个节点添加文本节点             
		name.appendChild(doc.createTextNode("xxxxxx") ); // 设置文本
		email.appendChild(doc.createTextNode("YYYYY") ); // 设置文本
		
		// 6、设置节点关系
		linkman.appendChild(name) ;							// 子节点
		linkman.appendChild(email) ;						// 子节点
		addresslist.appendChild(linkman) ;					// 子节点
		
		doc.appendChild(addresslist) ;						// 文档上保存节点
		
		// 7、输出文档到文件之中
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = null;
		try {
			t = tf.newTransformer();
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		}
		
		t.setOutputProperty(OutputKeys.ENCODING, "GBK") ;	// 设置编码
		DOMSource source = new DOMSource(doc);				// 输出文档
		StreamResult result = new StreamResult(xmlFile);		// 指定输出位置
		try {
			t.transform(source, result);					// 输出
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
