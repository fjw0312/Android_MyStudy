package login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mylogin.MainActivity;
import com.mylogin.R;


/**
 * Created by Administrator on 2017/9/26.
 */

public class LoginTable extends LinearLayout{

    public LoginTable(Context context) {
        super(context);
        init_view(context);
    }

    public LoginTable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init_view(context);
    }

    public LoginTable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init_view(context);
    }

    Context mContext;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;


    //初始化  加载控件
    private void init_view(	Context context){
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.mylogin_table,this);

        //获取控件
        accountEdit = (EditText)view.findViewById(R.id.account);
        passwordEdit = (EditText)view.findViewById(R.id.password);
        rememberPass = (CheckBox)view.findViewById(R.id.remember_pass);
        login = (Button)view.findViewById(R.id.login);

        pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean isRemember = pref.getBoolean("remember_password", false);
        //判断是使用保存的账户密码
        if(isRemember){
            //将账户密码设置获取到填入框
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");

            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        //设置监听器
        login.setOnClickListener(l);
    }
    private OnClickListener l = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==login){
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if(account.equals("fang") && password.equals("123")){
                    editor = pref.edit();
                    if(rememberPass.isChecked()){ //检查复选框 是否被选中
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    }else{
                        editor.clear();
                    }
                    editor.commit();
                    //进入主界面

                }else{
                    Toast.makeText(mContext, "account or password is invalid",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    };

}
