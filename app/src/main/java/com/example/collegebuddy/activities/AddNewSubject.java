package com.example.collegebuddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.collegebuddy.R;
import com.example.collegebuddy.viewModel.SubjectsViewModel;
import com.example.collegebuddy.util.MySharedPreferencesUtil;
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
        etPassword=findViewById(R.id.editTextPassword);
        btnSave= findViewById(R.id.buttonSaveSubject);
        btnClosePage=findViewById(R.id.btnClosePage);
        btnClosePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewSubject.this, TeacherActivity.class);
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjectName = etSubjectName.getText().toString().trim();
                String branch=etBranch.getText().toString().trim();
                String sem= etSem.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String branchSem = branch+"|"+sem+"th";
                String teacherId = MySharedPreferencesUtil.getUid(AddNewSubject.this);
                String email = MySharedPreferencesUtil.getEmail(AddNewSubject.this);

                SubjectsViewModel subjectsViewModel= new ViewModelProvider(AddNewSubject.this).get(SubjectsViewModel.class);
                subjectsViewModel.addNewSubject(email+subjectName,subjectName,password,branchSem,teacherId);
                startActivity(new Intent(AddNewSubject.this, TeacherActivity.class));
                finish();


            }
        });
    }
}