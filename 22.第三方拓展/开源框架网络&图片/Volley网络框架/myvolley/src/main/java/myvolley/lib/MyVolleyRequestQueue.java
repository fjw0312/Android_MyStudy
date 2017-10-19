package myvolley.lib;


import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 *
 * 
 * */
public class MyVolleyRequestQueue {

	public static RequestQueue mQueue;
	public static synchronized RequestQueue instance(Context context){
		if(mQueue == null){
			mQueue = Volley.newRequestQueue(context);
		}
		return mQueue;
	}

}
