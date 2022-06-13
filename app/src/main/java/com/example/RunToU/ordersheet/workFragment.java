package com.example.RunToU.ordersheet;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.RunToU.R;
import com.example.RunToU.login.authenticationData;
import com.iamport.sdk.domain.core.Iamport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class workFragment extends Fragment {

    private RequestQueue queue;
    private ArrayList<workData> arrayList;
    private workAdapter workAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    JSONArray jsonArray = new JSONArray();
    com.example.RunToU.ordersheet.jsonData jsonData;

    int state = 1;
    String url = "http://3.39.87.103/api/ordersheet/list/ALL/" + state;
    String pageCate = "ALL";

    //받아와서 뿌리는 사용자 정보
    String title;
    String content;
    String cate;
    String destination;
    String sheetId;
    int cost = 0;

    ImageButton btn_add, btnAll, btnHelp, btnDeli, btnClean, btnBug, btnRight, btnLeft;
    TextView txtState;

    //기본 이미지 변수
    int image = R.drawable.cate_all;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Tag", "onCreate - WorkFragment");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Tag", "onCreateView - WorkFragment");

        View view = (ViewGroup) inflater.inflate(R.layout.fragment_work, container, false);
        getActivity().setTitle("심부름");
        btn_add = view.findViewById(R.id.btn_add);
        btnAll = view.findViewById(R.id.btnAll);
        btnHelp = view.findViewById(R.id.btnHelp);
        btnDeli = view.findViewById(R.id.btnDeli);
        btnClean = view.findViewById(R.id.btnClean);
        btnBug = view.findViewById(R.id.btnBug);
        btnRight = view.findViewById(R.id.btnRight);
        btnLeft = view.findViewById(R.id.btnLeft);
        txtState = view.findViewById(R.id.state);

        cateView(url);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == 1){
                    Toast.makeText(getContext(), "최신 페이지 입니다!", Toast.LENGTH_SHORT).show();
                }else if(state != 1) {
                    state -= 1;
                    String url = "http://3.39.87.103/api/ordersheet/list/"+ pageCate + "/" +state;
                    cateView(url);
                }
                txtState.setText(String.valueOf(state));

            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag", "jsonArray : "+jsonArray.toString());
                if(jsonArray.length() < 10){
                    Toast.makeText(getContext(), "마지막 페이지 입니다!", Toast.LENGTH_SHORT).show();
                }else if(state != 0){
                    state += 1;
                    String url = "http://3.39.87.103/api/ordersheet/list/"+ pageCate + "/" + state;
                    cateView(url);
                }
                txtState.setText(String.valueOf(state));
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 1;
                pageCate = "ALL";
                String url = "http://3.39.87.103/api/ordersheet/list/"+ pageCate + "/" +state;
                cateView(url);
                Toast.makeText(getContext(), "전체 심부름입니다!", Toast.LENGTH_SHORT).show();
                txtState.setText(String.valueOf(state));
            }
        });

        btnDeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 1;
                pageCate = "DELIVERY_AND_SHOPPING";
                String url = "http://3.39.87.103/api/ordersheet/list/"+ pageCate + "/" +state;
                cateView(url);
                Toast.makeText(getContext(), "배달 심부름입니다!", Toast.LENGTH_SHORT).show();
                txtState.setText(String.valueOf(state));

            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 1;
                pageCate = "ACCOMPANY";
                String url = "http://3.39.87.103/api/ordersheet/list/"+ pageCate + "/" +state;
                cateView(url);
                Toast.makeText(getContext(), "돌봄 심부름입니다!", Toast.LENGTH_SHORT).show();
                txtState.setText(String.valueOf(state));

            }
        });

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 1;
                pageCate = "CLEANING_AND_HOUSEWORK";
                String url = "http://3.39.87.103/api/ordersheet/list/"+ pageCate + "/" +state;
                cateView(url);
                Toast.makeText(getContext(), "청소 심부름입니다!", Toast.LENGTH_SHORT).show();
                txtState.setText(String.valueOf(state));

            }
        });

        btnBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 1;
                pageCate = "ANTI_BUG";
                String url = "http://3.39.87.103/api/ordersheet/list/"+ pageCate + "/" +state;
                cateView(url);
                Toast.makeText(getContext(), "벌레/쥐 심부름입니다!", Toast.LENGTH_SHORT).show();
                txtState.setText(String.valueOf(state));

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), writeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void cateView(String url){
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
                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    jsonArray = jsonObject.getJSONArray("simpOrderSheetInfoList");
                    Log.d("tag", "jsonArray : " + jsonArray.toString());

                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jTemp  = jsonArray.getJSONObject(i);
                        title = jTemp.getString("title");
                        content = jTemp.getString("contentSample");
                        cate = jTemp.getString("category");
                        destination = jTemp.getString("destination");
                        cost = jTemp.getInt("cost");
                        sheetId = jTemp.getString("orderSheetId");

                        String[] str = destination.split("\\(");

                        if(cate.equals("DELIVERY_AND_SHOPPING")){
                            image = R.drawable.cate_deli;
                        }else if(cate.equals("CLEANING_AND_HOUSEWORK")){
                            image = R.drawable.cate_clean;
                        }else if(cate.equals("DELIVERY_AND_INSTALLATION")){
                            image = R.drawable.cate_help;
                        }else if(cate.equals("ACCOMPANY")){
                            image = R.drawable.cate_help;
                        }else if(cate.equals("ANTI_BUG")){
                            image = R.drawable.cate_bug;
                        }else if(cate.equals("ROLE_ACTING")){
                            image = R.drawable.cate_help;
                        }else{
                            image = R.drawable.cate_all;
                        }

                        workData workData = new workData(image, String.valueOf(cost)+"원", str[0], title, sheetId);
                        arrayList.add(workData);
                        workAdapter.notifyDataSetChanged();

                        workAdapter.setOnItemClickListener(new workAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                sheetId  = arrayList.get(position).getTxtNum();
                                destination = arrayList.get(position).getTv_Far();
                                String[] str = destination.split(",");

                                Intent intent = new Intent(getContext(), ordersheetActivity.class);
                                intent.putExtra("sheetId",sheetId);
                                intent.putExtra("address", str[0]);
                                startActivity(intent);
                                Log.d("Tag", "for check");
                            }
                        });

                        Log.d("Tag", workAdapter.toString());

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
                headers.put("Cookie", authenticationData.SessionControl.INSTANCE.getSess());
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
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Tag", "onResume - WorkFragment");

    }

    @Override
    public void onPause() {
        Log.d("Tag", "onPause - WorkFragment");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.d("Tag", "onDestroyView - WorkFragment");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("Tag", "onDestroy - WorkFragment");
        super.onDestroy();
    }
}
