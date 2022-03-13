package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class writeActivity extends AppCompatActivity {
    //왜인진 모르겠으나 fragment로 했을 때는 오류가나고 안넘어감 -> (activity로 만들었음)


    private Spinner cateSpinner;
    private ArrayAdapter cateAdapter;

    private WebView webView;
    private TextView txt_address;
    private Handler handler;

    EditText title_write, price_write, detail_write, context_write;     //액티비티가 종료되면서 데이터를 넘겨주나? or 서버에 올라가나?
    ImageButton btnGotopur;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        setTitle("요청서 작성");
        setViews();

        title_write = findViewById(R.id.title_write);
        price_write = findViewById(R.id.price_write);
        detail_write = findViewById(R.id.detail_write);
        context_write = findViewById(R.id.context_write);

//        int tempPrice;
//        try{
//            tempPrice = Integer.parseInt(price_write.getText().toString());
//        }catch (NumberFormatException e){
//            tempPrice = 0;
//        }
//        int price = tempPrice;

        btnGotopur = findViewById(R.id.btnGotopur);
        btnGotopur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    int price = Integer.parseInt(price_write.getText().toString());
                    //
                    if(title_write.getText().toString().length()<= 5){
                        Toast.makeText(getApplication(), "제목을 5자 이상 작성해주세요", Toast.LENGTH_SHORT).show();
                    }else if(cateSpinner.getSelectedItem().toString().equals("--선택--")){
                        Toast.makeText(getApplication(), "카테고리를 선택해주세요.", Toast.LENGTH_SHORT).show();
                    }else if(price <= 0 || price > 100000){
                        Toast.makeText(getApplication(), "금액을 정확히 작성해주세요.", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(view.getContext(), purchaseActivity.class);
                        startActivity(intent);
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplication(), "요청서를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setViews(){
        cateSpinner = findViewById(R.id.cate_write);
        cateAdapter = ArrayAdapter.createFromResource(this, R.array.cate, R.layout.support_simple_spinner_dropdown_item);
        cateSpinner.setAdapter(cateAdapter);
    }
}
