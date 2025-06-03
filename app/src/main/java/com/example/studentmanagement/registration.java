package com.example.studentmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {
    EditText firstname,lastname,email,password;

    String strfname,strlname,stremail,strpass;
    Bean2 bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);


    }

    public void submit(View view) {
        strfname=firstname.getText().toString();
        strlname=lastname.getText().toString();
        stremail=email.getText().toString();
        strpass=password.getText().toString();

        firstname.setText("");
        lastname.setText("");
        email.setText("");
        password.setText("");
        bean =new Bean2(strfname,strlname,stremail,strpass);
        connectingToFirebase(bean);

        Intent intent =new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void connectingToFirebase(Bean2 bean) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");
        String userId=myRef.push().getKey();
        myRef.child(userId).setValue(bean).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                // Optionally navigate to login or home screen here
            } else {
                Toast.makeText(getApplicationContext(), "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loginpage(View view) {
        Intent intent =new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void cancel(View view) {
        firstname.setText("");
        lastname.setText("");
        email.setText("");
        password.setText("");
    }
}