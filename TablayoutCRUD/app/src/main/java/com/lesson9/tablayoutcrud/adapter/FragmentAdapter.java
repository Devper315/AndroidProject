package com.lesson9.tablayoutcrud.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lesson9.tablayoutcrud.fragment.FragmentAdd;
import com.lesson9.tablayoutcrud.fragment.FragmentSearch;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private int numPage;
    public FragmentAdapter(@NonNull FragmentManager fm, int num) {
        super(fm, num);
        this.numPage = num;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentAdd();
            case 1:
                return new FragmentSearch();
        }
        return new FragmentAdd();
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
