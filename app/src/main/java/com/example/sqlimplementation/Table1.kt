package com.example.sqlimplementation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlimplementation.databinding.FragmentTable1Binding

class Table1 : Fragment() {
    lateinit var binding: FragmentTable1Binding
    lateinit var database: DataBaseHelper
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_table1, container, false)
        database = DataBaseHelper(requireContext())
        binding.button.setOnClickListener {
            val text = binding.editTextText.text.toString()
            database.insert(text,1)
        }
        val data : List<String> = database.fetchData(1)
        val id : List<Int> = database.fetchId(1)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = Adapter(data,id)
        binding.textView.text = database.selectQuery()
        val  deleteId = 3
        database.delete(deleteId)
            return binding.root
    }

}