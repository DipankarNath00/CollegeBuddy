package com.example.collegebuddy.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.collegebuddy.model.SubjectListModel;

import java.util.ArrayList;

public class SubjectRepository {
    private MutableLiveData<ArrayList<SubjectListModel>> subjectList;

    public LiveData<ArrayList<SubjectListModel>> getSubjects() {
        return subjectList;
    }
}
