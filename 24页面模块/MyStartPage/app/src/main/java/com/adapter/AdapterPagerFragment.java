package com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 * 定义 碎片适配器
 */

public class AdapterPagerFragment extends FragmentPagerAdapter {
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
    public android.support.v4.app.Fragment getItem(int position) {
        if(mFragments==null) return null;
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if(mFragments==null) return 0;
        return  mFragments.size();
    }

}
