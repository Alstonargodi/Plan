package com.example.wetterbericht.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.todo
import com.example.wetterbericht.model.room.todoandsubtask
import kotlinx.android.synthetic.main.cv_todo_home.view.*

class todohomeadapter: RecyclerView.Adapter<todohomeadapter.viewholder>() {
    private var datalist = emptyList<todoandsubtask>()
    class viewholder(itemview : View): RecyclerView.ViewHolder(itemview) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.cv_todo_home,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var curitem = datalist[position]
        holder.itemView.tv_home_date.text = curitem.todo.deadlinedate.toString()
        holder.itemView.tv_home_time.text = curitem.todo.deadlinetime.toString()
        holder.itemView.tv_home_status.text = curitem.todo.status.toString()
    }

    override fun getItemCount(): Int {
        val jumlah = datalist.size
        Log.d("jumlah",jumlah.toString())
        return datalist.size
    }

    fun setdata(data : List<todoandsubtask>){
        datalist = data
        notifyDataSetChanged()
    }
}