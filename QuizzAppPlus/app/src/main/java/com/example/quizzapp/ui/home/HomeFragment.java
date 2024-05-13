package com.example.quizzapp.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.quizzapp.R;
import com.example.quizzapp.chaptertopics.TopicActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {
    CardView heading1, heading2, heading3, heading4, heading5;
    ImageView image1, image2, image3, image4, image5;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        heading1 = view.findViewById(R.id.heading1);
        heading2 = view.findViewById(R.id.heading2);
        heading3 = view.findViewById(R.id.heading3);
        heading4 = view.findViewById(R.id.heading4);
        heading5 = view.findViewById(R.id.heading5);

        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        image4 = view.findViewById(R.id.image4);
        image5 = view.findViewById(R.id.image5);
        loadImage();

        heading1.setOnClickListener(this);
        heading2.setOnClickListener(this);
        heading3.setOnClickListener(this);
        heading4.setOnClickListener(this);
        heading5.setOnClickListener(this);

        return view;
    }

    private void loadImage() {
        String url1 = "https://firebasestorage.googleapis.com/v0/b/quizzapp-2234b.appspot.com/o/Quizz%201.png?alt=media&token=23bcdf84-51fd-4792-b5ff-6b30190f691d";
        String url2 = "https://firebasestorage.googleapis.com/v0/b/quizzapp-2234b.appspot.com/o/Quizz%202.jpg?alt=media&token=4378d4b5-f27c-4e4c-8d50-fe111927f523";
        Glide.with(getContext()).load(url1).into(image1);
        Glide.with(getContext()).load(url2).into(image2);
        Glide.with(getContext()).load(url1).into(image3);
        Glide.with(getContext()).load(url2).into(image4);
        Glide.with(getContext()).load(url1).into(image5);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), TopicActivity.class);
        switch (view.getId()) {
            case R.id.heading1:
                intent.putExtra("chapterName", "heading1");
                startActivity(intent);
                break;
            case R.id.heading2:
                intent.putExtra("chapterName", "heading2");
                startActivity(intent);
                break;
            case R.id.heading3:
                intent.putExtra("chapterName", "heading3");
                startActivity(intent);
                break;
            case R.id.heading4:
                intent.putExtra("chapterName", "heading4");
                startActivity(intent);
                break;
            case R.id.heading5:
                intent.putExtra("chapterName", "heading5");
                startActivity(intent);
                break;
        }
    }
}