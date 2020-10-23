package com.example.p473_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar,progressBar2;
    TextView textView,textView2;

    Button button,button2,button3;
    MyHandler myHandler;
    MyHandler2 myHandler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar.setMax(50);
        progressBar2.setMax(50);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        myHandler = new MyHandler();
        myHandler2 = new MyHandler2();
    }

    public void ckbt(View v)  {
        if(v.getId() == R.id.button){
            MyThread t = new MyThread();
            t.start();
            button.setEnabled(false);
            //t.start시작되면 바로 button함수 실

//            이 부분을 스레드로 만든다.
//
//            for(int i=0;i<=20;i++){
//                progressBar.setProgress(i);
//                textView.setText(i+"");
//                Thread.sleep(1000);
//            }

        }else if(v.getId() == R.id.button2){
            Thread t = new Thread(new MyTHread2());
            t.start();
            button2.setEnabled(false);

        }else if ((v.getId() == R.id.button3)){
            progress();

        }
    }

    public void progress(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        AlertDialog.Builder dailog = new AlertDialog.Builder(this);
        dailog.setTitle("progress");
        dailog.setMessage("5 seconds");

        final Handler handler = new Handler();

        dailog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.setCancelable(false);
                progressDialog.setTitle("Downloading...");

                progressDialog.show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //progress 5초 후에 사라짐
                        progressDialog.dismiss();

                    }
                },5000);
            }
        });
        dailog.show();
    }

    //모든 스레드는 run에서
    //별도의 스레드는 For문이 돌아간다.
    //Thread t = new Thread(){


       class MyThread extends Thread{
           @Override
           public void run() {
               for(int i=0;i<=50;i++){
                   progressBar.setProgress(i);
                   textView.setText(i+"");
                   try {
                       Thread.sleep(200);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }

               //sub thread에서 mainthread의 위젯 내용을 변경하기 위한 함수
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       button.setEnabled(true);
                   }
               });



           }
       };


    //};

    //class의 함수 위에 선
    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int data = bundle.getInt("tdata",0);
            textView.setText("Han1:"+data);
            progressBar.setProgress(data);
        }
    }

    class MyHandler2 extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {

            Bundle bundle = msg.getData();
            int data = bundle.getInt("tdata",0);
            textView2.setText("Han2:"+data);
            progressBar2.setProgress(data);
            if (data == 50){
                button.setEnabled(true);
            }
        }
    }

    class MyTHread2 implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<=50;i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Handler
                //여기서 동작되어 나오는 i의 값을 handler에 보냄
                //즉 1부터 50까지 다운 받고있는 상태를 두개의 handler에게 변화 값을 알려준다.
                //-> subthread에 변경되는 상태를 mainThread에게 알려주는 것이 handler서
                Message message = myHandler.obtainMessage();
                Message message2 = myHandler2.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("tdata:",i);
                message.setData(bundle);
                message2.setData(bundle);
                //myHandler.sendMessage(message);
                myHandler2.sendMessage(message2);


            }

        }
    }
}