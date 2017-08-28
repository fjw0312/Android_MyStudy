package Myhttp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;


/**
 * HttpURLConnection  �ӿ�       java.net ����httpͨ��  �̳�URLConnection
 * androidԴ����Ŀ
 * */
public class HttpURLConnectionHAL {

	public HttpURLConnectionHAL() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * GET POST
	 * һ���ʹ������
	 * post:  http://xxx:8081//servlet/LoginServlt
	 * get:   http://xxx:8081//servlet/LoginServlt?username=root&pwd=123
	 * */
	
	//���� GET ����
	public static String sendReques_Get(String strUrl){
		String str_response = "";
		HttpURLConnection connection = null;
		try{
			URL url = new URL(strUrl);
			connection = (HttpURLConnection)url.openConnection();   //����������   ���¿���ֱ���շ�������
			connection.setRequestMethod("GET"); //����Ϊ�����ȡ����     ���͡�GET��
			//connection.setRequestProperty("encoding", "uft-8");  ָ������
			connection.setConnectTimeout(8000); //�����������ӳ�ʱ   ���Բ�����
			connection.setReadTimeout(8000);    //���������ȡ��ʱ   ���Բ�����		
			if(connection.getResponseCode() == 200){  //���ݽ��ճɹ�
				InputStream in = connection.getInputStream(); //��ȡ�����ֽ���
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in));
				String line;
				while((line = bufferReader.readLine())!=null){
					str_response = str_response + line;
				}
			}	
		}catch(Exception e){
			Log.e("HttpURLConnectionHAL->sendReques_Get","���������쳣�׳���");
		}finally{
			if (connection != null) {
				connection.disconnect();
				}
		}
		return str_response;
	}
	
	//���� POST ����
	public static String sendReques_Post(String strUrl, String para){
		String str_response = "";
		HttpURLConnection connection = null;
		try{
			URL url = new URL(strUrl);
			connection = (HttpURLConnection)url.openConnection();   //����������   ���¿���ֱ���շ�������
			connection.setRequestMethod("POST"); //����Ϊ�����ȡ����     ���͡�GET��
		//	connection.setRequestProperty("contentType", "");  //���ô��������
		//	connection.setRequestProperty("Content-Length", String.valueOf(para.getBytes().length));  //���ô�������ݳ���
			connection.setDoInput(true);  //���� ��ȡ����
			connection.setDoOutput(true);  //���� д������
			connection.setConnectTimeout(8000); //�����������ӳ�ʱ   ���Բ�����
			connection.setReadTimeout(8000);    //���������ȡ��ʱ   ���Բ�����		
			
			//�� ����  
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes("username=admin&password=123456");
			//�� ����
			if(connection.getResponseCode() == 200){  //���ݽ��ճɹ�
				InputStream in = connection.getInputStream(); //��ȡ�����ֽ���
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in));
				String line;
				while((line = bufferReader.readLine())!=null){
					str_response = str_response + line;
				}
			}	
		}catch(Exception e){
			Log.e("HttpURLConnectionHAL->sendReques_Get","���������쳣�׳���");
		}finally{
			if (connection != null) {
				connection.disconnect();
				}
		}
		return str_response;
	}

}
