package com.example.collegebuddy.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;
//onDelete = CASCADE
@Entity(tableName = "attendanceTable",foreignKeys = {
        @ForeignKey(entity = Student.class ,parentColumns = "studentID",childColumns ="studentId"),
        @ForeignKey(entity = Subject.class, parentColumns = "subjectId", childColumns = "subjectId")
})
public class Attendance {

    @PrimaryKey(autoGenerate = true)
    private int attendanceId;
    private int studentId;
    @ColumnInfo(name = "subjectId")
    private int subjectId;
    @ColumnInfo(name = "attendanceDate")
    private Date attendanceDate;

    private int present;
    private int absent;

}
