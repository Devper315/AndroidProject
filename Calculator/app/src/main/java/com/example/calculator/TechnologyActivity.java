package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.model.Technology;
import com.example.calculator.model.TechnologyAdapter;

public class TechnologyActivity extends AppCompatActivity {
    private TextView tv;
    private ListView listView;

    private Technology[] techList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);
//        initListViewString();
        initData();
        TechnologyAdapter adapter = new TechnologyAdapter(this, techList);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i = 0; i < listView.getAdapter().getCount(); i++){
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                listView.getChildAt(position).setBackgroundColor(Color.YELLOW);
                Technology t = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), t.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        tv = findViewById(R.id.tv);
        registerForContextMenu(tv);
    }

    private void initData() {
        int[] imgs = {R.drawable.ic_blue, R.drawable.ic_green, R.drawable.ic_red};
        String[] names = {"Android", "Ios", "Window phone"};
        String[] subs = {"Sub1", "Sub2", "Sub3"};
        String[] descs = {"Desc1", "Desc2", "Desc3"};
        techList = new Technology[3];
        for (int i = 0; i < 3; i++) {
            techList[i] = new Technology(imgs[i], names[i], subs[i], descs[i]);
        }
    }

    private void initListViewString() {
        listView = findViewById(R.id.listView);
        String[] techList = getResources().getStringArray(R.array.technology);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.tech_item_string, techList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selection = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), selection, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.color_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRed:
                tv.setTextColor(Color.RED);
                break;
            case R.id.menuGreen:
                tv.setTextColor(Color.GREEN);
                break;
            case R.id.menuBlue:
                tv.setTextColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuContact:
                Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuEmail:
                Toast.makeText(this, "Email", Toast.LENGTH_LONG).show();
                break;
            case R.id.menuExit:
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}