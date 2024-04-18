package com.example.sqlimplementation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.sqlimplementation.databinding.FragmentTable2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Table2 : Fragment() {
    lateinit var binding: FragmentTable2Binding
    private lateinit var db: AppDataBase
    private lateinit var dao: Dao
    private lateinit var recyclerView: RecyclerView
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_table2, container, false)

//        val migration = object : Migration(1, 2) {
//            override fun migrate(db: SupportSQLiteDatabase) {
//                db.execSQL("ALTER TABLE Students RENAME TO OldStudents")
//                db.execSQL("CREATE TABLE Students (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, student_name TEXT NOT NULL, department_name TEXT NOT NULL)")
//                db.execSQL("INSERT INTO Students (id, student_name, department_name) SELECT id, name AS student_name, department_name AS department_name FROM OldStudents")
//                db.execSQL("DROP TABLE Students")
//            }
//        }

        db = Room.databaseBuilder(
            requireContext().applicationContext,
            AppDataBase::class.java,
            "Students"
        ).build()
        dao = db.dao()
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        binding.button.setOnClickListener {
            val data = Students(
                id = binding.studentId.text.toString().toInt(),
                studentName = binding.studentName.text.toString(),
                departmentName = binding.studentDepartment.text.toString()
            )
            CoroutineScope(Dispatchers.IO).launch {
                dao.insert(data)
                }

        }
        CoroutineScope(Dispatchers.IO).launch {
            val getData : LiveData<List<Students>> =  dao.getAll()
            withContext(Dispatchers.Main) {
                getData.observe(viewLifecycleOwner, Observer { students ->
                    if (students != null && students.isNotEmpty()) {
                        recyclerView.adapter = Adpater2(students)
                    }
                })
            }
        }



        return binding.root
    }
}