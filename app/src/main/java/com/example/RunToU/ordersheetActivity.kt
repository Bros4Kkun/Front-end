package com.example.RunToU

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.android.volley.*
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.example.RunToU.SessionControl.SessionControl.sess
import com.example.RunToU.chatroomVolley.chatroomVolley.chatroomVolley
import kotlinx.android.synthetic.main.activity_ordersheet.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.HashMap


class ordersheetActivity : AppCompatActivity() {
    private var queue: RequestQueue? = null
    var title: String? = null


    var cate: String? = null
    var destination: String? = null
    var address: String? = null
    var fragment: Fragment? = null
    var cost = 0
    var lat = 0.0
    var lon = 0.0
//    val mapViewContainer : ViewGroup? = null
//    val mapView : MapView? = null

    var sheetId: String? = null
    public override fun onCreate(savedInstanceState: Bundle?) { //persistentState의 유무로 xml이 나오고 안나오고 갈린다 왜일까?
        super.onCreate(savedInstanceState) //이 액티비티 같은경우엔 내가 없앴음.
        setContentView(R.layout.activity_ordersheet)
        var order_cost: TextView = findViewById(R.id.order_cost)
        var order_title: TextView= findViewById(R.id.order_title)
        var order_time: TextView= findViewById(R.id.order_time)
        var order_cate: ImageView = findViewById(R.id.order_cate)
        var order_num : TextView = findViewById(R.id.order_num)
        var order_content: TextView = findViewById(R.id.order_content)
        var address_order: TextView = findViewById(R.id.address_order)
        var btnChat: ImageButton = findViewById(R.id.btnChat)

        val mapView = MapView(this)
        val mapViewContainer = findViewById<View>(R.id.map_view) as ViewGroup

        val intent = intent
        sheetId = intent.getStringExtra("sheetId")
        address = intent.getStringExtra("address")
        order_num.setText(sheetId)
        val url = "http://3.39.87.103/api/ordersheet/$sheetId"

        Log.d("what", "url : $url")
        val geocoder = Geocoder(application)
        var list: List<Address>? = null
        try {
            list = geocoder.getFromLocationName(address, 10)
        } catch (e: IOException) {
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생")
        }
        if (list != null) {
            if (list.size == 0) {
                Log.d("Tag", "집에가자")
            } else {
                val addr  = list[0]
                lat = addr.latitude
                lon = addr.longitude
                Log.d("Tag", "sample : $lat, $lon")
            }
        }

//        val mapView = MapView(this)
//        val mapViewContainer = findViewById<View>(R.id.map_view) as ViewGroup

        val mapPoint = MapPoint.mapPointWithGeoCoord(lat, lon)
        mapView.setMapCenterPoint(mapPoint, false)

        mapViewContainer.addView(mapView)
        Log.d("Tag", "mapViewContainer : " + mapViewContainer.toString())

        val marker = MapPOIItem()
        marker.itemName = "Perform Marker"
        marker.tag = 0
        marker.mapPoint = mapPoint
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker)

        val request: JsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            Response.Listener { response ->
                Log.d("what", "response : $response")
                try {
                    var jsonObject = JSONObject(response.toString())
                    Log.d("what", "jsonObject : $jsonObject")
                    jsonObject = jsonObject.getJSONObject("orderSheetItem")
                    title = jsonObject.getString("title")
                    var content: String= jsonObject.getString("content")
                    cate = jsonObject.getString("category")
                    destination = jsonObject.getString("destination")
                    cost = jsonObject.getInt("cost")
                    var time: String = jsonObject.getString("wishedDeadLine")
                    var num: Int = jsonObject.getInt("orderSheetId")

                    //String[] str = destination.split(",");
                    btnChat.setOnClickListener(View.OnClickListener {
                        chatroomVolley(
                            application, num
                        ){
                            if(it){
                                chatRoomAdapter.chatindex = chatRoomAdapter.chRo
                                val intent = Intent(this,ChatRoomActivity2::class.java)
                                Stompclass.Stomclass.subscribe("/topic/chatroom/",chatRoomAdapter.chRo)
                                startActivity(intent)
                            }

                        }
                    })
                    val d_temp = time.substring(0, 10)
                    val d_arr = d_temp.split("-").toTypedArray()
                    val t_temp = time.substring(11, 18)
                    val t_arr = t_temp.split(":").toTypedArray()
                    val w_temp =
                        d_arr[0] + "년 " + d_arr[1] + "월 " + d_arr[2] + "일 " + t_arr[0] + "시 " + t_arr[1] + "분"
                    order_cost.setText(cost.toString() + "원")
                    order_num.setText("No.$num")
                    order_title.setText(title)
                    order_time.setText(w_temp)
                    order_content.setText(content)
                    address_order.setText(destination)
                    if (cate == "DELIVERY_AND_SHOPPING") {
                        order_cate.setImageResource(R.drawable.cate_deli)
                    } else if (cate == "CLEANING_AND_HOUSEWORK") {
                        order_cate.setImageResource(R.drawable.cate_clean)
                    } else if (cate == "DELIVERY_AND_INSTALLATION") {
                        order_cate.setImageResource(R.drawable.cate_help)
                    } else if (cate == "ACCOMPANY") {
                        order_cate.setImageResource(R.drawable.cate_help)
                    } else if (cate == "ANTI_BUG") {
                        order_cate.setImageResource(R.drawable.cate_bug)
                    } else if (cate == "ROLE_ACTING") {
                        order_cate.setImageResource(R.drawable.cate_help)
                    } else {
                        order_cate.setImageResource(R.drawable.cate_all)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
                Response.ErrorListener { error -> Log.e("error", "onErrorResponse : " + error.toString()) }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> = HashMap()
                headers["Cookie"] = sess!!
                headers["Content-Type"] = "application/json"
                return headers
            }
        }

        //requestQueue가 Deprecated됨을 해결하기 위한 코드

        // Instantiate the cache
        val cache: Cache = DiskBasedCache(cacheDir, 1024 * 1024)
        // Set up the network to use HttpURLConnection as the HTTP client.
        val network: Network = BasicNetwork(HurlStack())
        // Instantiate the RequestQueue with the cache and network.
        queue = RequestQueue(cache, network)
        // Start the queue
        queue!!.start()
        request.setShouldCache(false)
        queue!!.add(request)
        Log.d("Tag", "mapViewContainer : " + mapViewContainer.toString())

    }

    override fun onResume() {
        Log.d("Tag", "onResume - OrderSheetActivity")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Tag", "onPause - OrderSheetActivity")
        super.onPause()
        val mapViewContainer = findViewById<View>(R.id.map_view) as ViewGroup
        mapViewContainer.removeAllViews()

    }

    override fun onStop() {
        super.onStop()
        Log.d("Tag", "onStop - OrderSheetActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Tag", "onDestroy - OrderSheetActivity")
    }
}