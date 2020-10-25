package com.example.ws;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tx_id;
    TextView tx_pwd;
    HttpAsync httpAsync;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx_id = findViewById(R.id.tx_id);
        tx_pwd=findViewById(R.id.tx_pwd);
    }

    public void login(View v){

        //서버와 연동을 위해 async 활용

        String id=tx_id.getText().toString(); //저장된 tx_id 값 가져옴
        String pwd=tx_pwd.getText().toString();
        //Toast.makeText(this,id+""+pwd,Toast.LENGTH_SHORT).show();
        String url = "http://192.168.123.107/web/login.jsp";
        url += "?id="+id+"&pwd="+pwd;
        Log.d("tag",url);

        httpAsync = new HttpAsync();
        httpAsync.execute(url);

    }

    class HttpAsync extends AsyncTask<String,String,String>{

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
            progressDialog.dismiss();
            String result = s.trim();
            if(result.equals("1")){
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }else{
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Login fail");
                dialog.setMessage("try again");
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                dialog.show();

            }
        }
    }
}