package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class my_profileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_profile, container, false);

        Button btnIntroduce, btnRevRec, btnJobRec;
        ImageButton btnDetInfo, btnJob, btnMyList, btnSetting;

        btnIntroduce = rootView.findViewById(R.id.btnIntroduce);
        btnRevRec = rootView.findViewById(R.id.btnRevRec);
        btnJobRec = rootView.findViewById(R.id.btnJobRec);
        btnDetInfo = rootView.findViewById(R.id.btnDetInfo);
        btnJob = rootView.findViewById(R.id.btnJob);
        btnMyList = rootView.findViewById(R.id.btnMyList);
        btnSetting = rootView.findViewById(R.id.btnSetting);

        btnIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment= new Fragment(R.layout.fragment_introduce); // 객체 생성
                getFragmentManager().beginTransaction().add(R.id.infoFrag, fragment).commit();

                btnIntroduce.setBackgroundColor(Color.parseColor("#6EB943"));
                btnJobRec.setBackgroundColor(Color.WHITE);
                btnRevRec.setBackgroundColor(Color.WHITE);
                btnIntroduce.setTextColor(Color.WHITE);
                btnJobRec.setTextColor(Color.BLACK);
                btnRevRec.setTextColor(Color.BLACK);
            }
        });
        btnJobRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment= new Fragment(R.layout.fragment_jobrec); // 객체 생성
                getFragmentManager().beginTransaction().add(R.id.infoFrag, fragment).commit();

                btnIntroduce.setBackgroundColor(Color.WHITE);
                btnJobRec.setBackgroundColor(Color.parseColor("#6EB943"));
                btnRevRec.setBackgroundColor(Color.WHITE);
                btnIntroduce.setTextColor(Color.BLACK);
                btnJobRec.setTextColor(Color.WHITE);
                btnRevRec.setTextColor(Color.BLACK);
            }
        });
        btnRevRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new Fragment(R.layout.fragment_revrec); // 객체 생성
                getFragmentManager().beginTransaction().add(R.id.infoFrag, fragment).commit();
                btnIntroduce.setBackgroundColor(Color.WHITE);
                btnJobRec.setBackgroundColor(Color.WHITE);
                btnRevRec.setBackgroundColor(Color.parseColor("#6EB943"));
                btnIntroduce.setTextColor(Color.BLACK);
                btnJobRec.setTextColor(Color.BLACK);
                btnRevRec.setTextColor(Color.WHITE);
            }
        });

//        btnSetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment fragment= new Fragment(R.layout.fragment_setting); // 객체 생성
//                getFragmentManager().beginTransaction().add(, fragment).commit();
//            }
//        });

        return rootView;
    }
}