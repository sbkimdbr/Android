package com.example.p711;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


      //mainactivity 뒤에서 푸시 메시지를 받기위한 서비스
public class MyFService extends FirebaseMessagingService {
    public MyFService() {
    }



       //여기서 푸시 메시지 받음
       //아래 선언 한 대로 메세지를 받아서 출력함
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        String title = remoteMessage.getNotification().getTitle(); // 처음 제목 받아옴,이클립스의 title부분 값 받아
        String control = remoteMessage.getData().get("control");
        String data = remoteMessage.getData().get("data");

        //실제로 데이터 들어오는 부분
        //이 부분을 메인으로 보낸다
        Log.d("[TAG]:",title+"  "+control+"  "+data);

        //다른 페이지로 전송할 때 쓰는 함수
        //Service에서 받은내용 main의 broadcastreciever로 intent함수를 활용하여 보냄
        //action의 이름으로 보낸다
        //main은 action의 이을 받음
        Intent intent = new Intent("notification");
        intent.putExtra("title",title);
        intent.putExtra("control",control);
        intent.putExtra("data",data);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
