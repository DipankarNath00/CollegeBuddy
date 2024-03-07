package com.example.collegebuddy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils; // Import for TextUtils.isEmpty()
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.collegebuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore; // Import for Firestore

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Spinner spinnerUserType;
    private Button buttonRegister;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db; // Declare Firestore instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase App (if not already done in another activity)
        // Consider moving this to a central initialization point if applicable
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance(); // Initialize Firestore instance

        // Find view elements by ID
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        spinnerUserType = findViewById(R.id.spinnerUserType);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Handle register button click
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String userType = spinnerUserType.getSelectedItem().toString();

        // Validate user input using TextUtils.isEmpty() for better readability
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(userType)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Confirm your password", Toast.LENGTH_SHORT).show();
            return;
        } else if (!confirmPassword.equals(password)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign-in successful, store user data
                            FirebaseUser user = mAuth.getCurrentUser();
                            SharedPreferences sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("userName",username);

                            editor.putString("email", email);
                            editor.putString("role",userType);
                            editor.putString("password",password);
                            editor.putString("uid", user.getUid());
                           // Add other user information as needed
                            editor.apply();
                            storeUserData(user, username, userType);
                            Toast.makeText(Register.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, LoginActivity.class));
                            finish();
                        } else {
                            // Sign-in failed, display a message to the user
                            Toast.makeText(Register.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void storeUserData(FirebaseUser user,String username,String role){
        //Create a new document in user collection in firestore
        Map<String ,Object> userData = new HashMap<>();
        userData.put("username",username);
        userData.put("userId",user.getUid());
        userData.put("email",user.getEmail());
        userData.put("role",role);

        db.collection("Users").document(user.getUid()).set(userData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // User data stored successfully
                            Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            // Navigate to the appropriate dashboard based on role
                            if (role.equals("Teacher")){
                                Intent intent = new Intent(Register.this, TeacherActivity.class);
                                startActivity(intent);
                                finish();
                            }else if(role.equals("Student")){
                                //Navigate to student activity
                            }
                        } else {
                            // Error handling
                            Exception e = task.getException();
                            Toast.makeText(Register.this, "Error saving user data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
