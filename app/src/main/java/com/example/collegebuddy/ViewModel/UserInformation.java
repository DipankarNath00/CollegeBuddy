package com.example.collegebuddy.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserInformation extends ViewModel {
    private MutableLiveData<String> username;
    private MutableLiveData<String> role;
    private MutableLiveData<String> email;

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public void setUsername(String Username) {
         username.setValue(Username);
    }

    public MutableLiveData<String> getRole() {
        return role;
    }

    public void setRole(String userRole) {
        role.setValue(userRole);
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        email.setValue(Email);
    }
}
