package com.example.firebaseconnectivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {
    TextView signUp;
    EditText username,password;
    Button login;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        signUp = findViewById(R.id.register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        progress = findViewById(R.id.loading);
        signUp.setOnClickListener(view ->
                startActivity(new Intent(loginActivity.this,NewRegistrationActivity.class)));
        login.setOnClickListener(view -> {
            if(username.getText().length()==0){
                username.setError("Username is empty");
                return;
            }
            if(password.getText().length()==0){
                password.setError("Password is empty");

                return;
            }
            final String inputUserName = username.getText().toString();
            final String inputPassword = password.getText().toString();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("userdata");
            Query checkUser = databaseReference.orderByChild("username").equalTo(inputUserName);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        String passwordCheck = dataSnapshot.child(inputUserName).child("password").getValue(String.class);
                        if(passwordCheck!=null && passwordCheck.equals(inputPassword)){
                            password.setError(null);
                            progress.setVisibility(View.VISIBLE);

                            Toast.makeText(loginActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(loginActivity.this,Dashboard.class);
                            intent.putExtra("username",inputUserName);
                            startActivity(intent);
                            finish();
                        }else{
                            password.setError("Wrong Password");
                        }

                    }
                    else{
                        username.setError("User name doesn't exist");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        });
    }
}