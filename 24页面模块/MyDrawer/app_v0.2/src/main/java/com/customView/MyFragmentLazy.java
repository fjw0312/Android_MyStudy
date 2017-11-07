package com.customView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mydrawer.R;
import com.utils.GetFitBitmap;

/**
 * Created by fjw0312 on 2017/10/10.
 *  碎片 自定义 懒加载基础类
 *  特别：碎片不拍被提前 初始化预加载，不耗时 不耗内存
 *       只当碎片显示时，才延时加载更新数据。
 *       只需继承后，重写 lazyLaod()、stopLoad()、onCreateView();
 *       延时显示后才延时操作放在lazyLaod()、 预加载就执行放在onCreateView()若不需任何控件初始化可设备layout id后不重写。
 */

public class MyFragmentLazy extends Fragment{

    boolean isInit = false; //碎片初始化 标志变量
    boolean isVisible = false; //是否可见
    public int R_ly_id = R.layout.item_img; //布局文件id; //当不需要初始化控件 时可直接设置该变量


    //构造
    public MyFragmentLazy(){
        super();
    }

/*
     //   外部传参数  --可直接调用newInstance
     //        Bundle bundle = new Bundle();
     //       bundle.putInt("key", img_src_s[i]);
     //        myFragment.setArguments(bundle);
    //实例化 并传参数  ----方便实例化 与传参
    public static MyFragmentLazy newInstance(int arg){
        MyFragmentLazy fragment = new MyFragmentLazy();
        Bundle bundle = new Bundle();
        bundle.putInt("key", arg);
        fragment.setArguments(bundle);
        return fragment;
    }
    public static MyFragmentLazy newInstance(String argc){
        MyFragmentLazy fragment = new MyFragmentLazy();
        Bundle bundle = new Bundle();
        bundle.putString("key", argc);
        fragment.setArguments(bundle);
        return fragment;
    }
*/
    //判断是否加载  数据
    protected void isCanLoadData(){
        if(!isInit) return;  //未初始化 不处理
        if(getUserVisibleHint()){  //初始化 并 碎片可见
            isVisible = true;
            //处理碎片可见 数据
            lazyLaod();
        }else{
            isVisible = false;
            //处理碎片不可见  数据终止
            stopLoad();
        }
    }
    //加载数据
    protected void lazyLaod(){
        //获取相关数据
       // int src_id = (int)getArguments().get("key");


    }
    //停止加载数据
    protected void stopLoad(){

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();  //已预加载 并 可见 时加载数据
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R_ly_id, container, false);
        //初始化控件  初始化赋值
        //imageView = (ImageView)view.findViewById(R.id.img_id);

        return view;
    }
    /*     外部传参数  --可直接调用newInstance
            Bundle bundle = new Bundle();
            bundle.putInt("key", img_src_s[i]);
            myFragment.setArguments(bundle);

     */
    @Override  //接收数据
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isInit = true;
        //预加载 若可见 加载数据
        isCanLoadData();
    }

    @Override  //销毁 碎片可见视图  不一定释放销毁碎片
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isVisible = false;
    }

}
