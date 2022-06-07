package com.example.RunToU;

import static net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.RunToU.databinding.ActivityMainBinding;
import com.example.RunToU.databinding.FragmentLogBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LogFragment extends Fragment{

    private FragmentLogBinding binding;
    private RequestQueue queue;
    int cate = 0;
    TextView content, pName, exDay;
    String url = "http://3.39.87.103/api/match/now";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Tag", "onCreate - LogFragment");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("Tag", "onCreateView - LogFragment");

        binding = FragmentLogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("Tag", "onResume - LogFragment");

            ViewGroup mapViewContainer = getView().findViewById(R.id.map_view);
            MapView mapView = new MapView(getContext());
            content = binding.content;
            pName = binding.pNameLog;
            exDay = binding.exDayLog;

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    Log.d("Tag", jsonObject.toString());
                    int id = jsonObject.getInt("id");
                    JSONObject s_temp = jsonObject.getJSONObject("orderSheetInfo"); //orderSheetId, destination
                    JSONObject o_temp = jsonObject.getJSONObject("ordererInfo");
                    JSONObject p_temp = jsonObject.getJSONObject("performerInfo");
                    String date = jsonObject.getString("completedDateTime");

//                    Boolean done = jsonObject.getBoolean("completed");
//                    Boolean doneReq = jsonObject.getBoolean("completionRequest");

                    String contentSample = s_temp.getString("contentSample");
                    String title = s_temp.getString("title");
                    String[] destination = s_temp.getString("destination").split(",");
                    int cost = s_temp.getInt("cost");

                    String orderer = o_temp.getString("nickname");
                    String performer = p_temp.getString("nickname");

                    Log.d("Tag", "Success");

                    if(date.equals(null)){
                        date = "1997-02-15";
                    }

                    content.setText(contentSample);
                    pName.setText("수행자 : " + performer);
                    exDay.setText("기한 : " + date);


                    //지도 표시
                    final Geocoder geocoder = new Geocoder(getContext());
                    List<Address> list = null;
                    try{
                        list = geocoder.getFromLocationName(destination[0], 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(list != null){
                        if(list.size() == 0){

                        }else{
                            Address addr = list.get(0);
                            double lat = addr.getLatitude();
                            double lon = addr.getLongitude();

                            Log.d("Tag", "Success : " + String.valueOf(lat) +" "+ String.valueOf(lon));

                            MapPOIItem marker = new MapPOIItem();
                            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(lat, lon);
                            mapView.setMapCenterPoint(mapPoint, false);
                            Log.d("Tag", "Success : ?");
                            mapViewContainer.addView(mapView);
                            Log.d("Tag", "mapViewContainer : " + mapViewContainer.toString());
                            marker.setItemName(destination[1]);   //마커를 클릭했을 때, 표시되는 텍스트
                            marker.setTag(0);   //모름
                            marker.setMapPoint(mapPoint);
                            marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
                            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                            mapView.addPOIItem(marker);
                            Log.d("Tag", "Success : " + String.valueOf(lat) +" "+ String.valueOf(lon));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Error!!!");
            }
        }){
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
    public void onPause() {
        Log.d("Tag", "onPause - LogFragment");

        ViewGroup mapViewContainer = getView().findViewById(R.id.map_view);
        mapViewContainer.removeAllViews();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.d("Tag", "onDestroyView - LogFragment");
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDestroy() {
        Log.d("Tag", "onDestroy - LogFragment");
        super.onDestroy();
    }

    public void checkDone(){
        String url = "http://3.39.87.103/api/match/now";

    }

    public void checkDoing(){
        String url = "http://3.39.87.103/api/match/now";

    }
}
