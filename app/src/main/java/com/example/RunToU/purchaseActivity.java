package com.example.RunToU;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class purchaseActivity extends AppCompatActivity {

    private RequestQueue queue;
    private static final String TAG = "MAIN";

    TextView tv_Money;
    ImageButton btnBack, btnPay;
    int temp = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        btnBack = findViewById(R.id.btnBack);
        btnPay = findViewById(R.id.btnPay);

        tv_Money = findViewById(R.id.tv_Money);

        queue = Volley.newRequestQueue(this);
        String url = "http://3.39.87.103/api/ordersheet";

        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    tv_Money.setText(response.getString("cost") + "원");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonRequest.setTag(TAG);
        queue.add(jsonRequest);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (temp == 0) {
                    Toast.makeText(getApplication(), "아직 입금이 확인되지 않았습니다!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "입금 완료!!!", Toast.LENGTH_SHORT).show();
                }
            }});
    }
}