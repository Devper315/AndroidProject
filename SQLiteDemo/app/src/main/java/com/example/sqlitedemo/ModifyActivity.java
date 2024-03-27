package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sqlitedemo.dao.ItemHelper;
import com.example.sqlitedemo.model.Item;

import java.util.Calendar;

public class ModifyActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spCategory;
    private EditText eTitle, ePrice, eDate;
    private Button btnUpdate, btnDelete, btnCancel;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        initView();
        btnCancel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        eDate.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        eTitle.setText(item.getTitle());
        ePrice.setText(item.getPrice() + "");
        eDate.setText(item.getDate());
        int position = 0;
        for (int i = 0; i < spCategory.getCount(); i++){
            if (spCategory.getItemAtPosition(i).toString().equals(item.getCategory())){
                position = i;
                break;
            }
        }
        spCategory.setSelection(position);
    }

    private void initView(){
        spCategory = findViewById(R.id.spCategory);
        eTitle = findViewById(R.id.eTitle);
        ePrice = findViewById(R.id.ePrice);
        eDate = findViewById(R.id.eDate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        spCategory.setAdapter(new ArrayAdapter<>(this, R.layout.category_spinner,
                getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        ItemHelper itemHelper = new ItemHelper(this);
        if (view == eDate){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(ModifyActivity.this, (datePicker, y, m, d) -> {
                String date = String.format("%02d/%02d/%d", d, m + 1, y);
                eDate.setText(date);
            }, year, month, day);
            dialog.show();
        }
        if (view == btnCancel){
            finish();
        }
        if (view == btnUpdate){
            String title = eTitle.getText().toString();
            String price = ePrice.getText().toString();
            String category = spCategory.getSelectedItem().toString();
            String date = eDate.getText().toString();
            if (!title.isEmpty() && price.matches("\\d+")){
                Item editItem = new Item(title, category, Double.valueOf(price), date);
                itemHelper.update(editItem, item.getId());
                finish();
            }
            else Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
        }
        if (view == btnDelete){
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa mục " + item.getTitle() + " không ?");
            builder.setIcon(R.drawable.icon_delete);
            builder.setPositiveButton("Có", (dialogInterface, i) -> {
                itemHelper.deleteById(item.getId());
                finish();
            });
            builder.setNegativeButton("Không", (dialogInterface, i) -> {
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}