package com.example.sugarorm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.sugarorm.adapter.ModelAdapter;
import com.example.sugarorm.model.Company;
import com.example.sugarorm.model.Person;
import com.orm.SugarContext;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Person> personList;
    private RecyclerView recyclerView;
    private ModelAdapter adapter;
    private Button btnAdd, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);
        personList = Person.listAll(Person.class);
        initView();
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        adapter = new ModelAdapter(personList, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initView(){
        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
    }

    @Override
    public void onClick(View view) {
        if (view == btnAdd){
            Company newCompany = new Company("IZI", "Hanoi");
            Person newPerson = new Person("Devper", newCompany);
            newPerson.save();
            personList.add(newPerson);
            adapter.notifyDataSetChanged();
        }
        if (view == btnDelete){
            Person.deleteAll(Person.class);
            personList.clear();
            adapter.notifyDataSetChanged();
        }
    }
}