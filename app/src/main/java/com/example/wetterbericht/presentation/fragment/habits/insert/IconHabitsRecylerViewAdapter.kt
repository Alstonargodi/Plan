package com.example.wetterbericht.presentation.fragment.habits.insert

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetterbericht.data.local.entity.dailyhabits.IconHabits
import com.example.wetterbericht.databinding.ItemcvHabitsBinding
import com.example.wetterbericht.databinding.ItemcvIconHabitsBinding

class IconHabitsRecylerViewAdapter(
    private val iconList : List<IconHabits>
) : RecyclerView.Adapter<IconHabitsRecylerViewAdapter.ViewHolder>(){

    class ViewHolder(val binding : ItemcvIconHabitsBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemcvIconHabitsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = iconList[position]
        val icon = holder.itemView.context.resources.getIdentifier(
            data.iconPath,
            "drawable",
            holder.itemView.context.packageName
        )
        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(icon)
            .into(holder.binding.imgIconHabits)
    }

    override fun getItemCount(): Int = iconList.size
}