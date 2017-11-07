package com.mydrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.customView.MyFragmentLazy;

/**
 * Created by fjw0312 on 2017/10/31.
 *  碎片
 */

public class Class1Fragment extends MyFragmentLazy{

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("Class1Fragment","setUserVisibleHint="+String.valueOf(isVisibleToUser));
    }

    @Override
    protected void lazyLaod() {
        super.lazyLaod();
        //获取相关数据  并 数据控件处理
        //   int src_id = (int)getArguments().get("key");
    }

    @Override
    protected void stopLoad() {
        super.stopLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_fragment_class1, container, false);
        Log.i("Class1Fragment","intio onCreateView");

        return view;
    }

}
