package com.example.RunToU;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ordersheetActivity extends AppCompatActivity {

    private RequestQueue queue;

    String title, time, num, cate, destination, content;
    int cost;

    TextView order_cost, order_title, order_time, order_num, order_content;
    ImageView order_cate;
    String sheetId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {//persistentState의 유무로 xml이 나오고 안나오고 갈린다 왜일까?
        super.onCreate(savedInstanceState);//이 액티비티 같은경우엔 내가 없앴음.
        setContentView(R.layout.activity_ordersheet);

        order_cost = findViewById(R.id.order_cost);
        order_title = findViewById(R.id.order_title);
        order_time = findViewById(R.id.order_time);
        order_cate = findViewById(R.id.order_cate);
        order_num = findViewById(R.id.order_num);
        order_content = findViewById(R.id.order_content);

        Intent intent = getIntent();
        sheetId = intent.getStringExtra("sheetId");

        order_num.setText(sheetId);

        String url = "http://3.39.87.103/api/ordersheet/" + sheetId;
        Log.d("what","url : " + url);


        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("what", "response : "+response.toString());

                try{
                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    Log.d("what", "jsonObject : "+jsonObject.toString());
                    jsonObject = jsonObject.getJSONObject("orderSheetItem");

                    title = jsonObject.getString("title");
                    content = jsonObject.getString("content");
                    cate = jsonObject.getString("category");
                    destination = jsonObject.getString("destination");
                    cost = jsonObject.getInt("cost");
                    time = jsonObject.getString("wishedDeadLine");
                    num = jsonObject.getString("orderSheetId");

                    String d_temp = time.substring(0,10);
                    String d_arr[] = d_temp.split("-");

                    String t_temp = time.substring(11,18);
                    String t_arr[] = t_temp.split(":");

                    String w_temp = d_arr[0]+"년 " + d_arr[1] + "월 " + d_arr[2] + "일 " + t_arr[0] + "시 " + t_arr[1] + "분";

                    order_cost.setText(cost+"원");
                    order_num.setText("No."+num);
                    order_title.setText(title);
                    order_time.setText(w_temp);
                    order_content.setText(content);

                    if(cate.equals("DELIVERY_AND_SHOPPING")){
                        order_cate.setImageResource(R.drawable.cate_deli);
                    }else if(cate.equals("CLEANING_AND_HOUSEWORK")){
                        order_cate.setImageResource(R.drawable.cate_clean);
                    }else if(cate.equals("DELIVERY_AND_INSTALLATION")){
                        order_cate.setImageResource(R.drawable.cate_help);
                    }else if(cate.equals("ACCOMPANY")){
                        order_cate.setImageResource(R.drawable.cate_help);
                    }else if(cate.equals("ANTI_BUG")){
                        order_cate.setImageResource(R.drawable.cate_bug);
                    }else if(cate.equals("ROLE_ACTING")){
                        order_cate.setImageResource(R.drawable.cate_help);
                    }else{
                        order_cate.setImageResource(R.drawable.cate_all);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "onErrorResponse");
            }
        }) {
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
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        // Instantiate the RequestQueue with the cache and network.
        queue = new RequestQueue(cache, network);
        // Start the queue
        queue.start();

        request.setShouldCache(false);
        queue.add(request);
    }
}
