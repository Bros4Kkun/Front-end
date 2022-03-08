package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class noticeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_notice, container, false);
        ImageButton btnBack = rootView.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Fragment Fragment = new Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.profileLayout, Fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }
}