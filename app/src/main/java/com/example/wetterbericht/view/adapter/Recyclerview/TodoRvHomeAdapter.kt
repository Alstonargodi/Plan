package com.example.wetterbericht.view.adapter.Recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import kotlinx.android.synthetic.main.tcv_todo_card.view.*

import android.annotation.SuppressLint
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.viewmodel.local.LocalViewModel


class TodoRvHomeAdapter: RecyclerView.Adapter<TodoRvHomeAdapter.viewholder>() {

    lateinit var localvmodel : LocalViewModel

    var data = emptyList<TodoLocal>()

    class viewholder(view : View): RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.tcv_todo_card,parent,false))
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val item = data[position]
        holder.itemView.tvtoxo_card_name.text = item.title



    }


    override fun getItemCount(): Int = data.size


    fun setdata(list: List<TodoLocal>){
        data = list
        notifyDataSetChanged()
    }


}