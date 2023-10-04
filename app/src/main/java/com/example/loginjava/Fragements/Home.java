package com.example.loginjava.Fragements;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginjava.R;
import com.example.loginjava.databinding.FragmentHomeBinding;
import com.example.loginjava.models.User;


public class Home extends Fragment {
FragmentHomeBinding bind ;

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
                System.out.println(user);
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentHomeBinding.inflate(getLayoutInflater());

        return bind.getRoot();
    }
}