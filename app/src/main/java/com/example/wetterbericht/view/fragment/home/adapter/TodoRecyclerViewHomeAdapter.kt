package com.example.wetterbericht.view.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.annotation.SuppressLint
import com.example.wetterbericht.databinding.TcvTodoCardBinding
import com.example.wetterbericht.model.local.TodoLocal


class TodoRecyclerViewHomeAdapter(private val data: List<TodoLocal>)
    : RecyclerView.Adapter<TodoRecyclerViewHomeAdapter.ViewHolder>() {

    private lateinit var detailCallback : DetailCallback


    fun detailOnItemCallback(callback : DetailCallback){
        this.detailCallback = callback
    }

    class ViewHolder(var binding : TcvTodoCardBinding): RecyclerView.ViewHolder(binding.root){
        private lateinit var detailTodo : TodoLocal
        fun bind(item : TodoLocal){
            detailTodo = item
            binding.tvtoxoCardName.text = item.title
            binding.tvtoxoCardName.setTextColor(item.levelColor)
        }

        fun getData(): TodoLocal = detailTodo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TcvTodoCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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