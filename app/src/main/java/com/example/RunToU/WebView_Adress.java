package com.example.RunToU;

<<<<<<< HEAD
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
=======
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
>>>>>>> ac4ac6e76d452ebb3a3f528b1dec28434dfc823f
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
<<<<<<< HEAD
import androidx.appcompat.app.AlertDialog;
=======
>>>>>>> ac4ac6e76d452ebb3a3f528b1dec28434dfc823f
import androidx.appcompat.app.AppCompatActivity;

public class WebView_Adress extends AppCompatActivity {

<<<<<<< HEAD

    WebView webView;

    class MyJavaScriptInterface{
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processDATA(String data){
            Bundle extra = new Bundle();
            Intent intent = new Intent();
            extra.putString("data", data);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
=======
    WebView webView;
    //String url = "C:\\Users\\YoungseoJeon\\Documents\\vscode\\addressPage.html";
>>>>>>> ac4ac6e76d452ebb3a3f528b1dec28434dfc823f

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

<<<<<<< HEAD
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
                builder.setMessage("페이지를 로드하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:sample4_execDaumPostcode();");
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPermissionRequest(final PermissionRequest request){
                request.grant(request.getResources());
            }

        });

        webView.getSettings().setDomStorageEnabled(true);
//        webView.loadUrl("http://서버주소/daum.html");
        webView.loadUrl("file:///android_asset/daum.html");


=======
        webView = findViewById(R.id.webview_write);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/addressPage.html");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
>>>>>>> ac4ac6e76d452ebb3a3f528b1dec28434dfc823f

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
