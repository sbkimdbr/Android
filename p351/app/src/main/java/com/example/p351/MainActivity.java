package com.example.p351;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
Fragment1 fragment1;
Fragment3 fragment3;
FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment3 = new Fragment3();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout, fragment1).commit();
        //fragmentManager.beginTransaction().replace(R.id.framelayout,fragment3).commit();

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.m1) {
                            //Toast.makeText(MainActivity.this,"menu1",Toast.LENGTH_LONG).show();
                            fragmentManager.beginTransaction().replace(R.id.framelayout, fragment1).commit();
                            return true;
                        } else if (menuItem.getItemId() == R.id.m2) {
                            Toast.makeText(MainActivity.this, "SecondMenu", Toast.LENGTH_LONG).show();
                            return true;
                        } else if (menuItem.getItemId() == R.id.m3) {
                            fragmentManager.beginTransaction().replace(R.id.framelayout, fragment3).commit();

                            return true;

                        }


                        return false;
                    }
                }

        );

    }


}

