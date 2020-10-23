package com.example.p251_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String [] permissions = {

                //어던 퍼미션
                Manifest.permission.CALL_PHONE
        };
        ActivityCompat.requestPermissions(this,permissions,101);
    }
    public void ckbt(View v){
            //secondActivity로 이동
            Intent intent = new Intent(getApplicationContext(),SecondActivity.class);

            //int와 string 값을 보낸
            intent.putExtra("data",100);
            intent.putExtra("str","String Data");

            startActivity(intent);

    }
}