package com.mylogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import login.LoginTableActivity;
import login.UserLoginActivity;

public class MainActivity extends AppCompatActivity {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.Bn_next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==button){
                //    Intent intent = new Intent(MainActivity.this, UserLoginActivity.class);
                    Intent intent = new Intent(MainActivity.this, LoginTableActivity.class);
                    startActivity(intent);
                }
            }
        });

    }


}
