package Myhttp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * URL  �ӿ�      ��ȡͳһ��Դ��λ��
 * UrlHAL  ֻ�ܶ�ȡurl��Դ
 * ���أ��ֽ���   �ַ���   ������
 * */
public class UrlHAL {

	public UrlHAL() {
		// TODO Auto-generated constructor stub
	}
	//���� ͳһ��Դ��λ��   
	public static String urlParse(String strUrl){
		String str = strUrl+"/n";
		try {
			URL url = new URL(strUrl);
			str = str +"Э�飺"+ url.getProtocol() +"\n";
			str = str +"��֤��Ϣ��"+ url.getAuthority() +"\n";
			str = str +"�ļ��������������"+ url.getFile() +"\n";
			str = str +"��������"+ url.getHost() +"\n";
			str = str +"·����"+ url.getPath() +"\n";
			str = str +"�˿ڣ�"+ url.getPort() +"\n";
			str = str +"Ĭ�϶˿ڣ�"+ url.getDefaultPort() +"\n";
			str = str +"���������"+ url.getQuery() +"\n";
			str = str +"��λλ�ã�"+ url.getRef() +"\n";
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}           //ʵ����URL
		return str;
	}
	//���������ļ�������
	public static void downLoadFormUrl(String strUrl,String savePath, String fileName) throws IOException{
		URL url = new URL(strUrl);           //ʵ����URL 
		DataInputStream in = new DataInputStream(url.openStream()); 
		DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath+fileName));
		byte[] buffer = new byte[2048];
		int count = 0;
		while( (count = in.read(buffer,0,2048))>0 ){
			out.write(buffer, 0, count);
		}
		out.close();
		in.close();
	}
	
	//��ȡURL ��λ��Դ���������磩    ������Դ   �ֽ���
	public static InputStream getInputStream_URL(String strUrl){
		InputStream in = null;
		try {
			URL url = new URL(strUrl);           //ʵ����URL
			in = url.openStream();   //����Դ ��ȡ��������		
			
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}
	//��ȡURL ��λ��Դ���������磩    ������Դ   �ַ���
	public static Reader getReader_URL(String strUrl){
		    Reader in = null;
			try {
				URL url = new URL(strUrl);           //ʵ����URL
				InputStream inStream = url.openStream();   //����Դ ��ȡ��������		
				in = new InputStreamReader(inStream);   //����Դ ��ȡ��������	
				//or/ in = new InputStreamReader(new BufferedInputStream(url.openStream()));
				
			}catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return in;
		}
	//��ȡURL ��λ��Դ���������磩    ������Դ   ����
	public static Object getObject_URL(String strUrl){
		    Object object = null;
			try {
				URL url = new URL(strUrl);           //ʵ����URL
				object = url.getContent();				
				
			}catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return object;
		}
}
