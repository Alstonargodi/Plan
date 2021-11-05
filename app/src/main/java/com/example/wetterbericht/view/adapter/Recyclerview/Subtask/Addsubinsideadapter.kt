package com.example.wetterbericht.view.adapter.Recyclerview.Subtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.Do.subtaskoutside
import com.example.wetterbericht.model.room.subtaskinside
import kotlinx.android.synthetic.main.cv_subtask_actadd.view.*

class Addsubinsideadapter: RecyclerView.Adapter<Addsubinsideadapter.viewholder>() {
    var subin = emptyList<subtaskinside>()
    var subput = emptyList<subtaskoutside>()

    class viewholder(view: View): RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.cv_subtask_actadd,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val item = subin[position]
        val itemo = subput[position]

        holder.itemView.tvcb_subtask.text = item.task
    }

    override fun getItemCount(): Int {
        return subin.size
    }

    fun setdata(data : List<subtaskinside>){
        subin = data
        notifyDataSetChanged()
    }

    fun setout(data: List<subtaskoutside>){
        subput = data
        notifyDataSetChanged()
    }
}