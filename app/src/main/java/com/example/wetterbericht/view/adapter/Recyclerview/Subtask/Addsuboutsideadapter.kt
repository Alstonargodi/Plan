package com.example.wetterbericht.view.adapter.Recyclerview.Subtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.Do.subtaskoutside
import kotlinx.android.synthetic.main.cv_subtask_actadd.view.*

class Addsuboutsideadapter: RecyclerView.Adapter<Addsuboutsideadapter.viewholder>() {
    var out = emptyList<subtaskoutside>()

    class viewholder(view : View): RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.cv_subtask_actadd,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val item = out[position]
        holder.itemView.tvcb_subtask.text = item.task
    }

    override fun getItemCount(): Int {
        return out.size
    }

    fun setout(data: List<subtaskoutside>){
        out = data
        notifyDataSetChanged()
    }


}