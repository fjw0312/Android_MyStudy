package com.myhttp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/10/11.
 */

public class MyFragment3 extends Fragment{

    public MyFragment3() {
    }


    TextView textView;

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

        if(getArguments()!=null){
            String title = (String)getArguments().get("key");
            if(title!=null){
                textView.setText(title);
            }
        }

    }

}
