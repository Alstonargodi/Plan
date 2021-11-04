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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.model.room.todoandsubtask
import com.example.wetterbericht.viewmodel.room.todoviewmodel


class Todoinsideadapter: RecyclerView.Adapter<Todoinsideadapter.viewholder>() {
    var data = emptyList<todoandsubtask>()
    val adapter = Addtodosubadapter()

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

        holder.itemView.recview_todo_inside.adapter = adapter
        holder.itemView.recview_todo_inside.layoutManager = LinearLayoutManager(holder.itemView.context)


    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setdata(list: List<todoandsubtask>){
        data = list
        notifyDataSetChanged()
    }

}