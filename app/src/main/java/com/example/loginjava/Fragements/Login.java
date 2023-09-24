package com.example.loginjava.Fragements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.loginjava.R;
import com.example.loginjava.databinding.FragmentLoginBinding;


public class Login extends Fragment {

private FragmentLoginBinding bind;

    public Login() {
    }

    public static Login newInstance(String param1, String param2) {
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
        bind = FragmentLoginBinding.inflate(getLayoutInflater());
        bind.navigateRegister.setOnClickListener(View->navigateToRegister());
        return bind.getRoot();
    }

    private void navigateToRegister() {
        FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.main_start_frame, new Register());
        trans.addToBackStack(null);
        trans.commit();
    }
}