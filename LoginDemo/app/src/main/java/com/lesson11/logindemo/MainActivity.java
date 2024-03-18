package com.lesson11.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lesson11.logindemo.model.Account;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txtInfor);
        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        Account accountLogin = (Account) intent.getSerializableExtra("accountLogin");
        if (account != null && accountLogin != null){
            if (account.getUsername().equals(accountLogin.getUsername()) && accountLogin.getPassword().equals(account.getPassword())){
                txt.setText("Đăng nhập thành công");
            }
            else txt.setText("Đăng nhập thất bại");
        }
        else{
            txt.setText("Đăng nhập thất bại");
        }
    }
}