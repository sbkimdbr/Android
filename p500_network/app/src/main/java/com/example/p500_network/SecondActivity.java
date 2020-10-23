package com.example.p500_network;



import androidx.appcompat.app.AppCompatActivity;



import android.app.ProgressDialog;

import android.content.Context;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import android.os.AsyncTask;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.Adapter;

import android.widget.BaseAdapter;

import android.widget.ImageView;

import android.widget.LinearLayout;

import android.widget.ListView;

import android.widget.TextView;


import com.example.p500_network.HttpConnect;
import com.example.p500_network.Item;
import com.example.p500_network.R;

import org.json.JSONArray;

import org.json.JSONException;

import org.json.JSONObject;



import java.io.InputStream;

import java.net.MalformedURLException;

import java.net.URL;

import java.nio.channels.AsynchronousChannelGroup;

import java.util.ArrayList;



public class SecondActivity extends AppCompatActivity {

    ListView listView;

    LinearLayout container;

    ArrayList<Item> list;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        listView = findViewById(R.id.listView);

        container = findViewById(R.id.container);

        list = new ArrayList<>();

        getData();

    }



    private void getData() {

        String url = "http://192.168.123.107/web/items.jsp";

        ItemAsync itemAsync = new ItemAsync();

        itemAsync.execute(url);

    }



    class ItemAsync extends AsyncTask<String,Void,String>{



        ProgressDialog progressDialog;



        @Override

        protected void onPreExecute() {

            progressDialog = new ProgressDialog(SecondActivity.this);

            progressDialog.setTitle("Get Data ...");

            progressDialog.setCancelable(false);

            progressDialog.show();

        }



        @Override
        //주된 작업 실행

        protected String doInBackground(String... strings) {

            String url = strings[0].toString();

            String result = HttpConnect.getString(url);

            return result;

        }



        @Override

        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);

        }



        @Override

        protected void onPostExecute(String s) {



            progressDialog.dismiss();

            JSONArray ja = null;

            try {

                ja = new JSONArray(s);

                for(int i=0;i<ja.length();i++){

                    JSONObject jo = ja.getJSONObject(i);
                    String id = jo.getString("id");
                    String name = jo.getString("name");

                    int age = jo.getInt("age");

                    String img = jo.getString("img");

                    Item item = new Item(id,name,age,img);

                    list.add(item);

                }

            } catch (JSONException e) {

                e.printStackTrace();

            }



            ItemAdapter itemAdapter = new ItemAdapter();

            listView.setAdapter(itemAdapter);



        }

    } // end AsyncTask



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

            LayoutInflater inflater =

                    (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            itemView = inflater.inflate(R.layout.item,container,true);

            TextView tx_id = itemView.findViewById(R.id.textView);

            TextView tx_name = itemView.findViewById(R.id.textView2);

            TextView tx_age = itemView.findViewById(R.id.textView3);

            tx_id.setText(list.get(position).getId());

            tx_name.setText(list.get(position).getName());

            tx_age.setText(list.get(position).getAge()+"");

            //이 부분에 Alertdialog창 띄어지는 부분 함께 추가 



            final ImageView imageView = itemView.findViewById(R.id.imageView);

            String img = list.get(position).getImg();

            final String url = "http://192.168.123.107/web/img/"+img;

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

    } // end Adapter



}