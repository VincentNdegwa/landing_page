package com.example.loginjava.Fragements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginjava.R;
import com.example.loginjava.databinding.FragmentRegisterBinding;


public class Register extends Fragment {

FragmentRegisterBinding bind;

    public Register() {
        // Required empty public constructor
    }


    public static Register newInstance(String param1, String param2) {

        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentRegisterBinding.inflate(getLayoutInflater());
        bind.navigateLogin.setOnClickListener(View->navigateToLogin());

        return bind.getRoot();
    }

    private void navigateToLogin(){
        FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.main_start_frame, new Login());
        trans.addToBackStack(null);
        trans.commit();
    }

}