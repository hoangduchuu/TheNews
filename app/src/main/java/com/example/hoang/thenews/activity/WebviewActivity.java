package com.example.hoang.thenews.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hoang.myapplication.R;


public class WebviewActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent i = getIntent();
        webView = (WebView)findViewById(R.id.webView);
        String url =         i.getStringExtra("url");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
    }
}
