package com.example.collegebuddy.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collegebuddy.model.SubjectListModel;
import com.example.collegebuddy.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

public class SubjectsViewModel extends ViewModel {

    private SubjectRepository subjectRepository = new SubjectRepository();

    public LiveData<List<SubjectListModel>> subjectList ;

    private final MutableLiveData<Boolean> _subjectSaved = new MutableLiveData<>();
    public LiveData<Boolean> subjectSaved = _subjectSaved;
    public SubjectsViewModel(SubjectRepository repository) {
        subjectRepository = repository;
    }

    // Add an empty constructor
    public SubjectsViewModel() {
    }

    public SubjectsViewModel(Context context) {
        getSubjects(context);
    }

    public LiveData<List<SubjectListModel>> getSubjects(Context context) {
        subjectList=subjectRepository.getSubjects(context);
        return subjectList;
    }

    public void addNewSubject( String subjectName, String password, String semBranch) {
        subjectRepository.addSubject(subjectName,password,semBranch);
        _subjectSaved.setValue(true);
    }

    public void resetSubjectAddedStatus() {
        _subjectSaved.setValue(false);
    }

    public void editSubject(String subjectName, String password, String semBranch) {
        subjectRepository.editSubject( subjectName, password, semBranch);
    }

    public void deleteSubject(String subjectId) {
        subjectRepository.deleteSubject(subjectId);
    }
}
