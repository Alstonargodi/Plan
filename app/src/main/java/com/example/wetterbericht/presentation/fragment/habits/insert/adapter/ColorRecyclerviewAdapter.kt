package com.example.wetterbericht.presentation.fragment.habits.insert.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.data.local.entity.dailyhabits.ColorHabits
import com.example.wetterbericht.databinding.ItemcvColorHabitsBinding

class ColorRecyclerviewAdapter(
    private var colorList : List<ColorHabits>
): RecyclerView.Adapter<ColorRecyclerviewAdapter.ViewHolder>() {

    private lateinit var getColorHabits: ColorCallback

    fun getColorHabits(callback : ColorCallback){
        this.getColorHabits = callback
    }
    class ViewHolder(val binding: ItemcvColorHabitsBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemcvColorHabitsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = colorList[position]
        holder.binding.cardColor.apply {
            setCardBackgroundColor(
                Color.parseColor(data.colorHex)
            )
            setOnClickListener {
                getColorHabits.colorCallback(data.colorHex)
            }
        }
    }

    override fun getItemCount(): Int = colorList.size

    interface ColorCallback{
        fun colorCallback(name : String)
    }

}