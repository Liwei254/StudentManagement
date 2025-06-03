package com.example.studentmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MarksAdapter adapter;
    Button add_student_button;
    ArrayList<StudentMarks> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        add_student_button=findViewById(R.id.add_student_button);
        add_student_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AddStudent.class);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.marks_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        students = new ArrayList<>();
        students.add(new StudentMarks("ADM001", "Joseph Makau"));
        students.add(new StudentMarks("ADM002", "Dwayne Lemon"));
        students.add(new StudentMarks("ADM003", "Willvie Nyamweya"));
        adapter = new MarksAdapter(students);
        recyclerView.setAdapter(adapter);
    }

}