package com.example.wetterbericht.view.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.annotation.SuppressLint
import com.example.wetterbericht.databinding.TcvTodoCardBinding
import com.example.wetterbericht.model.local.TodoLocal


class TodoRvHomeAdapter: RecyclerView.Adapter<TodoRvHomeAdapter.ViewHolder>() {

    //todo binding
    var data = emptyList<TodoLocal>()

    private lateinit var detailCallback : detailCallBack

    fun detilOnItemCallback(callback : detailCallBack){
        this.detailCallback = callback
    }
    class ViewHolder(var binding : TcvTodoCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TcvTodoCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.tvtoxoCardName.text = item.title
        holder.binding.tvtoxoCardName.setTextColor(item.levelColor)


        holder.binding.layTodo.setOnClickListener {
            detailCallback.detailCallBack(item)
        }

    }


    override fun getItemCount(): Int = data.size


    fun setdata(list: List<TodoLocal>){
        data = list
        notifyDataSetChanged()
    }


    interface detailCallBack{
        fun detailCallBack(data : TodoLocal)
    }
}