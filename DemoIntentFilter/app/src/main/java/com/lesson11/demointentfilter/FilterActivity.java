package com.lesson11.demointentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class FilterActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        tv = findViewById(R.id.tv);
//        showWebInfo();
//        showMessageInfo();
        showCallInfo();
    }

    private void showWebInfo() {
        Uri url = getIntent().getData();
        String s = "Scheme:" + url.getScheme() + "\nHost:" + url.getHost();
        int k = 1;
        for (String path : url.getPathSegments()) {
            s += "\npara" + k + ":" + path;
            k++;
        }
        s += "\naction:" + getIntent().getAction();
        tv.setText(s);
    }

    private void showMessageInfo() {
        String s = "Content:" + getIntent().getStringExtra("sms_body").toString();
        s += "\ndata:" + getIntent().getDataString();
        tv.setText(s);
    }

    private void showCallInfo() {
        String num = getIntent().getDataString().substring(4);
        tv.setText(num);
    }
}