package com.lesson9.tablayoutcrud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lesson9.tablayoutcrud.R;

public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private int[] imgs;

    public SpinnerAdapter(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_spinner, viewGroup, false);
        ImageView img = itemView.findViewById(R.id.item_spinner_img);
        img.setImageResource(imgs[position]);
        return itemView;
    }
}
