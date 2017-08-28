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
 * HttpURLConnection  接口       java.net 网络http通信  继承URLConnection
 * android源生项目
 * */
public class HttpURLConnectionHAL {

	public HttpURLConnectionHAL() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * GET POST
	 * 一般的使用区别
	 * post:  http://xxx:8081//servlet/LoginServlt
	 * get:   http://xxx:8081//servlet/LoginServlt?username=root&pwd=123
	 * */
	
	//发送 GET 请求
	public static String sendReques_Get(String strUrl){
		String str_response = "";
		HttpURLConnection connection = null;
		try{
			URL url = new URL(strUrl);
			connection = (HttpURLConnection)url.openConnection();   //与服务端连接   接下可以直接收发数据了
			connection.setRequestMethod("GET"); //设置为请求获取数据     发送“GET”
			//connection.setRequestProperty("encoding", "uft-8");  指定编码
			connection.setConnectTimeout(8000); //设置网络连接超时   可以不设置
			connection.setReadTimeout(8000);    //设置网络读取超时   可以不设置		
			if(connection.getResponseCode() == 200){  //数据接收成功
				InputStream in = connection.getInputStream(); //获取网络字节流
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in));
				String line;
				while((line = bufferReader.readLine())!=null){
					str_response = str_response + line;
				}
			}	
		}catch(Exception e){
			Log.e("HttpURLConnectionHAL->sendReques_Get","网络请求异常抛出！");
		}finally{
			if (connection != null) {
				connection.disconnect();
				}
		}
		return str_response;
	}
	
	//发送 POST 请求
	public static String sendReques_Post(String strUrl, String para){
		String str_response = "";
		HttpURLConnection connection = null;
		try{
			URL url = new URL(strUrl);
			connection = (HttpURLConnection)url.openConnection();   //与服务端连接   接下可以直接收发数据了
			connection.setRequestMethod("POST"); //设置为请求获取数据     发送“GET”
		//	connection.setRequestProperty("contentType", "");  //设置传入的类型
		//	connection.setRequestProperty("Content-Length", String.valueOf(para.getBytes().length));  //设置传入的数据长度
			connection.setDoInput(true);  //允许 读取数据
			connection.setDoOutput(true);  //允许 写入数据
			connection.setConnectTimeout(8000); //设置网络连接超时   可以不设置
			connection.setReadTimeout(8000);    //设置网络读取超时   可以不设置		
			
			//先 发送  
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes("username=admin&password=123456");
			//再 接收
			if(connection.getResponseCode() == 200){  //数据接收成功
				InputStream in = connection.getInputStream(); //获取网络字节流
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in));
				String line;
				while((line = bufferReader.readLine())!=null){
					str_response = str_response + line;
				}
			}	
		}catch(Exception e){
			Log.e("HttpURLConnectionHAL->sendReques_Get","网络请求异常抛出！");
		}finally{
			if (connection != null) {
				connection.disconnect();
				}
		}
		return str_response;
	}

}
