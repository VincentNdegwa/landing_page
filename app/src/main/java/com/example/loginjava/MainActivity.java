package com.example.loginjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToLogin();
            }
        },3000);
    }

    private void navigateToLogin() {
        Intent userintent = new Intent(this, UserPage.class);
        startActivity(userintent);
    }

        @Override
    public void onBackPressed() {
        finishAffinity();
    }

}