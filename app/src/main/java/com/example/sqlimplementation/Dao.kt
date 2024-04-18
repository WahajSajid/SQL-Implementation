package com.example.sqlimplementation

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM students")
    fun getAll(): LiveData<List<Students>>

    @Insert
    fun insert(vararg students: Students)

    @Query("DELETE FROM students WHERE id = :Id")
    fun delete(Id: Int)

    @Query("UPDATE students SET departmentName= :attributeData WHERE id = :Id")
    fun updateDepartmentName(attributeData: String, Id: Int)

    @Query("UPDATE students SET studentName= :attributeData WHERE id = :Id")
    fun updateStudentName(attributeData: String,Id: Int)


}