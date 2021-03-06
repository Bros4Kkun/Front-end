package com.example.RunToU.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.RunToU.R;
import com.example.RunToU.profile.reviewAdapter;
import com.example.RunToU.profile.reviewData;

import java.util.ArrayList;

public class revrecFragment extends Fragment {

    private ArrayList<reviewData> arrayList;
    private com.example.RunToU.profile.reviewAdapter reviewAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_revrec, container, false);

        recyclerView = view.findViewById(R.id.rvReview);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        reviewAdapter = new reviewAdapter(arrayList);
        recyclerView.setAdapter(reviewAdapter);

        return view;
    }
}