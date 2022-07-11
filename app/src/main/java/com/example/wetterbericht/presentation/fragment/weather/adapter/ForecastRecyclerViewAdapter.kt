package com.example.wetterbericht.presentation.fragment.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.data.remote.openweather.forecast.ForecastItem
import kotlinx.android.synthetic.main.itemcv_forecast.view.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.round

class ForecastRecyclerViewAdapter(private val foreList : List<ForecastItem>)
    : RecyclerView.Adapter<ForecastRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemcv_forecast,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = foreList[position]
        val dateFormat = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")
        val date = LocalDate.parse(item.date,dateFormat)
        val time = LocalTime.parse(item.date,dateFormat)

        val tvDateFormat = "${date.dayOfWeek}, ${time}"

        val temp = round(item.temp.toDouble()).toInt()
        holder.itemView.tv_f_date.text = tvDateFormat
        holder.itemView.tv_f_desc.text = item.desc
        holder.itemView.tv_f_temp.text = "$temp c"

    }

    override fun getItemCount(): Int = foreList.size


}