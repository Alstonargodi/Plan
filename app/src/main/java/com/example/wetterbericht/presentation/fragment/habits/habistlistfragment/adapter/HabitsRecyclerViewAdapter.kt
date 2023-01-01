package com.example.wetterbericht.presentation.fragment.habits.habistlistfragment.adapter

import android.annotation.SuppressLint
import android.graphics.Color
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
        @SuppressLint("ResourceAsColor")
        fun bind(item : DailyHabits){
            val iconHabits = itemView.context.resources.getIdentifier(
                item.iconHabits,
                "drawable",
                itemView.context.packageName
            )
            binding.tvHabitsName.text = item.title
            binding.tvHabitsDuration.text = item.minuteFocus.toString()
            binding.tvHabitsName.apply {
                setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    iconHabits,
                    0
                )
            }
            binding.cvlayoutHabits.setCardBackgroundColor(item.colorHabits)
            if (item.colorHabits != Color.parseColor("#FFFFFF")){
                binding.tvHabitsName.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvHabitsDuration.setTextColor(Color.parseColor("#FFFFFF"))
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