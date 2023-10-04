package com.example.loginjava.Fragements;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginjava.Connection.MysqlConnection;
import com.example.loginjava.R;
import com.example.loginjava.databinding.FragmentLoginBinding;
import com.example.loginjava.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;


public class Login extends Fragment {

    FirebaseAuth auth = FirebaseAuth.getInstance();
private FragmentLoginBinding bind;

    public Login() {
    }

    public static Login newInstance(String param1, String param2) {
        return null;
    }
//    public  static Connection connection;

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
//        connection = MysqlConnection.getConnection();
        if (bind.emailInput.getEditText().length()>0 && bind.passwordInput.getEditText().length()>0){

            String strEmail = bind.emailInput.getEditText().getText().toString().trim();
            String strPassword = bind.passwordInput.getEditText().getText().toString().trim();

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("User");
            Query query = userRef.orderByChild("email").equalTo(strEmail);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        for (DataSnapshot dataSnap : snapshot.getChildren()){
                            User user = dataSnap.getValue(User.class);
                            if (user != null && user.getPassword().equals(strPassword)){
                                FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                                Toast.makeText(getContext(), "You are logged in", Toast.LENGTH_SHORT).show();
                                trans.replace(R.id.main_start_frame, Home.newInstance(user));
                                trans.commit();
                                return;
                            }else {
                                Toast.makeText(getContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else {
                        Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


//            Toast.makeText(getContext(),"Logged in", Toast.LENGTH_LONG).show();
        }else {
            if (bind.emailInput.getEditText().length()<= 0){
                bind.emailInput.setError("Please provide email");
            }
            if (bind.passwordInput.getEditText().length()<= 0){
                bind.emailInput.setError("Password cannot be empty");
            }
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