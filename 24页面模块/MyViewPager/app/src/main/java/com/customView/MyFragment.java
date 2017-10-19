package com.customView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myviewpager.R;

/**
 * Created by Administrator on 2017/10/10.
 *  碎片 自定义 基础类
 */

public class MyFragment extends Fragment{

    TextView textView;
    public int BackgroudColor = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_layout, container, false);

        textView = (TextView)view.findViewById(R.id.Tx_id);
        return view;
    }

    @Override  //接收数据
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String title = (String)getArguments().get("key");
        textView.setText(title);
        textView.setBackgroundColor(BackgroudColor);
    }


}
