package Test.test_1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Test.test_1.model.DuAn;
import Test.test_1.model.DuAnAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DuAnAdapter.DuAnListener {
    private EditText duAn, start, end;
    private Button add, update;
    private RecyclerView rv;
    private DuAnAdapter adapter;
    private int curPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        start.setOnClickListener(this);
        end.setOnClickListener(this);

        add.setOnClickListener(this);
        update.setOnClickListener(this);
    }

    private void initView() {
        duAn = findViewById(R.id.duAn);
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        add = findViewById(R.id.add);
        update = findViewById(R.id.update);
        update.setEnabled(false);
        rv = findViewById(R.id.rv);
        adapter = new DuAnAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Calendar c = Calendar.getInstance();
        if (v == start) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    start.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        }

        if (v == end) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    end.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        }

        if (v == add) {
            String tenDA = duAn.getText().toString();
            String ngayBD = start.getText().toString();
            String ngayKT = end.getText().toString();
            if (tenDA.equals("") || ngayBD.equals("") || ngayKT.equals("")) {
                Toast.makeText(MainActivity.this, "Dữ liệu trống!!!", Toast.LENGTH_SHORT).show();
            } else {
                adapter.add(new DuAn(tenDA, ngayBD, ngayKT));
            }
        }

        if (v == update) {
            String tenDA = duAn.getText().toString();
            String ngayBD = start.getText().toString();
            String ngayKT = end.getText().toString();
            if (tenDA.equals("") || ngayBD.equals("") || ngayKT.equals("")) {
                Toast.makeText(MainActivity.this, "Dữ liệu trống!!!", Toast.LENGTH_SHORT).show();
            } else {
                adapter.update(new DuAn(tenDA, ngayBD, ngayKT), curPosition);
                update.setEnabled(false);
                add.setEnabled(true);
            }
        }
    }

    @Override
    public void onClickListener(View view, int position) {
        update.setEnabled(true);
        add.setEnabled(false);
        curPosition = position;
        DuAn an = adapter.getDuAn(position);
        if (an == null) {
            return;
        }
        duAn.setText(an.getTenDA());
        start.setText(an.getNgayBD());
        end.setText(an.getNgayKT());
    }
}