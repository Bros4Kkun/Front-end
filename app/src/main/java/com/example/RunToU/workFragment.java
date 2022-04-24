package com.example.RunToU;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

public class workFragment extends Fragment {

    private ArrayList<workData> arrayList;
    private workAdapter workAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    int t = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_work, container, false);
        getActivity().setTitle("심부름");
        ImageButton btn_add = view.findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), writeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(t==1){
            recyclerView = getActivity().findViewById(R.id.rvWork);
            linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);

            arrayList = new ArrayList<>();

            workAdapter = new workAdapter(arrayList);
            recyclerView.setAdapter(workAdapter);
            workData workData = new workData(R.drawable.cate_deli, "96000", "500m", "치킨 무 빼고 포장해주세요.");
            arrayList.add(workData);
            workAdapter.notifyDataSetChanged();
        }
        t+=1;
    }
}
