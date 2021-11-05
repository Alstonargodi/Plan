package com.example.wetterbericht.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.Inside
import com.example.wetterbericht.model.room.insidendsubtask

import kotlinx.android.synthetic.main.cv_todo.view.*

class todoadapter : RecyclerView.Adapter<todoadapter.viewholder>() {

    private var datalist = emptyList<Inside>()

    class viewholder(itemview : View): RecyclerView.ViewHolder(itemview) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.cv_todo,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var curitem = datalist[position]
        holder.itemView.tv_todo_title.text = curitem.title
        holder.itemView.tv_todo_status.text = curitem.status
        holder.itemView.tv_todo_date.text = curitem.deadlinedate.toString()
        holder.itemView.tv_todo_time.text = curitem.deadlinetime.toString()
        holder.itemView.tv_todo_desc.text = curitem.desc

    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setdata(data : List<Inside>){
        datalist = data
        notifyDataSetChanged()
    }
}