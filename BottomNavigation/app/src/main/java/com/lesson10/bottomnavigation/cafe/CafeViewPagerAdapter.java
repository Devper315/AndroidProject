package com.lesson10.bottomnavigation.cafe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CafeViewPagerAdapter extends FragmentStatePagerAdapter {
    public CafeViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 1) return new FragmentMoka();
        if (position == 2) return new FragmentRobusta();
        return new FragmentCuli();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 1) return "MOKA";
        if (position == 2) return "ROBUSTA";
        return "CULI";
    }
}
