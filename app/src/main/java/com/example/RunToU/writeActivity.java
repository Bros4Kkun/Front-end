package com.example.RunToU;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class writeActivity extends AppCompatActivity {
    //왜인진 모르겠으나 fragment로 했을 때는 오류가나고 안넘어감 -> (activity로 만들었음)

    public static WebView webview;
    public String url = "http://192.168.0.44";


    private Spinner cateSpinner;
    private ArrayAdapter cateAdapter;
    private RequestQueue queue;
    // String url = "http://3.39.87.103/api/ordersheet";
    //private static final String TAG = "MAIN";

    private WebView webView;
    private TextView txt_address;
    private Handler handler;

    //TextView txtTest;
    EditText title_write, price_write, detail_write, context_write;     //액티비티가 종료되면서 데이터를 넘겨주나? or 서버에 올라가나?
    ImageButton btnGotopur;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        setTitle("요청서 작성");
        setViews();

        queue = Volley.newRequestQueue(this);

        //txtTest = findViewById(R.id.txtTest);

        title_write = findViewById(R.id.title_write);
        price_write = findViewById(R.id.price_write);
        detail_write = findViewById(R.id.detail_write);
        context_write = findViewById(R.id.context_write);

//        int tempPrice;
//        try{
//            tempPrice = Integer.parseInt(price_write.getText().toString());
//        }catch (NumberFormatException e){
//            tempPrice = 0;
//        }
//        int price = tempPrice;

        btnGotopur = findViewById(R.id.btnGotopur);
        btnGotopur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    int price = Integer.parseInt(price_write.getText().toString());
                    //
                    if(title_write.getText().toString().length()<= 5){
                        Toast.makeText(getApplication(), "제목을 5자 이상 작성해주세요", Toast.LENGTH_SHORT).show();
                    }else if(cateSpinner.getSelectedItem().toString().equals("--선택--")){
                        Toast.makeText(getApplication(), "카테고리를 선택해주세요.", Toast.LENGTH_SHORT).show();
                    }else if(price <= 0 || price > 100000){
                        Toast.makeText(getApplication(), "금액을 정확히 작성해주세요.", Toast.LENGTH_SHORT).show();
                    }else{
                        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){protected Map<String, String> getParams() throws AuthFailureError{
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("id", "none");
                            params.put("ordererId", "none");
                            params.put("matchingId", "none");
                            params.put("title", title_write.getText().toString());
                            params.put("content", context_write.getText().toString());
                            params.put("category", cateSpinner.getSelectedItem().toString());
                            params.put("destnation", detail_write.getText().toString());
                            params.put("cost", price_write.getText().toString());
                            params.put("isPayed", "none");
                            params.put("wishedDeadLine", "none");
                            params.put("삭제하기", "none");
                            return params;
                        }
                        };
                        queue.add(jsonRequest);
//                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                //txtTest.setText("Response is : " + response.toString());
//                            }
//                        }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //txtTest.setText("That didn't work!");
//                            }
//                        });
//                        MySingleton.getInstance(this).addToRequestQueue(JsonObjectRequest);
//                        queue.add(stringRequest);


                        Intent intent = new Intent(view.getContext(), purchaseActivity.class);
                        startActivity(intent);
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplication(), "요청서를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setViews(){
        cateSpinner = findViewById(R.id.cate_write);
        cateAdapter = ArrayAdapter.createFromResource(this, R.array.cate, R.layout.support_simple_spinner_dropdown_item);
        cateSpinner.setAdapter(cateAdapter);
    }

}
//onStop을 사용할때 Tag로 불러와 사용하는듯 하다.