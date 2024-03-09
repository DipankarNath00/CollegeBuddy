package com.example.collegebuddy.model;

import android.widget.TextView;

public class SubjectListModel {
    String subjectName,branchSem,subjectId;
    int image;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public SubjectListModel(String subjectId,String subjectName, String branchSem, int image) {
        this.subjectId=subjectId;
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
