package com.example.studentmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private Button cancel, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        username = findViewById(R.id.firstname);
        password = findViewById(R.id.password);
        cancel = findViewById(R.id.cancel_button);
        login = findViewById(R.id.login);

        // Cancel button clears input fields
        cancel.setOnClickListener(v -> {
            username.setText("");
            password.setText("");
        });

        // Login button triggers Firebase authentication
        login.setOnClickListener(v -> {
            String strname = username.getText().toString().trim();
            String strpass = password.getText().toString().trim();

            // Validate input fields
            if (strname.isEmpty() || strpass.isEmpty()) {
                if (strname.isEmpty()) username.setError("Username is required");
                if (strpass.isEmpty()) password.setError("Password is required");
                Toast.makeText(MainActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            connfirebase(strname, strpass);
        });
    }

    private void connfirebase(String strname, String strpass) {
        // Ensure Firebase is initialized
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("Users");

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // Check if activity is still valid
                    if (isFinishing()) return;

                    boolean found = false;
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        try {
                            Bean2 user = snap.getValue(Bean2.class);
                            // Check for null user or fields
                            if (user != null &&
                                    user.getFirstname() != null &&
                                    user.getPassword() != null &&
                                    user.getFirstname().equals(strname) &&
                                    user.getPassword().equals(strpass)) {
                                found = true;
                                break;
                            }
                        } catch (Exception e) {
                            Log.e("FirebaseError", "Error parsing user data: " + e.getMessage(), e);
                        }
                    }

                    if (found) {
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish(); // Prevent going back to login screen
                    } else {
                        username.setError("Invalid username or password");
                        password.setError("Invalid username or password");
                        Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Check if activity is still valid
                    if (isFinishing()) return;

                    Log.e("FirebaseError", "Database error: " + error.getMessage(), error.toException());
                    Toast.makeText(MainActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.e("FirebaseError", "Firebase initialization failed: " + e.getMessage(), e);
            Toast.makeText(MainActivity.this, "Failed to connect to database", Toast.LENGTH_LONG).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, registration.class);
        startActivity(intent);
    }
}