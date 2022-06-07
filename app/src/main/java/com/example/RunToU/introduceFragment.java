package com.example.RunToU;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.example.RunToU.databinding.FragmentIntroduceBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class introduceFragment extends Fragment {

    TextView tvIntroduce;
    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Tag", "onCreateView - introduce");

        View view = inflater.inflate(R.layout.fragment_introduce, container, false);
        tvIntroduce = view.findViewById(R.id.tvIntroduce);
        return view;
    }

    @Override
    public void onResume() {
        Log.d("Tag", "onResume - introduce");

        super.onResume();

        Log.d("Tag", "이게 중요함");

        String url = "http://3.39.87.103/api/user/test";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String id = response.getString("accountId");
                    String url = "http://3.39.87.103/api/user/profile/" + id;
                    final JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                String self = response.getString("selfIntroduction");
                                Log.d("Tag","Introduce : " + self);
//                                tvIntroduce.setText(self);

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
                            headers.put("Cookie", SessionControl.SessionControl.INSTANCE.getSess());
                            headers.put("Content-Type", "application/json");
                            return headers;
                        }
                    };

                    request1.setShouldCache(false);
                    queue.add(request1);

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
                headers.put("Cookie", SessionControl.SessionControl.INSTANCE.getSess());
                headers.put("Content-Type","application/json");
                return headers;
            }
        };

        //requestQueue가 Deprecated됨을 해결하기 위한 코드
        // Instantiate the cache
        Cache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024);
        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        // Instantiate the RequestQueue with the cache and network.
        queue = new RequestQueue(cache, network);
        // Start the queue
        queue.start();
        request.setShouldCache(false);
        queue.add(request);

    }


    @Override
    public void onDestroy() {
        Log.d("Tag", "onDestroy - introduce");

        super.onDestroy();
    }

    @Override
    public void onStop() {
        Log.d("Tag", "onStop - introduce");

        super.onStop();
    }



}