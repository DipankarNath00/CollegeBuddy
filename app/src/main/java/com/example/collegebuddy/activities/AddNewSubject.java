package com.example.collegebuddy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.collegebuddy.R;

public class AddNewSubject extends AppCompatActivity {
    EditText etSubjectName,etBranch, etSem,etPassword;
    Button btnSave,btnClosePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_subject);
        etSubjectName = findViewById(R.id.editTextSubjectName);
        etBranch=findViewById(R.id.editTextBranch);
        etSem= findViewById(R.id.editTextSemester);
        btnSave= findViewById(R.id.buttonSaveSubject);
        btnClosePage=findViewById(R.id.btnClosePage);
        btnClosePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewSubject.this, TeacherActivity.class);
                finish();
            }
        });
    }
}