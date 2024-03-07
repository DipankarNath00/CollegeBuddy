package com.example.collegebuddy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegebuddy.R;
import com.example.collegebuddy.ViewModel.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Spinner userType;
    private TextView tvRegister, tvPassword;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase App (if not already done)
        // Consider moving this to a central initialization point if applicable
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        // Find views by ID
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        userType = findViewById(R.id.spinnerUserType);
        tvRegister = findViewById(R.id.textViewRegister); // Assuming ID for register text
        tvPassword = findViewById(R.id.textViewForgotPassword); // Assuming ID for password text
        login = findViewById(R.id.buttonLogin);
        db = FirebaseFirestore.getInstance();

        // Handle login button click
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String role = userType.getSelectedItem().toString();
        //Storing the user information in viewmodel
//        UserInformation userInformation = new ViewModelProvider(this).get(UserInformation.class);
//        userInformation.setEmail(email);
//        userInformation.setRole(role);






        // Validate user input
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(role)) {
            Toast.makeText(this, "Please select your user type", Toast.LENGTH_SHORT).show();
            return;
        }

        // Attempt login with Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                //Store user information
                                SharedPreferences sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("email", email);
                                editor.putString("role",role);
                                editor.putString("password",password);
                                editor.putString("uid", user.getUid());
                                // Add other user information as needed
                                editor.apply();
                                Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                // Navigate to the appropriate activity based on user role or logic
                                // You might need to implement additional checks based on user role
                                // For example, navigate to different activities for different roles
                                // Intent intent = new Intent(LoginActivity.this, YourActivity.class);
                                // startActivity(intent);
                                if(role.equals("Student")){
//                                    Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
//                                    startActivity(intent);
                                } else if (role.equals("Teacher")) {
                                    Intent i = new Intent(LoginActivity.this, TeacherActivity.class);

                                    startActivity(i);

                                }
                                finish();

                            } else {
                                // Handle unexpected scenario where user object is null
                                Toast.makeText(LoginActivity.this, "An error occurred. Please try again", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Login failed, display error message
                            Toast.makeText(LoginActivity.this, "Login failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
