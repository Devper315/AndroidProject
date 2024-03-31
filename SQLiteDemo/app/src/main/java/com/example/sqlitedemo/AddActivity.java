package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sqlitedemo.dao.ItemHelper;
import com.example.sqlitedemo.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spCategory;
    private EditText eTitle, ePrice, eDate;
    private Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        eDate.setOnClickListener(this);
    }

    private void initView() {
        spCategory = findViewById(R.id.spCategory);
        eTitle = findViewById(R.id.eTitle);
        ePrice = findViewById(R.id.ePrice);
        eDate = findViewById(R.id.eDate);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        spCategory.setAdapter(new ArrayAdapter<>(this, R.layout.category_spinner,
                getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        if (view == btnAdd){
            String title = eTitle.getText().toString();
            String price = ePrice.getText().toString();
            String category = spCategory.getSelectedItem().toString();
            String date = eDate.getText().toString();
            if (!title.isEmpty() && price.matches("\\d+")){
                Item newItem = new Item(title, category, Double.valueOf(price), date);
                ItemHelper itemHelper = new ItemHelper(this);
                itemHelper.addItem(newItem);
                finish();
            }
            else Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
        }
        if (view == btnCancel){
            finish();
        }
        if (view == eDate){
            DatePickerDialog dialog = Utils.buildDateDialog(this, (EditText) view);
            dialog.show();
        }
    }
}