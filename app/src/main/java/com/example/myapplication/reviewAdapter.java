package com.example.myapplication;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class reviewAdapter extends BaseAdapter {

    CircleImageView ci_profile;
    TextView tvId, tvReCa, tvPoint, tvStory;

    ArrayList<reviewItem> reviewItemList = new ArrayList<reviewItem>();

    public reviewAdapter(){

    }

    @Override
    public int getCount() {
        return reviewItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.form_review, viewGroup, false);
        }

        ci_profile = view.findViewById(R.id.ci_profile);
        tvId = view.findViewById(R.id.tvId);
        tvReCa = view.findViewById(R.id.tvReCa);
        tvPoint = view.findViewById(R.id.tvPoint);
        tvStory = view.findViewById(R.id.tvStory);
        return null;
    }
}
