package com.example.sqlimplementation

import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    var id : MutableList<Int> = mutableListOf()
    fun addItem(item: List<Int>){
        id = item.toMutableList()
    }

}