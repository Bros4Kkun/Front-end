package com.example.RunToU.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.RunToU.R;
import com.example.RunToU.purchase.purchaseActivity;

public class settingActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button btnPoint = findViewById(R.id.btnPoint);
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), purchaseActivity.class);
                startActivity(intent);
            }
        });
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("설정");
//        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
