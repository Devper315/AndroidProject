package com.lesson8.viewtab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lesson8.viewtab.fragment.FragmentFood;
import com.lesson8.viewtab.fragment.FragmentMovie;
import com.lesson8.viewtab.fragment.FragmentTravel;

@SuppressWarnings("deprecation")
public class FragmentAdapter extends FragmentPagerAdapter {
    private int pageNumber;
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNumber = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentFood fragmentFood = new FragmentFood();
                return fragmentFood;
            case 1:
                FragmentMovie fragmentMovie = new FragmentMovie();
                return fragmentMovie;
            case 2:
                FragmentTravel fragmentTravel = new FragmentTravel();
                return fragmentTravel;
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageNumber;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "FOOD";
            case 1: return "MOVIE";
            case 2: return "TRAVEL";
        }
        return null;
    }
}
