package com.example.collegebuddy.util;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferencesUtil {

    private static final String USER_DATA_PREFS = "user_data";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ROLE = "role";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_UID = "uid";
    private static final String KEY_USER_NAME="username";

    public static void saveUserData(Context context, String email, String role, String password, String uid,String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DATA_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_UID, uid);
        editor.putString(KEY_USER_NAME,username);

        // Add other user information as needed

        editor.apply();
    }
    public static String getUserName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DATA_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_NAME, "");
    }

    public static String getEmail(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DATA_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    public static String getRole(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DATA_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ROLE, "");
    }

    public static String getPassword(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DATA_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PASSWORD, "");
    }

    public static String getUid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DATA_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_UID, "");
    }

    // Add methods for other user information as needed
}
