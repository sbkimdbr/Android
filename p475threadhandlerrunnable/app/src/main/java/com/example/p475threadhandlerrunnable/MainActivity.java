package com.example.p475threadhandlerrunnable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2= findViewById(R.id.textView2);

    }

    public void ckbt(View v){
        if (v.getId()==R.id.button){
            MyThread t=new MyThread();
            t.start();

        }
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {

        }
    }

    class MyHandler2 extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {

        }
    }


    class MyThread extends Thread{
        @Override
        public void run() {
            int a=0;
            Random random = new Random();
            for(int i=0;i<=200;i++){
                a=random.nextInt(1);
                textView.setText(i+"");
                try {
                    Thread.sleep(100);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    }

}