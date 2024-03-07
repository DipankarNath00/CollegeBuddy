package com.example.collegebuddy.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegebuddy.R;
import com.example.collegebuddy.adapters.subjectAdapter;
import com.example.collegebuddy.model.SubjectListModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


    public class SubjectsFragment extends Fragment {

        private RecyclerView recyclerView;
        private FloatingActionButton fabAddNewSubjects;
        private subjectAdapter adapter;
        private LinearLayoutManager linearLayoutManager;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_subjects, container, false);

            // Find views
            recyclerView = view.findViewById(R.id.recyclerViewSubjectList);
            fabAddNewSubjects = view.findViewById(R.id.fabAddNewSubjects);

            // Create and set layout manager
            linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);

            // Create an empty adapter with initial data
//            ArrayList<SubjectListModel> data = new ArrayList<>();
//            data.add(new SubjectListModel("Computer Science", "Semester 1", R.drawable.ic_book));
//            data.add(new SubjectListModel("Mathematics", "Semester 2", R.drawable.ic_book));
//            data.add(new SubjectListModel("Physics", "Semester 3", R.drawable.ic_book));
            adapter = new subjectAdapter(new ArrayList<>());
            fetchData();

            // Set the adapter
            recyclerView.setAdapter(adapter);

            // Set click listener for FAB
            fabAddNewSubjects.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle adding a new subject (navigate to another activity/fragment)
                }
            });

            return view;
        }

        private void fetchData() {
            // Replace with your actual remote data fetching logic (e.g., using libraries like Retrofit or Volley)
            // This is a placeholder example
            ArrayList<SubjectListModel> data = new ArrayList<>();
            data.add(new SubjectListModel("Computer Science", "Semester 1", R.drawable.ic_book));
            data.add(new SubjectListModel("Mathematics", "Semester 2", R.drawable.ic_book));
            data.add(new SubjectListModel("Physics", "Semester 3", R.drawable.ic_book));

            // Update the adapter with fetched data
            adapter.updateData(data);
        }
    }


