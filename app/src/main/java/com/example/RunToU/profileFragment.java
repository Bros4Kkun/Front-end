package com.example.RunToU;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class profileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);

        Button btnIntroduce, btnRevRec, btnJobRec;
        ImageButton btnReport, btnReview, btnMessage, btnNotice;

        btnIntroduce = rootView.findViewById(R.id.btnIntroduce);
        btnRevRec = rootView.findViewById(R.id.btnRevRec);
        btnJobRec = rootView.findViewById(R.id.btnJobRec);
        btnReport = rootView.findViewById(R.id.btnReport);
        btnReview = rootView.findViewById(R.id.btnReview);
        btnMessage = rootView.findViewById(R.id.btnMessage);
        btnNotice = rootView.findViewById(R.id.btnNotice);

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Activity.class);
//                startActivity(intent);
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Activity.class);
//                startActivity(intent);
            }
        });
//        종민님 화면


        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), reviewActivity.class);
                startActivity(intent);
            }
        });

        btnNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new Fragment(R.layout.fragment_notice); // 객체 생성
                getFragmentManager().beginTransaction().add(R.id.profileLayout, fragment).commit();
            }
        });

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

                Fragment fragment= new Fragment(R.layout.fragment_short_jobrec); // 객체 생성
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
        return rootView;
    }
}