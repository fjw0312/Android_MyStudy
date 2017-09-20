package com.project01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends AppCompatActivity {


    Button Bn_Pre;
    Button Bn_Next;
    PtrClassicFrameLayout ptrFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bn_Pre = (Button)findViewById(R.id.Bn_pre);
        Bn_Next = (Button)findViewById(R.id.Bn_next);


        ptrFrameLayout = (PtrClassicFrameLayout)findViewById(R.id.ptrClassicFrameLayout);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {  //处理刷新
            public void onRefreshBegin(PtrFrameLayout frame) {  //开始刷新
                // TODO Auto-generated method stub
                //处理包裹的content刷新业务。
                //延时2s后  结束刷新
                ptrFrameLayout.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        ptrFrameLayout.refreshComplete();	//结束刷新
                    }
                }, 2000);
            }

            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content,
                                             View header) {
                // TODO Auto-generated method stub//返回true  表示可以刷新 。
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }
}
