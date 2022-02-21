package com.example.secondhomework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainViewPagerAdapter(var tasks: MutableList<MutableList<String>>) :
    RecyclerView.Adapter<MainViewPagerAdapter.MainViewPagerViewHolder>() {
    inner class MainViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewPagerViewHolder {
//        val taskAdapter = TaskAdapter(listOfTasks)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_view_pager, parent, false)
        return MainViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewPagerViewHolder, position: Int) {
        val currentTasks = tasks[position]
        holder.itemView.apply {
            val tasksAdapter = TaskAdapter(currentTasks)
            val rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
            rvTasks.adapter = tasksAdapter
            rvTasks.layoutManager = LinearLayoutManager(this.context)

            val btnAdd = findViewById<ImageButton>(R.id.btnAddTask)
            btnAdd.setOnClickListener {
                val etText = findViewById<TextInputEditText>(R.id.etTask)
                val newTask = etText.text.toString()
                currentTasks.add(newTask)
                tasksAdapter.notifyItemInserted(currentTasks.size-1)
                etText.text?.clear()
            }
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }


}