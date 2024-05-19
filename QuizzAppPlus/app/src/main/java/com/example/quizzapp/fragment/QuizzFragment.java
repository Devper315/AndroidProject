package com.example.quizzapp.fragment;

import static com.example.quizzapp.Utils.loginUser;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quizzapp.R;
import com.example.quizzapp.authentication.LoginActivity;
import com.example.quizzapp.quizz.StartQuizz;

public class QuizzFragment extends Fragment {
    Button playQuizz;

    public QuizzFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_quizz, container, false);
        playQuizz = root.findViewById(R.id.play_quizz);
        playQuizz.setOnClickListener(view -> {
            if (loginUser != null){
                startActivity(new Intent(getContext(), StartQuizz.class));
            }
            else{
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        return root;
    }
}