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
 * DOM  �ӿ�
 * */
public class DomParseHAL {

	public DomParseHAL() {
		// TODO Auto-generated constructor stub
	}
	
	//DOM ����
	public static void xml_DomParseHAL(String xmlFile){
		// 1������DocumentBuilderFactory��������ȡ��DocumentBuilder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 2��ͨ��DocumentBuilderFactoryȡ��DocumentBuilder
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		// 3������Document�ӿڶ���ͨ��DocumentBuilder�����DOM����ת������
		Document doc = null;
		try {
			doc = builder.parse(xmlFile);	// ��ȡָ��·����xml�ļ�
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 4������linkman�Ľڵ�
		NodeList nl = doc.getElementsByTagName("linkman");
		// 5�����NodeList�е�һ���ӽڵ����ı��ڵ������
		for (int x = 0; x < nl.getLength(); x++) {			// ѭ������ڵ�����
			Element e = (Element) nl.item(x) ;				// ȡ��ÿһ��Ԫ��
		//	String str = e.getAttribute("name");
			String strName = e.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();	// �����ı�
			String strEmail = e.getElementsByTagName("email").item(0).getFirstChild().getNodeValue();	// �����ı�
		}
	}
	
	
	
	//DOM  д��
	public static void xml_DomSaveHAL(String xmlFile){
		// 1������DocumentBuilderFactory��������ȡ��DocumentBuilder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 2��ͨ��DocumentBuilderFactoryȡ��DocumentBuilder
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		// 3������Document�ӿڶ���ͨ��DocumentBuilder�����DOM����ת������
		 Document doc = builder.newDocument();						// ����һ���µ��ĵ�
		 
		// 4���������������ڵ�
		Element addresslist = doc.createElement("addresslist") ;// �����ڵ�
		Element linkman = doc.createElement("linkman") ;	// �����ڵ�
		Element name = doc.createElement("name") ;			// �����ڵ�
		Element email = doc.createElement("email") ;		// �����ڵ�
		
		// 5�����ýڵ���ı����ݣ�����Ϊÿһ���ڵ�����ı��ڵ�             
		name.appendChild(doc.createTextNode("xxxxxx") ); // �����ı�
		email.appendChild(doc.createTextNode("YYYYY") ); // �����ı�
		
		// 6�����ýڵ��ϵ
		linkman.appendChild(name) ;							// �ӽڵ�
		linkman.appendChild(email) ;						// �ӽڵ�
		addresslist.appendChild(linkman) ;					// �ӽڵ�
		
		doc.appendChild(addresslist) ;						// �ĵ��ϱ���ڵ�
		
		// 7������ĵ����ļ�֮��
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = null;
		try {
			t = tf.newTransformer();
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		}
		
		t.setOutputProperty(OutputKeys.ENCODING, "GBK") ;	// ���ñ���
		DOMSource source = new DOMSource(doc);				// ����ĵ�
		StreamResult result = new StreamResult(xmlFile);		// ָ�����λ��
		try {
			t.transform(source, result);					// ���
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
