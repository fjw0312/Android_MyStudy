package MyJson;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;


/**
 * ����json
 * */
public class ParseJson {

	public ParseJson() {
		// TODO Auto-generated constructor stub
	}
	
	//��������json �ַ���
	public static void parseJson(String jsonData) throws Exception{
		JSONObject jsonObject = new JSONObject(jsonData);
		int head = jsonObject.getInt("head");
		JSONArray jsonArray = jsonObject.getJSONArray("");
	}
	//����json ����
	public static void parseJsonArray(String jsonData) throws Exception{
		JSONArray jsonArray = new JSONArray(jsonData);
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String id = jsonObject.getString("id");
			String Name = jsonObject.getString("name");
			String version = jsonObject.getString("version");
			
			Log.e("id", id);
			Log.e("Name", Name);
			Log.e("version", version);
		}
	}

}
