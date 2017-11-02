package com.customView;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 *  自定义控制  继承现有控件ViewPager
 *  组件功能：  页面切换容器
 *  notice:   Context 必须传入FragmentActivity
 *  author:  fjw0312@163.com
 */

public class MyViewPager extends ViewPager{
    public MyViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        init_view(context);
    }
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context,attrs);
        init_view(context);
    }
    public MyViewPager(Context context) {
        super(context);
        init_view(context);
    }

    // 使用变量定义
    Context mContext;
    AdapterPagerFragment  adapter;


    //初始化 控件
    private void init_view(Context context){
       mContext  = context;
    }
    //参数1：activity中viewpager布局  和fragment中ViewPager布局 不一样
    //外部调用api  更新 控件页面数据  参数：FrameActivity.getSupportFragmentManager() / fragment.getChildFragmentManager()
    public void init_adapter(FragmentManager fragmentManager, List<Fragment> mFragments){
        if(adapter!=null){
            adapter.UpdateAdapter(mFragments);
            adapter.notifyDataSetChanged();
        }else{
           adapter = new AdapterPagerFragment( fragmentManager, mFragments);
           this.setAdapter(adapter);
        }
    }
    //外部调用api  更新  控件某个item 数据（某个页面数据）
    public void Update(int id, Fragment fragment){
        if(adapter!=null) {
            adapter.UdateAdapter(id, fragment);
            adapter.notifyDataSetChanged();
        }
    }



    //定义 碎片适配器
    private class AdapterPagerFragment extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public AdapterPagerFragment(FragmentManager fm){
            super(fm);
        }

        public AdapterPagerFragment(FragmentManager fm, List<Fragment> mFragments) {
            super(fm);
            this.mFragments = mFragments;
        }

        //更新 适配器 链表
        public void UpdateAdapter(List<Fragment> mFragments){
            if(this.mFragments!=null){
                this.mFragments.clear();
            }
            this.mFragments = mFragments;
        }
        //更新  适配器  链表某个字item
        public void UdateAdapter(int id,Fragment fragment){
            if(id>=getCount()) return;
            List<Fragment> newFragments = new ArrayList<Fragment>();
            for(int i=0;i<mFragments.size();i++){
                if(i==id){
                    newFragments.add(fragment);
                }else{
                    newFragments.add(mFragments.get(i));
                }
            }
            mFragments.clear();
            mFragments = newFragments;
            newFragments = null;
        }


        @Override
        public Fragment getItem(int position) {
            if(mFragments==null) return null;
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            if(mFragments==null) return 0;
            return  mFragments.size();
        }
    }


}
