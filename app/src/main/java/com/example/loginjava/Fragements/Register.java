package com.example.loginjava.Fragements;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginjava.R;
import com.example.loginjava.databinding.FragmentRegisterBinding;
import com.example.loginjava.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Register extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
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
        Toast.makeText(getContext(), "in register", Toast.LENGTH_SHORT);
        if (username.length() > 0 && email.length() > 0 && password1.length() > 0 && password2.length() > 0) {
            String strPassword1 = password1.getText().toString().trim();
            String strPassword2 = password2.getText().toString().trim();
            String strUsername = username.getText().toString().trim();
            String strEmail = email.getText().toString().trim();

            if (strPassword1.equals(strPassword2)) {
                User reg_user = new User(strUsername, strEmail, strPassword1);
                String userId = databaseReference.child("User").push().getKey();
                DatabaseReference userRef =  databaseReference.child("User").child(userId);
                userRef.setValue(reg_user).addOnSuccessListener(Void->{
                   userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if (snapshot.exists()){
                               User insertedUser = snapshot.getValue(User.class);
                               FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                               trans.replace(R.id.main_start_frame, Home.newInstance(insertedUser));
                               trans.commit();
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {
                           Toast.makeText(getContext(), "Error retrieving user,Please try to login", Toast.LENGTH_SHORT).show();
                       }
                   });
                }).addOnFailureListener(e->{
                    Toast.makeText(getContext(), "User registration failed: "+e.getMessage(), Toast.LENGTH_SHORT).show();

                });
                Toast.makeText(getContext(), "You can login", Toast.LENGTH_SHORT).show();
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