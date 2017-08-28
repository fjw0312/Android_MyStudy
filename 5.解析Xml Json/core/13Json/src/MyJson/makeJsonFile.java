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
 * ���� Json �ļ�
 * 
 * */
public class makeJsonFile {

	public makeJsonFile() {
		// TODO Auto-generated constructor stub
	}
	
	private String nameData[] = new String[] { "��xx", "Զ���Ƽ�", "NBxx" };	     // ��������
	private int ageData[] = new int[] { 26, 5, 7 };								// ��������
	private boolean isMarraiedData[] = new boolean[] { false, true, false };	// �������
	private double salaryData[] = new double[] { 3000.0, 5000.0, 9000.0 };		// ��������
	private Date birthdayData[] = new Date[] { new Date(), new Date(),new Date() };														// ��������
	private String companyName = "Զ����Զ��" ;			                    // ��˾����
	private String companyAddr = "�й��㶫������" ;						        // ��˾��ַ
	private String companyTel = "13726248137" ;								// ��˾�绰
	
	
	public void makeJson(String fileName){
		JSONObject allData = new JSONObject(); 			// �Ƚ����������alldata����
		JSONArray sing = new JSONArray();				// �����µ�JSONArray����
		for (int x = 0; x < this.nameData.length; x++) {
			JSONObject temp = new JSONObject();			// ����һ���µ�JSONObject
			try {
				temp.put("name", this.nameData[x]);			// ����Ҫ���������
				temp.put("age", this.ageData[x]);			// ����Ҫ���������
				temp.put("married", this.isMarraiedData[x]);// ����Ҫ���������
				temp.put("salary", this.salaryData[x]);		// ����Ҫ���������
				temp.put("birthday", this.birthdayData[x]);	// ����Ҫ���������
			} catch (JSONException e) {
				e.printStackTrace();
			}
			sing.put(temp); 								// ����һ����Ϣ
		}
		try {
			allData.put("persondata", sing);				// �������е�����
			allData.put("company", this.companyName);		// ��������
			allData.put("address", this.companyAddr);		// ��������
			allData.put("telephone", this.companyTel);		// ��������
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//�����ļ�
		try {
			makeFile(fileName,allData.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�ַ���  д���ļ�
	private void makeFile(String fileName,String content) throws Exception{
		File file = new File(fileName);
		if (! file.getParentFile().exists()) {				// ���ļ��в�����
			file.getParentFile().mkdirs() ; 				// �����ļ��� 
		}
		PrintWriter print = new PrintWriter(file);
		print.print(content);
		
		print.close();
	}
			

}
