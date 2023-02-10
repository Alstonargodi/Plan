package com.example.wetterbericht.presentation.fragment.habits.habistlistfragment.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.databinding.ItemcvHabitsBinding
import com.example.wetterbericht.data.local.entities.dailyhabits.DailyHabits

class HabitsRecyclerViewAdapter(private val data : List<DailyHabits>)
    : RecyclerView.Adapter<HabitsRecyclerViewAdapter.ViewHolder>(){
    private lateinit var detailHabitsCallback : DetailHabitsCallback
    fun detailHabitsCallback(callback : DetailHabitsCallback){
        this.detailHabitsCallback = callback
    }
    class ViewHolder(val binding : ItemcvHabitsBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(item : DailyHabits){
            val iconHabits = itemView.context.resources.getIdentifier(
                item.iconHabits,
                "drawable",
                itemView.context.packageName
            )
            binding.apply {
                tvHabitsName.text = item.title
                tvHabitsDuration.text = item.minuteFocus.toString()
                tvHabitsName.apply {
                    setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        iconHabits,
                        0
                    )
                }
                tvHabitsDuration.setTextColor(item.colorHabits)
                tvHabitsName.setTextColor(item.colorHabits)
                if (item.colorHabits == Color.parseColor("#FFFFFF")){
                    tvHabitsDuration.setTextColor(Color.BLACK)
                    tvHabitsName.setTextColor(Color.BLACK)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemcvHabitsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
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