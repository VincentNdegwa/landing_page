package com.example.loginjava.Fragements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        bind.mainLoginButton.setOnClickListener(View->loginUser());
        return bind.getRoot();
    }

    private void loginUser() {
        if (bind.emailInput.getEditText().length()>0 && bind.passwordInput.getEditText().length()>0){
            Toast.makeText(getContext(),"Logged in", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(),"Please insert values", Toast.LENGTH_LONG).show();

        }
    }

    private void navigateToRegister() {
        FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.main_start_frame, new Register());
        trans.addToBackStack(null);
        trans.commit();
    }
}