package org.lxh.demo;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

public class MyIntentContentDemo extends Activity {
	private static final int PICK_CONTACT_SUBACTIVITY = 1;				// 定义操作标记
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		Uri uri = Uri.parse("content://contacts/people");				// 连接URI
		Intent intent = new Intent(Intent.ACTION_PICK, uri);			// 指定Intent
		super.startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY);// 调用Intent
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case PICK_CONTACT_SUBACTIVITY:									// 接收返回的数据
			Uri ret = data.getData();									// 单个数据Uri
			String phoneSelection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
						+ "=?"; 										// 设置查询条件
			String[] phoneSelectionArgs = { String.valueOf(ContentUris
					.parseId(ret)) }; 									// 查询参数
			Cursor c = super.managedQuery(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					phoneSelection, phoneSelectionArgs, null);			// 查询全部手机号码
			StringBuffer buf = new StringBuffer() ;					// 用于接收全部电话
			buf.append("电话号码是：") ;
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) { 	// 循环取数据
				buf.append(c.getString(c.getColumnIndex(
						ContactsContract.CommonDataKinds.Phone.NUMBER)))
							.append("、");								// 取出电话号码
			}
			Toast.makeText(this, buf, Toast.LENGTH_LONG).show();		// 显示信息
		}
	}
}