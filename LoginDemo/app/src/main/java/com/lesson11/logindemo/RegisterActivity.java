package com.lesson11.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lesson11.logindemo.model.Account;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUser, tvPass;
    private Button btnCancel, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        btnCancel.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private void initView(){
        tvUser = findViewById(R.id.txtUsername);
        tvPass = findViewById(R.id.txtPassword);
        btnCancel = findViewById(R.id.btnCancel);
        btnRegister = findViewById(R.id.btnRegister);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRegister){
            Account account = new Account(tvUser.getText().toString(), tvPass.getText().toString());
            Intent intent = new Intent();
            intent.putExtra("data", account);
            setResult(RESULT_OK, intent);
            finish();
        }
        if (view.getId() == R.id.btnCancel){
            setResult(RESULT_CANCELED, null);
            finish();
        }
    }
}