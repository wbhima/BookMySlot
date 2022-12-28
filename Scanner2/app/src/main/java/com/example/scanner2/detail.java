package com.example.scanner2;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class detail extends AppCompatActivity {
    TextView name,email,phone,city,startup,reg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        city=findViewById(R.id.city);
        Intent i = getIntent();
        userId = i.getStringExtra("Your_KEY");
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();


        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot> (){
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                assert documentSnapshot != null;
                city.setText(documentSnapshot.getString("eventname"));
                email.setText(documentSnapshot.getString("email"));
              name.setText(documentSnapshot.getString("name"));
                phone.setText(documentSnapshot.getString("phone"));
               // Toast.makeText(detail.this,
                //       documentSnapshot.getString("eventname"), Toast.LENGTH_LONG).show();
            }
        });

    }
}