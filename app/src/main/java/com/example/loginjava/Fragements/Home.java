package com.example.loginjava.Fragements;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginjava.R;
import com.example.loginjava.databinding.FragmentHomeBinding;
import com.example.loginjava.models.User;


public class Home extends Fragment {
FragmentHomeBinding bind ;
User userData;

    public Home() {
        // Required empty public constructor
    }

    @NonNull
    public static Home newInstance(User user) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putParcelable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            User user = getArguments().getParcelable("user");
            if (user != null){
                userData = user;
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentHomeBinding.inflate(getLayoutInflater());
        String username = userData.getUsername();
        String email = userData.getEmail();
        bind.emailDisplay.setText(email);
        bind.usernameDisplay.setText(username);
        bind.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.main_start_frame, new Login());
                trans.commit();
            }
        });
        return bind.getRoot();
    }
}