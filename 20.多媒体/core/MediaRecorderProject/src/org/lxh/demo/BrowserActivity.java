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
	private ImageButton back = null ;						// 定义按钮
	private ListView videolist = null; 						// 定义列表视图
	private SimpleAdapter recordSimpleAdapter = null; 		// 适配器
	private List<Map<String, Object>> recordFiles = null; 	// 保存所有的List数据
	private String recDir = "mldnvideo";					// 保存目录
	private File recordVideoSaveFileDir = null; 			// 文件保存目录
	private boolean sdcardExists = false;					// 判断SD卡是否存在
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.browser);				// 调用布局管理器
		this.videolist = (ListView) super.findViewById(R.id.videolist); 	// 查找组件
		this.back = (ImageButton) super.findViewById(R.id.back) ;		// 查找组件
		if (this.sdcardExists = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) { 				// 判断SD卡是否存在
			this.recordVideoSaveFileDir = new File(Environment
					.getExternalStorageDirectory().toString()
					+ File.separator
					+ BrowserActivity.this.recDir + File.separator); // 保存录音目录
			if (!this.recordVideoSaveFileDir.exists()) { 	// 父文件夹不存在
				this.recordVideoSaveFileDir.mkdirs(); 		// 创建新的文件夹
			}
		}
		this.getRecordFiles(); 								// 取得全部的文件列表
		this.videolist.setOnItemClickListener(
				new OnItemClickListenerImpl());				// 设置单击事件
		this.back.setOnClickListener(new BackOnClickListenerImpl());// 设置单击事件
	}
	private void getRecordFiles() { 						// 取得全部录音文件
		this.recordFiles = new ArrayList<Map<String, Object>>(); // 实例化List集合
		System.out.println(this.recordVideoSaveFileDir.listFiles().length);
		if (this.sdcardExists) { 							// 如果SD卡存在
			File files[] = this.recordVideoSaveFileDir.listFiles();// 列出目录中的文件
			for (int x = 0; x < files.length; x++) {
				Map<String, Object> fileInfo = new HashMap<String, Object>();
				fileInfo.put("filename", files[x].getName());	// 增加显示内容
				this.recordFiles.add(fileInfo);				// 保存数据
			}
			this.recordSimpleAdapter = new SimpleAdapter(this,
					this.recordFiles, R.layout.recordfiles,
					new String[] { "filename" }, 
					new int[] { R.id.filename });			// 实例化适配器
			this.videolist.setAdapter(this.recordSimpleAdapter); // 定义列表视图
		}
	}
	private class OnItemClickListenerImpl implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> adapter, View view,
				int position, long id) {							// 选项单击
			if (BrowserActivity.this.recordSimpleAdapter.
					getItem(position) instanceof Map) {				// 判断是否是Map实例
				Map<?, ?> map = (Map<?, ?>) BrowserActivity.this.recordSimpleAdapter
						.getItem(position);							// 取出指定位置的内容
				Intent intent = new Intent(BrowserActivity.this,
						PlayVideoActivity.class); // 指定Intent
				intent.putExtra("filepath",
						BrowserActivity.this.recordVideoSaveFileDir.toString()
								+ File.separator
								+ map.get("filename").toString());
				BrowserActivity.this.startActivity(intent);		// 启动Activity
			}
		}
	}
	private class BackOnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View view) {
			Intent it = new Intent(BrowserActivity.this,
					MyMediaRecorderDemo.class);					// 指定Intent
			BrowserActivity.this.startActivity(it) ;			// 跳转Intent
		}
		
	}
}
