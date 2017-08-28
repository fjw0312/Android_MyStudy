package Myhttp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;


/**
 * HttpClient 接口
 * android 内嵌的apach 开源HttpClient  在android 6.0 就被舍弃了。
 * date:2017.5.24
 * */
public class HttpClientHAL {

	public HttpClientHAL() {
		// TODO Auto-generated constructor stub
	}
	
	//发送  GET 请求
	public static String sendReques_Get(String strUrl){
		String str_response = "";
		try{
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(strUrl);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			//接收返回响应
			if(httpResponse.getStatusLine().getStatusCode()==200){	
				HttpEntity httpEntity = httpResponse.getEntity();			
				str_response = EntityUtils.toString(httpEntity,"utf-8");		
			}
		}catch(Exception e){
			Log.e("HttpClientHAL->sendReques_Get","网络异常抛出！");
		}
		return str_response;
	}
	
	//上报    POST
	public static String sendReques_Post(String strUrl){
		String str_response = "";
		try{
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(strUrl);
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", "admin"));
			params.add(new BasicNameValuePair("password", "123456"));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
			httpPost.setEntity(entity);
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			//接收返回响应
			if(httpResponse.getStatusLine().getStatusCode()==200){
				HttpEntity httpEntity = httpResponse.getEntity();	
				str_response = EntityUtils.toString(httpEntity,"utf-8");
			}
		}catch(Exception e){
			Log.e("HttpClientHAL->sendReques_Get","网络异常抛出！");
		}
		return str_response;
	}
}
