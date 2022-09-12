package com.example.firebaseconnectivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {
    TextView text;
    FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebase.getReference("userdata");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        text = findViewById(R.id.helloText);
        String username = getIntent().getExtras().getString("username");
        text.setText(username);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String firstName =snapshot.child(username).child("firstName").getValue(String.class);
                String secondName =snapshot.child(username).child("secondName").getValue(String.class);
                String display;
                display = "Hello "+firstName+" "+secondName;
                text.setText(display);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}