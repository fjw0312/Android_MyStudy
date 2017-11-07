package com.mydrawer;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.customView.CirclePageIndicator;
import com.customView.MyFragment;
import com.customView.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 * 首页碎片
 */

public class MainFragment extends Fragment {

    MyViewPager myViewPager;
    CirclePageIndicator circlePageIndicator;

    List<Fragment> fragments;
    int[] img_src_ids = {R.mipmap.k_logo1,R.mipmap.k_logo2,R.mipmap.k_logo3};

    public MainFragment() {
         super();

        fragments = newfragments(img_src_ids); //初始化 碎片链表
    }
    //传入 图片资源id
    private List<Fragment> newfragments(int[] img_src_s){
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < img_src_s.length; i++) {
            MyFragment myFragment = new MyFragment();
            Bundle bundle = new Bundle();
            int src_id = img_src_s[i];
            bundle.putInt("key", src_id);
            myFragment.setArguments(bundle);

            fragments.add(myFragment);
        }
        return fragments;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);

        myViewPager = (MyViewPager)view.findViewById(R.id.MyViewPager);
        circlePageIndicator = (CirclePageIndicator)view.findViewById(R.id.CirclePageIndicator);

        myViewPager.init_adapter(getChildFragmentManager(),fragments); //初始化 ViewPager适配器
        circlePageIndicator.setViewPager(myViewPager);  //设置 圆点数 和页面滑动监听
        return view;
    }


    @Override  //接收数据
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        String info = (String)getArguments().get("key");

    }
}
