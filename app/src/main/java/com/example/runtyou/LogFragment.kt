package com.example.runtyou


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import net.daum.mf.map.api.MapView
import com.example.runtyou.databinding.FragmentLogBinding
import kotlinx.android.synthetic.main.fragment_log.*


class LogFragment : Fragment(){
    private lateinit var binding: FragmentLogBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mapView = MapView(activity)
        map_view.addView(mapView)


    }
}