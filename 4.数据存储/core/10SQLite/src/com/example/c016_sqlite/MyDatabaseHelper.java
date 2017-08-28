package com.example.c016_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

//定义一个数据库类   方便创建\升级  数据库
public class MyDatabaseHelper extends SQLiteOpenHelper{

	
	@Override  //创建/打开 数据库  后进行读写（如果磁盘满只能读）   一般不用重写
	public SQLiteDatabase getReadableDatabase() {
		// TODO Auto-generated method stub
		return super.getReadableDatabase();
	}
	@Override  //创建/打开 数据库 后进行读写 （如果磁盘满写会异常报错） 一般不用重写
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

	@Override  //当对该数据库实例化函数getWritableDatabase()或 getReadableDatabase时 会进入该函数
	public void onCreate(SQLiteDatabase db) {  //只要数据库建立，只会进入1次   （除非app卸载再进入） 
		// TODO Auto-generated method stub
		try{
			db.execSQL(CREATE_BOOK);
		//	db.execSQL(CREATE_TABLE); 
		}catch(Exception e){
			Log.e("MyDatabaseHlper->onCreate>>","创建表异常抛出！");
		}
		
		Toast.makeText(mContext, "create succeeded", Toast.LENGTH_LONG).show();
		Log.e("MyDatabaseHelper->onCreate","into!");
	}

	@Override  //当版本更新时 会执行该函数  new MyDatabaseHelper(MainActivity.this,file2,null,2￥￥这里版本号更新);后实例化db
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) { //arg1:oldVersion  arg2:newVersion
		// TODO Auto-generated method stub  //常用于在保护 现有数据库情况下  增加表和修改表  的数据库更新
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
