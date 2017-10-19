package com.mysmartrefreshlayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


/**
 * 上下拉刷新  框架 SmartRefreshLayout  +  MaterialHeader（经典）、 Header
 * 可以设置内容是否一起拖动
 * */
public class Page3Activity extends AppCompatActivity {

    Button  Bn_pre;
    Button  Bn_next;
    RefreshLayout smartRefreshLayout;
    ListView listView;
    String[] str_s = {"微信","QQ","陌陌","来往","探探",
            "爱奇艺","优酷","腾讯视频","乐视","bilibili",
            "凤凰","头条","网易","虎扑","天行"};

    private View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==Bn_pre){
                Intent intent = new Intent(Page3Activity.this, Page2Activity.class);
                startActivity(intent);
            }else if(v==Bn_next){
                Intent intent = new Intent(Page3Activity.this, Page4Activity.class);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        Bn_pre = (Button)findViewById(R.id.Bn_pre);
        Bn_next = (Button)findViewById(R.id.Bn_next);
        Bn_pre.setOnClickListener(l);
        Bn_next.setOnClickListener(l);
        smartRefreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true)); //设置 Header 为 Material样式
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲
        smartRefreshLayout.setPrimaryColors(Color.TRANSPARENT);  //设置 主题颜色
        smartRefreshLayout.setEnableHeaderTranslationContent(false); //拖动刷新时 不拖动内容

        listView = (ListView)findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str_s);
        listView.setAdapter(adapter);
        //设置  刷新 监听
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Log.i("onRefresh","进入 刷新");
                refreshlayout.finishRefresh(2000);  //2000ms
            }
        });
        //设置  刷新下载 监听
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Log.i("onRefresh","进入 下载");
                refreshlayout.finishLoadmore(2000);//2000ms
            }
        });

    }

}
