package com.example.tcpip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.msg.Msg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView tx_list,tx_msg;
    EditText et_ip,et_msg;

    int port;
    String address;
    String id;
    Socket socket;
    Sender sender;

    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx_list = findViewById(R.id.tx_list);
        tx_msg = findViewById(R.id.tx_msg);
        et_ip = findViewById(R.id.et_ip);
        et_msg = findViewById(R.id.et_msg);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        FirebaseMessaging.getInstance().subscribeToTopic("client").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              String msg = "FCM Complete";
                if (!task.isSuccessful()) {
                    msg = "FCM Fail";
                }
                Log.d("[TAG]:", msg);
            }
        });

        port = 5555;
        address = "192.168.123.107";
        id = "[Subi]";

        new Thread(con).start();

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(receiver, new IntentFilter("notification"));

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            Msg msg = new Msg(null,id,"q");
            sender.setMsg(msg);
            new Thread(sender).start();
            if(socket!=null){
                socket.close();
            }
            onDestroy();
            finish();
        }catch (Exception e){

        }

    }


    Runnable con = new Runnable() {
            @Override
            public void run() {
                try {
                    Connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        private void Connect () throws IOException {
            try {
                socket = new Socket(address, port);
            } catch (Exception e) {
                while (true) {
                    //System.out.println("Retry...");
                    try {

                        Thread.sleep(2000);
                        socket = new Socket(address, port);
                        break;
                    } catch (Exception e1) {
                        System.out.println("Retry...");
                    }

                }

            }
            System.out.println("Connenected socket of Server...:" + address);
            sender = new Sender(socket);
            new Receiver(socket).start();
            getList();
        }


    private void getList(){
            //사용자의 정보를 읽으면 된다.
        //1을 보내면 서버는 데이터를 주고 receiver가 메시지를 받는다
        Msg msg = new Msg(null,"[Subi]","1");
        sender.setMsg(msg);
        new Thread(sender).start();

    }


    public void clickBt(View v){
        String txip = et_ip.getText().toString();
        String txmsg = et_msg.getText().toString();
        Msg msg=null;

        if(txip==null || txip.equals("")) {
            msg = new Msg(id, txmsg);

        }else{
            ArrayList<String> ips = new ArrayList<>();
            ips.add(txip);


            msg=new Msg(ips,id,txmsg);


        }
            sender.setMsg(msg);


        new  Thread(sender).start();

    }

    class Receiver extends Thread{
        Socket socket;
        ObjectInputStream oi;
        public Receiver(Socket socket) throws IOException {
            this.socket=socket;
            oi = new ObjectInputStream(this.socket.getInputStream());
        }
        @Override
        public void run() {
            // 클라이언트의 메세지를 계속 받아야한다
            //현재 사용자에 대한 정보
            //for 문으로 hashmap에 정보를넣어라
            //1을 보낸 클라이언트 정보
            //서버의 접속자 ip들이 hm에 들어있음

            while(oi != null) {
                Msg msg = null;

                try {
                    msg=(Msg)oi.readObject();
                    if(msg.getMaps()!=null) {
                        HashMap<String,Msg> hm=
                                msg.getMaps();
                        Set<String> keys = hm.keySet();
                        for(String k:keys) {
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {

                             }
                         });
                         System.out.println(k);

                        }
                        continue;
                    }
                    final Msg finalMsg = msg;
                    Log.d("--------------------------",finalMsg.getMsg());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           String tx=tx_msg.getText().toString();
                           tx_msg.setText("["+finalMsg.getId()+"]"+finalMsg.getMsg()+"\n"+tx);
                        }
                    });

                    System.out.println("["+msg.getId()+"]"+msg.getMsg());
                    //System.out.println(msg.getMsg());

                } catch (Exception e) {
                    //여기서 예외되면 클라이언트가 죽은 상태임
                    e.printStackTrace();
                    break;
                }
            }//end while
            try {
                if(oi!=null) {
                    oi.close();
                }
                if(socket!=null) {
                    socket.close();
                }
            } catch (Exception e) {

            }
        }


//        private void sendMsg(Msg msg) {
//
//         et_ip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//             @Override
//             public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                 String etip = et_ip.getText().toString();
//                 Msg msg= null;
//                 if (etip.equals("1")){
//                     msg = new Msg(id,etip);
//                 }else{
//                     ArrayList<String> ips = new ArrayList<>();
//                     ips.add("/192.168.0.82");
//                     msg = new Msg(null,id,etip);
//                 }
//                 return false;
//             }
//         });
//        }

    }

    class Sender implements Runnable{
        Socket socket;
        ObjectOutputStream oo ;
        Msg msg;

        public Sender(Socket socket) throws IOException {
            this.socket=socket;
            oo=new ObjectOutputStream(socket.getOutputStream());
           setMsg(msg);
        }

        public void setMsg(Msg msg) {
            this.msg=msg;
        }

        @Override
        public void run() {
            if(oo!=null) {
                try {
                    oo.writeObject(msg);//msg전송 서버에게 전송한다.
                   //et_msg.setText("");
                } catch (IOException e) {
                    // server가 죽어있을 확률이 크다

                    try {
                        if(socket!=null) {
                            socket.close();
                        }
                    }catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();

                    }

                    try {

                        Thread.sleep(2000);
                        Connect();


                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

        }



    }
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String title = intent.getStringExtra("title");
                String control = intent.getStringExtra("control");
                String data = intent.getStringExtra("data");
                Toast.makeText(MainActivity.this, title + " " + control + " " + data,
                        Toast.LENGTH_LONG).show();


            }
        }
    };

}