package com.example.quizzapp.authentication;

import static com.example.quizzapp.Utils.loginUser;
import static com.example.quizzapp.Utils.reference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.quizzapp.R;
import com.example.quizzapp.Utils;
import com.example.quizzapp.dao.QuizzHelper;
import com.example.quizzapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText regName, regEmail, regPassword;
    Button registerBtn, regLogBtn;
    ImageView regImage;
    static int REQUEST_CODE = 1;
    Uri pickedImgUri;
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
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        regLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
        regImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 22){
                    checkAndRequestPermission();
                }
                else openGallery();
            }
        });
    }

    private void openLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void checkAndRequestPermission() {
        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(RegisterActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        else openGallery();
    }

    @SuppressWarnings("deprecation")
    private void openGallery() {
        startActivityForResult(new Intent().setAction(Intent.ACTION_GET_CONTENT)
                .setType("image/*"), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null){
                pickedImgUri = data.getData();
                regImage.setImageURI(pickedImgUri);
            }
        }
    }

    private void registerUser() {
        String name, email, password;
        name = regName.getText().toString().trim();
        email = regEmail.getText().toString().trim();
        password = regPassword.getText().toString().trim();
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || pickedImgUri == null){
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
                                updateUserImage(name, pickedImgUri, registerUser);

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
        QuizzHelper helper = new QuizzHelper(this);
        if (!helper.checkUserByFirebaseId(loginUser.getUid())) {
            User localUser = new User(loginUser.getDisplayName(), loginUser.getUid());
            helper.addUser(localUser);
        }
        startActivity(new Intent(this, ProfileActivity.class));
        finish();
    }

    private void updateUserImage(String name, Uri pickedImgUri, FirebaseUser currentUser) {
        StorageReference storage = FirebaseStorage.getInstance().getReference().child("user_image");
        StorageReference imgFilePath = storage.child(pickedImgUri.getLastPathSegment());
        imgFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imgFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();
                        currentUser.updateProfile(changeRequest);
                    }
                });
            }
        });
    }
}