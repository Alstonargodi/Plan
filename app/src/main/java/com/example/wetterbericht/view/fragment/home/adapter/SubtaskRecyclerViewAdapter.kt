package com.example.wetterbericht.view.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.databinding.TvcTodoSubtaskBinding
import com.example.wetterbericht.model.local.entity.todolist.TodoSubTask

class SubtaskRecyclerViewAdapter: ListAdapter<TodoSubTask,SubtaskRecyclerViewAdapter.ViewHolder>(
    ItemComprator()
){

    class ViewHolder(val binding: TvcTodoSubtaskBinding) : RecyclerView.ViewHolder(binding.root) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TvcTodoSubtaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.tvTitleSubtask.text= data.title



    }



    class ItemComprator : DiffUtil.ItemCallback<TodoSubTask>() {
        override fun areItemsTheSame(oldItem: TodoSubTask, newItem: TodoSubTask): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: TodoSubTask, newItem: TodoSubTask): Boolean {
            return oldItem == newItem
        }
    }


}