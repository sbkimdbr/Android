package com.example.p552_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.content.SharedPreferences.*;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("login",MODE_PRIVATE); // 최초 사용을 위해 선언, 외부에서 접근금지 (read,rewrite의 기능도 있음)함
        String status = sp.getString("status","");
        Toast.makeText(this,status, Toast.LENGTH_LONG).show();
    }

    //로그인 상태던지 현재의 상태를 sharedPreferences에 저장하고 삭제하는 기능
    public void ck(View v){
        //정보를 저장해두기 위해서 저장
        SharedPreferences.Editor edit= sp.edit(); //SharedPreferences에 값 저장
        edit.putString("status","ok"); //status란 이름의 창에 ok띄움
        edit.commit();

    }

    public void ck2(View v){
        //저장된 것을 삭제한
        SharedPreferences.Editor edit= sp.edit();
        edit.remove("status");
        edit.commit();

    }

}