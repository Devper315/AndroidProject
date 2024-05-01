package com.example.quizzapp.ui.code;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.quizzapp.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class CodeFragment extends Fragment implements View.OnClickListener {
    CircleImageView image1, image2, image3, image4, image5, image6, image7, image8;
    public CodeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_code, container, false);
        image1 = view.findViewById(R.id.chapter1);
        image2 = view.findViewById(R.id.chapter2);
        image3 = view.findViewById(R.id.chapter3);
        image4 = view.findViewById(R.id.chapter4);
        image5 = view.findViewById(R.id.chapter5);
        image6 = view.findViewById(R.id.chapter6);
        image7 = view.findViewById(R.id.chapter7);
        image8 = view.findViewById(R.id.chapter8);
        loadImage();
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
        image7.setOnClickListener(this);
        image8.setOnClickListener(this);

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
        Glide.with(getContext()).load(url2).into(image6);
        Glide.with(getContext()).load(url1).into(image7);
        Glide.with(getContext()).load(url2).into(image8);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), CodeCategory.class);
        int viewId = v.getId();
        if (viewId == R.id.chapter1){
            intent.putExtra("codeCategory", "chapter1");
            startActivity(intent);
        }
        if (viewId == R.id.chapter2){
            intent.putExtra("codeCategory", "chapter2");
            startActivity(intent);
        }
        if (viewId == R.id.chapter3){
            intent.putExtra("codeCategory", "chapter3");
            startActivity(intent);
        }
        if (viewId == R.id.chapter4){
            intent.putExtra("codeCategory", "chapter4");
            startActivity(intent);
        }
        if (viewId == R.id.chapter5){
            intent.putExtra("codeCategory", "chapter5");
            startActivity(intent);
        }
        if (viewId == R.id.chapter6){
            intent.putExtra("codeCategory", "chapter6");
            startActivity(intent);
        }
        if (viewId == R.id.chapter7){
            intent.putExtra("codeCategory", "chapter7");
            startActivity(intent);
        }
        if (viewId == R.id.chapter8){
            intent.putExtra("codeCategory", "chapter8");
            startActivity(intent);
        }
    }
}