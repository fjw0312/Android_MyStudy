package com.customView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mydrawer.R;
import com.utils.GetFitBitmap;

/**
 * Created by fjw0312 on 2017/11/17.
 *  继承MyFragmentLazy
 */

public class MyFragment extends MyFragmentLazy{

    ImageView imageView;
    public int img_width = 1080;
    public int img_height = 480;

    //构造
    public MyFragment(){
        super();
    }

    //实例化 并传参数
    public static MyFragment newInstance(int arg){
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("key", arg);
        fragment.setArguments(bundle);
        return fragment;
    }
    public static MyFragment newInstance(String argc){
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", argc);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void lazyLaod() {
        super.lazyLaod();
        //获取相关数据
     //   int src_id = (int)getArguments().get("key");
        //将 图片加载
     //   Bitmap bitmap = GetFitBitmap.getResourcesImage(getResources(),src_id, img_width, img_height);
     //   imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void stopLoad() {
        super.stopLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.item_img, container, false);

        //初始化控件
        imageView = (ImageView)view.findViewById(R.id.img_id);
        //获取相关数据  //将 图片初始化加载
        int src_id = (int)getArguments().get("key");
        Bitmap bitmap = GetFitBitmap.getResourcesImage(getResources(),src_id, img_width, img_height);
        imageView.setImageBitmap(bitmap);

        return view;
    }
}
