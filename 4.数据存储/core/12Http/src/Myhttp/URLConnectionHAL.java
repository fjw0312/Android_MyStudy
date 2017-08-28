package Myhttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * URLconnection �ӿ�     java.net  ����urlͨ�š�
 * 
 * */
public class URLConnectionHAL {

	public URLConnectionHAL() {
		// TODO Auto-generated constructor stub
	}
	//***************************���� ��ȡ***********************************************
	//��ȡURL ��λ��Դ���������磩    ������Դ   �ֽ���   ����-OK
	public static InputStream getInputStream_URL(String strUrl){
		InputStream in =  null;
		URLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);               //ʵ����URL
			urlConnection = url.openConnection();//��ȡurl��������
			//���� ��������  ����   ��ʡ��
			urlConnection.setConnectTimeout(8000);
			urlConnection.setReadTimeout(8000);	
			
			urlConnection.connect();              //���������������
			in = urlConnection.getInputStream();  //��ȡ�����ֽ���
			

		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return in;
	}
	//��ȡURL ��λ��Դ���������磩    ������Դ   �ַ���    ����-OK
	public static Reader getReader_URL(String strUrl){
		    Reader reader = null;
			URLConnection urlConnection = null;
			try {
				URL url = new URL(strUrl);           //ʵ����URL
				urlConnection = url.openConnection();//��ȡurl��������
				//���� ��������  ����   ��ʡ��
				urlConnection.setConnectTimeout(8000);
				urlConnection.setReadTimeout(8000);	
				
				urlConnection.connect();              //���������������
				InputStream inStream = urlConnection.getInputStream();  //��ȡ�����ֽ���	
				reader = new InputStreamReader(inStream);   //����Դ ��ȡ��������	
				//or/ in = new InputStreamReader(new BufferedInputStream(urlConnection.getInputStream()));
				
			}catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return reader;
		}
	//��ȡURL ��λ��Դ���������磩    ������Դ   ����
	public static Object getObject_URL(String strUrl){
		    Object object = null;
		    URLConnection urlConnection = null;
			try {
				URL url = new URL(strUrl);           //ʵ����URL
				urlConnection = url.openConnection();//��ȡurl��������
				//���� ��������  ����   ��ʡ��
				urlConnection.setConnectTimeout(8000);
				urlConnection.setReadTimeout(8000);	
				
				urlConnection.connect();              //���������������
				object = urlConnection.getContent();  //��ȡ���� ����			
				
			}catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return object;
		}
	
	
	//***************************���� д��***********************************************
	//��ȡURL ��λ��Դ���������磩    д����Դ   �ֽ���
		public static InputStream setInputStream_URL(String strUrl, byte[] buf){
			InputStream in =  null;
			OutputStream out = null;
			URLConnection urlConnection = null;
			try {
				URL url = new URL(strUrl);               //ʵ����URL
				urlConnection = url.openConnection();//��ȡurl��������
				//���� ��������  ���� 
				urlConnection.setDoInput(true);
				urlConnection.setDoOutput(true);
				urlConnection.setUseCaches(false);
				urlConnection.setConnectTimeout(8000);
				urlConnection.setReadTimeout(8000);	
				urlConnection.setRequestProperty("accept", "*/*");
				urlConnection.setRequestProperty("connection", "Keep-Alive");
				
				urlConnection.connect();              //���������������
				out = urlConnection.getOutputStream();  //��ȡ�����ֽ���
				out.write(buf,0, buf.length);
				out.flush();
				
				in = urlConnection.getInputStream();  //��ȡ�����ֽ���
				//�ر�
				out.close();
			}catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return in;	
		}
		//��ȡURL ��λ��Դ���������磩    д����Դ   �ַ���
		public static void setReader_URL(String strUrl, String sendStr){
				OutputStream out = null;
				URLConnection urlConnection = null;
				try {
					URL url = new URL(strUrl);           //ʵ����URL
					urlConnection = url.openConnection();//��ȡurl��������
					//���� ��������  ����   
					//urlConnection.setDoInput(true);
					urlConnection.setDoOutput(true);
					urlConnection.setUseCaches(false);
					urlConnection.setConnectTimeout(8000);
					urlConnection.setReadTimeout(8000);	
					//urlConnection.setRequestProperty("field", "newValue"); //����ͷ�ֶ�
					
					urlConnection.connect();              //���������������
					out = urlConnection.getOutputStream();  //��ȡ�����ֽ���
					PrintWriter priwrite = new PrintWriter(new OutputStreamWriter(out));
					priwrite.print(sendStr);
					priwrite.flush();
					
					//�ر�
					out.close();
					priwrite.close();
				}catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//��ȡURL ��λ��Դ���������磩    д����Դ   ����
		public static void setObject_URL(String strUrl, Object object){
			    OutputStream out = null;
			    URLConnection urlConnection = null;
				try {
					URL url = new URL(strUrl);           //ʵ����URL
					urlConnection = url.openConnection();//��ȡurl�������� 
					//���� ��������  ����   ��ʡ��
					//urlConnection.setDoInput(true);
					urlConnection.setDoOutput(true);
					urlConnection.setUseCaches(false);
					urlConnection.setConnectTimeout(8000);
					urlConnection.setReadTimeout(8000);	
					
					urlConnection.connect();              //���������������
					out = urlConnection.getOutputStream();  //��ȡ�����ֽ���
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
					objectOutputStream.writeObject(object);
					objectOutputStream.flush();
					
					//�ر�
					out.close();
					objectOutputStream.close();
				}catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

}
