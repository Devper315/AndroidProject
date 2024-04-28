package com.example.quizzapp.chaptertopics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quizzapp.R;

public class TopicAdapter extends BaseAdapter {
    String[] topicName;
    Context context;

    public TopicAdapter(String[] topicName, Context context) {
        this.topicName = topicName;
        this.context = context;
    }

    @Override
    public int getCount() {
        return topicName.length;
    }

    @Override
    public Object getItem(int i) {
        return topicName[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View gridView = view;
        if (view == null){
            gridView = LayoutInflater.from(context).inflate(R.layout.custom_topic_item_layout, viewGroup, false);
        }
        TextView textView = gridView.findViewById(R.id.topic_text);
        textView.setText(topicName[i]);
        return gridView;
    }
}
