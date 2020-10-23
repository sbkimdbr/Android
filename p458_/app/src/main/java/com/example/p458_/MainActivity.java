package com.example.p458_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {


    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient());


        webSettings.setJavaScriptEnabled(true);

    }


    public void ckbt(View v){
        if(v.getId() == R.id.button){
            webView.loadUrl("http://m.naver.com");

        }else if(v.getId() ==  R.id.button2){
            webView.loadUrl("http://www.daum.net");

        }else if(v.getId() ==  R.id.button3){
            webView.loadUrl(("http://127.0.0.1/android"));

        }
    }
}