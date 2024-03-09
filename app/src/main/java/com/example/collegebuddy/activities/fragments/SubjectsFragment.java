package com.example.collegebuddy.activities.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegebuddy.R;
import com.example.collegebuddy.activities.AddNewSubject;
import com.example.collegebuddy.adapters.subjectAdapter;
import com.example.collegebuddy.adapters.subjectAdapter;
import com.example.collegebuddy.model.SubjectListModel;
import com.example.collegebuddy.viewModel.SubjectsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;
import java.util.ArrayList;

public class SubjectsFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAddNewSubjects;
    private subjectAdapter adapter;
    private ProgressBar subjectProgressBar;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseAuth auth;
    private SubjectsViewModel subjectsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);

        // Find views
        recyclerView = view.findViewById(R.id.recyclerViewSubjectList);
        fabAddNewSubjects = view.findViewById(R.id.fabAddNewSubjects);
        subjectProgressBar = view.findViewById(R.id.progressBarSubject);

        // Create and set layout manager
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // Create an empty adapter with initial data
        adapter = new subjectAdapter(new ArrayList<>());

        // Set the adapter
        recyclerView.setAdapter(adapter);

        // Set click listener for FAB
        fabAddNewSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle adding a new subject (navigate to another activity/fragment)
                Intent intent = new Intent(view.getContext(), AddNewSubject.class);
                startActivity(intent);
            }
        });
        subjectsViewModel = new ViewModelProvider(this).get(SubjectsViewModel.class);
        //Fetch data when a user mavigates to this page
        LiveData<List<SubjectListModel>> subjects=subjectsViewModel.getSubjects(requireContext());
        adapter.updateData(subjects.getValue());

        //
        subjectsViewModel.getSubjects(requireContext()).observe(getViewLifecycleOwner(), new Observer<List<SubjectListModel>>() {
            @Override
            public void onChanged(List<SubjectListModel> subjects) {
                if (subjects!=null){
                    adapter.updateData(subjects);
                }else {
                    // Handle case when no subjects are available
                    Toast.makeText(requireContext(), "No subjects available", Toast.LENGTH_SHORT).show();
                }

            }
        });







        return view;
    }


}
