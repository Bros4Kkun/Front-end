package com.example.RunToU;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class purchaseActivity extends AppCompatActivity {

    ImageButton btnBack, btnPay;
    TextView tv_Money;
    String mTemp;
    private RequestQueue queue;
    int pk = 12;//게시물 pk
    int temp = 0; //입금 구분자
    String url = "http://3.39.87.103/api/ordersheet/12";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        queue = Volley.newRequestQueue(this);
        tv_Money = findViewById(R.id.tv_Money);
        btnBack = findViewById(R.id.btnBack);
        btnPay = findViewById(R.id.btnPay);

        try {
            final JSONObject object = new JSONObject();
            object.put("cost",mTemp);

            final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    mTemp = response.getString("cost");
                    System.out.print("Test%%%%%%%%%%%%%%%%" + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.print("error" + error);
                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Cookie", SessionControl.SessionControl.INSTANCE.getSess());
                    headers.put("Content-Type","application/json");
                    return headers;
                }
            };

//            String cost = getText(mTemp).toString();
//            Toast.makeText(this, cost, Toast.LENGTH_LONG).show();
            request.setShouldCache(false);
            queue.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //tv_Money.setText(mTemp);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        btnPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(temp == 0){
//                    Toast.makeText(getApplication(), "아직 입금이 확인되지 않았습니다!!!", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getApplication(), "입금 완료!!!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}