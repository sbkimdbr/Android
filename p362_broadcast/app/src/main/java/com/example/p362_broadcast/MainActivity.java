package com.example.p362_broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    //어떤 통신을 받겠다라고 선언
    //어떠한 broadcast 들의 종류를 받을 것인지 등록하는 필터가 Intent filter

    //Broadcast 가 받는데 전화, 네트워크 이런거를 받는 곳이 intent filter
    //두개 함께 움직임
    BroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        //which kind of permissions
        String permissions [] = {

                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS
        };

        ActivityCompat.requestPermissions(this,permissions,101);

        //여러개 브로드캐스트 사용하면 intent filter에 등록
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        // 등록된 것을 받겠다.
        // 모든 앱간의 전송은 intent함수가 실행된다.
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String cmd = intent.getAction();
                ConnectivityManager cm = null;

                NetworkInfo mobile = null;
                NetworkInfo wifi = null;

                //connectivity의 내용 변경
                //변경된 내용 확인하기
                if(cmd.equals("android.net.conn.CONNECTIVITY_CHANGE")){
                    cm = (ConnectivityManager) context.getSystemService(
                            Context.CONNECTIVITY_SERVICE

                    );
                    mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                    wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                    //상태확
                    if (mobile != null && mobile.isConnected()){

                    }else if(wifi != null && wifi.isConnected()){
                        imageView.setImageResource(R.drawable.wifi);

                    }else{
                        imageView.setImageResource(R.drawable.nonwifi);

                    }

                }else if(cmd.equals("android.provider.Telephony.SMS_RECEIVED")){
                   Toast.makeText(context,"SMS_RECEIVED",Toast.LENGTH_SHORT).show();

                  //sms내용ㅇ이 배열에 들어가있음
                   Bundle bundle = intent.getExtras();
                   Object [] obj = (Object [])bundle.get("pdus");
                    SmsMessage [] messages = new  SmsMessage[obj.length];
                    for(int i =0;i<obj.length;i++){
                        String format = bundle.getString("format");
                        messages[i]= SmsMessage.createFromPdu(
                                (byte[]) obj[i],format
                        );

                    }

                    String msg = "";
                    if(messages !=null && messages.length>0){
                        msg += messages[0].getOriginatingAddress()+"\n";
                        msg += messages[0].getMessageBody().toLowerCase();
                        msg += new Date(messages[0].getTimestampMillis()).toString();
                        textView.setText(msg);

                    }
                }
            }
        };

        //받은 것을 등록하겠다---> 즉 연결하겠다.
        registerReceiver(broadcastReceiver,intentFilter);

    }//end onCreate

    //register한 후에 연결 끊어주는 destroy 함수 꼭 작성(연결 끊는 함수는 stop도 있지만 destroy)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    public void ckbt(View v){

        if(v.getId() == R.id.button){
            //1.permission check sentence
            //2. Intent 함수를 사용하여 permission granted or
            int check = PermissionChecker.checkSelfPermission(this,Manifest.permission.CALL_PHONE);
            if (check == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:010-1111-1111"));
                startActivity(intent);
            }else{
                Toast.makeText(this,"Denied",Toast.LENGTH_LONG).show();
            }

        }else if(v.getId() == R.id.button2){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(

                    "tel:0101010",
                    null,
                    "hello hello",
                    null,
                    null
            );
            Toast.makeText(this,"Send OK",Toast.LENGTH_LONG).show();

        }
    }
}