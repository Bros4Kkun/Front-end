package com.example.RunToU;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebView_Adress extends AppCompatActivity {

    WebView webView;
    //String url = "C:\\Users\\YoungseoJeon\\Documents\\vscode\\addressPage.html";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = findViewById(R.id.webview_write);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/addressPage.html");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

//        {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, java.lang.String url) {
//                if (url != null && url.startsWith("")) {
//                    try {
//                        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
//                        startActivity(intent);
//                        return true;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                view.loadUrl(url);
//                return false;
//            }
//        }
        //webView.setWebChromeClient(new WebChromeClient());

        }

//        private class WebViewClientClass extends WebViewClient{
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        }


    }
