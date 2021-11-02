package com.example.wetterbericht.view.adapter.Recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.todo
import kotlinx.android.synthetic.main.tcv_todo_card.view.*
import android.view.MotionEvent

import android.annotation.SuppressLint
import android.view.View.OnTouchListener

import android.widget.ScrollView
import com.example.wetterbericht.model.room.todoandsubtask


class Todoinsideadapter: RecyclerView.Adapter<Todoinsideadapter.viewholder>() {
    var data = emptyList<todoandsubtask>()

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
        holder.itemView.tvtoxo_card_desc.text = item.todo.desc

        holder.itemView.scrollcard_todo.setOnTouchListener { v, event ->
            holder.itemView.parent.requestDisallowInterceptTouchEvent(true)
            holder.itemView.onTouchEvent(event)

            true
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setdata(list: List<todoandsubtask>){
        data = list
        notifyDataSetChanged()
    }

}