package com.example.wetterbericht.view.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.annotation.SuppressLint
import com.example.wetterbericht.databinding.ItemcvTodoBinding
import com.example.wetterbericht.model.local.entity.todolist.TodoLocal


class TodoRecyclerViewAdapter(private val data: List<TodoLocal>)
    : RecyclerView.Adapter<TodoRecyclerViewAdapter.ViewHolder>() {

    private lateinit var detailCallback : DetailCallback


    fun detailOnItemCallback(callback : DetailCallback){
        this.detailCallback = callback
    }

    class ViewHolder(var binding : ItemcvTodoBinding): RecyclerView.ViewHolder(binding.root){
        private lateinit var detailTodo : TodoLocal
        fun bind(item : TodoLocal){
            detailTodo = item
            binding.tvtoxoCardName.text = item.title
            binding.tvtoxoCardName.setTextColor(item.levelColor)
        }

        fun getData(): TodoLocal = detailTodo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemcvTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        holder.binding.layTodo.setOnClickListener {
            detailCallback.detailCallBack(item)
        }

    }

    override fun getItemCount(): Int = data.size

    interface DetailCallback{
        fun detailCallBack(data : TodoLocal)
    }
}