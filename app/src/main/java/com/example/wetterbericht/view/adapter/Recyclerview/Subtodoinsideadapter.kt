package com.example.wetterbericht.view.adapter.Recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.subtask
import kotlinx.android.synthetic.main.cv_subtask_actadd.view.*

class Subtodoinsideadapter: RecyclerView.Adapter<Subtodoinsideadapter.viewholder>() {
    var subdata = emptyList<subtask>()

    class viewholder(view : View): RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.cv_subtask_actadd,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val item = subdata[position]
        holder.itemView.tvcb_subtask.text = item.task
    }

    override fun getItemCount(): Int {
        return subdata.size
    }

    fun setdata(data : List<subtask>){
        subdata = data
        notifyDataSetChanged()
    }

}