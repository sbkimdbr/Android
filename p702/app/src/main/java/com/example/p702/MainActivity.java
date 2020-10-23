package com.example.p702;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    NotificationManager manager;

    //p.709 상단바에 알림 알려주기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void ck1(View v) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//getsystem:하드웨어 기능 제어

        //빨간줄-Permission check, if else구문 사용하여 버전체크 해주기
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(1000, 10));

        } else {
            vibrator.vibrate(1000);

        }

    }

    public void ck2(View v) {

        MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.mp);
        player.start();
    }

    public void ck3(View v) {

    }

    public void ck4(View v) {

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= 26) {
            if (manager.getNotificationChannel("ch1") == null) {
                manager.createNotificationChannel(new NotificationChannel("ch1", "chname"
                        , NotificationManager.IMPORTANCE_DEFAULT));

            }

            builder = new NotificationCompat.Builder(this, "ch1");
        } else {
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentTitle("Test");
        builder.setContentText("conText");
        builder.setSmallIcon(R.drawable.m9);
        Notification noti = builder.build();
        manager.notify(1, noti);

    }





    public void ck5(View v){

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT>=26){
            if(manager.getNotificationChannel("ch2")==null){
                manager.createNotificationChannel(new NotificationChannel("ch2","chname2"
                        ,NotificationManager.IMPORTANCE_DEFAULT));

            }
            builder=new NotificationCompat.Builder(this,"ch2");
        }else {
            builder = new NotificationCompat.Builder(this);
        }

        //내 자신이 자기 자신을 호출
        //Intent를 이용하여 액티비티 띄어줌

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent  = PendingIntent.getActivity(
          this,101,intent,PendingIntent.FLAG_UPDATE_CURRENT
        );//새롭게 호출

        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent); //클릭하면 pending Intent실
            builder.setContentTitle("Test");
            builder.setContentText("conText");
            builder.setSmallIcon(R.drawable.m9);
            Notification noti = builder.build();
            manager.notify(1,noti);

        }

}