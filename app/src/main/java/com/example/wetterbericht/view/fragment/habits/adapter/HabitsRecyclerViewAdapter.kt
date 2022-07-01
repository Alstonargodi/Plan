package com.example.wetterbericht.view.fragment.habits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.databinding.ItemcvHabitsBinding
import com.example.wetterbericht.model.local.entity.HabitsLocal
import com.example.wetterbericht.view.fragment.home.adapter.TodoRecyclerViewAdapter

class HabitsRecyclerViewAdapter(private val data : List<HabitsLocal>)
    : RecyclerView.Adapter<HabitsRecyclerViewAdapter.ViewHolder>(){

    private lateinit var detailHabitsCallback : DetailHabitsCallback

    fun detailHabitsCallback(callback : DetailHabitsCallback){
        this.detailHabitsCallback = callback
    }

    class ViewHolder(val binding : ItemcvHabitsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item : HabitsLocal){
            binding.tvHabitsName.text = item.name
            binding.tvHabitsDuration.text = item.duration.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemcvHabitsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.binding.cvlayoutHabits.setOnClickListener {
            detailHabitsCallback.detailHabitsCallback(item)
        }
    }

    override fun getItemCount(): Int = data.size

    interface DetailHabitsCallback{
        fun detailHabitsCallback(data : HabitsLocal)
    }


}