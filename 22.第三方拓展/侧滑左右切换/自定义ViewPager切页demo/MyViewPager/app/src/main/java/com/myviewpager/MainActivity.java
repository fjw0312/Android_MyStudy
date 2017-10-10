package com.myviewpager;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;

import com.customView.MyFragment;
import com.customView.MyHorizontalListView;
import com.customView.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    HorizontalScrollView horizontalScrollView;
    MyHorizontalListView myHorizontalListView;
    List<String> strTitle_lst = new ArrayList<String>();

    MyViewPager myViewPager;
    List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strTitle_lst.add("汽车");
        strTitle_lst.add("火车");
        strTitle_lst.add("单车");
        strTitle_lst.add("飞机");
        strTitle_lst.add("轮船");
        strTitle_lst.add("走路");
        strTitle_lst.add("骑马");
        strTitle_lst.add("牛车");
        strTitle_lst.add("牛车1");
        strTitle_lst.add("牛车2");
        strTitle_lst.add("牛车3");
        strTitle_lst.add("牛车4");
        strTitle_lst.add("牛车5");
        strTitle_lst.add("牛车6");

        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.HorScrolView_id);
        myHorizontalListView = (MyHorizontalListView) findViewById(R.id.MyHorizontalListView); //获取控件初始化
        myHorizontalListView.Update(strTitle_lst);  //更新控件数据
        myHorizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //控件设置item点击监听
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Adapter adapter = parent.getAdapter(); //获得控件 适配器
                myHorizontalListView.Select(position);  //更新选中item
                myViewPager.setCurrentItem(position);     //切换ViewPager页面

                translateMyHorizontalListView_itemToCenter(position);  //选择item 自动居中移动
            }
        });

        for (int i = 0; i < strTitle_lst.size(); i++) {
            MyFragment myFragment = new MyFragment();
            Bundle bundle = new Bundle();
            String value = strTitle_lst.get(i);
            bundle.putString("key", value);
            myFragment.setArguments(bundle);

            switch (i % 3) {
                case 0:
                    myFragment.BackgroudColor = Color.BLUE;
                    break;
                case 1:
                    myFragment.BackgroudColor = Color.CYAN;
                    break;
                case 2:
                    myFragment.BackgroudColor = Color.RED;
                    break;
                default:
                    break;
            }
            fragments.add(myFragment);
        }

        myViewPager = (MyViewPager) findViewById(R.id.MyViewPager); //获取控件
        myViewPager.Update(fragments); //放入碎片页面
        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override //页面 切换结束  position页面id
            public void onPageSelected(int position) {
                myHorizontalListView.Select(position);  //更新myHorizontalListView选中item
                translateMyHorizontalListView_itemToCenter(position);  //选择item 自动居中移动
            }

            @Override //state 0->1->2->0  1正在滑动 2滑动完毕  0不动了
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        // horizontalScrollView.scrollTo(tranlate,0);
        horizontalScrollView.smoothScrollTo(tranlate, 0);
    }
}
