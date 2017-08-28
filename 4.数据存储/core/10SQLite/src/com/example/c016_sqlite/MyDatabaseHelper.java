package com.example.c016_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

//����һ�����ݿ���   ���㴴��\����  ���ݿ�
public class MyDatabaseHelper extends SQLiteOpenHelper{

	
	@Override  //����/�� ���ݿ�  ����ж�д�����������ֻ�ܶ���   һ�㲻����д
	public SQLiteDatabase getReadableDatabase() {
		// TODO Auto-generated method stub
		return super.getReadableDatabase();
	}
	@Override  //����/�� ���ݿ� ����ж�д �����������д���쳣���� һ�㲻����д
	public SQLiteDatabase getWritableDatabase() {
		// TODO Auto-generated method stub
		return super.getWritableDatabase();
	}

	private Context mContext;
	public String CREATE_TABLE = "";
	public static final String CREATE_BOOK = "create table Book ("
					+ "id integer primary key autoincrement,"
					+ "author text, "
					+ "price real, "
					+ "pages integer, "
					+ "name text)";
	public static final String CREATE_CATEGORY = "create table Category ("
					+ "id integer primary key autoincrement, "
					+ "actegory_name text, "
					+ "category_code integer)";
	
	public MyDatabaseHelper(Context context,String name,CursorFactory factory,int version) {
		// TODO Auto-generated constructor stub
		super(context,name,factory,version);
		mContext = context; 
	}

	@Override  //���Ը����ݿ�ʵ��������getWritableDatabase()�� getReadableDatabaseʱ �����ú���
	public void onCreate(SQLiteDatabase db) {  //ֻҪ���ݿ⽨����ֻ�����1��   ������appж���ٽ��룩 
		// TODO Auto-generated method stub
		try{
			db.execSQL(CREATE_BOOK);
		//	db.execSQL(CREATE_TABLE); 
		}catch(Exception e){
			Log.e("MyDatabaseHlper->onCreate>>","�������쳣�׳���");
		}
		
		Toast.makeText(mContext, "create succeeded", Toast.LENGTH_LONG).show();
		Log.e("MyDatabaseHelper->onCreate","into!");
	}

	@Override  //���汾����ʱ ��ִ�иú���  new MyDatabaseHelper(MainActivity.this,file2,null,2��������汾�Ÿ���);��ʵ����db
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) { //arg1:oldVersion  arg2:newVersion
		// TODO Auto-generated method stub  //�������ڱ��� �������ݿ������  ���ӱ���޸ı�  �����ݿ����
		switch(arg1){
		case 1:
			db.execSQL(CREATE_BOOK);
		case 2:
			db.execSQL(CREATE_TABLE);
		default: break;
		}
//		db.execSQL("drop table if exists Book");
//		db.execSQL("drop table if exists Category"); 
		Log.e("MyDatabaseHelper->onUpgrade","into!");
	}

}
