package com.lesson11.demointentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
    }

    public void email(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "phanvanthi315@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mời tham gia khóa học Android");
        startActivity(Intent.createChooser(intent, "Chọn email"));

    }

    public void send(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        startActivity(intent );
    }

    public void web(View view){
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://google.com"));
        startActivity(intent);
    }

    public void send2(View view){
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:0912004866"));
        intent.putExtra("sms_body", "Tham gia khóa học đi");
        startActivity(intent);
    }

    public void call(View view){
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0377114536"));
        startActivity(intent);
    }
}