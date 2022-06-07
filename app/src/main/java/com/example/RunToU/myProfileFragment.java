package com.example.RunToU;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

public class myProfileFragment extends Fragment {

    private RequestQueue queue;

    TextView nickname, temp;
    ImageButton btnDetInfo,btnJob, btnReviewList;
    Button btnIntroduce,btnJobRec ,btnRevRec;
    View infoFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_profile, container, false);

        nickname = rootView.findViewById(R.id.nickname);
        btnDetInfo = rootView.findViewById(R.id.btnDetInfo);
        btnJob = rootView.findViewById(R.id.btnJob);
        btnReviewList = rootView.findViewById(R.id.btnReviewList);
        btnIntroduce = rootView.findViewById(R.id.btnIntroduce);
        btnJobRec = rootView.findViewById(R.id.btnJobRec);
        btnRevRec = rootView.findViewById(R.id.btnRevRec);
        infoFrag = rootView.findViewById(R.id.infoFrag);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        String url = "http://3.39.87.103/api/user/test";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String id = response.getString("accountId");
                    nickname.setText(response.getString("nickname"));

                    String url = "http://3.39.87.103/api/user/profile/" + id;

                    temp(id);
                    final JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                String self = response.getString("selfIntroduction");
                                temp = infoFrag.findViewById(R.id.tvIntroduce);
                                temp.setText(self);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
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

        //init
        Fragment fragment= new Fragment(R.layout.fragment_introduce); // 객체 생성
        getFragmentManager().beginTransaction().add(R.id.infoFrag, fragment).commit();
        btnIntroduce.setBackgroundColor(Color.parseColor("#6EB943"));
        btnJobRec.setBackgroundColor(Color.WHITE);
        btnRevRec.setBackgroundColor(Color.WHITE);
        btnIntroduce.setTextColor(Color.WHITE);
        btnJobRec.setTextColor(Color.BLACK);
        btnRevRec.setTextColor(Color.BLACK);

        //상세
        btnDetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), edit_introduceActivity.class);
                startActivity(intent);
            }
        });

        //이력
        btnJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), jobrecActivity.class);
                startActivity(intent);
            }
        });

        //관심목록
        btnReviewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), reviewActivity.class);
                startActivity(intent);
            }
        });




        btnJobRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment= new Fragment(R.layout.fragment_short_jobrec); // 객체 생성
                getFragmentManager().beginTransaction().add(R.id.infoFrag, fragment).commit();

                btnIntroduce.setBackgroundColor(Color.WHITE);
                btnJobRec.setBackgroundColor(Color.parseColor("#6EB943"));
                btnRevRec.setBackgroundColor(Color.WHITE);
                btnIntroduce.setTextColor(Color.BLACK);
                btnJobRec.setTextColor(Color.WHITE);
                btnRevRec.setTextColor(Color.BLACK);
            }
        });
        btnRevRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new Fragment(R.layout.fragment_revrec); // 객체 생성
                getFragmentManager().beginTransaction().add(R.id.infoFrag, fragment).commit();
                btnIntroduce.setBackgroundColor(Color.WHITE);
                btnJobRec.setBackgroundColor(Color.WHITE);
                btnRevRec.setBackgroundColor(Color.parseColor("#6EB943"));
                btnIntroduce.setTextColor(Color.BLACK);
                btnJobRec.setTextColor(Color.BLACK);
                btnRevRec.setTextColor(Color.WHITE);
            }
        });

    }
    public void temp(String id){
        String url = "http://3.39.87.103/api/user/profile/" + id;

        btnIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new Fragment(R.layout.fragment_introduce); // 객체 생성
                getFragmentManager().beginTransaction().add(R.id.infoFrag, fragment).commit();
                btnIntroduce.setBackgroundColor(Color.parseColor("#6EB943"));
                btnJobRec.setBackgroundColor(Color.WHITE);
                btnRevRec.setBackgroundColor(Color.WHITE);
                btnIntroduce.setTextColor(Color.WHITE);
                btnJobRec.setTextColor(Color.BLACK);
                btnRevRec.setTextColor(Color.BLACK);

                final JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String self = response.getString("selfIntroduction");
                            temp = infoFrag.findViewById(R.id.tvIntroduce);
                            temp.setText(self);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Cookie", SessionControl.SessionControl.INSTANCE.getSess());
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };

                Cache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024);
                // Set up the network to use HttpURLConnection as the HTTP client.
                Network network = new BasicNetwork(new HurlStack());
                // Instantiate the RequestQueue with the cache and network.
                queue = new RequestQueue(cache, network);
                // Start the queue
                queue.start();
                request1.setShouldCache(false);
                queue.add(request1);

            }
        });

    }

}
