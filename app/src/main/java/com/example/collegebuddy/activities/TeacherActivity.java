package com.example.collegebuddy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.collegebuddy.R;
import com.example.collegebuddy.activities.fragments.SubjectsFragment;
import com.google.android.material.navigation.NavigationView;

public class TeacherActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    NavigationView teacherNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        drawerLayout = findViewById(R.id.teacherDrawer);
        buttonDrawerToggle=findViewById(R.id.buttonDrawerToggle);
        teacherNavigationView=findViewById(R.id.teacherNavigationView);
        if (savedInstanceState == null) { // Only load if not restored from a previous state
            Fragment fragment = new SubjectsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
        buttonDrawerToggle.setOnClickListener(view -> drawerLayout.open());
        teacherNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemid = item.getItemId();
                Fragment fragment = null;
                if (itemid==R.id.teacherResources) {
                    //Resource page should open
                } else if (itemid==R.id.teacher_settings) {
                    //The settings page will open

                } else if (itemid==R.id.teacherLogout) {
                    //The user should logout of the app and return to the login page
                }else{
                    //The home page should open

                    fragment = new SubjectsFragment();
                }
                if (fragment!=null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment_container,fragment);
                    transaction.commit();
                    item.setChecked(true);
                }
                drawerLayout.close();
                return true ;
            }
        });
    }
}