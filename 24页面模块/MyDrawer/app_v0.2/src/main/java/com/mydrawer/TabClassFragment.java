package com.mydrawer;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;

import com.customView.MyFragmentLazy;
import com.customView.MyHorizontalListView;
import com.customView.MyViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 * 设备碎片
 */

public class TabClassFragment extends MyFragmentLazy {

    HorizontalScrollView horizontalScrollView;
    MyHorizontalListView myHorizontalListView;
    MyViewPager myViewPager;

   // List<String> strTitle_lst = new ArrayList<String>();
    String[] strTitle_lst = new String[]{"UPS","电表","空调","微环境"};
    List<Fragment> fragments;

    public TabClassFragment() {
        super();
        fragments = newfragments();
        Log.i("E_fragment","into super!");
    }

    private List<Fragment> newfragments(){
        fragments = new ArrayList<Fragment>();
        fragments.add(new Class1Fragment());
        fragments.add(new Class2Fragment());
        fragments.add(new Class3Fragment());
        fragments.add(new Class4Fragment());
        return fragments;
    }


    //定义 控制横向列表 点击选择的Item 居中自移动
    private void translateMyHorizontalListView_itemToCenter(int position){
        int horLstView_Width = myHorizontalListView.getWidth(); //横向列表长度
        int horSrcView_Width = horizontalScrollView.getWidth();  //横向列表可见长度
        int tranlate = position * myHorizontalListView.itemWith + myHorizontalListView.itemWith / 2 - horSrcView_Width / 2;
        if (tranlate < 0) {
            tranlate = 0;
        } else if (tranlate > horLstView_Width - horSrcView_Width) {
            tranlate = horLstView_Width - horSrcView_Width;
        }

        horizontalScrollView.smoothScrollTo(tranlate, 0); // horizontalScrollView.scrollTo(tranlate,0);
    }



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tabclass, container, false);
        Log.i("TabClassFragment>>","onCreateView into!");
        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.HorScrolView_id);
        myHorizontalListView = (MyHorizontalListView) view.findViewById(R.id.MyHorizontalListView); //获取控件
       // myHorizontalListView.Update(Arrays.asList(strTitle_lst),(int)(MainActivity.wPx/strTitle_lst.length));//更新控件数据 item.lenth
        myHorizontalListView.Update(Arrays.asList(strTitle_lst),250);//更新控件数据 item.lenth
        myViewPager = (MyViewPager) view.findViewById(R.id.MyViewPager); //获取控件
        myViewPager.init_adapter(getChildFragmentManager(),fragments);  //初始化适配器  初始化数据
        myViewPager.setOffscreenPageLimit(strTitle_lst.length-1); //设置缓存 个数  保证流畅性

        //设置监听
        myHorizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //控件设置item点击监听
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Adapter adapter = parent.getAdapter(); //获得控件 适配器
                myHorizontalListView.Select(position);  //更新选中item
                myViewPager.setCurrentItem(position);     //切换ViewPager页面

                //       translateMyHorizontalListView_itemToCenter(position);  //选择item 自动居中移动
            }
        });
        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override //页面 切换结束  position页面id
            public void onPageSelected(int position) {
                Log.i("Tab_fragment","into myViewPager onPageSelected! = "+String.valueOf(position));
                myHorizontalListView.Select(position);  //更新myHorizontalListView选中item
             //   translateMyHorizontalListView_itemToCenter(position);  //选择item 自动居中移动
            }

            @Override //state 0->1->2->0  1正在滑动 2滑动完毕  0不动了
            public void onPageScrollStateChanged(int state) {

            }
        });
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
