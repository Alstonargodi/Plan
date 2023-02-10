package com.example.wetterbericht.presentation.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.data.local.entities.dailytask.TodoSubTask
import com.example.wetterbericht.databinding.ItemcvTodoSubtaskBinding

class SubtaskRecyclerViewAdapter(
    private val subTask : MutableList<TodoSubTask>
) : RecyclerView.Adapter<SubtaskRecyclerViewAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemcvTodoSubtaskBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemcvTodoSubtaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = subTask[position]
        holder.binding.tvTitleSubtask.text= data.title
    }

    fun removeItem(position : Int){
        subTask.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = subTask.size

}