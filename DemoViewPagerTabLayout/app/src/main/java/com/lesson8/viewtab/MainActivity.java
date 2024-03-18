package com.lesson8.viewtab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button btnPre, btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        FragmentManager manager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(manager, 3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTablayoutTitleColor();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Resources resources = getResources();
                switch (tab.getPosition()){
                    case 0:
                        tabLayout.setTabTextColors(Color.BLACK, resources.getColor(R.color.colorPink));
                        btnNext.setBackgroundColor(resources.getColor(R.color.colorPink));
                        btnPre.setBackgroundColor(resources.getColor(R.color.colorPink));
                    case 1:
                        tabLayout.setTabTextColors(Color.BLACK, resources.getColor(R.color.colorGreen));
                        btnNext.setBackgroundColor(resources.getColor(R.color.colorGreen));
                        btnPre.setBackgroundColor(resources.getColor(R.color.colorGreen));
                    case 2:
                        tabLayout.setTabTextColors(Color.BLACK, resources.getColor(R.color.colorBlue));
                        btnNext.setBackgroundColor(resources.getColor(R.color.colorBlue));
                        btnPre.setBackgroundColor(resources.getColor(R.color.colorBlue));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        btnPre.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void initView(){
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        btnPre = findViewById(R.id.btnPre);
        btnNext = findViewById(R.id.btnNext);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNext:
                if (viewPager.getCurrentItem() == 2){
                    return;
                }
                else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    setTablayoutTitleColor();
                }
                break;
            case R.id.btnPre:
                if (viewPager.getCurrentItem() == 0){
                    return;
                }
                else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    setTablayoutTitleColor();
                }
                break;
        }
    }
    private void setTablayoutTitleColor(){
        switch (viewPager.getCurrentItem()){
            case 0:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorPink));
            case 1:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorGreen));
            case 2:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorBlue));
        }
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0){
            super.onBackPressed();
        }
        else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}