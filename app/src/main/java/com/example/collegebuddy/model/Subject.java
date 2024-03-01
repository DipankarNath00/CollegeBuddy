package com.example.collegebuddy.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subjectTable")
public class Subject {
    @PrimaryKey
    private  int subjectId;
    private String subjectName;


}
