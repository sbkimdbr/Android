package com.example.p331;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
   Fragment1 fragment1;
   Fragment2 fragment2;
   Fragment3 fragment3;
   MainActivity m;

   FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1= new Fragment1(m);
        fragment2= new Fragment2();
        fragment3= new Fragment3();

        fragmentManager= getSupportFragmentManager();

        //framelayout 에 fragment1을 놓겠다.
        fragmentManager.beginTransaction().replace(R.id.framelayout,fragment1).commit();


        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()== R.id.tab1){
                    //Toast.makeText(MainActivity.this,"tab1",Toast.LENGTH_LONG).show();
                    fragmentManager.beginTransaction().replace(R.id.framelayout,fragment1).commit();
                }else if(item.getItemId()== R.id.tab2){
                    //Toast.makeText(MainActivity.this,"tab2",Toast.LENGTH_LONG).show();
                    fragmentManager.beginTransaction().replace(R.id.framelayout,fragment2).commit();
                }else if(item.getItemId()== R.id.tab3){
                    //Toast.makeText(MainActivity.this,"tab3",Toast.LENGTH_LONG).show();
                    fragmentManager.beginTransaction().replace(R.id.framelayout,fragment3).commit();
                }
                return false;
            }
        });


    }
}