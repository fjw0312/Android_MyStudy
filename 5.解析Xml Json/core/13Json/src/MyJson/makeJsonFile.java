package MyJson;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 创建 Json 文件
 * 
 * */
public class makeJsonFile {

	public makeJsonFile() {
		// TODO Auto-generated constructor stub
	}
	
	private String nameData[] = new String[] { "方xx", "远方科技", "NBxx" };	     // 姓名数据
	private int ageData[] = new int[] { 26, 5, 7 };								// 年龄数据
	private boolean isMarraiedData[] = new boolean[] { false, true, false };	// 婚否数据
	private double salaryData[] = new double[] { 3000.0, 5000.0, 9000.0 };		// 工资数据
	private Date birthdayData[] = new Date[] { new Date(), new Date(),new Date() };														// 生日数据
	private String companyName = "远方的远方" ;			                    // 公司名称
	private String companyAddr = "中国广东深圳市" ;						        // 公司地址
	private String companyTel = "13726248137" ;								// 公司电话
	
	
	public void makeJson(String fileName){
		JSONObject allData = new JSONObject(); 			// 先建立最外面的alldata对象
		JSONArray sing = new JSONArray();				// 定义新的JSONArray对象
		for (int x = 0; x < this.nameData.length; x++) {
			JSONObject temp = new JSONObject();			// 创建一个新的JSONObject
			try {
				temp.put("name", this.nameData[x]);			// 设置要保存的数据
				temp.put("age", this.ageData[x]);			// 设置要保存的数据
				temp.put("married", this.isMarraiedData[x]);// 设置要保存的数据
				temp.put("salary", this.salaryData[x]);		// 设置要保存的数据
				temp.put("birthday", this.birthdayData[x]);	// 设置要保存的数据
			} catch (JSONException e) {
				e.printStackTrace();
			}
			sing.put(temp); 								// 保存一组信息
		}
		try {
			allData.put("persondata", sing);				// 保存所有的数据
			allData.put("company", this.companyName);		// 保存数据
			allData.put("address", this.companyAddr);		// 保存数据
			allData.put("telephone", this.companyTel);		// 保存数据
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//创建文件
		try {
			makeFile(fileName,allData.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//字符串  写入文件
	private void makeFile(String fileName,String content) throws Exception{
		File file = new File(fileName);
		if (! file.getParentFile().exists()) {				// 父文件夹不存在
			file.getParentFile().mkdirs() ; 				// 创建文件夹 
		}
		PrintWriter print = new PrintWriter(file);
		print.print(content);
		
		print.close();
	}
			

}
