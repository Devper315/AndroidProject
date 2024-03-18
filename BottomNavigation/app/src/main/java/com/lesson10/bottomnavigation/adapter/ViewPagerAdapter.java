package com.lesson10.bottomnavigation.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.lesson10.bottomnavigation.fragment.FragmentCafe;
import com.lesson10.bottomnavigation.fragment.FragmentHome;
import com.lesson10.bottomnavigation.fragment.FragmentNotification;
import com.lesson10.bottomnavigation.fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) return new FragmentHome();
        if (position == 1) return new FragmentNotification();
        if (position == 2) return new FragmentSearch();
        return new FragmentCafe();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
