package com.example.wetterbericht.view.adapter.Recyclerview.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.Detail_todo
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.Do.outsideandsubtask
import kotlinx.android.synthetic.main.tcv_todo_card.view.*

class Todooutsideadapter: RecyclerView.Adapter<Todooutsideadapter.viewholder>() {
    var out = emptyList<outsideandsubtask>()

    class viewholder(view : View): RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.tcv_todo_card,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val item = out[position]
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

    override fun getItemCount(): Int {
        return out.size
    }

    fun setdata(data : List<outsideandsubtask>){
        out = data
        notifyDataSetChanged()
    }

}