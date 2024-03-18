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
import com.lesson8.fragmentdemo.fragment.ThirdFragment;

public class MainActivity3 extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Button btnAddA, btnAddB, btnAddC, btnRemoveA, btnRemoveB, btnRemoveC, btnBack, btnPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        fragmentManager = getSupportFragmentManager();
        initView();
        btnAddA.setOnClickListener(view -> {
            FirstFragment firstFragment = new FirstFragment();
            add(firstFragment, "fragA", "fa");
        });
        btnAddB.setOnClickListener(view -> {
            SecondFragment secondFragment = new SecondFragment();
            add(secondFragment, "fragA", "fb");
        });
        btnAddC.setOnClickListener(view -> {
            ThirdFragment thirdFragment = new ThirdFragment();
            add(thirdFragment, "fragC", "fc");
        });
        btnRemoveA.setOnClickListener(view -> {
            remove("fragA");
        });
        btnRemoveB.setOnClickListener(view -> {
            remove("fragB");
        });
        btnRemoveC.setOnClickListener(view -> {
            remove("fragC");
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.popBackStack();
            }
        });
        btnPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.popBackStack("fa", 0);
            }
        });

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0){
            fragmentManager.popBackStack();
        }
        super.onBackPressed();
    }

    private void add(Fragment fragment, String tag, String name) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(name);
        fragmentTransaction.commit();
    }

    private void remove(String tag){
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    private void initView() {
        btnAddA = findViewById(R.id.btnAddA);
        btnAddB = findViewById(R.id.btnAddB);
        btnAddC = findViewById(R.id.btnAddC);
        btnRemoveA = findViewById(R.id.btnRemoveA);
        btnRemoveB = findViewById(R.id.btnRemoveB);
        btnRemoveC = findViewById(R.id.btnRemoveC);
        btnBack = findViewById(R.id.btnBack);
        btnPop = findViewById(R.id.btnPopA);

    }
}