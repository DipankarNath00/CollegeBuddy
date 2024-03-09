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
    private FirebaseFirestore firestore;
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
        // Initialize Firebase components
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();



        // Retrieve the user's document ID
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            // Retrieve user document
            firestore.collection("users")
                    .document(userId)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null && document.exists()) {
                                // Extract subject IDs from the user document
                                List<String> subjectIds = (List<String>) document.get("subjects");

                                // Fetch subjects from the subjects collection
                                if (subjectIds != null && !subjectIds.isEmpty()) {
                                    fetchSubjects(subjectIds);
                                } else {
                                    // Handle the case when the user has no subjects
                                    Toast.makeText(requireContext(), "User has no subjects", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Handle the case when the user document doesn't exist
                                Toast.makeText(requireContext(), "User document not found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Handle errors while retrieving the user document
                            Toast.makeText(requireContext(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }


        return view;
    }

    private void fetchSubjects(List<String> subjectIds) {
        // Fetch subjects from the subjects collection
        subjectsViewModel.subjectList.observe(getViewLifecycleOwner(), subjects -> {
            if (subjects != null) {
                // Update your UI with the list of subjects
                adapter.setSubjects(subjects);
            } else {
                // Handle the case when subjects are null
                Toast.makeText(requireContext(), "Error fetching subjects", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
