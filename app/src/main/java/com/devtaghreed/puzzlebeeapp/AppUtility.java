package com.devtaghreed.puzzlebeeapp;

import android.os.CountDownTimer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtility {

    public static boolean email_validation(String email) {
        String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";
        Pattern pat = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
        Matcher mat = pat.matcher(email);

        return mat.find();
    }






}
