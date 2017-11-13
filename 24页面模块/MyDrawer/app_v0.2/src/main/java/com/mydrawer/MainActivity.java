package com.mydrawer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.utils.FullScreenUI;


/***
 * 该项目 为简单的页面切换框架。
 * MainActivity 为Drawerlayout 切换页面方案
 * TabActivity  为Tablayout 可上下标题的页面切换方案
 *  
 * */
public class MainActivity extends AppCompatActivity {

    String[] strClassName = {"首页","设备信息","页面信息"};

    ImageView img_logo;
    TextView Tx_logo;
    ImageView img_info;
    TextView Tx_info;
    ImageView img_exit;
    TextView Tx_exit;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    ListView listView;
    ArrayAdapter<String> adapter;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MainFragment mainFragment;
    TabClassFragment tabClassFragment;
    PageFragment pageFragment;


    public static float wPx;
    public static float hPx;

    private void show_fragement(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(mainFragment);
        fragmentTransaction.hide(tabClassFragment);
        fragmentTransaction.hide(pageFragment);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();   //提交事务
    }

    private void init_fragment(Bundle savedInstanceState){  //必须在onCreate调用
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if(savedInstanceState != null){  //内存重启  不用添加碎片页面了
            mainFragment = (MainFragment)fragmentManager.findFragmentByTag(mainFragment.getClass().getName());
            tabClassFragment = (TabClassFragment)fragmentManager.findFragmentByTag(tabClassFragment.getClass().getName());
            pageFragment = (PageFragment)fragmentManager.findFragmentByTag(pageFragment.getClass().getName());

        }else{
            mainFragment = new MainFragment();
            tabClassFragment = new TabClassFragment();
            pageFragment = new PageFragment();
            fragmentTransaction.add(R.id.frameLayout_id,mainFragment,mainFragment.getClass().getName());
            fragmentTransaction.add(R.id.frameLayout_id,tabClassFragment,tabClassFragment.getClass().getName());
            fragmentTransaction.add(R.id.frameLayout_id,pageFragment,pageFragment.getClass().getName());
        }
        fragmentTransaction.hide(tabClassFragment);
        fragmentTransaction.hide(pageFragment);
        fragmentTransaction.commit();   //提交事务
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FullScreenUI.FullScreenUI(this);
        //获取 屏幕长宽
        wPx = this.getResources().getDisplayMetrics().widthPixels;
        hPx = this.getResources().getDisplayMetrics().heightPixels;
        Log.i("MainActivity>>","into!");
        //获取控件
        img_logo = (ImageView)findViewById(R.id.img_logo);
        Tx_logo = (TextView)findViewById(R.id.Tx_logo);
        img_info = (ImageView)findViewById(R.id.img_info);
        Tx_info = (TextView)findViewById(R.id.Tx_info);
        img_exit = (ImageView)findViewById(R.id.img_exit);
        Tx_exit = (TextView)findViewById(R.id.Tx_exit);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        frameLayout = (FrameLayout)findViewById(R.id.frameLayout_id);
        listView = (ListView)findViewById(R.id.lstView_id);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strClassName);
        listView.setAdapter(adapter);

        //初始化 activity 中所用到的所有fragment
        init_fragment(savedInstanceState);

        img_logo.setOnClickListener(l);
        Tx_logo.setOnClickListener(l);
        img_info.setOnClickListener(l);
        Tx_info.setOnClickListener(l);
        img_exit.setOnClickListener(l);
        Tx_exit.setOnClickListener(l);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                String name = (String)adapter.getItem(position);
                if(name.equals(strClassName[0])){
                    show_fragement(mainFragment);  //首页碎片

                }else if(name.equals(strClassName[1])){
                    show_fragement(tabClassFragment);  //设备碎片

                }else if(name.equals(strClassName[2])){
                    show_fragement(pageFragment);     //页面信息

                }else{
                    return;
                }
                drawerLayout.closeDrawers();//关闭所有抽屉
            }
        });
    }

    private View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==img_logo){
                Intent intent = new Intent(MainActivity.this, TabActivity.class);
                startActivity(intent);
            }else if(v==Tx_logo){

            }else if(v==img_info || v==Tx_info){

            }else if(v==img_exit || v==Tx_exit){
                MainActivity.this.finish();
            }
        }
    };
}
