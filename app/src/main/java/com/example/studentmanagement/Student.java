
package com.example.studentmanagement;

public class Student {
    public String admissionNumber, name;
    public int math, english, kiswahili, science, social, ire, total;
    public String grade;

    public Student() {} // Required by Firebase

    public Student(String admissionNumber, String name, int math, int english, int kiswahili, int science, int social, int ire) {
        this.admissionNumber = admissionNumber;
        this.name = name;
        this.math = math;
        this.english = english;
        this.kiswahili = kiswahili;
        this.science = science;
        this.social = social;
        this.ire = ire;
        this.total = math + english + kiswahili + science + social + ire;
        this.grade = calculateGrade(this.total);
    }

    private String calculateGrade(int total) {
        if (total >= 400) return "A";
        else if (total >= 350) return "B";
        else if (total >= 300) return "C";
        else if (total >= 250) return "D";
        else return "E";
    }
}
