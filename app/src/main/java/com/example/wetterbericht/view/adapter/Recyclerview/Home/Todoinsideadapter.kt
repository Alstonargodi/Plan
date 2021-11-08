package com.example.wetterbericht.view.adapter.Recyclerview.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import kotlinx.android.synthetic.main.tcv_todo_card.view.*

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle

import com.example.wetterbericht.Detail_activity
import com.example.wetterbericht.model.room.insidendsubtask
import com.example.wetterbericht.view.receiver.alarmreceiver
import com.example.wetterbericht.view.util.todo.Alarmreceiver


class Todoinsideadapter: RecyclerView.Adapter<Todoinsideadapter.viewholder>() {
    var data = emptyList<insidendsubtask>()

    class viewholder(view : View): RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.tcv_todo_card,parent,false))
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val item = data[position]
        holder.itemView.tvtoxo_card_name.text = item.todo.title
        holder.itemView.tvtoxo_card_tanggal.text = item.todo.deadlinedate
        holder.itemView.tvtoxo_card_waktu.text = item.todo.deadlinetime

        holder.itemView.lay_todo.setOnClickListener {

            val intent = Intent(holder.itemView.context,Detail_activity::class.java)
            intent.putExtra("namaact",item.todo.title)
            intent.putExtra("desc",item.todo.desc)



            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setdata(list: List<insidendsubtask>){
        data = list
        notifyDataSetChanged()
    }

}