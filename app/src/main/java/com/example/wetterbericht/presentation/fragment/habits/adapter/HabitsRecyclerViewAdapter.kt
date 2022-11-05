package com.example.wetterbericht.presentation.fragment.habits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.databinding.ItemcvHabitsBinding
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits

class HabitsRecyclerViewAdapter(private val data : List<DailyHabits>)
    : RecyclerView.Adapter<HabitsRecyclerViewAdapter.ViewHolder>(){

    private lateinit var detailHabitsCallback : DetailHabitsCallback

    fun detailHabitsCallback(callback : DetailHabitsCallback){
        this.detailHabitsCallback = callback
    }

    class ViewHolder(val binding : ItemcvHabitsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item : DailyHabits){
            binding.tvHabitsName.text = item.title
            binding.tvHabitsDuration.text = item.minuteFocus.toString()

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
        fun detailHabitsCallback(data : DailyHabits)
    }


}