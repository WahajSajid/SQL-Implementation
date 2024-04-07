package com.example.sqlimplementation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.temporal.TemporalAccessor

class Adapter(private val data: List<String>,private val id: List<Int>):RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textView)
        val idView = view.findViewById<TextView>(R.id.idText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.textView.text = data[position]
        holder.idView.text = id[position].toString()
    }

    override fun getItemCount(): Int = data.size
}