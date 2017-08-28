package com.example.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

//FileDeal: ���ش��� �ļ��� �ļ� ���� ����������
//author: fang
//date:  2016 10 18 
//function:����ʵʱ���ݵ������ļ��У���ʵʱ�����ļ��е���ʷ���� 
public class FileDeal {

//		String path = "/mnt/sdcard/tmp/";
/*		String his_equip_path = "/mgrid/log/HisEquip/";     //type:1
		String his_signal_path = "/mgrid/log/HisSignal/";   //type:2
		String his_event_path = "/mgrid/log/event/";        //type:3 
		//String RC_signal = "/mgrid/log/HisFormula/";		
		String his_formulaLine_path = "/mgrid/log/HisFormula/Line/"; //pue����ʽ������ �����ļ�·��                             //type:10
		String his_formulaDay_path = "/mgrid/log/HisFormula/Day/";  //���õ���/pue�ֲ�  ����ʽ������ �����ļ�·��  //type:11
		String his_formulaMon_path = "/mgrid/log/HisFormula/Mon/";  //���õ���/pue�ֲ� ����ʽ������ �����ļ�·��   //type:12
		String his_formulaYear_path = "/mgrid/log/HisFormula/Year/"; //���õ���/pue�ֲ�����ʽ������ �����ļ�·��    //type:13
*/		
		String his_equip_path = "/data/ChaoYF/App_Data/HisEquipt/";     //type:1
		String his_signal_path = "/data/ChaoYF/App_Data/HisSignal/";   //type:2
		String his_event_path = "/data/ChaoYF/App_Data/history/";        //type:3 	
		String his_formulaLine_path = "/data/ChaoYF/App_Data/HisFormula/Line/"; //pue����ʽ������ �����ļ�·��                             //type:10
		String his_formulaDay_path = "/data/ChaoYF/App_Data/HisFormula/Day/";  //���õ���/pue�ֲ�  ����ʽ������ �����ļ�·��  //type:11
		String his_formulaMon_path = "/data/ChaoYF/App_Data/HisFormula/Mon/";  //���õ���/pue�ֲ� ����ʽ������ �����ļ�·��   //type:12
		String his_formulaYear_path = "/data/ChaoYF/App_Data/HisFormula/Year/"; //���õ���/pue�ֲ�����ʽ������ �����ļ�·��    //type:13
	
		File file = null;   //�ļ������
		Writer fws = null;   //д���ַ���
		Reader frs = null;   //�����ַ���   
		InputStream fis = null;  //��ȡ�ֽ���
		OutputStream fos = null; //д���ֽ���
		BufferedReader bufread = null; //�߼���ȡ�ı���
		PrintWriter  priwrite = null; //�߼�д���ı���
		public static int num_signal = 0;  //��ʶд���źŸ���
//		public static int w_line_num = 0;  //�ļ�д����ź�����
//		public static int r_line_num = 0;  //�ļ��Ѿ���ȡ���ź�����
		
		

	//�ж��ļ��Ƿ�Ϊ�յ�
	public boolean has_file(String filename , int who){
		if(who == 1) file = new File(his_equip_path+filename+".dat");
		else if(who == 2) file = new File(his_signal_path+filename+".dat");
		else if(who == 3) file = new File(his_event_path+filename+".dat");
//		else if(who == 4) file = new File(RC_signal+filename+".dat");
		
		else if(who == 10) file = new File(his_formulaLine_path+filename+".dat");
		else if(who == 11) file = new File(his_formulaDay_path+filename+".dat");
		else if(who == 12) file = new File(his_formulaMon_path+filename+".dat");
		else if(who == 13) file = new File(his_formulaYear_path+filename+".dat");
		if(!file.exists()){ //�ж��ļ�/Ŀ¼�Ƿ���� �������½�
			try{
			file.createNewFile(); //�½�Ŀ¼/�ļ�
			}catch(IOException e){
				return false;
			}
		}
		//�ж��ļ��Ƿ�ɶ�
		if(!file.canRead()){
			Log.e("�ļ�","���ɶ�");
			return false;
		}	
		
		//�ж��ļ��Ƿ��д
		if(!file.canWrite()){
			Log.e("�ļ�","����д");
			return false;
		}

/*		try{
			fis = new FileInputStream(file);
			fos = new FileOutputStream(file);
		}catch(Exception e){
			Log.e("�ļ�","����ȡʧ�ܣ�");
		}
*/		
		return true;
	}
	
