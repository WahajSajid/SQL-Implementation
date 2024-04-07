package com.example.sqlimplementation

import android.os.Bundle
import android.util.Size
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlimplementation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
   lateinit var database: DataBaseHelper
   lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        database = DataBaseHelper(this)
        binding.button.setOnClickListener {
            val text = binding.editTextText.text.toString()
            database.insert(text)
        }
        val data : List<String> = database.fetchData()
        val id : List<Int> = database.fetchId()
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(data,id)
        binding.textView.text = database.selectQuery()
//        val  deleteId = 3
//        database.delete(deleteId)
        val updateId = 4
        database.update(updateId)
    }
}