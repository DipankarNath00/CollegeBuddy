package com.example.collegebuddy.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.collegebuddy.model.Attendance;
import com.example.collegebuddy.model.Student;

@Dao
public interface AttendanceRecordDao {
  @Insert
  void insert(Attendance attendance);
  @Update
    void  update(Attendance attendance);
  @Delete
    void  delete(Attendance attendance);
//  @Query()
//    LiveData<Li>

}
