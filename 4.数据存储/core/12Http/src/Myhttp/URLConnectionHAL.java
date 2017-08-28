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
 * URLconnection 接口     java.net  网络url通信。
 * 
 * */
public class URLConnectionHAL {

	public URLConnectionHAL() {
		// TODO Auto-generated constructor stub
	}
	//***************************网络 读取***********************************************
	//获取URL 定位资源（包括网络）    返回资源   字节流   测试-OK
	public static InputStream getInputStream_URL(String strUrl){
		InputStream in =  null;
		URLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);               //实例化URL
			urlConnection = url.openConnection();//获取url网络连接
			//设置 网络连接  参数   可省略
			urlConnection.setConnectTimeout(8000);
			urlConnection.setReadTimeout(8000);	
			
			urlConnection.connect();              //与服务器建立连接
			in = urlConnection.getInputStream();  //获取输入字节流
			

		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return in;
	}
	//获取URL 定位资源（包括网络）    返回资源   字符流    测试-OK
	public static Reader getReader_URL(String strUrl){
		    Reader reader = null;
			URLConnection urlConnection = null;
			try {
				URL url = new URL(strUrl);           //实例化URL
				urlConnection = url.openConnection();//获取url网络连接
				//设置 网络连接  参数   可省略
				urlConnection.setConnectTimeout(8000);
				urlConnection.setReadTimeout(8000);	
				
				urlConnection.connect();              //与服务器建立连接
				InputStream inStream = urlConnection.getInputStream();  //获取输入字节流	
				reader = new InputStreamReader(inStream);   //打开资源 获取到输入流	
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
	//获取URL 定位资源（包括网络）    返回资源   对象
	public static Object getObject_URL(String strUrl){
		    Object object = null;
		    URLConnection urlConnection = null;
			try {
				URL url = new URL(strUrl);           //实例化URL
				urlConnection = url.openConnection();//获取url网络连接
				//设置 网络连接  参数   可省略
				urlConnection.setConnectTimeout(8000);
				urlConnection.setReadTimeout(8000);	
				
				urlConnection.connect();              //与服务器建立连接
				object = urlConnection.getContent();  //获取数据 对象			
				
			}catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return object;
		}
	
	
	//***************************网络 写入***********************************************
	//读取URL 定位资源（包括网络）    写入资源   字节流
		public static InputStream setInputStream_URL(String strUrl, byte[] buf){
			InputStream in =  null;
			OutputStream out = null;
			URLConnection urlConnection = null;
			try {
				URL url = new URL(strUrl);               //实例化URL
				urlConnection = url.openConnection();//获取url网络连接
				//设置 网络连接  参数 
				urlConnection.setDoInput(true);
				urlConnection.setDoOutput(true);
				urlConnection.setUseCaches(false);
				urlConnection.setConnectTimeout(8000);
				urlConnection.setReadTimeout(8000);	
				urlConnection.setRequestProperty("accept", "*/*");
				urlConnection.setRequestProperty("connection", "Keep-Alive");
				
				urlConnection.connect();              //与服务器建立连接
				out = urlConnection.getOutputStream();  //获取输入字节流
				out.write(buf,0, buf.length);
				out.flush();
				
				in = urlConnection.getInputStream();  //获取输入字节流
				//关闭
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
		//获取URL 定位资源（包括网络）    写入资源   字符流
		public static void setReader_URL(String strUrl, String sendStr){
				OutputStream out = null;
				URLConnection urlConnection = null;
				try {
					URL url = new URL(strUrl);           //实例化URL
					urlConnection = url.openConnection();//获取url网络连接
					//设置 网络连接  参数   
					//urlConnection.setDoInput(true);
					urlConnection.setDoOutput(true);
					urlConnection.setUseCaches(false);
					urlConnection.setConnectTimeout(8000);
					urlConnection.setReadTimeout(8000);	
					//urlConnection.setRequestProperty("field", "newValue"); //设置头字段
					
					urlConnection.connect();              //与服务器建立连接
					out = urlConnection.getOutputStream();  //获取输入字节流
					PrintWriter priwrite = new PrintWriter(new OutputStreamWriter(out));
					priwrite.print(sendStr);
					priwrite.flush();
					
					//关闭
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
		//获取URL 定位资源（包括网络）    写入资源   对象
		public static void setObject_URL(String strUrl, Object object){
			    OutputStream out = null;
			    URLConnection urlConnection = null;
				try {
					URL url = new URL(strUrl);           //实例化URL
					urlConnection = url.openConnection();//获取url网络连接 
					//设置 网络连接  参数   可省略
					//urlConnection.setDoInput(true);
					urlConnection.setDoOutput(true);
					urlConnection.setUseCaches(false);
					urlConnection.setConnectTimeout(8000);
					urlConnection.setReadTimeout(8000);	
					
					urlConnection.connect();              //与服务器建立连接
					out = urlConnection.getOutputStream();  //获取输入字节流
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
					objectOutputStream.writeObject(object);
					objectOutputStream.flush();
					
					//关闭
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
