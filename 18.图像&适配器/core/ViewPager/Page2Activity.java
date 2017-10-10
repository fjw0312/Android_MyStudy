package com.myhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import myadapter.AdapterPagerFragment;
import myadapter.AdapterViewpager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjw0312 on 2017/9/30.
 */

public class Page2Activity extends FragmentActivity {


    TextView Tx_page1;
    TextView Tx_page2;
    ViewPager viewPager;
    ViewFlipper viewFlipper;
    AdapterViewpager adapterViewpager;
    List<View> lst_view = new ArrayList<View>();
    ImageView image1;
    ImageView image2;
    ImageView image3;

    private View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==Tx_page1){
                viewPager.setCurrentItem(0);
            }else if(v==Tx_page2){
                viewPager.setCurrentItem(1);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        Tx_page1 = (TextView)findViewById(R.id.Tx_page1);
        Tx_page2 = (TextView)findViewById(R.id.Tx_page2);
        viewPager = (ViewPager)findViewById(R.id.VP_id);
        viewFlipper = (ViewFlipper)findViewById(R.id.VF_id);

        Tx_page1.setOnClickListener(l);
        Tx_page2.setOnClickListener(l);
        //监听 页面切换  修改Tab
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override  //只有 有拉动 就会进入  positionOffset”指的是偏移量的百分比  positionOffsetPixels”指的是偏移量的数值
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Tx_page1.setText("");
            }

            @Override
            public void onPageSelected(int position) { //页面 切换结束  position页面id
                Tx_page1.setText("page1-B-"+String.valueOf(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {  //state 0->1->2->0  1正在滑动 2滑动完毕  0不动了
                Tx_page1.setText("page1-C-"+String.valueOf(state));
            }

        });


        //方案 1 ：直接 显示imageView
     /*   image1 = new ImageView(this);
        image1.setImageResource(R.mipmap.f1);
        image2 = new ImageView(this);
        image2.setImageResource(R.mipmap.f2);
        image3 = new ImageView(this);
        image3.setImageResource(R.mipmap.f3);
        lst_view.add(image1);
        lst_view.add(image2);
        lst_view.add(image3);

        adapterViewpager = new AdapterViewpager(lst_view);
        viewPager.setAdapter(adapterViewpager);
    */

        //方案2： 直接 显示Layout  一个个Layout 切换
   /*   View view = View.inflate(this, R.layout.item_pagerview,null);
        lst_view.add(view);

        adapterViewpager = new AdapterViewpager(lst_view);
        viewPager.setAdapter(adapterViewpager);
   */
       //方案3：  使用 碎片fragment  一个个 碎片 页面切换
        List<Fragment> fragments=new ArrayList<Fragment>();
        fragments.add( new MyFragment() );
        fragments.add( new MyFragment2() );

        AdapterPagerFragment adapter = new AdapterPagerFragment(getSupportFragmentManager(), fragments); //需要在FragmentActivity类里
        viewPager.setAdapter(adapter);


    }
}
