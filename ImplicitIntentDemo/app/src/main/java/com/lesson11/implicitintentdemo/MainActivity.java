package com.lesson11.implicitintentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iWeb, iMail, iCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iWeb = findViewById(R.id.web);
        iMail = findViewById(R.id.mail);
        iCall = findViewById(R.id.call);
        iWeb.setOnClickListener(this);
        iMail.setOnClickListener(this);
        iCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.web){
            Intent web = new Intent(Intent.ACTION_VIEW);
            web.setData(Uri.parse("https://www.google.com.vn/"));
            startActivity(web);

        }
        if (view.getId() == R.id.mail){
            Intent mail = new Intent(Intent.ACTION_VIEW);
            mail.setData(Uri.parse("sms:" + "0377114536"));
            mail.putExtra("sms_body", "");
            startActivity(mail);
        }
        if (view.getId() == R.id.call){
            Intent phone = new Intent(Intent.ACTION_DIAL);
            phone.setData(Uri.parse("tel:0377114536"));
            startActivity(phone);
        }
    }
}