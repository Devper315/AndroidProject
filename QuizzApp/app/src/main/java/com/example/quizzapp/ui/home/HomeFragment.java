package com.example.quizzapp.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizzapp.R;
import com.example.quizzapp.chaptertopics.TopicActivity;

public class HomeFragment extends Fragment {
    CardView heading1;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        heading1 = view.findViewById(R.id.heading1);
        heading1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TopicActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}