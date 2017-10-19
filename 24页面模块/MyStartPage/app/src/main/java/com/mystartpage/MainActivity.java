package com.mystartpage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.customView.CirclePageIndicator;
import com.utils.GetFitBitmap;


public class MainActivity extends AppCompatActivity {

    CirclePageIndicator circlePageIndicator;
    Button button;
    static int i = 0;

    ImageView imageView;

    public static float wPx;
    public static float hPx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题拦
        setContentView(R.layout.activity_main);

    //    StatusBarCompat.compat(this, Color.RED);
        wPx = this.getResources().getDisplayMetrics().widthPixels;
        hPx = this.getResources().getDisplayMetrics().heightPixels;

        circlePageIndicator = (CirclePageIndicator)findViewById(R.id.CirclePageIndicator);
        button = (Button)findViewById(R.id.Bn_id);
        circlePageIndicator.setCircleNum(10);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    i++;
            //    circlePageIndicator.setSelectItem(i);
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

        imageView = (ImageView)findViewById(R.id.img_id);
        Bitmap bitmap = GetFitBitmap.getResourcesImage(getResources(),R.mipmap.m1,(int)wPx, 1000);
        imageView.setImageBitmap(bitmap);


    }
}
