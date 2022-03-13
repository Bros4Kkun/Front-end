package com.example.myapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class reviewActivity extends AppCompatActivity {

    private ArrayList<reviewData> arrayList;
    private reviewAdapter reviewAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    ImageButton btnBack;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        setTitle("리뷰");

        recyclerView = findViewById(R.id.rvReview);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        reviewAdapter = new reviewAdapter(arrayList);
        recyclerView.setAdapter(reviewAdapter);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewData reviewData = new reviewData(R.mipmap.ic_launcher, "test1", "test2", "★★★★★", "test4");
                arrayList.add(reviewData);
                reviewAdapter.notifyDataSetChanged();
            }
        });
    }

}
