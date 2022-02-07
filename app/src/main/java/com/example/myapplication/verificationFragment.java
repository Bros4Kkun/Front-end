package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class verificationFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_verification, container, false);
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("수행자 확인");
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Inflate the layout for this fragment
        return view;
    }
}