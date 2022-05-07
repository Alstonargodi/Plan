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
import com.example.wetterbericht.model.local.TodoSubTask
import com.example.wetterbericht.model.local.TodoandSubTask
import com.example.wetterbericht.view.insert.adapter.ChipAdapter
import kotlinx.android.synthetic.main.tcv_todo_card.view.*

class SubTaskAdapter: ListAdapter<TodoandSubTask,SubTaskAdapter.ViewHolder>(
    ItemComprator()
){

    class ViewHolder(private val binding: TcvTodoCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TodoandSubTask) {
            data.subtask.forEach {
                binding.tvtoxoCardName.text = it.title
                Log.d("adaptersub",it.title)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TcvTodoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    class ItemComprator : DiffUtil.ItemCallback<TodoandSubTask>() {
        override fun areItemsTheSame(oldItem: TodoandSubTask, newItem: TodoandSubTask): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TodoandSubTask, newItem: TodoandSubTask): Boolean {
            return oldItem.todo.title == newItem.todo.description
        }
    }


}