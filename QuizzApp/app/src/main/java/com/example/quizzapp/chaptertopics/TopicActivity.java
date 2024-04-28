package com.example.quizzapp.chaptertopics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.adservices.topics.Topic;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.quizzapp.R;

public class TopicActivity extends AppCompatActivity {
    Toolbar toolbar;
    ExpanableHeightGridView gridView;
    String[] topicName = {"Sub Heading 1", "Sub Heading 2", "Sub Heading 3", "Sub Heading 4", "Sub Heading 5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        toolbar = findViewById(R.id.toolbar);
        gridView = findViewById(R.id.topics_name);
        gridView.setExpand(true);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TopicAdapter adapter = new TopicAdapter(topicName, TopicActivity.this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(TopicActivity.this, topicName[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}