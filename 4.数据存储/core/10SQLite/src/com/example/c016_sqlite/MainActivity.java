package com.example.c016_sqlite;

import java.io.File;
import java.io.IOException;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Sqlite ���ݿ�
 * 
 * */
public class MainActivity extends Activity {

	private Button button01;
	private Button button02;
	private Button button03;
	private Button button04;
	private Button button05;
	private Button button06;
	private Button button11;
	private Button button12;
	private Button button13;
	private Button button14;
	
	private String path = "/data/data/com.example.c016_sqlite/databases/";
	private String file1 = "mydb1.db";
	private String file2 = "mydb2.db";
	
	public static  String CREATE_TABLE = "create table BooK ("
			+ "id integer primary key autoincrement,"
			+ "author text, "
			+ "price real, "
			+ "pages integer, "
			+ "name text)";
	
	
	//�޸ı�����
	//db1.execSQL("ALTER TABLE myTable1 RENAME��TO newTable");
	//�������һ��AGE
	//db1.execSQL("ALTER TABLE myTable1 ADD COLUMN AGE INTEGER");
	//ɾ����
	//db1.execSQL("DROP��TABLE��myTable1");
	
	//��ȡ���� ���  sql ���
	private String getCreateTable(String tableName){
		CREATE_TABLE = "create table "+tableName+" ("
				+ "id integer primary key autoincrement,"
				+ "author text, "
				+ "price real, "
				+ "pages integer, "
				+ "name text)";
		return CREATE_TABLE;
	}
	
	private MyDatabaseHelper dbHelper; 
	SQLiteDatabase db1,db2;
	//ʵ����������
	private OnClickListener l = new OnClickListener() {
		
		@SuppressLint("NewApi")
		@Override
		public void onClick(View arg0) { //�����ݿ����CRUD����һ��Ҫȷ���ڴ����ݿ⣬���ڸñ�֮�������
			// TODO Auto-generated method stub
			if(arg0==button01){	         //ֱ�Ӵ������ݿ�	                             ����OK
				db1 = SQLiteDatabase.openOrCreateDatabase(path+file1, null); //���Զ��ִ��
			}else if(arg0==button02){   //�½�һ�ű�      ���Խ����ű�                        ����OK
				try{
					getCreateTable("myTable01");     
					db1.execSQL(CREATE_TABLE);   //���ִ����Ҫ�����쳣
				}catch(Exception e){
					Log.e("OnClickListener>>1","�������쳣�׳���");
				} 
				try{
					getCreateTable("myTable02");
					db1.execSQL(CREATE_TABLE);
				}catch(Exception e){
					Log.e("OnClickListener>>2","�������쳣�׳���");
				}
			}else if(arg0==button03){   //����һ������   �������� ��׷���ڱ����  id���Զ�+1;   ����OK
				ContentValues values = new ContentValues();
				values.put("name", "fang");
				values.put("author", "fjw"); 
				values.put("pages", 454); 
				values.put("price", 16.98);
				db1.insert("myTable01", null, values);
			}else if(arg0==button04){   //���� ������                                                     ����OK
				ContentValues values = new ContentValues();
				values.put("name", "fang02");
				values.put("author", "fjw-2"); 
				values.put("pages", 555);
				values.put("price", 99.88);
				db1.update("myTable01", values, "id=?", new String[]{"2"});
			}else if(arg0==button05){   //��ѯ������          id��author ����  ��ֻȥid=2 ����                 ����OK
				Cursor sursor = db1.query("myTable01", new String[]{"id","author"}, "id=?", new String[]{"2"}, null, null, null);
				int count = sursor.getCount();
				Log.e("count>>1",String.valueOf(count));
				while(sursor.moveToNext()){
				//	int id = sursor.getInt(0);
				//	String str = sursor.getString(1);
					int id = sursor.getInt(sursor.getColumnIndex("id"));
					String str = sursor.getString(sursor.getColumnIndex("author"));
					Log.e("count>>id",String.valueOf(id)+"  "+str);
				}
			
			}else if(arg0==button06){   //ɾ��       ����OK
				db1.delete("myTable01", "id=?", new String[]{"1"}); //ɾ��ĳ������
			//	File file = new File(path+file1);
			//	db1.deleteDatabase(file);  //ɾ�����ݿ� ����OK
				
		//		db1.execSQL("drop table myTable01");   //ɾ����   ����OK
				//db1.close();���Ҫ�ǵùر����ݿ�
	//==============================================================================================			
			}else if(arg0==button11){   //�Զ��崴�����ݿ�
				dbHelper = new MyDatabaseHelper(MainActivity.this,file2,null,2);
			}else if(arg0==button12){   //�½�һ�ű�
				db2 = dbHelper.getWritableDatabase();
			}else if(arg0==button13){   //����һ������
				ContentValues values = new ContentValues();
				values.put("name", "fangjiongwen");
				values.put("author", "fjw0312");
				values.put("pages", 888);
				values.put("price", 99.999);
				db2.insert("Book", null, values);
			}else if(arg0==button14){   //ɾ�����
				
			}
		} 
	};
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ȡ�ؼ�
		button01 = (Button)findViewById(R.id.button01);
		button02 = (Button)findViewById(R.id.button02);
		button03 = (Button)findViewById(R.id.button03);
		button04 = (Button)findViewById(R.id.button04);
		button05 = (Button)findViewById(R.id.button05);
		button06 = (Button)findViewById(R.id.button06);
		button11 = (Button)findViewById(R.id.button11);
		button12 = (Button)findViewById(R.id.button12);
		button13 = (Button)findViewById(R.id.button13);
		button14 = (Button)findViewById(R.id.button14);
		//���ü�����
		button01.setOnClickListener(l);
		button02.setOnClickListener(l);
		button03.setOnClickListener(l);
		button04.setOnClickListener(l);
		button05.setOnClickListener(l);
		button06.setOnClickListener(l);
		button11.setOnClickListener(l);
		button12.setOnClickListener(l);
		button13.setOnClickListener(l);
		button14.setOnClickListener(l);
	
		
		File file = new File(path);
		if(!file.exists()){  //�ļ�·��������  �½�
			file.mkdir(); 
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
