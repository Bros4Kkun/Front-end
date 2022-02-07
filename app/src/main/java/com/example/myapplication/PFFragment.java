package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PFFragment extends AppCompatActivity {

    Button btnJobRec, btnRevRec, btnIntroduce;
    ImageButton btnReport, btnReview, btnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("프로필");

        Fragment fragment_profile= new Fragment(); // 객체 생성
        getSupportFragmentManager().beginTransaction().add(R.id.infoFrag, fragment_profile).commit();

        btnIntroduce = findViewById(R.id.btnIntroduce);
        btnRevRec = findViewById(R.id.btnRevRec);
        btnJobRec = findViewById(R.id.btnJobRec);
        btnReport = findViewById(R.id.btnReport);
        btnReview = findViewById(R.id.btnReview);
        btnMessage = findViewById(R.id.btnMessage);

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),reviewActivity.class);
                startActivity(intent);
            }
        });

        btnIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment_profile= new Fragment(R.layout.fragment_profile); // 객체 생성
                getSupportFragmentManager().beginTransaction().add(R.id.infoFrag, fragment_profile).commit();

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

                Fragment fragment_profile= new Fragment(R.layout.fragment_jobrec); // 객체 생성
                getSupportFragmentManager().beginTransaction().add(R.id.infoFrag, fragment_profile).commit();

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
                Fragment fragment_profile= new Fragment(R.layout.fragment_review); // 객체 생성
                getSupportFragmentManager().beginTransaction().add(R.id.infoFrag, fragment_profile).commit();
                btnIntroduce.setBackgroundColor(Color.WHITE);
                btnJobRec.setBackgroundColor(Color.WHITE);
                btnRevRec.setBackgroundColor(Color.parseColor("#6EB943"));
                btnIntroduce.setTextColor(Color.BLACK);
                btnJobRec.setTextColor(Color.BLACK);
                btnRevRec.setTextColor(Color.WHITE);
            }
        });
    }
}