package com.example.studentmanagement;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MarksAdapter extends RecyclerView.Adapter<MarksAdapter.ViewHolder> {
    private final List<StudentMarks> students;

    public MarksAdapter(ArrayList<StudentMarks> students) {
        this.students = students;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView admissionNumber, name, total, grade;
        EditText math, english, kiswahili, science, social, ire;

        public ViewHolder(View itemView) {
            super(itemView);
            admissionNumber = itemView.findViewById(R.id.admission_number);
            name = itemView.findViewById(R.id.student_name);
            math = itemView.findViewById(R.id.math_mark);
            english = itemView.findViewById(R.id.english_mark);
            kiswahili = itemView.findViewById(R.id.kiswahili_mark);
            science = itemView.findViewById(R.id.science_mark);
            social = itemView.findViewById(R.id.social_mark);
            ire = itemView.findViewById(R.id.ire_mark);
            total = itemView.findViewById(R.id.total_mark);
            grade = itemView.findViewById(R.id.grade_mark);
        }
    }

    @Override
    public MarksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_marks, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MarksAdapter.ViewHolder holder, int position) {
        StudentMarks student = students.get(position);

        holder.admissionNumber.setText(student.getAdmissionNumber());
        holder.name.setText(student.getName());

        // Set marks (show empty if 0)
        holder.math.setText(student.getMath() == 0 ? "" : String.valueOf(student.getMath()));
        holder.english.setText(student.getEnglish() == 0 ? "" : String.valueOf(student.getEnglish()));
        holder.kiswahili.setText(student.getKiswahili() == 0 ? "" : String.valueOf(student.getKiswahili()));
        holder.science.setText(student.getScience() == 0 ? "" : String.valueOf(student.getScience()));
        holder.social.setText(student.getSocial() == 0 ? "" : String.valueOf(student.getSocial()));
        holder.ire.setText(student.getIre() == 0 ? "" : String.valueOf(student.getIre()));

        holder.total.setText(String.valueOf(student.getTotal()));
        holder.grade.setText(student.getGrade());

        // Helper to update student marks and UI after text changes
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                int math = parseMark(holder.math.getText().toString());
                int english = parseMark(holder.english.getText().toString());
                int kiswahili = parseMark(holder.kiswahili.getText().toString());
                int science = parseMark(holder.science.getText().toString());
                int social = parseMark(holder.social.getText().toString());
                int ire = parseMark(holder.ire.getText().toString());

                student.setMath(math);
                student.setEnglish(english);
                student.setKiswahili(kiswahili);
                student.setScience(science);
                student.setSocial(social);
                student.setIre(ire);

                holder.total.setText(String.valueOf(student.getTotal()));
                holder.grade.setText(student.getGrade());
            }

            private int parseMark(String text) {
                try {
                    return Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        };

        // Remove existing watchers first (to avoid multiple triggers)
        if (holder.math.getTag() instanceof TextWatcher)
            holder.math.removeTextChangedListener((TextWatcher) holder.math.getTag());
        if (holder.english.getTag() instanceof TextWatcher)
            holder.english.removeTextChangedListener((TextWatcher) holder.english.getTag());
        if (holder.kiswahili.getTag() instanceof TextWatcher)
            holder.kiswahili.removeTextChangedListener((TextWatcher) holder.kiswahili.getTag());
        if (holder.science.getTag() instanceof TextWatcher)
            holder.science.removeTextChangedListener((TextWatcher) holder.science.getTag());
        if (holder.social.getTag() instanceof TextWatcher)
            holder.social.removeTextChangedListener((TextWatcher) holder.social.getTag());
        if (holder.ire.getTag() instanceof TextWatcher)
            holder.ire.removeTextChangedListener((TextWatcher) holder.ire.getTag());

        // Attach watcher
        holder.math.addTextChangedListener(watcher);
        holder.math.setTag(watcher);
        holder.english.addTextChangedListener(watcher);
        holder.english.setTag(watcher);
        holder.kiswahili.addTextChangedListener(watcher);
        holder.kiswahili.setTag(watcher);
        holder.science.addTextChangedListener(watcher);
        holder.science.setTag(watcher);
        holder.social.addTextChangedListener(watcher);
        holder.social.setTag(watcher);
        holder.ire.addTextChangedListener(watcher);
        holder.ire.setTag(watcher);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}