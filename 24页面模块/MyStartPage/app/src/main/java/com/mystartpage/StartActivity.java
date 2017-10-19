package com.mystartpage;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.customView.CirclePageIndicator;
import com.customView.MyFragment;
import com.customView.MyViewPager;
import com.utils.FullScreenUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */

public class StartActivity extends AppCompatActivity {


    MyViewPager myViewPager;
    CirclePageIndicator circlePageIndicator;

    List<Fragment> fragments = new ArrayList<Fragment>();
    int[] img_src_ids = {R.mipmap.m1,R.mipmap.m2,R.mipmap.m3,R.mipmap.m4,
                         R.mipmap.m5,R.mipmap.m6,R.mipmap.m7,R.mipmap.m8,};

    private void init_MyViewPager(){
        for (int i = 0; i < img_src_ids.length; i++) {
            MyFragment myFragment = new MyFragment();
            Bundle bundle = new Bundle();
            int src_id = img_src_ids[i];
            bundle.putInt("key", src_id);
            myFragment.setArguments(bundle);

            fragments.add(myFragment);
        }

        myViewPager.Update(fragments); //放入碎片页面
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FullScreenUI.FullScreenIntoFlagUI(this); //设置 沉浸式 页面模式
        //FullScreenUI.FullSreenHideFlagUI(this);

        myViewPager = (MyViewPager)findViewById(R.id.MyViewPager);
        circlePageIndicator = (CirclePageIndicator)findViewById(R.id.CirclePageIndicator);

        init_MyViewPager();
        circlePageIndicator.setViewPager(myViewPager);  //设置 圆点数 和页面滑动监听
        circlePageIndicator.setActivity(this);         // 设置 圆点数滑动  控制 Activity生命

    }



    @Override
    protected void onDestroy() {
        myViewPager.removeAllViews();
        super.onDestroy();
    }
}