	//д���ַ���
	public boolean write_buf(String buf){
		
		try{
		fws = new FileWriter(file);
		fws.write(buf);
		fws.close();
		}catch(Exception e){
			Log.e("�ļ�","д���ַ���ʧ�ܣ�");
		}
		
		return true;
	}
	//�����ַ���
	public String read_buf(String buf){
		
		try{
			char c[] = new char[100];
			frs = new FileReader(file);
			for(int i=0; (frs.read(c))!=-1;i++);
			frs.close();
			buf = c.toString();
			}catch(Exception e){
				Log.e("�ļ�","�����ַ���ʧ�ܣ�");
			}
		
		return buf;
	}
	//д�����ֽ�
	public boolean write_byte(int buf){
			
		try{
			fos = new FileOutputStream(file);
			fos.write(buf);
			fos.close();
			}catch(Exception e){
				Log.e("�ļ�","д���ֽ���ʧ�ܣ�");
			}
		
			return true;
	}
	//�����ֽ�
	public int[] read_byte(int[] buf){
			
		try{		
			fis = new FileInputStream(file);
			for(int i=0; (buf[i]=fis.read())!=-1;i++);
			fis.close();
			}catch(Exception e){
				Log.e("�ļ�","�����ֽ���ʧ�ܣ�");
			}
				
			return buf;
	}
	//д�� �����ַ���
	public boolean write_str(String buf){

		
		try{
			priwrite = new PrintWriter(new FileWriter(file,true),true); //trueβ��д�����ַ���
			priwrite.print(buf);  //β��д�����ַ���
			priwrite.close();
			}catch(Exception e){
				Log.e("�ļ�","д���ַ���ʧ�ܣ�");
			}
		
			return true;
	}	
	//д���� �����ַ���
	public boolean write_line(String buf){

		
		try{
			priwrite = new PrintWriter(new FileWriter(file,true),true); //trueβ��д�����ַ���
			priwrite.println(buf);  //β��д�����ַ���
			priwrite.close();
			}catch(Exception e){
				Log.e("�ļ�","д���ַ���ʧ�ܣ�");
			}
		
			return true;
	}
	
	//������  ͷһ���ַ� �����ַ���
	public String read_line(){				
		
		try{
			bufread = new BufferedReader( new FileReader(file));
			if((buf_line = bufread.readLine())==null){
				bufread.close();
				return null;
				}
			bufread.close();				
			}catch(Exception e){
				Log.e("�ļ�","�����ַ���ʧ�ܣ�");
				return null;
			}
						
			return buf_line;
	}
	//д����� �����ַ���
	public boolean write_lines(String[] bufs){
		if(bufs==null) return false;
		
		try{
			priwrite = new PrintWriter(new FileWriter(file,true),true); //trueβ��д�����ַ���
			for(int i=0;i<bufs.length;i++){
				priwrite.println(bufs[i]);  //β��д�����ַ���
			}
			priwrite.close();
			}catch(Exception e){
				Log.e("�ļ�","д���ַ���ʧ�ܣ�");
			}
		
			return true;
	}
	//���ļ�  �ļ����һ���ַ�
		public String read_later_line(){							
			
			try{
				 String line = "";
				 bufread = new BufferedReader( new FileReader(file));
				 while((line = bufread.readLine())!=null){	
					 buf_later_line = line;
				  }
				// Log.i("local_file->read_later_line��ȡ�������һ���ַ���",buf_later_line);
				  bufread.close();
				}catch(Exception e){
					Log.e("�ļ�","�����ַ���ʧ�ܣ�");
					return null;
				}
							
				return buf_later_line;
		}
	
	//������  �������ַ� �����ַ�������
		public boolean read_all_line(){
				
			String buf = new String();
			
			buflist1.clear();	
//			buflist1 = new ArrayList<String>();
			try{
				bufread = new BufferedReader( new FileReader(file));
				while((buf = bufread.readLine())!=null){
					buflist1.add(buf);
				}
				bufread.close();				
				}catch(Exception e){
					Log.e("�ļ�","�����ַ���ʧ�ܣ�");
					return false;
				}
					
				return true;
		}
		 
	//��ȡ����ĳЩ�ַ���  ���ַ� �������ĳ�ַ���-�����ַ������������������ַ�
		public String read_str_line(String buf){
			buf_str_line = "";
			String buff = "";
			try{
				 bufread = new BufferedReader( new FileReader(file));
				 while((buff = bufread.readLine())!=null){
			//		 if(buf_str_line.indexOf(buf)!= -1)  break;	 //����ĳ�ַ������˳�while
					 if(buff.contains(buf)){
						 buf_str_line = buff;
						 break;
					 }
				  }				 
				  bufread.close();
				}catch(Exception e){
					Log.e("�ļ�","�����ַ���ʧ�ܣ�");
					return buf_str_line;
				}
			return buf_str_line;
		}
	//��ȡ����ĳЩ�ַ���  �������ַ� �������ĳ�ַ���-�����ַ�����ӽ�����������������������
		public List<String> read_str_all_line(String buf){
			String buff = "";
			buflist2.clear();
			try{
				 bufread = new BufferedReader( new FileReader(file));
				 while((buff = bufread.readLine())!=null){
			//		 if(buf_str_line.indexOf(buf)!= -1)  break;	 //����ĳ�ַ������˳�while
					 if(buff.contains(buf)){
						 buflist2.add(buff);
					 }
				  }				 
				  bufread.close();
				}catch(Exception e){
					Log.e("�ļ�","�����ַ���ʧ�ܣ�");
					return buflist2;
				}
			return buflist2;
		}	
	
		public List<String> buflist1 = new ArrayList<String>();
		public List<String> buflist2 = new ArrayList<String>();
		String buf_line = new String();
		String buf_later_line = new String();
		String buf_str_line = new String();
}
