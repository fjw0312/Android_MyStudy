package myHttp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;


/**
 * HttpURLconnection 接口
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
			connection.setRequestMethod("GET"); //设置为请求获取数据   发送“post”
			connection.setConnectTimeout(8000); //设置网络连接超时
			connection.setReadTimeout(8000);    //设置网络读取超时
			InputStream in = connection.getInputStream(); //获取网络字节流
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in));
			String line;
			while((line = bufferReader.readLine())!=null){
				getStr = getStr + line;
			}
		}catch(Exception e){
			Log.e("HttpURLconnectionHAL->sendReques","网络请求异常抛出！");
		}finally{
			
		}
		return getStr;
	}

}
