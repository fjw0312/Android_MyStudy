package myHttp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;


/**
 * HttpURLconnection �ӿ�
 * 
 * */
public class HttpURLconnectionHAL {

	public HttpURLconnectionHAL() {
		// TODO Auto-generated constructor stub
	}
	
	public static String sendReques(String strUrl){
		String getStr = "";
		try{
			URL url = new URL(strUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET"); //����Ϊ�����ȡ����   ���͡�post��
			connection.setConnectTimeout(8000); //�����������ӳ�ʱ
			connection.setReadTimeout(8000);    //���������ȡ��ʱ
			InputStream in = connection.getInputStream(); //��ȡ�����ֽ���
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in));
			String line;
			while((line = bufferReader.readLine())!=null){
				getStr = getStr + line;
			}
		}catch(Exception e){
			Log.e("HttpURLconnectionHAL->sendReques","���������쳣�׳���");
		}finally{
			
		}
		return getStr;
	}

}
