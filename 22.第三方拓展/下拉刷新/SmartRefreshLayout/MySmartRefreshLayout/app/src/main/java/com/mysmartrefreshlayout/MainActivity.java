package com.mysmartrefreshlayout;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class MainActivity extends AppCompatActivity {

    Button  Bn_pre;
    Button  Bn_next;
    SmartRefreshLayout smartRefreshLayout;

    private View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==Bn_pre){

            }else if(v==Bn_next){
                Intent intent = new Intent(MainActivity.this, Page2Activity.class);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bn_pre = (Button)findViewById(R.id.Bn_pre);
        Bn_next = (Button)findViewById(R.id.Bn_next);
        Bn_pre.setOnClickListener(l);
        Bn_next.setOnClickListener(l);
        smartRefreshLayout = (SmartRefreshLayout)findViewById(R.id.refreshLayout);
        smartRefreshLayout.setPrimaryColors(Color.BLUE);  //设置 主题颜色
        smartRefreshLayout.setHeaderHeight(80);

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
