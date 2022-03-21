package com.example.RunToU;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class purchaseActivity extends AppCompatActivity {

    ImageButton btnBack, btnPay;
    int temp = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        btnBack = findViewById(R.id.btnBack);
        btnPay = findViewById(R.id.btnPay);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp == 0){
                    Toast.makeText(getApplication(), "아직 입금이 확인되지 않았습니다!!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(), "입금 완료!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //입금 시간 흐르는 것 확인해야하나?
    }
}
