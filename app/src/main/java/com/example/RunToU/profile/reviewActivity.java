package com.example.RunToU.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class reviewActivity extends AppCompatActivity {

    private ArrayList<reviewData> arrayList;
    private com.example.RunToU.profile.reviewAdapter reviewAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private RequestQueue queue;


    ImageButton btnBack;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        setTitle("리뷰");

        recyclerView = findViewById(R.id.rvReview);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        reviewAdapter = new reviewAdapter(arrayList);
        recyclerView.setAdapter(reviewAdapter);


        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button btn_add = findViewById(R.id.btn_add);
        Button btnTest = findViewById(R.id.btnTest);
        String url = "http://3.39.87.103/api/review/orderer/own";

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Tag", "response Success");
                        try {
                            String content = response.getString("content");
                            int score = response.getInt("score");
                            String star = null;
                            for(int i = 0; i < score; i++){
                                star += "★";
                            }

                            reviewData reviewData = new reviewData(R.mipmap.ic_launcher, "teset12", "배달", star, content);
                            arrayList.add(reviewData);
                            reviewAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) { }
                }) {
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
            }
        });

        //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        String url1 = "http://3.39.87.103/api/review/match/749";
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "버튼 꾹");

                try {
                    JSONObject object = new JSONObject();
                    object.put("content","좀 잘했네" );
                    object.put("score", 5);

                    final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url1, object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Tag", "response Success");
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
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

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
