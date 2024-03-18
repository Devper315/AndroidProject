package com.example.tourmanager.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tourmanager.R;

public class TourSpinnerAdapter extends BaseAdapter {
    private int[] imgs = {R.drawable.car, R.drawable.motorbike};
    private Context context;

    public TourSpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    @SuppressLint("MissingInflatedId")
    public View getView(int position, View view, ViewGroup viewGroup) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_image, viewGroup, false);
        ImageView img = item.findViewById(R.id.tour_img_view);
        img.setImageResource(imgs[position]);
        return item;
    }
}
