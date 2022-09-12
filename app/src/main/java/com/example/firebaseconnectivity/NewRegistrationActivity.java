package com.example.firebaseconnectivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewRegistrationActivity extends AppCompatActivity {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    String passwordRegex = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";
    EditText email,firstName,lastName,password,conPassword,phoneNo,username;
    Button submit;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_registration);
        email = findViewById(R.id.Email_id);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        password = findViewById(R.id.password);
        conPassword = findViewById(R.id.confirm_password);
        submit = findViewById(R.id.submit_button);
        phoneNo = findViewById(R.id.phone);
        username = findViewById(R.id.username);
        submit.setOnClickListener(view -> {
            String firName = firstName.getText().toString();
            String secName = lastName.getText().toString();
            String emailStr = email.getText().toString();
            String passwordStr = password.getText().toString();
            String conPasswordStr = conPassword.getText().toString();
            String phoneStr = phoneNo.getText().toString();
            String usernameStr = username.getText().toString();
            if(!usernameStr.isEmpty()) {
                if(!usernameStr.contains(".")) {
                    if (!emailStr.isEmpty()) {
                        if (emailStr.matches(emailRegex)) {
                            if (!firName.isEmpty()) {
                                if (!secName.isEmpty()) {
                                    if (!phoneStr.isEmpty()) {
                                        if (phoneStr.length() == 10) {
                                            if (!passwordStr.isEmpty()) {
                                                if (passwordStr.matches(passwordRegex)) {
                                                    if (!conPasswordStr.isEmpty()) {
                                                        if (conPasswordStr.equals(passwordStr)) {
                                                            firebaseDatabase = FirebaseDatabase.getInstance();
                                                            DatabaseReference reference = firebaseDatabase.getReference("userdata");
                                                            storingData storingData = new storingData(usernameStr,emailStr, firName, secName, phoneStr, passwordStr);
                                                            reference.child(usernameStr).setValue(storingData);
                                                            Toast.makeText(getApplicationContext(), emailStr + "Successfully registered", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(NewRegistrationActivity.this,Dashboard.class);
                                                            intent.putExtra("username",usernameStr);
                                                            startActivity(intent);
                                                            finish();
                                                        } else {
                                                            conPassword.setError("Doesn't matches with password");
                                                        }
                                                    } else {
                                                        conPassword.setError("Required field");
                                                    }
                                                } else {
                                                    password.setError("Set strong password");
                                                }
                                            } else {
                                                password.setError("Required field");
                                            }
                                        } else {
                                            phoneNo.setError("Invalid number");
                                        }
                                    } else {
                                        phoneNo.setError("Required field");
                                    }
                                } else {
                                    lastName.setError("Required field");
                                }
                            } else {
                                firstName.setError("Required field");
                            }
                        } else {
                            email.setError("Invalid email");
                        }
                    } else {
                        email.setError("Required field");
                    }
                }else{
                    username.setError("Should not contain .");
                }
            }else{
                username.setError("Required field");
            }
        });

    }

}