package com.example.p287_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String [] permission = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };                     //Permission 배열로 만든다

        ActivityCompat.requestPermissions(this, permission, 101);

        //fragment1 = new Fragment1();
        fragment1 = (Fragment1)getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        actionBar = getSupportActionBar();
        actionBar.setTitle("Fragment");
        actionBar.setLogo(R.drawable.im2);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        //actionBar.hide();
    }

    @Override
    //상단바 메뉴의 버튼을 누르면 화면 전환된다.
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.m1){
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment,fragment1
            ).commit();

        }else if(item.getItemId() == R.id.m2){
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment,fragment2
            ).commit();

        }else if(item.getItemId()==R.id.m3){
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment,fragment3
            ).commit();

        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.mymenu,menu);
      return true;
    }

    public void ckbt(View v){
        if(v.getId()==R.id.button2){
getSupportFragmentManager().beginTransaction().replace(
           R.id.fragment,fragment1).commit();
        }
        if(v.getId()==R.id.button3){
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment,fragment2
            ).commit();

        }
        if(v.getId()==R.id.button4){
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment,fragment3).commit();

        }
    }
}