package myokhttp.lib;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHAL {

	public OkHttpHAL() {
		// TODO Auto-generated constructor stub
	}
	public static String strRe = "";

	// 同步请求GET     测试OK
	public static String getString_GET(String strUrl){
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
					.url(strUrl)
					.build();
        try{
			Response response = client.newCall(request).execute();
			return response.body().string();
		}catch(Exception e){
			e.printStackTrace();
		}


		return strRe;
	}
	//同步请求  POST
	public static String getString_POST(String strUrl, String strJson){
		final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(JSON, strJson); //提交 Json 数据
	/*	RequestBody body = new FormBody.Builder()             //提交  参数
				.add("key1", "value1")
				.add("key2", "value2")
				.build();
	*/
		Request request = new Request.Builder()
					.url(strUrl)
					.post(body)
					.build();
		try {
			Response response = client.newCall(request).execute();  //同步 execute  异步 enqueue
			return response.body().string();
		}catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}




	// 异步Get请求 String测试   OK
	public static String getString_GET_OKHttp(String strUrl){
		//1.创建OkhttpClient对象
		OkHttpClient mOkHttpClient = new OkHttpClient();
		//2.创建一个Request
		final Request request = new Request.Builder().url(strUrl).build();
		//3.new call
		Call call = mOkHttpClient.newCall(request);
		//4.异步 加入调度
		call.enqueue(new Callback(){
					@Override
					public void onFailure(Call arg0, IOException arg1) {
						// TODO Auto-generated method stub
						Log.i("fang", "onFailure---");
					}

					@Override
					public void onResponse(Call arg0, Response response)
							throws IOException {
						// TODO Auto-generated method stub
					//	byte[] bs = response.body().bytes();
					//	InputStream in = response.body().byteStream();
						strRe = response.body().string();
						if (null != response.cacheResponse()) {
							String str = response.cacheResponse().toString();
							Log.i("fang", "cache---" + str);
						} else {
							String str = response.networkResponse().toString();
							Log.i("fang", "network---" + str);
						}
					}
		        });       
		return strRe;
	}
	public static void getBytes_GET_OkHttp(String strFile,String strUrl){
		//设置网络 缓存
		int maxMemory = (int) (Runtime.getRuntime().maxMemory() ); // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。单位：K
		int cacheSize = maxMemory / 8;   // 使用最大可用内存值的1/8作为缓存的大小
		File cacheFile = new File(strFile);
		Cache cache = new Cache(cacheFile, cacheSize);

		//1.创建OkhttpClient对象
	//	OkHttpClient mOkHttpClient = new OkHttpClient();
		OkHttpClient mOkHttpClient = new OkHttpClient.Builder().cache(cache).build();  //可以 添加缓存  超时等
		//2.创建一个Request
		final Request request = new Request.Builder().url(strUrl).build();
		//3.new call
		Call call = mOkHttpClient.newCall(request);
		//4.异步 加入调度
		call.enqueue(new Callback(){
			@Override
			public void onFailure(Call call, IOException e) {
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				byte[] bs = response.body().bytes();
				//InputStream in = response.body().byteStream();
			}
		});
	}

	// 异步POST  提交参数
	public static String putString_POST_OKHttp(String strUrl){
		//1.创建RequetBody对象
		RequestBody requestBody = new FormBody.Builder().add("key1", "value1").build();  //添加参数

		//2.创建OkhttpClient对象
		OkHttpClient mOkHttpClient = new OkHttpClient();
		//3.创建一个Request
		final Request request = new Request.Builder().url(strUrl).post(requestBody).build();
		//4.new call
		Call call = mOkHttpClient.newCall(request);
		//5.异步加入调度        同步mOkHttpClient.newCall(request).execute();
		call.enqueue(new Callback(){
			@Override
			public void onFailure(Call arg0, IOException arg1) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onResponse(Call arg0, Response response)
					throws IOException {
				// TODO Auto-generated method stub
				strRe = response.body().string();
			}
		});
		return strRe;
	}

	// 异步POST  提交json数据
	public static String putJson_POST_OKHttp(String strUrl, String postStrJson){
		//1.创建RequetBody对象
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8"); //post类型
		RequestBody requestBody = RequestBody.create(mediaType, postStrJson);

		//2.创建OkhttpClient对象
		OkHttpClient mOkHttpClient = new OkHttpClient();
		//3.创建一个Request
		final Request request = new Request.Builder().url(strUrl).post(requestBody).build();
		//4.new call
		Call call = mOkHttpClient.newCall(request);
		//5.异步加入调度        同步mOkHttpClient.newCall(request).execute();
		call.enqueue(new Callback(){
			@Override
			public void onFailure(Call arg0, IOException arg1) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onResponse(Call arg0, Response response)
					throws IOException {
				// TODO Auto-generated method stub
				strRe = response.body().string();
			}
		});
		return strRe;
	}

	// 异步POST  提交文件
	public static String putFile_POST_OKHttp(String strUrl, File postFile){
		//1.创建RequetBody对象
		MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");   //post类型
		RequestBody requestBody = RequestBody.create(mediaType, postFile);

		//2.创建OkhttpClient对象
		OkHttpClient mOkHttpClient = new OkHttpClient();
		//3.创建一个Request
		final Request request = new Request.Builder().url(strUrl).post(requestBody).build();
		//4.new call
		Call call = mOkHttpClient.newCall(request);
		//5.异步加入调度        同步mOkHttpClient.newCall(request).execute();
		call.enqueue(new Callback(){
			@Override
			public void onFailure(Call arg0, IOException arg1) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onResponse(Call arg0, Response response)
					throws IOException {
				// TODO Auto-generated method stub
				strRe = response.body().string();
			}
		});
		return strRe;
	}
}
