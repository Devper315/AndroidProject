package com.example.calculator.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.calculator.R;

public class TechnologyAdapter extends ArrayAdapter<Technology> {
    private Context context;
    private Technology[] techList;
    public TechnologyAdapter(@NonNull Context context, Technology[] techList) {
        super(context, R.layout.tech_item, techList);
        this.context = context;
        this.techList = techList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tech_item, null, true);
        ImageView imageView = view.findViewById(R.id.tech_item_img);
        TextView techName = view.findViewById(R.id.tech_item_name);
        TextView techSub = view.findViewById(R.id.tech_item_sub);
        TextView techDescription = view.findViewById(R.id.tech_item_description);
        Technology technology = techList[position];
        imageView.setImageResource(technology.getImg());
        techName.setText(technology.getName());
        techSub.setText(technology.getSub());
        techDescription.setText(technology.getDescribe());
        return view;
    }

    @Nullable
    @Override
    public Technology getItem(int position) {
        return techList[position];
    }
}
