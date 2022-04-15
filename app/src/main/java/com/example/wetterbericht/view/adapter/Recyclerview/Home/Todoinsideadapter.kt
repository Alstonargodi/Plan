package com.example.wetterbericht.view.adapter.Recyclerview.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import kotlinx.android.synthetic.main.tcv_todo_card.view.*

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.example.wetterbericht.Detail_todo
import com.example.wetterbericht.model.room.insidendsubtask
import com.example.wetterbericht.viewmodel.room.todoviewmodel


class Todoinsideadapter: RecyclerView.Adapter<Todoinsideadapter.viewholder>() {

    lateinit var localvmodel : todoviewmodel

    var data = emptyList<insidendsubtask>()

    class viewholder(view : View): RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.tcv_todo_card,parent,false))
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val item = data[position]
        holder.itemView.tvtoxo_card_name.text = item.todo.title
        holder.itemView.tvtoxo_card_waktu.text = item.todo.deadlinetime

        holder.itemView.lay_todo.setOnClickListener {
            var dialog = Detail_todo()
            var spfragment = (holder.itemView.context as AppCompatActivity).supportFragmentManager

            var args = Bundle()
            args.putString("date",item.todo.deadlinedate)
            args.putString("time",item.todo.deadlinetime)
            args.putString("title",item.todo.title)
            args.putString("desc",item.todo.desc)



            dialog.setArguments(args)
            dialog.show(spfragment,"dialog")


        }

    }


    override fun getItemCount(): Int = data.size


    fun setdata(list: List<insidendsubtask>){
        data = list
        notifyDataSetChanged()
    }


}