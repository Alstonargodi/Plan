package com.example.wetterbericht.presentation.fragment.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetterbericht.data.remote.weather.openweather.forecast.ForecastItem
import com.example.wetterbericht.databinding.ItemcvForecastBinding
import com.example.wetterbericht.helpers.Converter.dateParser
import com.example.wetterbericht.helpers.Converter.imageUrlParser
import kotlin.math.round

class ForecastRecyclerViewAdapter(private val foreList : List<ForecastItem>)
    : RecyclerView.Adapter<ForecastRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding : ItemcvForecastBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemcvForecastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = foreList[position]
        val tvDateFormat = dateParser(item.date)

        val temp = round(item.temp.toDouble()).toInt()
        holder.binding.apply {
            tvFDate.text = tvDateFormat
            tvFDesc.text = item.desc
            "$temp c".also { tvFTemp.text = it }

            Glide.with(holder.binding.root)
                .load(imageUrlParser(item.iconUrl))
                .into(imgIconCond)
        }
    }

    override fun getItemCount(): Int = foreList.size
}