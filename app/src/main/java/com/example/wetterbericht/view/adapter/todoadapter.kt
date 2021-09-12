package com.example.wetterbericht.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.view.fragment_todoDirections
import com.example.wetterbericht.model.room.todo
import kotlinx.android.synthetic.main.cv_todo.view.*

class todoadapter : RecyclerView.Adapter<todoadapter.viewholder>() {

    private var datalist = emptyList<todo>()

    class viewholder(itemview : View): RecyclerView.ViewHolder(itemview) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.cv_todo,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var curitem = datalist[position]
        holder.itemView.tv_todo_id.text = curitem.id.toString()
        holder.itemView.tv_todo_title.text = curitem.title
        holder.itemView.tv_todo_status.text = curitem.status
        holder.itemView.tv_todo_date.text = curitem.deadlinedate.toString()
        holder.itemView.tv_todo_time.text = curitem.deadlinetime.toString()
        holder.itemView.tv_todo_desc.text = curitem.desc

        holder.itemView.cv_todo.setOnClickListener {
            val intent = fragment_todoDirections.actionFragmentTodoToUpdate(curitem)
            holder.itemView.findNavController().navigate(intent)
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setdata(data : List<todo>){
        datalist = data
        notifyDataSetChanged()
    }
}