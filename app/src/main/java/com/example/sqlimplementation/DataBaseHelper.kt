package com.example.sqlimplementation

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID
import androidx.lifecycle.ViewModel

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DataBase_name, null, DataBase_Version ) {

    companion object{
        private const val DataBase_name :String = "Names"
        private const val DataBase_Version: Int  = 1
        private const val TABLE_NAME = "Names_Data"
        private const val COLUMN_NAME = "name"
        private const val ID = "id"
        private const val TABLE2_NAME = "Table_2"
        private const val COLUMN_DPT = "dpt"
        private const val COLUMN2_ID = "id"
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(db: SQLiteDatabase?) {
      val CREATE_TABlE = "CREATE TABLE $TABLE_NAME (" +
              "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
              "$COLUMN_NAME TEXT NOT NULL);"
        db?.execSQL("CREATE TABLE $TABLE2_NAME(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dpt TEXT NOT NULL )")
        db?.execSQL(CREATE_TABlE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE $TABLE_NAME;"
        db?.execSQL(DROP_TABLE)
    }
    fun insert(name: String,tableName: Int){
        val db = this.writableDatabase
        if(tableName == 1){
            val values = ContentValues()
            values.put(COLUMN_NAME,name)
            db.insert(TABLE_NAME,null, values)
        } else if(tableName == 2){
            val values =  ContentValues()
            values.put(COLUMN_DPT,name)
            db.insert(TABLE2_NAME,null,values)
        }
        db.close()
    }
    @SuppressLint("Recycle")
    fun fetchData(tableName: Int): List<String>{
        val database = readableDatabase
        val dataList = mutableListOf<String>()
        if(tableName ==1){
            val cursor :Cursor = database.rawQuery("SELECT $COLUMN_NAME FROM $TABLE_NAME",null)
            while(cursor.moveToNext()){
                val data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                dataList.add(data)
        }
        } else if(tableName ==2){
            val cursor :Cursor = database.rawQuery("SELECT $COLUMN_DPT FROM $TABLE2_NAME",null)
            while(cursor.moveToNext()){
                val data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DPT))
                dataList.add(data)
            }
        }
        database.close()
        return dataList
    }
    @SuppressLint("Recycle")
    fun fetchId(tableName: Int): List<Int>{
        val db = this.readableDatabase
        val datList = mutableListOf<Int>()
        if(tableName == 1){
            val cursor:Cursor = db.rawQuery("SELECT $ID FROM $TABLE_NAME",null)
            while(cursor.moveToNext()){
                val data = cursor.getString(cursor.getColumnIndexOrThrow(ID)).toInt()
                datList.add(data)
            }
        } else if(tableName == 2){
            val cursor:Cursor = db.rawQuery("SELECT $COLUMN2_ID FROM $TABLE2_NAME",null)
            while(cursor.moveToNext()){
                val data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN2_ID)).toInt()
                datList.add(data)
            }
        }

        db.close()
        return datList
    }
    @SuppressLint("Recycle")
    fun selectQuery(): String{
        val db = this.readableDatabase
        val cursor:Cursor = db.rawQuery("SELECT $COLUMN_NAME FROM $TABLE_NAME WHERE ID = 3",null)
        var name = ""
        while(cursor.moveToNext())
            {
                name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)).toString()
            }
        db.close()
        return name
    }

    fun delete(id: Int) {
        val db = writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME WHERE $ID = $id")
        db.close()
    }
    fun update(id: Int){
        val db = this.writableDatabase
        db.execSQL("UPDATE $TABLE_NAME SET $COLUMN_NAME = 'Adnan Butt' WHERE $ID = $id ")
        db.close()
    }
}