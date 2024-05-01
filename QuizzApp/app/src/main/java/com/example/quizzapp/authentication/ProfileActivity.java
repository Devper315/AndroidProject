package com.example.quizzapp.authentication;

import static com.example.quizzapp.Utils.loginUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quizzapp.MainActivity;
import com.example.quizzapp.R;
import com.example.quizzapp.Utils;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    TextView name, email;
    ImageView userImage;
    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.user_name);
        email = findViewById(R.id.user_email);
        logoutBtn = findViewById(R.id.logout_btn);
        userImage = findViewById(R.id.user_image);
        name.setText(loginUser.getDisplayName());
        email.setText(loginUser.getEmail());
        Glide.with(this).load(loginUser.getPhotoUrl()).into(userImage);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Utils.deleteLoginUser();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}