package com.example.RunToU.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.RunToU.R;
import com.example.RunToU.login.authenticationData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class edit_introduceActivity extends AppCompatActivity {

    ImageButton btnBack;
    ImageButton btnEdit;
    ImageView isChecked;
    TextView isVerified, userName;
    EditText editIntroduce;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("Tag", "onCreate - edit");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_introduce);

        RequestQueue queue;
//        RequestQueue queue1 = null;

        final ImageButton[] btnVeri = new ImageButton[1];

        isVerified = findViewById(R.id.isVerified);
        isChecked = findViewById(R.id.isChecked);
        btnVeri[0] = findViewById(R.id.btnVeri);
        btnEdit = findViewById(R.id.btnEdit);
        btnBack = findViewById(R.id.btnBack);
        editIntroduce = findViewById(R.id.editIntroduce);
        userName = findViewById(R.id.userName);

        Log.d("Tag","리퀘 USERNAME");


        String temp = isVerified.getText().toString();

        if(temp.equals("X")){
            btnVeri[0].setVisibility(View.VISIBLE);
            isChecked.setVisibility(View.INVISIBLE);
        }else if(temp.equals("O")){
            btnVeri[0].setVisibility(View.INVISIBLE);
            isChecked.setVisibility(View.VISIBLE);
        }

        String url = "http://3.39.87.103/api/user/test";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    RequestQueue queue;
                    String id = response.getString("accountId");
                    String nickname = response.getString("nickname");
                    userName.setText(nickname);
                    Log.d("Tag",id+"  "+nickname);

                    String url = "http://3.39.87.103/api/user/profile/" + id;

                    final JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String self = response.getString("selfIntroduction");
                                editIntroduce.setText(self);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<>();
                            headers.put("Cookie", authenticationData.SessionControl.INSTANCE.getSess());
                            headers.put("Content-Type", "application/json");
                            return headers;
                        }
                    };

                    Cache cache2 = new DiskBasedCache(getApplication().getCacheDir(), 1024 * 1024);
                    // Set up the network to use HttpURLConnection as the HTTP client.
                    Network network2 = new BasicNetwork(new HurlStack());
                    // Instantiate the RequestQueue with the cache and network.
                    queue = new RequestQueue(cache2, network2);
                    // Start the queue
                    queue.start();

                    request1.setShouldCache(false);
                    queue.add(request1);



                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Tag", "리퀘에러 "+e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Tag", "리퀘에러 "+error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Cookie", authenticationData.SessionControl.INSTANCE.getSess());
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        Cache cache = new DiskBasedCache(getApplication().getCacheDir(), 1024 * 1024);
        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        // Instantiate the RequestQueue with the cache and network.
        queue = new RequestQueue(cache, network);
        // Start the queue
        queue.start();

        request.setShouldCache(false);
        queue.add(request);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnVeri[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), verificationActivity.class);
                startActivity(intent);
            }//인증을 하는 수단을 추가해야할듯...
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://3.39.87.103/api/user/profile/introduction";
                RequestQueue queue;
                final JSONObject object = new JSONObject();
                try {
                    Log.d("Tag","editIntroduce2 : " + editIntroduce.getText().toString());
                    object.put("selfIntroduction", editIntroduce.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Tag","editIntroduce3 : " + editIntroduce.getText().toString());

                final JsonObjectRequest requestEdit = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Tag","response : " + response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Tag","error : " + error.toString());

                    }
                }) {@Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Cookie", authenticationData.SessionControl.INSTANCE.getSess());
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };

                //requestQueue가 Deprecated됨을 해결하기 위한 코드
                // Instantiate the cache
                Cache cache1 = new DiskBasedCache(getApplication().getCacheDir(), 1024 * 1024);
                // Set up the network to use HttpURLConnection as the HTTP client.
                Network network1 = new BasicNetwork(new HurlStack());
                // Instantiate the RequestQueue with the cache and network.
                queue = new RequestQueue(cache1, network1);
                // Start the queue
                queue.start();
                request.setShouldCache(false);
                queue.add(request);

                Toast.makeText(getApplication(), "프로필이 수정되었습니다!", Toast.LENGTH_LONG).show();
//                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        Log.d("Tag", "onResume - edit");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.d("Tag", "onDestroy - edit");

        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.d("Tag", "onStop - edit");

        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("Tag", "onRestart - edit");

        super.onRestart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Log.d("Tag", "onCreateView - edit");

        return super.onCreateView(name, context, attrs);
    }
}
