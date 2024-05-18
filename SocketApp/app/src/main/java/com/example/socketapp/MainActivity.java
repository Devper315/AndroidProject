package com.example.socketapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.txt);
        new Thread(() -> {
            try {
                Socket socket = new Socket("192.168.42.103", 8888);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String name = in.readLine();
                MainActivity.this.runOnUiThread(() -> {
                    textView.setText(name);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

}
