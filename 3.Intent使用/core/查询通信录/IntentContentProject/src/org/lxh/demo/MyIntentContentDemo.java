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
	private static final int PICK_CONTACT_SUBACTIVITY = 1;				// ����������
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		Uri uri = Uri.parse("content://contacts/people");				// ����URI
		Intent intent = new Intent(Intent.ACTION_PICK, uri);			// ָ��Intent
		super.startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY);// ����Intent
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case PICK_CONTACT_SUBACTIVITY:									// ���շ��ص�����
			Uri ret = data.getData();									// ��������Uri
			String phoneSelection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
						+ "=?"; 										// ���ò�ѯ����
			String[] phoneSelectionArgs = { String.valueOf(ContentUris
					.parseId(ret)) }; 									// ��ѯ����
			Cursor c = super.managedQuery(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					phoneSelection, phoneSelectionArgs, null);			// ��ѯȫ���ֻ�����
			StringBuffer buf = new StringBuffer() ;					// ���ڽ���ȫ���绰
			buf.append("�绰�����ǣ�") ;
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) { 	// ѭ��ȡ����
				buf.append(c.getString(c.getColumnIndex(
						ContactsContract.CommonDataKinds.Phone.NUMBER)))
							.append("��");								// ȡ���绰����
			}
			Toast.makeText(this, buf, Toast.LENGTH_LONG).show();		// ��ʾ��Ϣ
		}
	}
}