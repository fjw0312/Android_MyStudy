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
 * Sqlite 数据库
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
	
	
	//修改表名字
	//db1.execSQL("ALTER TABLE myTable1 RENAME　TO newTable");
	//表新添加一列AGE
	//db1.execSQL("ALTER TABLE myTable1 ADD COLUMN AGE INTEGER");
	//删除表
	//db1.execSQL("DROP　TABLE　myTable1");
	
	//获取创建 表的  sql 语句
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
	//实例化监听器
	private OnClickListener l = new OnClickListener() {
		
		@SuppressLint("NewApi")
		@Override
		public void onClick(View arg0) { //对数据库操作CRUD操作一定要确认在打开数据库，存在该表之后操作。
			// TODO Auto-generated method stub
			if(arg0==button01){	         //直接创建数据库	                             测试OK
				db1 = SQLiteDatabase.openOrCreateDatabase(path+file1, null); //可以多次执行
			}else if(arg0==button02){   //新建一张表      可以建多张表                        测试OK
				try{
					getCreateTable("myTable01");     
					db1.execSQL(CREATE_TABLE);   //多次执行需要捕获异常
				}catch(Exception e){
					Log.e("OnClickListener>>1","创建表异常抛出！");
				} 
				try{
					getCreateTable("myTable02");
					db1.execSQL(CREATE_TABLE);
				}catch(Exception e){
					Log.e("OnClickListener>>2","创建表异常抛出！");
				}
			}else if(arg0==button03){   //插入一行内容   插入内容 会追加在表后面  id会自动+1;   测试OK
				ContentValues values = new ContentValues();
				values.put("name", "fang");
				values.put("author", "fjw"); 
				values.put("pages", 454); 
				values.put("price", 16.98);
				db1.insert("myTable01", null, values);
			}else if(arg0==button04){   //更新 表内容                                                     测试OK
				ContentValues values = new ContentValues();
				values.put("name", "fang02");
				values.put("author", "fjw-2"); 
				values.put("pages", 555);
				values.put("price", 99.88);
				db1.update("myTable01", values, "id=?", new String[]{"2"});
			}else if(arg0==button05){   //查询表内容          id和author 的列  并只去id=2 的行                 测试OK
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
			
			}else if(arg0==button06){   //删除       测试OK
				db1.delete("myTable01", "id=?", new String[]{"1"}); //删除某行数据
			//	File file = new File(path+file1);
			//	db1.deleteDatabase(file);  //删除数据库 测试OK
				
		//		db1.execSQL("drop table myTable01");   //删除表   测试OK
				//db1.close();最后要记得关闭数据库
	//==============================================================================================			
			}else if(arg0==button11){   //自定义创建数据库
				dbHelper = new MyDatabaseHelper(MainActivity.this,file2,null,2);
			}else if(arg0==button12){   //新建一张表
				db2 = dbHelper.getWritableDatabase();
			}else if(arg0==button13){   //插入一行内容
				ContentValues values = new ContentValues();
				values.put("name", "fangjiongwen");
				values.put("author", "fjw0312");
				values.put("pages", 888);
				values.put("price", 99.999);
				db2.insert("Book", null, values);
			}else if(arg0==button14){   //删除表格
				
			}
		} 
	};
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取控件
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
		//设置监听器
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
		if(!file.exists()){  //文件路径不存在  新建
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
