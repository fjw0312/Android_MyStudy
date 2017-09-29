package myvolley.lib;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public class MyGosnHAL {

    public MyGosnHAL() {
        super();
        m_gson = new Gson();
    }
    Gson m_gson = null;

    class u{
        int a;
        int b;
    }

    // 解析出  一个类
    public Object from_class(String jsonData, Object object) {

        object = m_gson.fromJson(jsonData, object.getClass());
        return object;
    }

    //解析出  一个类链表
   public List<Object> from_class(String jsonData, List<Object> object_lst) {

       object_lst = m_gson.fromJson(jsonData, new TypeToken< List<Object>>(){}.getType());
       return object_lst;
   }



}
