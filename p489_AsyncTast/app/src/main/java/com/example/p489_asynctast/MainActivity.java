package com.example.p489_asynctast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button,button2;
    SeekBar seekBar;
    TextView textView;
    ImageView imageView;
    MyAsynch myAsynch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button2 = findViewById(R.id.button2);
        button.setEnabled(false);
        button.setEnabled(true);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(100);//시크바 사이즈 설정
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

    }

    public void ckbt1(View v){
        //myasynch실행
        MyAsynch myAsynch = new MyAsynch();
        myAsynch.execute();
    }

    public void ckbt2(View v){
        myAsynch.cancel(true);
        myAsynch.onCancelled();

    }

    class MyAsynch extends AsyncTask<Integer,Integer,String> {

        //쓰레드 실행


        @Override
        protected void onPreExecute() {
            button.setEnabled(false);
            button2.setEnabled(true);

        }

        @Override
        protected String doInBackground(Integer... integers) {
            //int a=integers[0].intValue();
            int sum=0;
            for(int i=0;i<=100;i++){
                if(isCancelled() == true){
                    break;
                }
                sum+=i;
                publishProgress(i);//onProgressUpdate에 생성되는 값을 던져준다.
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "result:"+sum;
        }

        //진행되고 있는 값들의 정보를 알 수 있다.
        @Override
        protected void onProgressUpdate(Integer... values) {
            int i = values[0].intValue();//i값을 받는다.
            seekBar.setProgress(i);//받은 i값을 세팅해준다.
            if(i<=30){
                imageView.setImageResource(R.drawable.down);

            }else if(i<=70){
                imageView.setImageResource(R.drawable.middle);

            }else if(i<=100){
                imageView.setImageResource(R.drawable.up);
            }
        }

        //Thread end->get return String type
        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
            button.setEnabled(true);
            button2.setEnabled(false);

        }

        @Override
        protected void onCancelled() {
            seekBar.setProgress(0);
            textView.setText("");
            imageView.setImageResource(R.drawable.ic_launcher_background);
            button.setEnabled(true);
            button2.setEnabled(false);

        }
    }
}