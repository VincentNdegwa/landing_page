package com.example.loginjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.loginjava.Fragements.Login;

public class UserPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        FrameLayout main_frame = findViewById(R.id.main_start_frame);
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.main_start_frame, new Login());
        trans.commit();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}