package com.example.p251_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        //int result = intent.getIntExtra("data",0);

        //여러개 데이터를 수월하게 가져올 수 있다.- bundle
        Bundle bundle = intent.getExtras();
        int result = bundle.getInt("data",0);
        String str_result = bundle.getString("str","");
        Toast.makeText(this,str_result+":"+result , Toast.LENGTH_LONG).show();
    }

    public void ckbt2 (View v){
        //secondActivity로 이동
        Intent intent = new Intent(getApplicationContext(),ThirdActivity.class);
        startActivity(intent);

    }
}