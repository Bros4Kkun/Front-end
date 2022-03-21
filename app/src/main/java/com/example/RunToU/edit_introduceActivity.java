package com.example.RunToU;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class edit_introduceActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_introduce);

        ImageButton btnVeri, btnChange, btnEdit;
        ImageView isChecked;
        TextView isVerified;
        EditText editIntroduce;

        btnVeri = findViewById(R.id.btnVeri);
        btnEdit = findViewById(R.id.btnEdit);
        isChecked = findViewById(R.id.isChecked);
        isVerified = findViewById(R.id.isVerified);
        editIntroduce = findViewById(R.id.editIntroduce);

        String temp = isVerified.getText().toString();

        Toast toast = Toast.makeText(this, temp, Toast.LENGTH_LONG);
        toast.show();

        if(temp.equals("X")){
            btnVeri.setVisibility(View.VISIBLE);
            isChecked.setVisibility(View.INVISIBLE);
        }else if(temp.equals("O")){
            btnVeri.setVisibility(View.INVISIBLE);
            isChecked.setVisibility(View.VISIBLE);
        }

        btnVeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), verificationActivity.class);
                startActivity(intent);
            }//인증을 하는 수단을 추가해야할듯...
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
