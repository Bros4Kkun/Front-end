package com.example.RunToU;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class purchaseActivity extends AppCompatActivity {

    ImageButton btnBack, btnPay;
    TextView tv_Money;
    JSONObject mTemp;
    private RequestQueue queue;
    int pk = 12;//게시물 pk 변수 예정
    int temp = 1; //입금 구분자
    String url = "http://3.39.87.103/api/ordersheet/12";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        queue = Volley.newRequestQueue(this);
        tv_Money = findViewById(R.id.tv_Money);
        btnBack = findViewById(R.id.btnBack);
        btnPay = findViewById(R.id.btnPay);

        Fragment fragment = new Fragment();


//        Intent i = getIntent();
//         String title = i.getStringExtra("title");
//         String content = i.getStringExtra("content");
//         String category = i.getStringExtra("category");
//         String destination = i.getStringExtra("destination");
//         int cost = i.getIntExtra("cost", -1);
//         tv_Money.setText(cost);

//        Bundle bundle = getIntent().getExtras();
//        long cost = bundle.getLong("cost", -1);
//        tv_Money;


//            final JSONObject object = new JSONObject();
//            object.put("title",mTemp);

//        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    mTemp = response.getJSONObject("orderSheetItem");
//                    temp = mTemp.getInt("cost");
//                    tv_Money.setText(temp);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.print("error" + error);
//            }
//        })
//
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                //headers.put("Cookie", SessionControl.SessionControl.INSTANCE.getSess());
//                headers.put("Content-Type","application/json");
//                return headers;
//            }
//        };
//
//        request.setShouldCache(false);
//        queue.add(request);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp == 0){
                    Toast.makeText(getApplication(), "아직 입금이 확인되지 않았습니다!!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(), "입금 완료!!!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}