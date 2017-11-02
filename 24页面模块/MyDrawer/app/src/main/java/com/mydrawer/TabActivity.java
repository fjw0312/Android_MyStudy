package com.mydrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.customView.MyNavigationBar;
import com.customView.MyViewPager;
import com.utils.FullScreenUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 */

public class TabActivity extends AppCompatActivity {

    List<Fragment> fragments;
    MyViewPager myViewPager;
    MyNavigationBar navigationBar;

    private List<Fragment> newfragments(){
        fragments = new ArrayList<Fragment>();
        fragments.add(new MainFragment());
        fragments.add(new TabClassFragment());
        fragments.add(new Class3Fragment());
        fragments.add(new Class4Fragment());
        return fragments;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        FullScreenUI.FullScreenUI(this);

        newfragments();
        myViewPager = (MyViewPager) findViewById(R.id.MyViewPager); //获取控件
        myViewPager.init_adapter(getSupportFragmentManager(),fragments);  //初始化适配器  初始化数据
        navigationBar = (MyNavigationBar)findViewById(R.id.MyNavigationBar_id);
        navigationBar.setViewPager(myViewPager); //关联 myViewPager 实现切换页面
        navigationBar.onChange(0,false); //初始化图标

        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override //页面 切换结束  position页面id
            public void onPageSelected(int position) {
                navigationBar.onChange(position,false);

            }

            @Override //state 0->1->2->0  1正在滑动 2滑动完毕  0不动了
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
