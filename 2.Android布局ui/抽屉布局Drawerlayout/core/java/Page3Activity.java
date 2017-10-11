package com.myhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/10/11.
 */

public class Page3Activity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    ListView listView;
    LinearLayout linearLayout;
    TextView TextView;
    Button button01;
    Button button02;

    String[] str_array = {"腾讯","阿里","百度","京东","新浪","今日头条"};
    ArrayAdapter<String> adapter;

    private View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==button01){
                drawerLayout.openDrawer(Gravity.LEFT); //显示左边侧滑页
             //   drawerLayout.openDrawer(Gravity.RIGHT); //显示右边侧滑页
            }else if(v==button02){

            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        listView = (ListView)findViewById(R.id.lstView_id);
        linearLayout = (LinearLayout)findViewById(R.id.ly_id);
        button01 = (Button)findViewById(R.id.Bn_id01);
        button02 = (Button)findViewById(R.id.Bn_id02);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str_array);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fragment fragment = new MyFragment3();
                Bundle bundle = new Bundle();
                bundle.putString("key", str_array[position]);
                fragment.setArguments(bundle);

                //切换碎片  将碎片添加到FrameLayout不修改原有的元素 只替换Fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frameLayout_id, fragment ).commit();

                //关闭抽屉
                drawerLayout.closeDrawers();
            }
        });

        button01.setOnClickListener(l);
        button02.setOnClickListener(l);



        //抽屉监听  也可以用简易监听类SimpleDrawerListener
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            /**
             * 当抽屉被滑动的时候调用此方法
             * arg1 表示 滑动的幅度（0-1）
             */
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
               // Log.i("onDrawerSlide","onDrawerSlide:"+String.valueOf(slideOffset));
            }

            @Override  //当一个抽屉被完全打开的时候被调用
            public void onDrawerOpened(View drawerView) {
                Log.i("onDrawerOpened","onDrawerOpened");
            }

            @Override //当一个抽屉完全关闭的时候调用此方法
            public void onDrawerClosed(View drawerView) {
                Log.i("onDrawerClosed","onDrawerClosed");
            }

            /**
             * 当抽屉滑动状态改变的时候被调用
             * 状态值是STATE_IDLE（闲置--0）, STATE_DRAGGING（拖拽的--1）, STATE_SETTLING（固定--2）中之一。
             * 抽屉打开的时候，点击抽屉，drawer的状态就会变成STATE_DRAGGING，然后变成STATE_IDLE
             */
            @Override
            public void onDrawerStateChanged(int newState) {
                Log.i("newState","newState:"+String.valueOf(newState));
            }
        });

    }
}
