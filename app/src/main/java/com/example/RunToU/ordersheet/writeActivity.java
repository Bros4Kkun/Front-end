package com.example.RunToU.ordersheet;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import com.android.volley.toolbox.Volley;
import com.example.RunToU.R;
import com.example.RunToU.login.authenticationData;
import com.example.RunToU.main.MainActivity;
import com.example.RunToU.purchase.purchaseActivity;
import com.iamport.sdk.domain.core.Iamport;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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
    String cate = null;
    int point = 1500;   //이후 수정해야함

    Button btnDate, btnTime, price_rec;
    TextView address_write;
    EditText title_write, price_write, doro_write, context_write;     //액티비티가 종료되면서 데이터를 넘겨주나? or 서버에 올라가나?
    ImageButton btnGotopur;
    LocalDateTime nowTime = LocalDateTime.now();
    LocalDateTime deadLine;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        setTitle("요청서 작성");
        setViews();
        InitializeListener();
        Iamport.INSTANCE.init(this);

        int status = NetworkStatus.getConnectivityStatus(getApplicationContext());

        queue = Volley.newRequestQueue(this);

        title_write = findViewById(R.id.title_write);
        price_write = findViewById(R.id.price_write);
        address_write = findViewById(R.id.address_write);
        doro_write = findViewById(R.id.doro_write);

        context_write = findViewById(R.id.context_write);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnGotopur = findViewById(R.id.btnGotopur);
        price_rec = findViewById(R.id.price_rec);
        price_rec.setOnClickListener(new View.OnClickListener() { //가격버튼
            @Override
            public void onClick(View v) {
                if (cateSpinner.getSelectedItem().toString().equals("배달 및 장보기")) {
                    cate = "delivery-shopping";
                } else if (cateSpinner.getSelectedItem().toString().equals("청소 및 집안일")) {
                    cate = "cleaning-housework";
                } else if (cateSpinner.getSelectedItem().toString().equals("설치 조립 운반")) {
                    cate = "delivery-installation";
                } else if (cateSpinner.getSelectedItem().toString().equals("동행 및 돌봄")) {
                    cate = "accompany-role-acting";
                } else if (cateSpinner.getSelectedItem().toString().equals("역할 대행")) {
                    cate = "accompany-role-acting";
                }
                Intent intent = new Intent(getApplication(), popupActivity.class);
                if (cate != null) {
                    intent.putExtra("cate", cate);
                    intent.putExtra("cost", price_write.getText().toString());
                    Log.d("Tag", "Cost : " + price_write.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "카테고리를 선택해 주세요.", Toast.LENGTH_SHORT).show();
                }/*
                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, cate, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

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
*/
            }
        });

        String pointCheck = "http://3.39.87.103/api/user/point";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pointCheck, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    point = response.getInt("point");
                    Log.d("Tag", "point : " + point);


                    btnGotopur.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                int price = Integer.parseInt(price_write.getText().toString());

                                if (point < price || point == 0) {
                                    Toast.makeText(getApplication(), "포인트 충전이 필요합니다!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplication(), purchaseActivity.class);
                                    startActivity(intent);
                                } else if (point >= price) {
                                    Log.d("Tag", "point : " + point);

                                    String date = getDate();
                                    String time = getTime();
                                    Log.d("time", "Time :" + time);
                                    deadLine = LocalDateTime.parse(date + " " + time + ":10", formatter);

                                    if (title_write.getText().toString().length() <= 4) {
                                        Toast.makeText(getApplication(), "제목을 5자 이상 작성해주세요", Toast.LENGTH_SHORT).show();
                                    } else if (cateSpinner.getSelectedItem().toString().equals("--선택--")) {
                                        Toast.makeText(getApplication(), "카테고리를 선택해주세요.", Toast.LENGTH_SHORT).show();
                                    } else if (price <= 0 || price > 100001) {
                                        Toast.makeText(getApplication(), "금액을 정확히 작성해주세요.", Toast.LENGTH_SHORT).show();
                                    } else if (deadLine.isBefore(nowTime)) {
                                        Toast.makeText(getApplication(), "시간을 정확히 작성해주세요.", Toast.LENGTH_SHORT).show();
                                    } else {

                                        Intent intent = new Intent(view.getContext(), MainActivity.class);

                                        if (cateSpinner.getSelectedItem().toString().equals("배달 및 장보기")) {
                                            cate = "DELIVERY_AND_SHOPPING";
                                        } else if (cateSpinner.getSelectedItem().toString().equals("청소 및 집안일")) {
                                            cate = "CLEANING_AND_HOUSEWORK";
                                        } else if (cateSpinner.getSelectedItem().toString().equals("설치 조립 운반")) {
                                            cate = "DELIVERY_AND_INSTALLATION";
                                        } else if (cateSpinner.getSelectedItem().toString().equals("동행 및 돌봄")) {
                                            cate = "ACCOMPANY";
                                        } else if (cateSpinner.getSelectedItem().toString().equals("벌레 퇴치")) {
                                            cate = "ANTI_BUG";
                                        } else if (cateSpinner.getSelectedItem().toString().equals("역할 대행")) {
                                            cate = "ROLE_ACTING";
                                        } else if (cateSpinner.getSelectedItem().toString().equals("기타")) {
                                            cate = "ETC";
                                        }

                                        final JSONObject object = new JSONObject();
                                        object.put("title", title_write.getText().toString());
                                        object.put("content", context_write.getText().toString());
                                        object.put("category", cate);
                                        object.put("destination", doro_write.getText().toString() + "," + address_write.getText().toString());
                                        object.put("cost", Integer.parseInt(price_write.getText().toString()));
                                        object.put("wishedDeadline", deadLine);

                                        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                Log.d("onResponse", "OK");

                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Log.d("onError", "errrrrrrr");
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
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplication(), "심부름 등록 완료!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            } catch (NumberFormatException | JSONException e) {
                                Toast.makeText(getApplication(), "요청서를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Error : " + error.toString());
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

        Cache cache = new DiskBasedCache(getApplication().getCacheDir(), 1024 * 1024);
        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        // Instantiate the RequestQueue with the cache and network.
        queue = new RequestQueue(cache, network);
        // Start the queue
        queue.start();

        request.setShouldCache(false);
        queue.add(request);

        // 터치 안되게 막기 <- 이게ㅐ 뭔소리지?
        doro_write.setFocusable(false);
        doro_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("주소설정페이지", "주소입력창 클릭");
                Intent i = new Intent(getApplicationContext(), addressActivity.class);
                // 화면전환 애니메이션 없애기
                overridePendingTransition(0, 0);
                // 주소결과
                activityResultLauncher.launch(i);
//                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
            }
        });


        if (status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI) {
            activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent intent = result.getData();
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        Log.i("test", "data:" + data);
                        doro_write.setText(data);
                    }
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setViews() {
        cateSpinner = findViewById(R.id.cate_write);
        cateAdapter = ArrayAdapter.createFromResource(this, R.array.cate, R.layout.support_simple_spinner_dropdown_item);
        cateSpinner.setAdapter(cateAdapter);
    }

    public void InitializeListener() {
        d_callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (monthOfYear < 10) {
                    btnDate.setText(year + "년" + "0" + (monthOfYear + 1) + "월" + dayOfMonth + "일");
                    setDate(year + "-" + "0" + (monthOfYear + 1) + "-" + dayOfMonth);
                } else {
                    btnDate.setText(year + "년" + (monthOfYear + 1) + "월" + dayOfMonth + "일");
                    setDate(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                }
            }
        };

        t_callbackMethod = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.d("Tag", String.valueOf(hourOfDay));
                if (hourOfDay < 10 && minute < 10) {
                    btnTime.setText("0" + hourOfDay + "시" + "0" + minute + "분");
                    setTime("0" + hourOfDay + ":" + "0" + minute);
                } else if (hourOfDay < 10) {
                    btnTime.setText("0" + hourOfDay + "시" + minute + "분");
                    setTime("0" + hourOfDay + ":" + minute);
                } else if (minute < 10) {
                    btnTime.setText(hourOfDay + "시" + "0" + minute + "분");
                    setTime(hourOfDay + ":" + "0" + minute);
                } else {
                    btnTime.setText(hourOfDay + "시" + minute + "분");
                    setTime(hourOfDay + ":" + minute);
                }
            }
        };
    }

    public void d_OnClickHandler(View view) {
        String temp = nowTime.toString().substring(0, 10);
        String arr[] = temp.split("-");

        DatePickerDialog d_dialog = new DatePickerDialog(this, d_callbackMethod, Integer.parseInt(arr[0]),
                Integer.parseInt(arr[1]) - 1, Integer.parseInt(arr[2]));//왜 월은 0부터 셀까?
        d_dialog.show();
    }

    public void t_OnClickHandler(View view) {
        String temp = nowTime.toString().substring(11, 18);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Iamport.INSTANCE.close();
    }

}