package com.example.quizzapp.ui.code;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quizzapp.R;

import org.checkerframework.checker.units.qual.C;

public class CodeCategory extends AppCompatActivity {
    ListView programs;
    String[] list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_category);
        loadCategory();
        programs = findViewById(R.id.programs);
        CustomAdapter customAdapter = new CustomAdapter();
        programs.setAdapter(customAdapter);
        programs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(CodeCategory.this, CodeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadCategory() {
        final String[] chap1 = {"chapter 1", "list1", "list2", "list3", "list4", "list5", "list6", "list7"};
        final String[] chap2 = {"list1", "list2", "list3", "list4", "list5", "list6", "list7"};
        final String[] chap3 = {"list1", "list2", "list3", "list4", "list5", "list6", "list7"};
        final String[] chap4 = {"list1", "list2", "list3", "list4", "list5", "list6", "list7"};
        final String[] chap5 = {"list1", "list2", "list3", "list4", "list5", "list6", "list7"};
        final String[] chap6 = {"list1", "list2", "list3", "list4", "list5", "list6", "list7"};
        final String[] chap7 = {"list1", "list2", "list3", "list4", "list5", "list6", "list7"};
        final String[] chap8 = {"list1", "list2", "list3", "list4", "list5", "list6", "list7"};
        String category = getIntent().getStringExtra("codeCategory");
        switch (category){
            case "chapter1":
                list = chap1;
                break;
            case "chapter2":
                list = chap2;
                break;
            case "chapter3":
                list = chap3;
                break;
            case "chapter4":
                list = chap4;
                break;
            case "chapter5":
                list = chap5;
                break;
            case "chapter6":
                list = chap6;
                break;
            case "chapter7":
                list = chap7;
                break;
            case "chapter8":
                list = chap8;
                break;
        }

    }

    public class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
//            return list[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.code_category_item_layout, null);
            TextView textView = view.findViewById(R.id.code_title);
            textView.setText(list[position]);
            return view;
        }
    }
}