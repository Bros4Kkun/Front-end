package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class verificationFragment extends Fragment {

    private recyclerAdapter adapter;
    MainActivity mainActivity;
    RecyclerView rvVeri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_verification, container, false);
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("수행자 확인");
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();

        getData();

        return view;
    }

    public void init(){
        RecyclerView recyclerView = rvVeri;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new recyclerAdapter();
        recyclerView.setAdapter(adapter);
    }
    public void getData(){
        List<String> listTitle = Arrays.asList("이름1","이름2","이름3","이름4");
        List<String> listContent = Arrays.asList("내용1", "내용2", "내용3", "내용4");
        List<Integer> listResId = Arrays.asList(R.drawable.spider,R.drawable.spider,R.drawable.spider,R.drawable.spider);

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            veriData data = new veriData();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}