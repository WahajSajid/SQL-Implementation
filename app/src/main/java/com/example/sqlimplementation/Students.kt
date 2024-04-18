package com.example.sqlimplementation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Students(
    @PrimaryKey val id: Int,
    val studentName: String?,
    val departmentName: String
)
