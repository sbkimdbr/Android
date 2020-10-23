package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 ImageView himg;
 Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 = findViewById(R.id.button2);
        //himg = findViewById(R.id.himg);
    }
    public void clickBt(View view){
        himg.setVisibility(View.INVISIBLE);
        button2.setText(R.string.bt_test);
        Log.d("[TEST]","-------");
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }
//
//    public void clickBt2(View view){
//        Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
//        startActivity(intent);
//    }
//    public void clickBt3(View view){
//        Intent intent= new Intent(Intent.ACTION_VIEW,Uri.parse("tel:010-7686-2632"));
//        startActivity(intent);
//    }

    public void clickBts(View view){
        Intent intent = null;


        if(view.getId()==R.id.button2){
         intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://m.naver.com"));
        }else if (view.getId()==R.id.button3){
            intent=new Intent(Intent.ACTION_VIEW,Uri.parse("tel:010-"));
        }else if(view.getId()==R.id.button4){
            intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:01010"));
            //commision에 대한 체크 확
            if(checkSelfPermission(Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED){
                return;

            }

        }
        startActivity(intent);
    }
}
