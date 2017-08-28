package com.example.c015_sharedpreferencetest;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{

	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private EditText accountEdit;
	private EditText passwordEdit;
	private Button login;
	private CheckBox rememberPass;
	
	//ʵ����������
	private OnClickListener l = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0==login){
				String account = accountEdit.getText().toString();
				String password = passwordEdit.getText().toString();
				
				if(account.equals("fang") && password.equals("123")){
					editor = pref.edit();
					if(rememberPass.isChecked()){ //��鸴ѡ�� �Ƿ�ѡ��
						editor.putBoolean("remember_password", true);
						editor.putString("account", account);
						editor.putString("password", password);
					}else{
						editor.clear();
					}
					editor.commit();
					//����������
					Intent intent = new Intent(LoginActivity.this,MainActivity.class);
					startActivity(intent);
				}else{
					Toast.makeText(LoginActivity.this, "account or password is invalid", 
							Toast.LENGTH_LONG).show();
				}
			}
		} 
		

	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		//��ȡ�ؼ�
		accountEdit = (EditText)findViewById(R.id.account);
		passwordEdit = (EditText)findViewById(R.id.password);
		rememberPass = (CheckBox) findViewById(R.id.remember_pass);
		login = (Button)findViewById(R.id.login);
		
		//��/���� Ĭ�ϵ�sharedPerferences�ļ�
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		boolean isRemember = pref.getBoolean("remember_password", false);
		//�ж���ʹ�ñ�����˻�����
		if(isRemember){
			//���˻��������û�ȡ�������
			String account = pref.getString("account", "");
			String password = pref.getString("password", "");
			
			accountEdit.setText(account);
			passwordEdit.setText(password);
			rememberPass.setChecked(true);
		}
		
		//���ü�����
		login.setOnClickListener(l);
	}

}
