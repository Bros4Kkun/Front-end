package com.example.RunToU;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class workFragment extends Fragment {

    private RequestQueue queue;
    private ArrayList<workData> arrayList;
    private workAdapter workAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    int temp = 0;
    JSONArray jsonArray = new JSONArray();
    jsonData jsonData;


    String url = "http://3.39.87.103/api/ordersheet/list/ALL/1";

    //받아와서 뿌리는 사용자 정보
    String title;
    String content;
    String cate = "string";
    String destination;
    int cost = 0;

    //기본 이미지 변수
    int image = R.drawable.cate_deli;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_work, container, false);
        getActivity().setTitle("심부름");
        ImageButton btn_add = view.findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), writeActivity.class);
                startActivity(intent);
            }
        });

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                recyclerView = getActivity().findViewById(R.id.rvWork);
                linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                arrayList = new ArrayList<>();
                workAdapter = new workAdapter(arrayList);
                recyclerView.setAdapter(workAdapter);

                try {
                    //jsonObject = response.getJSONObject("");
                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    jsonArray = jsonObject.getJSONArray("orderSheetItemSampleList");
                    Log.d("tag", "jsonArray : " + jsonArray.toString());

                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jTemp  = jsonArray.getJSONObject(i);
                        title = jTemp.getString("title");
                        content = jTemp.getString("contentSample");
                        cate = jTemp.getString("category");
                        destination = jTemp.getString("destination");
                        cost = jTemp.getInt("cost");

                        if(cate.equals("DELIVERY_AND_SHOPPING")){
                            image = R.drawable.cate_deli;
                        }else if(cate.equals("CLEANING_AND_HOUSEWORK")){
                            image = R.drawable.cate_clean;
                        }else if(cate.equals("DELIVERY_AND_INSTALLATION")){
                            image = R.drawable.cate_help;
                        }else if(cate.equals("ACCOMPANY")){
                            image = R.drawable.cate_help;
                        }else if(cate.equals("ANTI_BUG")){
                            image = R.drawable.cate_hunt;
                        }else if(cate.equals("ROLE_ACTING")){
                            image = R.drawable.cate_help;
                        }else{
                            image = R.drawable.cate_all;
                        }
                        workData workData = new workData(image, String.valueOf(cost), destination, content);
                        arrayList.add(workData);
                        workAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("error", "error from jsonSet try/catch");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "error!!!!");
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
        return view;
    }
}