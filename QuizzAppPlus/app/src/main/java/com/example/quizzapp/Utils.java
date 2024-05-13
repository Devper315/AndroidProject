package com.example.quizzapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.units.qual.C;

public class Utils {
    public static FirebaseUser loginUser = null;
    public static DatabaseReference reference = null;

    public static void getLoginUser(){
        loginUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public static void deleteLoginUser(){
        loginUser = null;
    }
    public static void getDatabaseReference(){
        reference = FirebaseDatabase.getInstance().getReference();
    }


}
