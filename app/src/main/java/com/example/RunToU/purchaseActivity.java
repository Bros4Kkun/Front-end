package com.example.RunToU;

import android.content.Context;
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
    JSONObject jsonTemp;
    private RequestQueue queue;
    static Context context_pur;
    int pk = 12;//게시물 pk 변수 예정
    static int temp = 0; //입금 구분자
    int cost = 0;
    String url = "http://3.39.87.103/api/ordersheet/"+ String.valueOf(pk);  //얼마인지만 작성되니까 로컬데이터(intent)로 이동시키면 될듯

    //로컬데이터만 이동한다면 사실상 api 통신은 필요없는...걸지더

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        context_pur = this;

        queue = Volley.newRequestQueue(this);
        tv_Money = findViewById(R.id.tv_Money);
        btnBack = findViewById(R.id.btnBack);
        btnPay = findViewById(R.id.btnPay);

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //아래의 코드는 특정 주문서를 눌렀을때로 가자
//                    jsonTemp = response.getJSONObject("orderSheetItem");
//                    cost = jsonTemp.getInt("cost");
//                    Log.d("test", " mTemp.get(\"cost\")" +cost);
//                    tv_Money.setText(String.valueOf(cost)+"원");

                Intent i = getIntent();
                cost = i.getIntExtra("cost", -1);
                tv_Money.setText(String.valueOf(cost));

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

        request.setShouldCache(false);
        queue.add(request);
        temp += 1;

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
                    //        http://3.39.87.103/api/ordersheet/test/pay/{orderSheetId} 써보기
                }
            }
        });
    }
}