package MyJson;

import java.util.List;


/**
 * gson google ��Դ��
 * ע�⡰δ���gson.jar
 * */
public class myGson {

	public myGson() {
		// TODO Auto-generated constructor stub
	}
	
	
	public class person{
		int id;
		String name="";
		int age;
	}
	
	//����  ����jsonObject
	public static person ParseJsonWithGson(String jsonData, Class<person> type){
	//	Gson gson = new Gson();
	//	person p = gson.fromJson(jsonData, type);
	//	return p;
		return null;
	}
	//����  jsonArray �����б�
	public static List<person> ParseJsonArrayWithGson(String jsonData, Class<person> type){
		//	Gson gson = new Gson();
		//	List<person> p_lst = gson.fromJson(jsonData, new TypeToken<List<person>>(){}.getType() );
		//	return p;
		return null;
	}

}
