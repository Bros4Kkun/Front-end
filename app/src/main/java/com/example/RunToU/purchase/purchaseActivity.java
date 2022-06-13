package com.example.RunToU.purchase;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.example.RunToU.R;
import com.example.RunToU.login.authenticationData;
import com.iamport.sdk.data.sdk.IamPortApprove;
import com.iamport.sdk.data.sdk.IamPortRequest;
import com.iamport.sdk.data.sdk.PG;
import com.iamport.sdk.data.sdk.PayMethod;
import com.iamport.sdk.domain.core.Iamport;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import kotlin.Unit;

public class purchaseActivity extends AppCompatActivity {

    ImageButton btnBack, btn1000, btn5000, btn10000, btn50000, btn100000;
    TextView tv_point;
    JSONObject jsonTemp;
    private RequestQueue queue;
    static Context context_pur;
    static int temp = 0; //입금 구분자
    int cost = 0;
    String url = "http://3.39.87.103/api/user/point";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        Iamport.INSTANCE.init(this);

        queue = Volley.newRequestQueue(this);
        tv_point = findViewById(R.id.tv_point);
        btnBack = findViewById(R.id.btnBack);
        btn1000 = findViewById(R.id.btn1000);
        btn5000 = findViewById(R.id.btn5000);
        btn10000 = findViewById(R.id.btn10000);
        btn50000 = findViewById(R.id.btn50000);
        btn100000 = findViewById(R.id.btn100000);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String id = response.getString("accountId");
                    String nickName = response.getString("nickname");
                    String point = response.getString("point");

                    tv_point.setText(point);

                    btn1000.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            IamPortRequest iamPortRequest = IamPortRequest.builder()
                                    .pg(PG.kcp.makePgRawName(""))
                                    .pay_method(PayMethod.card.name())
                                    .name("1000 포인트 충전")
                                    .merchant_uid("mid_" + (new Date()).getTime())
                                    .amount("1000")
                                    .buyer_name(nickName).build();

