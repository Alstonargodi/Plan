package com.example.wetterbericht.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.model.remote.response.Foredata
import kotlinx.android.synthetic.main.itemcv_forecast.view.*
import kotlin.math.round

class ForecastAdapter(private val foreList : List<Foredata>)
    : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemcv_forecast,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = foreList[position]

        val temp = round(item.temp.toDouble()).toInt()
        holder.itemView.tv_f_date.text = item.date
        holder.itemView.tv_f_desc.text = item.desc
        holder.itemView.tv_f_temp.text = temp.toString() + " c"

    }

    override fun getItemCount(): Int = foreList.size


}