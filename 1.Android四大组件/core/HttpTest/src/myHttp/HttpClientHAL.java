package myHttp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;


/**
 * HttpClient �ӿ�
 * */
public class HttpClientHAL {

	public HttpClientHAL() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static String sendReques(String strUrl){
		String getStr = "";
		try{
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(strUrl);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			//���շ�����Ӧ
			if(httpResponse.getStatusLine().getStatusCode()==200){	
				HttpEntity httpEntity = httpResponse.getEntity();			
				getStr = EntityUtils.toString(httpEntity,"utf-8");		
			}
		}catch(Exception e){
			Log.e("HttpClientHAL->sendReques","�����쳣�׳���");
		}
		
		return getStr;
	}

}
