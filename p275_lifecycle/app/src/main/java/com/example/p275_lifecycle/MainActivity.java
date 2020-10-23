package com.example.p275_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("[TEST]","onCreate()");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("[TEST]","onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("[TEST]","onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("[TEST]","onDestroy()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        saveStave();
        Log.d("[TEST]","onPause()");

    }
    @Override
    protected void onResume() {
        super.onResume();
        restoreState();
        Log.d("[TEST]","onResume()");
    }

     public void restoreState(){

     }

     public void saveStave(){
        //shared 여기에 저장되면 나만 볼 수 있게 저장
        sp = getSharedPreferences("st", Activity.MODE_PRIVATE);
        if(sp!=null&& sp.contains("date")){
            String result = sp.getString("date","");
            Toast.makeText(this,result,Toast.LENGTH_LONG).show();
         }
SharedPreferences.Editor editor = sp.edit();
Date d = new Date();
editor.putString("date",d.toString());
editor.commit();

     }

}