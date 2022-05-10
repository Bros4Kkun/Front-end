package com.example.RunToU;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import net.daum.mf.map.api.MapView;

@RequiresApi(api = Build.VERSION_CODES.O)
public class writeActivity extends AppCompatActivity {
    //왜인진 모르겠으나 fragment로 했을 때는 오류가나고 안넘어감 -> (activity로 만들었음)

    private Spinner cateSpinner;
    private ArrayAdapter cateAdapter;

    private RequestQueue queue;

    String url = "http://3.39.87.103/api/ordersheet";

    private DatePickerDialog.OnDateSetListener d_callbackMethod;
    private TimePickerDialog.OnTimeSetListener t_callbackMethod;

    String date;
    String time;

    Button btnDate, btnTime;
    TextView address_write;
    EditText title_write, price_write, detail_write, context_write;     //액티비티가 종료되면서 데이터를 넘겨주나? or 서버에 올라가나?
    ImageButton btnGotopur;
    LocalDateTime nowTime = LocalDateTime.now();
    LocalDateTime deadLine;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        setTitle("요청서 작성");
        setViews();
        InitializeListener();

        queue = Volley.newRequestQueue(this);

        address_write = findViewById(R.id.address_write);
        title_write = findViewById(R.id.title_write);
        price_write = findViewById(R.id.price_write);
        detail_write = findViewById(R.id.address_write);
        context_write = findViewById(R.id.context_write);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnGotopur = findViewById(R.id.btnGotopur);

        btnGotopur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    int price = Integer.parseInt(price_write.getText().toString());
                    //
                    if (title_write.getText().toString().length() <= 4) {
                        Toast.makeText(getApplication(), "제목을 5자 이상 작성해주세요", Toast.LENGTH_SHORT).show();
                    } else if (cateSpinner.getSelectedItem().toString().equals("--선택--")) {
                        Toast.makeText(getApplication(), "카테고리를 선택해주세요.", Toast.LENGTH_SHORT).show();
                    } else if (price <= 0 || price > 100000) {
                        Toast.makeText(getApplication(), "금액을 정확히 작성해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(view.getContext(), purchaseActivity.class);
                        intent.putExtra("cost", price);

                        deadLine = LocalDateTime.parse(getDate()+" "+getTime()+":10",formatter);

                        final JSONObject object = new JSONObject();
                        object.put("title", title_write.getText().toString());
                        object.put("content", context_write.getText().toString());
                        object.put("category", cateSpinner.getSelectedItem().toString());
                        object.put("destination", detail_write.getText().toString());
                        object.put("cost", Integer.parseInt(price_write.getText().toString()));
                        object.put("wishedDeadline", deadLine);

                        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("onResponse","OK" );
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("onError","errrrrrrr");
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

                        request.setShouldCache(false);
                        queue.add(request);
                        startActivity(intent);
                        finish();

                    }
                } catch (NumberFormatException | JSONException e) {
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

    public void InitializeListener()
    {
        d_callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                if(monthOfYear < 10){
                    btnDate.setText(year + "년" + "0"+(monthOfYear+1) + "월" + dayOfMonth + "일");
                    setDate(year + "-" + "0"+ (monthOfYear+1) + "-" + dayOfMonth);
                }else{
                    btnDate.setText(year + "년" + (monthOfYear+1) + "월" + dayOfMonth + "일");
                    setDate(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
                }
            }
        };

        t_callbackMethod = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                if(minute < 10){
                    btnTime.setText(hourOfDay + "시" + "0"+ minute + "분");
                    setTime(hourOfDay + ":" + minute);
                }else{
                    btnTime.setText(hourOfDay + "시" + minute + "분");
                    setTime(hourOfDay + ":" + minute);
                }
            }
        };
    }

    public void d_OnClickHandler(View view)
    {
        String temp = nowTime.toString().substring(0,10);
        String arr[] = temp.split("-");

        DatePickerDialog d_dialog = new DatePickerDialog(this, d_callbackMethod, Integer.parseInt(arr[0]),
                Integer.parseInt(arr[1])-1, Integer.parseInt(arr[2]));//왜 월은 0부터 셀까?
        d_dialog.show();
    }

    public void t_OnClickHandler(View view)
    {
        String temp = nowTime.toString().substring(11,18);
        String arr[] = temp.split(":");

        TimePickerDialog t_dialog = new TimePickerDialog(this, t_callbackMethod, Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), true);
        t_dialog.show();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}