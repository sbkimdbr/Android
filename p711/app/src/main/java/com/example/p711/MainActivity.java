package com.example.p711;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;



public class MainActivity extends AppCompatActivity {

    NotificationManager manager;

    TextView tx;

    ////mainActivity는 firebase로 데이터를 받을 것 oncreate 명시함
    // fcm에서 오는 데이터를 받을 준비를 하겠다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx = findViewById(R.id.tx);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //topic부분과 이클립스 topic부분 일치시킴
        FirebaseMessaging.getInstance().subscribeToTopic("car").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String msg = "FCM Complete...";
                if (!task.isSuccessful()) {
                    msg = "FCM Fail";
                }
                Log.d("[TAG]:", msg);

            }
        });

        //값 받을 준비를 하는 곳
        //ation이 맵핑하 ㄴ이름의 값을 받음
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(receiver, new IntentFilter("notification"));

    }//end oncreate

    //service에서 action의 이름으로 보낸 데이터를 여기서 받음
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //뿌린 값을 실제로 받는 부분


            if (intent != null) {
                String title = intent.getStringExtra("title");
                String control = intent.getStringExtra("control");
                String data = intent.getStringExtra("data");
                tx.setText(control + " " + data);
                Toast.makeText(MainActivity.this, title + " " + control + " " + data,
                        Toast.LENGTH_LONG).show();


            }

        }


    };
}










