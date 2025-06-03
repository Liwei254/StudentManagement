package com.example.studentmanagement;

public class StudentMarks {
    private String admissionNumber, name;
    private int math, english, kiswahili, science, social, ire;

    public StudentMarks(String admissionNumber, String name) {
        this.admissionNumber = admissionNumber;
        this.name = name;
    }

    public String getAdmissionNumber() { return admissionNumber; }
    public String getName() { return name; }

    public int getMath() { return math; }
    public void setMath(int math) { this.math = math; }

    public int getEnglish() { return english; }
    public void setEnglish(int english) { this.english = english; }

    public int getKiswahili() { return kiswahili; }
    public void setKiswahili(int kiswahili) { this.kiswahili = kiswahili; }

    public int getScience() { return science; }
    public void setScience(int science) { this.science = science; }

    public int getSocial() { return social; }
    public void setSocial(int social) { this.social = social; }

    public int getIre() { return ire; }
    public void setIre(int ire) { this.ire = ire; }

    public int getTotal() {
        return math + english + kiswahili + science + social + ire;
    }

    public String getGrade() {
        int avg = getTotal() / 6;
        if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }
}