package com.example.quizzapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.quizzapp.R;
import com.example.quizzapp.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    EditText regName, regEmail, regPassword;
    Button registerBtn, regLogBtn;
    ImageView regImage;
    ProgressBar registerProgressBar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regName = findViewById(R.id.reg_name);
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        registerBtn = findViewById(R.id.register_button);
        regLogBtn = findViewById(R.id.reg_log_btn);
        regImage = findViewById(R.id.reg_image);
        registerProgressBar = findViewById(R.id.register_progress_bar);
        registerProgressBar.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();
        registerBtn.setOnClickListener(v -> registerUser());
        regLogBtn.setOnClickListener(v -> openLogin());

    }

    private void openLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    private void registerUser() {
        String name, email, password;
        name = regName.getText().toString().trim();
        email = regEmail.getText().toString().trim();
        password = regPassword.getText().toString().trim();
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else{
            registerProgressBar.setVisibility(View.VISIBLE);
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                FirebaseUser registerUser = auth.getCurrentUser();
                                UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build();
                                registerUser.updateProfile(changeRequest);
                                openProfile();
                                registerProgressBar.setVisibility(View.GONE);
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                registerProgressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }
    }

    private void openProfile() {
        Utils.getLoginUser();
        startActivity(new Intent(this, ProfileActivity.class));
        finish();
    }

}