package com.example.RunToU.profile;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.RunToU.R;

import java.util.ArrayList;

public class jobrecActivity extends AppCompatActivity {

    private ArrayList<jobData> arrayList;
    private com.example.RunToU.profile.jobAdapter jobAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView = findViewById(R.id.rvJob);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        jobAdapter = new jobAdapter(arrayList);
        recyclerView.setAdapter(jobAdapter);

        EditText editJob;
        ImageButton btn_addJob, btn_writeJob;

        editJob = findViewById(R.id.editJob);
        btn_addJob = findViewById(R.id.btn_addJob);
        btn_writeJob = findViewById(R.id.btn_writeJob);

        btn_writeJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobData jobData = new jobData(editJob.toString());  //받아와야함
                arrayList.add(jobData);
                jobAdapter.notifyDataSetChanged();
                editJob.setText("");
            }
        });

        btn_addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobrec);

    }
}