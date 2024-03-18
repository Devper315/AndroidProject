package com.lesson11.logindemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lesson11.logindemo.model.Account;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvUser, tvPass;
    private Button btnLogin, btnRegister;
    private Account accountLogin;

    public final static int REQUEST_CODE = 315;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private void initView(){
        tvUser = findViewById(R.id.txtUsername);
        tvPass = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin){
            Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
            Account account = new Account(tvUser.getText().toString(), tvPass.getText().toString());
            loginIntent.putExtra("account", account);
            loginIntent.putExtra("accountLogin", accountLogin);
            startActivity(loginIntent);
        }
        if (view.getId() == R.id.btnRegister){
            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivityForResult(registerIntent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if (data == null) Toast.makeText(this, "Đã hủy đăng ký", Toast.LENGTH_SHORT).show();
            else {
                accountLogin = (Account) data.getSerializableExtra("data");
                tvUser.setText(accountLogin.getUsername());
                tvPass.setText(accountLogin.getPassword());
            }
        }
    }
}