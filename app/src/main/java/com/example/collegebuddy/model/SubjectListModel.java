package com.example.collegebuddy.model;

import android.widget.TextView;

public class SubjectListModel {
    String subjectName,branchSem;
    int image;

    public SubjectListModel(String subjectName, String branchSem, int image) {
        this.subjectName = subjectName;
        this.branchSem = branchSem;
        this.image = image;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getBranchSem() {
        return branchSem;
    }

    public void setBranchSem(String branchSem) {
        this.branchSem = branchSem;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
