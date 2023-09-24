package com.example.loginjava.Fragements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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
        bind.mainRegisterButton.setOnClickListener(View->registerUser());

        return bind.getRoot();
    }

    private void registerUser() {
        EditText username = bind.usernameReg.getEditText();
        EditText email = bind.emailInputReg.getEditText();
        EditText password1 = bind.regPass.getEditText();
        EditText password2 = bind.regPass2.getEditText();

        if (username.length() > 0 && email.length() > 0 && password1.length() > 0 && password2.length() > 0) {
            String strPassword1 = password1.getText().toString().trim();
            String strPassword2 = password2.getText().toString().trim();

            if (strPassword1.equals(strPassword2)) {
                Toast.makeText(getContext(), "Registered as " + username.getText(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Password did not match", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Please insert values", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToLogin(){
        FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.main_start_frame, new Login());
        trans.addToBackStack(null);
        trans.commit();
    }

}