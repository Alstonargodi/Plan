package com.example.wetterbericht.view.adapter.Recyclerview.Home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.Detail_activity
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
        holder.itemView.tvtoxo_card_tanggal.text = item.todo.deadlinedate
        holder.itemView.tvtoxo_card_waktu.text = item.todo.deadlinetime

        holder.itemView.lay_todo.setOnClickListener {
            val intent = Intent(holder.itemView.context, Detail_activity::class.java)
            holder.itemView.context.startActivity(intent)
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