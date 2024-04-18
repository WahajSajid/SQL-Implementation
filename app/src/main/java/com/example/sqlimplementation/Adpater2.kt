package com.example.sqlimplementation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

@Suppress("CAST_NEVER_SUCCEEDS")
class Adpater2(
    private val student: List<Students>) : RecyclerView.Adapter<Adpater2.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id = view.findViewById<TextView>(R.id.id)
        val name = view.findViewById<TextView>(R.id.name)
        val departmentName = view.findViewById<TextView>(R.id.department)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adpater2.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.room_items, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: Adpater2.ViewHolder, position: Int) {
        holder.id.text = student[position].id.toString()
        holder.name.text = student[position].studentName
        holder.departmentName.text = student[position].departmentName
    }

    override fun getItemCount(): Int = student.size
}