package com.example.administrator.myapplication01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import myPackage01.MyFun01;
import myTestPackage.MyTestJar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyTestJar yy = new MyTestJar();
        int g = yy.fun_x(8,11);
       // Toast.makeText(this, String.valueOf(g), Toast.LENGTH_LONG).show();

        MyFun01 myfun = new MyFun01();
        int result = myfun.add(20,10);
        Toast.makeText(this, String.valueOf(result), Toast.LENGTH_LONG).show();
    }
}
