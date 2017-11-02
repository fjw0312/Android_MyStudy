package com.customView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mydrawer.R;
import com.utils.GetFitBitmap;

/**
 * Created by fjw0312 on 2017/10/10.
 *  碎片 自定义 基础类
 */

public class MyFragment extends Fragment{

    ImageView imageView;
    public int img_width = 1080;
    public int img_height = 480;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.item_img, container, false);

        imageView = (ImageView)view.findViewById(R.id.img_id);

        return view;
    }

    @Override  //接收数据
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int src_id = (int)getArguments().get("key");
        //将 图片加载
        Bitmap bitmap = GetFitBitmap.getResourcesImage(getResources(),src_id, img_width, img_height);
        imageView.setImageBitmap(bitmap);
    }


}
