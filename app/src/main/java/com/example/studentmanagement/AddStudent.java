package com.example.studentmanagement;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudent extends AppCompatActivity {
    EditText admissionNumber, name, math, english, kiswahili, science, social, ire;
    Button submit;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        admissionNumber = findViewById(R.id.admission_number);
        name = findViewById(R.id.student_name);
        math = findViewById(R.id.math_mark);
        english = findViewById(R.id.english_mark);
        kiswahili = findViewById(R.id.kiswahili_mark);
        science = findViewById(R.id.chemistry_mark);
        social = findViewById(R.id.Physics_mark);
        ire = findViewById(R.id.Biology_mark);
        submit = findViewById(R.id.submit_button);

        db = FirebaseDatabase.getInstance().getReference("Students");

        submit.setOnClickListener(v -> {
            String adm = admissionNumber.getText().toString();
            String stdName = name.getText().toString();

            int m = Integer.parseInt(math.getText().toString());
            int e = Integer.parseInt(english.getText().toString());
            int k = Integer.parseInt(kiswahili.getText().toString());
            int s = Integer.parseInt(science.getText().toString());
            int soc = Integer.parseInt(social.getText().toString());
            int i = Integer.parseInt(ire.getText().toString());

            Student student = new Student(adm, stdName, m, e, k, s, soc, i);
            db.child(adm).setValue(student)
                    .addOnSuccessListener(unused -> Toast.makeText(this, "Student added!", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(error -> Toast.makeText(this, "Failed: " + error.getMessage(), Toast.LENGTH_LONG).show());
        });
    }
}