package com.example.wetterbericht.view.adapter.Recyclerview.Detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.subtaskinside
import kotlinx.android.synthetic.main.cvsub_detailin.view.*

class Tododetailsubadapter:RecyclerView.Adapter<Tododetailsubadapter.viewholder>() {
    var datalist = emptyList<subtaskinside>()
    class viewholder(view: View): RecyclerView.ViewHolder(view){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.cvsub_detailin,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val item = datalist[position]
        holder.itemView.tv_subdetailin.text = item.task
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setdata(list: List<subtaskinside>){
        datalist = list
        notifyDataSetChanged()
    }

}