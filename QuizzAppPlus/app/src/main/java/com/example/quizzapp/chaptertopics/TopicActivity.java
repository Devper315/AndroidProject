package com.example.quizzapp.chaptertopics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.adservices.topics.Topic;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quizzapp.R;


public class TopicActivity extends AppCompatActivity {
    Toolbar toolbar;
    ExpanableHeightGridView gridView;
    String chapterName;
    TopicAdapter adapter;
    ImageView chapterImage;
    String[] arr = null;
    String[] chapter1 = {"Java", "Android", "Sub Heading 1", "Sub Heading 1", "Sub Heading 1"};
    String[] chapter2 = {"PHP", "Sub Heading 2", "Sub Heading 2", "Sub Heading 2", "Sub Heading 2"};
    String[] chapter3 = {"Sub Heading 3", "Sub Heading 3", "Sub Heading 3", "Sub Heading 3", "Sub Heading 3"};
    String[] chapter4 = {"Sub Heading 4", "Sub Heading 4", "Sub Heading 4", "Sub Heading 4", "Sub Heading 4"};
    String[] chapter5 = {"Javascript", "Sub Heading 5", "Sub Heading 5", "Sub Heading 5", "Sub Heading 5"};

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
        chapterName = getIntent().getStringExtra("chapterName");
        chapterImage = findViewById(R.id.chapter_image);
        compareAndOpen();

    }
    private void compareAndOpen(){
        String url1 = "https://firebasestorage.googleapis.com/v0/b/quizzapp-2234b.appspot.com/o/Quizz%201.png?alt=media&token=23bcdf84-51fd-4792-b5ff-6b30190f691d";
        String url2 = "https://firebasestorage.googleapis.com/v0/b/quizzapp-2234b.appspot.com/o/Quizz%202.jpg?alt=media&token=4378d4b5-f27c-4e4c-8d50-fe111927f523";
        if (chapterName.equals("heading1")) {
            arr = chapter1;
            Glide.with(TopicActivity.this).load(url1).into(chapterImage);
            getSupportActionBar().setTitle("heading 1");
        }
        if (chapterName.equals("heading2")) {
            arr = chapter2;
            Glide.with(TopicActivity.this).load(url1).into(chapterImage);
            getSupportActionBar().setTitle("heading 2");
        }
        if (chapterName.equals("heading3")) {
            arr = chapter3;
            Glide.with(TopicActivity.this).load(url1).into(chapterImage);
            getSupportActionBar().setTitle("heading 3");
        }
        if (chapterName.equals("heading4")) {
            arr = chapter4;
            Glide.with(TopicActivity.this).load(url1).into(chapterImage);
            getSupportActionBar().setTitle("heading 4");
        }
        if (chapterName.equals("heading5")) {
            arr = chapter5;
            Glide.with(TopicActivity.this).load(url1).into(chapterImage);
            getSupportActionBar().setTitle("heading 5");
        }

        adapter = new TopicAdapter(arr, TopicActivity.this);
        gridView.setAdapter(adapter);

    }

}