                            Iamport.INSTANCE.payment("iamport", null, null, iamPortRequest,
                                    iamPortApprove -> {
                                        // (Optional) CHAI 최종 결제전 콜백 함수.
                                        return Unit.INSTANCE;
                                    }, iamPortResponse -> {
                                        // 최종 결제결과 콜백 함수.
                                        String responseText = iamPortResponse.toString();
                                        Log.d("IAMPORT_SAMPLE", responseText);
                                        Toast.makeText(getApplication(), "포인트 충전이 완료되었습니다!", Toast.LENGTH_LONG).show();
                                        String[] temp = iamPortResponse.toString().split(",");

                                        if (temp[0].equals("IamPortResponse(imp_success=true")) {
                                            final JSONObject object = new JSONObject();
                                            try {
                                                object.put("earnPoint", 1000);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    Log.d("onResponse", "OK");
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Log.e("Tag", error.toString());
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
                                            request.setShouldCache(false);
                                            queue.add(request);
                                            finish();
                                        }
                                        return Unit.INSTANCE;
                                    });

                        }
                    });

                    btn5000.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            IamPortRequest iamPortRequest = IamPortRequest.builder()
                                    .pg(PG.kcp.makePgRawName(""))
                                    .pay_method(PayMethod.card.name())
                                    .name("5000 포인트 충전")
                                    .merchant_uid("mid_" + (new Date()).getTime())
                                    .amount("5000")
                                    .buyer_name(nickName).build();

                            Iamport.INSTANCE.payment("iamport", null, null, iamPortRequest,
                                    iamPortApprove -> {
                                        // (Optional) CHAI 최종 결제전 콜백 함수.
                                        return Unit.INSTANCE;
                                    }, iamPortResponse -> {
                                        // 최종 결제결과 콜백 함수.
                                        String responseText = iamPortResponse.toString();
                                        Log.d("IAMPORT_SAMPLE", responseText);
                                        Toast.makeText(getApplication(), responseText, Toast.LENGTH_LONG).show();
                                        String[] temp = iamPortResponse.toString().split(",");

                                        if (temp[0].equals("IamPortResponse(imp_success=true")) {
                                            final JSONObject object = new JSONObject();
                                            try {
                                                object.put("earnPoint", 5000);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    Log.d("onResponse", "OK");
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Log.e("Tag", error.toString());
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
                                            request.setShouldCache(false);
                                            queue.add(request);
                                            finish();
                                        }
                                        return Unit.INSTANCE;
                                    });
                        }
                    });

                    btn10000.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            IamPortRequest iamPortRequest = IamPortRequest.builder()
                                    .pg(PG.kcp.makePgRawName(""))
                                    .pay_method(PayMethod.card.name())
                                    .name("10000 포인트 충전")
                                    .merchant_uid("mid_" + (new Date()).getTime())
                                    .amount("10000")
                                    .buyer_name(nickName).build();

                            Iamport.INSTANCE.payment("iamport", null, null, iamPortRequest,
                                    iamPortApprove -> {
                                        // (Optional) CHAI 최종 결제전 콜백 함수.
                                        return Unit.INSTANCE;
                                    }, iamPortResponse -> {
                                        // 최종 결제결과 콜백 함수.
                                        String responseText = iamPortResponse.toString();
                                        Log.d("IAMPORT_SAMPLE", responseText);
                                        Toast.makeText(getApplication(), responseText, Toast.LENGTH_LONG).show();
                                        String[] temp = iamPortResponse.toString().split(",");

                                        if (temp[0].equals("IamPortResponse(imp_success=true")) {
                                            final JSONObject object = new JSONObject();
                                            try {
                                                object.put("earnPoint", 10000);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    Log.d("onResponse", "OK");
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Log.e("Tag", error.toString());
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
                                            request.setShouldCache(false);
                                            queue.add(request);
                                            finish();
                                        }
                                        return Unit.INSTANCE;
                                    });
                        }
                    });

                    btn50000.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            IamPortRequest iamPortRequest = IamPortRequest.builder()
                                    .pg(PG.kcp.makePgRawName(""))
                                    .pay_method(PayMethod.card.name())
                                    .name("50000 포인트 충전")
                                    .merchant_uid("mid_" + (new Date()).getTime())
                                    .amount("50000")
                                    .buyer_name(nickName).build();

                            Iamport.INSTANCE.payment("iamport", null, null, iamPortRequest,
                                    iamPortApprove -> {
                                        // (Optional) CHAI 최종 결제전 콜백 함수.
                                        return Unit.INSTANCE;
                                    }, iamPortResponse -> {
                                        // 최종 결제결과 콜백 함수.
                                        String responseText = iamPortResponse.toString();
                                        Log.d("IAMPORT_SAMPLE", responseText);
                                        Toast.makeText(getApplication(), responseText, Toast.LENGTH_LONG).show();
                                        String[] temp = iamPortResponse.toString().split(",");

                                        if (temp[0].equals("IamPortResponse(imp_success=true")) {
                                            final JSONObject object = new JSONObject();
                                            try {
                                                object.put("earnPoint", 50000);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    Log.d("onResponse", "OK");
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Log.e("Tag", error.toString());
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
                                            request.setShouldCache(false);
                                            queue.add(request);
                                            finish();
                                        }
                                        return Unit.INSTANCE;
                                    });
                        }
                    });

                    btn100000.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            IamPortRequest iamPortRequest = IamPortRequest.builder()
                                    .pg(PG.kcp.makePgRawName(""))
                                    .pay_method(PayMethod.card.name())
                                    .name("100000 포인트 충전")
                                    .merchant_uid("mid_" + (new Date()).getTime())
                                    .amount("100000")
                                    .buyer_name(nickName).build();

                            Iamport.INSTANCE.payment("iamport", null, null, iamPortRequest,
                                    iamPortApprove -> {
                                        // (Optional) CHAI 최종 결제전 콜백 함수.
                                        return Unit.INSTANCE;
                                    }, iamPortResponse -> {
                                        // 최종 결제결과 콜백 함수.
                                        String responseText = iamPortResponse.toString();
                                        Log.d("IAMPORT_SAMPLE", responseText);
                                        Toast.makeText(getApplication(), responseText, Toast.LENGTH_LONG).show();
                                        String[] temp = iamPortResponse.toString().split(",");

                                        if (temp[0].equals("IamPortResponse(imp_success=true")) {
                                            final JSONObject object = new JSONObject();
                                            try {
                                                object.put("earnPoint", 100000);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    Log.d("onResponse", "OK");
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Log.e("Tag", error.toString());
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
                                            request.setShouldCache(false);
                                            queue.add(request);
                                            finish();
                                        }
                                        return Unit.INSTANCE;
                                    });
                        }
                    });
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
                headers.put("Cookie", authenticationData.SessionControl.INSTANCE.getSess());
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };


        request.setShouldCache(false);
        queue.add(request);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Iamport.INSTANCE.close();
    }
}