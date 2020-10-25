package com.example.ws;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView listView;
    LinearLayout container;
    ArrayList<Item> list;
    MapFragment mapFragment;
    ReserveFragment reserveFragment;
    Button button7;
    Button button8;
    NotificationManager manager;
    ImageView imageView2;

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.m1) {
//            getSupportFragmentManager().beginTransaction().replace(
//                    R.id.fragment2, mapFragment
//            ).commit();
//
//        } else if (item.getItemId() == R.id.m2) {
//            getSupportFragmentManager().beginTransaction().replace(
//                    R.id.fragment2, reserveFragment
//            ).commit();
//        }return false;
//    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
        list = new ArrayList<>();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        imageView2 = findViewById(R.id.imageView2);

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
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(receiver, new IntentFilter("notification"));



        getData();
    }

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String title = intent.getStringExtra("title");
                String control = intent.getStringExtra("control");
                String data = intent.getStringExtra("data");

                Toast.makeText(SecondActivity.this, title + " " + control + " " + data,
                        Toast.LENGTH_LONG).show();


            }

        }
    };

    public void map(View v){
        Intent intent = new Intent(getApplicationContext(),MapFragment.class);
        intent.putExtra("data",100);
        intent.putExtra("str","String Data");
        startActivity(intent);

    }




    private void getData() {

        //데이터 가져오기 위해 서버와 연동
        //Async 사용하겠다고 선언
        //Async class 만들고 async 실행 -> 데이터는 json 형식으로 가져오겠다
                                        // listView에 출력함

        String url = "http://192.168.123.107/web/items.jsp";

        ItemAsync itemAsync = new ItemAsync();

        itemAsync.execute(url);

    }
    class ItemAsync extends AsyncTask<String,String,String>{

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(SecondActivity.this);

            progressDialog.setTitle("Get Data ...");

            progressDialog.setCancelable(false);

            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0].toString();

            String result = HttpConnect.getString(url);

            return result;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            JSONArray ja;
            try {
                ja=new JSONArray(s);
                for(int i=0;i<=ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);
                    String id = jo.getString("id");
                    String name = jo.getString("name");

                    String age = jo.getString("age");
                    String img = jo.getString("img");
                    Item item = new Item(id,name,age,img);
                    list.add(item);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ItemAdapter itemAdapter = new ItemAdapter();
            listView.setAdapter(itemAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                    builder.setTitle(list.get(position).getId());
                    builder.setMessage("name:"+list.get(position).getId()+"\n"+
                            "place:"+ list.get(position).getName()+"\n"
                            );
                    final String url = "http://192.168.123.107/web/img/";
                    Log.d("TAG",url);
                    LayoutInflater layoutInflater = getLayoutInflater();
                    View view1= layoutInflater.inflate(R.layout.dlayout, (ViewGroup) findViewById(R.id.dlayout));
                    ImageView dimg = view1.findViewById(R.id.imageView2);
                    GetImg t3 = new GetImg(list.get(position).getImg(), url, dimg);
                    t3.start();

                    builder.setView(view1);
                    AlertDialog dialog = builder.create();
                    dialog.show();


                }
            });

        }
    }

    class ItemAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = null;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item,container,true);
            TextView tx_id = itemView.findViewById(R.id.textView2);

            TextView tx_name = itemView.findViewById(R.id.textView3);

            TextView tx_age = itemView.findViewById(R.id.textView4);

            tx_id.setText(list.get(position).getId());

            tx_name.setText(list.get(position).getName());

            tx_age.setText(list.get(position).getAge()+"");

            final ImageView imageView = itemView.findViewById(R.id.imageView);

            String img = list.get(position).getImg();

            final String url = "http://192.168.123.107/web/img/"+img;
            GetImg t1 = new GetImg(img, url, imageView);
            t1.start();

            Thread t = new Thread(new Runnable() {

                @Override

                public void run() {

                    URL httpurl = null;

                    InputStream is = null;

                    try {

                        httpurl = new URL(url);

                        is = httpurl.openStream();

                        final Bitmap bm = BitmapFactory.decodeStream(is);

                        runOnUiThread(new Runnable() {

                            @Override

                            public void run() {

                                imageView.setImageBitmap(bm);

                            }

                        });



                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                }

            });

            t.start();



            return itemView;
        }


    }

    class GetImg extends Thread{
        String img;
        String url;
        ImageView imageView2;
         public GetImg(String ima,String url,ImageView imageView){
          this.img=img;
          this.url=url;
          this.imageView2= imageView2;
         }
    }

    @Override
    //상단바 메뉴의 버튼을 누르면 화면 전환된다.
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.m1){
            Intent intent = new Intent(getApplicationContext(),MapFragment.class);
        }else if(item.getItemId() == R.id.m2){
            Intent intent = new Intent(getApplicationContext(),ReserveFragment.class);
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

}



