package com.example.collegebuddy.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.collegebuddy.model.SubjectListModel;
import com.example.collegebuddy.repository.SubjectRepository;

import java.util.ArrayList;

public class SubjectsViewModel extends ViewModel {
    private SubjectRepository subjectRepository;
    private LiveData<ArrayList<SubjectListModel>> subjectList;
    public SubjectsViewModel(){
        subjectRepository = new SubjectRepository();
        subjectList= subjectRepository.getSubjects();
    }
    public  LiveData<ArrayList<SubjectListModel>> getSubjectList(){
        return subjectList;
    }
}
