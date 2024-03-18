package com.lesson8.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lesson8.fragmentdemo.fragment.FirstFragment;
import com.lesson8.fragmentdemo.fragment.SecondFragment;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private Button btnA, btnB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment;
        switch (view.getId()){
            case R.id.btnA:
                fragment = new FirstFragment();
                transaction.add(R.id.frame,fragment);
                break;
            case R.id.btnB:
                fragment = new SecondFragment();
                transaction.add(R.id.frame, fragment);
                break;
        }
        transaction.commit();
    }
}