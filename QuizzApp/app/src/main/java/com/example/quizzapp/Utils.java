package com.example.quizzapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Utils {
    public static FirebaseUser loginUser;

    public static void getLoginUser(){
        loginUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public static void deleteLoginUser(){
        loginUser = null;
    }
}
