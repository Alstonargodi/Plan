package com.example.wetterbericht.view.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.TcvTodoCardBinding
import com.example.wetterbericht.databinding.TvcTodoSubtaskBinding
import com.example.wetterbericht.model.local.TodoSubTask
import com.example.wetterbericht.model.local.TodoandSubTask
import com.example.wetterbericht.view.insert.adapter.ChipAdapter
import kotlinx.android.synthetic.main.tcv_todo_card.view.*

class SubTaskAdapter: ListAdapter<TodoSubTask,SubTaskAdapter.ViewHolder>(
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