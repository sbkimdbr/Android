package com.example.p500_network;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tx_id,tx_pwd;
    HttpAsync httpAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx_id = findViewById(R.id.tx_id);
        tx_pwd=findViewById(R.id.tx_pwd);
    }

    public void ck(View v){

        String id=tx_id.getText().toString(); //저장된 tx_id 값 가져옴
        String pwd=tx_pwd.getText().toString();
        //Toast.makeText(this,id+pwd,Toast.LENGTH_SHORT).show();
        String url = "http://192.168.123.107/web/login.jsp";
        url += "?id="+id+"&pwd="+pwd;
        httpAsync = new HttpAsync();
        httpAsync.execute(url);
        //String result=com.example.p500_network.MainActivity.HttpConnect.getString(url);

    }

    class HttpAsync extends AsyncTask<String,String,String> {

        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
           progressDialog = new ProgressDialog(MainActivity.this);
           progressDialog.setTitle("Login...");
           progressDialog.setCancelable(false);
           progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0].toString();
            String result=HttpConnect.getString(url);

            return result;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            String result = s.trim();
            if(result.equals("1")){
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);//페이지 넘기기


            }else{
                AlertDialog.Builder dailog = new AlertDialog.Builder(MainActivity.this);
                dailog.setTitle("Login fail");
                dailog.setMessage("try again");
                dailog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                dailog.show();

            }
        }
    }



}