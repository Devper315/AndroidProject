package com.lesson10.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lesson10.bottomnavigation.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0)
                    navigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                if (position == 1)
                    navigationView.getMenu().findItem(R.id.menu_notification).setChecked(true);
                if (position == 2)
                    navigationView.getMenu().findItem(R.id.menu_search).setChecked(true);
                if (position == 3)
                    navigationView.getMenu().findItem(R.id.menu_cafe).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_home) viewPager.setCurrentItem(0);
            if (id == R.id.menu_notification) viewPager.setCurrentItem(1);
            if (id == R.id.menu_search) viewPager.setCurrentItem(2);
            if (id == R.id.menu_cafe) viewPager.setCurrentItem(3);
            return true;
        });
        fab = findViewById(R.id.fab);

    }
}