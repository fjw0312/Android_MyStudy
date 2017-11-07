package com.mydrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.customView.MyFragmentLazy;

/**
 * Created by fjw0312 on 2017/10/31.
 *  碎片
 */

public class PageFragment extends MyFragmentLazy {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_page, container, false);

     //   imageView = (ImageView)view.findViewById(R.id.img_id);
        return view;
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



}
