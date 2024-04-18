package com.example.sqlimplementation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Students::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun dao(): Dao

}