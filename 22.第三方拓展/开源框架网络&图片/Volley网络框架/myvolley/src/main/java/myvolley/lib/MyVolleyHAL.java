package myvolley.lib;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/25.
 */

public class MyVolleyHAL {

    public static String strRe = "";
    public static JSONObject jsonObject = new JSONObject();

    // 获取  Http Get方法 String字符
    public static String getString_Volley_Get(Context context, String strUrl) {

        //1.创建 RequestQueue
        RequestQueue mQueue = MyVolleyRequestQueue.instance(context); //  RequestQueue mQueue = Volley.newRequestQueue(context);
        //2.创建StringRequest
        StringRequest stringRequest = new StringRequest(strUrl,
                new Response.Listener<String>() { //重写  volley.listener.onResponse
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        strRe = response;
                    }
                },
                new Response.ErrorListener() {//重写  volley.onErrorResponse.onResponse
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.w("MyVolleyHAL>","getString_Volley_Get>字符接收异常！");
                    }
                });
        //3. 请求数据对象 添加到Queue
        mQueue.add(stringRequest);

        return strRe;
    }

    // 获取  Http Get方法 JSONObject
    public static JSONObject getJsonObject_Volley_Get(Context context, String strUrl){
        //1.创建 RequestQueue
        RequestQueue mQueue = MyVolleyRequestQueue.instance(context); //  RequestQueue mQueue = Volley.newRequestQueue(context);
        //2.创建 JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(strUrl, jsonObject,
                new myRequestListener(), new myRequestErrorListener() );
        //3. 请求数据对象 添加到Queue
        mQueue.add(jsonObjectRequest);

        return jsonObject;
    }

    //获取 Http Get方法   ImageLoader
    public static ImageLoader getImageLoader_Volley_Get(Context context, String strUrl,
      ImageView imageView,int defaultImageResId,int errorImageResId,int maxWidth,int maxHeight){
        //1.创建 RequestQueue
        RequestQueue mQueue =  MyVolleyRequestQueue.instance(context);
        //2.创建 ImageLoader
        ImageLoader myImageLoader = new ImageLoader(mQueue, new MyImageCache());
        //ImageLoader imageLoader = myImageLoader;     //使用提前 定义的 图片接收器
        //3.创建 ImageListener
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, defaultImageResId, errorImageResId);
        //4.加载 图片到控件
        myImageLoader.get(strUrl, listener, maxWidth, maxHeight);  //也可以 不限制最大尺寸
        return myImageLoader;
    }


    private static class myRequestListener implements Response.Listener<JSONObject> {
        @Override
        public void onResponse(JSONObject response) {
            jsonObject = response;
        }
    }
    private static class myRequestErrorListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.w("MyVolleyHAL>","getJsonObject_Volley_Get>接收异常！");
        }
    }

}
