package com.example.recycleviewdemocrud.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.recycleviewdemocrud.R;
import com.example.recycleviewdemocrud.utils.Utils;

public class SpinnerAdapter extends BaseAdapter {
    private Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Utils.imgs.length;
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
    public View getView(int postion, View view, ViewGroup viewGroup) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_image, viewGroup, false);
        ImageView img = item.findViewById(R.id.img);
        img.setImageResource(Utils.imgs[postion]);
        return item;
    }
}
