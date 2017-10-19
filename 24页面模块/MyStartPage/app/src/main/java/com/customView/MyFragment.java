package com.customView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mystartpage.MainActivity;
import com.mystartpage.R;
import com.utils.GetFitBitmap;

import java.io.InputStream;

/**
 * Created by fjw0312 on 2017/10/10.
 *  碎片 自定义 基础类
 */

public class MyFragment extends Fragment{

    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.item_start_img, container, false);

        imageView = (ImageView)view.findViewById(R.id.img_id);

        return view;
    }

    @Override  //接收数据
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int src_id = (int)getArguments().get("key");
        //将 图片加载
        Bitmap bitmap = GetFitBitmap.getResourcesImage(getResources(),src_id,(int) MainActivity.wPx, (int) MainActivity.hPx);
        imageView.setImageBitmap(bitmap);
    }


}
