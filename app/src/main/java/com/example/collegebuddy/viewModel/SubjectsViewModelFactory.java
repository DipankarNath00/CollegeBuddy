package com.example.collegebuddy.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.collegebuddy.repository.SubjectRepository;
import com.example.collegebuddy.viewModel.SubjectsViewModel;

public class SubjectsViewModelFactory implements ViewModelProvider.Factory {

    private final SubjectRepository repository;

    public SubjectsViewModelFactory(SubjectRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SubjectsViewModel.class)) {
            return (T) new SubjectsViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}