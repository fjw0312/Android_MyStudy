package org.lxh.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class BrowserActivity extends Activity {
	private ImageButton back = null ;						// ���尴ť
	private ListView videolist = null; 						// �����б���ͼ
	private SimpleAdapter recordSimpleAdapter = null; 		// ������
	private List<Map<String, Object>> recordFiles = null; 	// �������е�List����
	private String recDir = "mldnvideo";					// ����Ŀ¼
	private File recordVideoSaveFileDir = null; 			// �ļ�����Ŀ¼
	private boolean sdcardExists = false;					// �ж�SD���Ƿ����
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.browser);				// ���ò��ֹ�����
		this.videolist = (ListView) super.findViewById(R.id.videolist); 	// �������
		this.back = (ImageButton) super.findViewById(R.id.back) ;		// �������
		if (this.sdcardExists = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) { 				// �ж�SD���Ƿ����
			this.recordVideoSaveFileDir = new File(Environment
					.getExternalStorageDirectory().toString()
					+ File.separator
					+ BrowserActivity.this.recDir + File.separator); // ����¼��Ŀ¼
			if (!this.recordVideoSaveFileDir.exists()) { 	// ���ļ��в�����
				this.recordVideoSaveFileDir.mkdirs(); 		// �����µ��ļ���
			}
		}
		this.getRecordFiles(); 								// ȡ��ȫ�����ļ��б�
		this.videolist.setOnItemClickListener(
				new OnItemClickListenerImpl());				// ���õ����¼�
		this.back.setOnClickListener(new BackOnClickListenerImpl());// ���õ����¼�
	}
	private void getRecordFiles() { 						// ȡ��ȫ��¼���ļ�
		this.recordFiles = new ArrayList<Map<String, Object>>(); // ʵ����List����
		System.out.println(this.recordVideoSaveFileDir.listFiles().length);
		if (this.sdcardExists) { 							// ���SD������
			File files[] = this.recordVideoSaveFileDir.listFiles();// �г�Ŀ¼�е��ļ�
			for (int x = 0; x < files.length; x++) {
				Map<String, Object> fileInfo = new HashMap<String, Object>();
				fileInfo.put("filename", files[x].getName());	// ������ʾ����
				this.recordFiles.add(fileInfo);				// ��������
			}
			this.recordSimpleAdapter = new SimpleAdapter(this,
					this.recordFiles, R.layout.recordfiles,
					new String[] { "filename" }, 
					new int[] { R.id.filename });			// ʵ����������
			this.videolist.setAdapter(this.recordSimpleAdapter); // �����б���ͼ
		}
	}
	private class OnItemClickListenerImpl implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> adapter, View view,
				int position, long id) {							// ѡ���
			if (BrowserActivity.this.recordSimpleAdapter.
					getItem(position) instanceof Map) {				// �ж��Ƿ���Mapʵ��
				Map<?, ?> map = (Map<?, ?>) BrowserActivity.this.recordSimpleAdapter
						.getItem(position);							// ȡ��ָ��λ�õ�����
				Intent intent = new Intent(BrowserActivity.this,
						PlayVideoActivity.class); // ָ��Intent
				intent.putExtra("filepath",
						BrowserActivity.this.recordVideoSaveFileDir.toString()
								+ File.separator
								+ map.get("filename").toString());
				BrowserActivity.this.startActivity(intent);		// ����Activity
			}
		}
	}
	private class BackOnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View view) {
			Intent it = new Intent(BrowserActivity.this,
					MyMediaRecorderDemo.class);					// ָ��Intent
			BrowserActivity.this.startActivity(it) ;			// ��תIntent
		}
		
	}
}
