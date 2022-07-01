package com.example.wetterbericht.view.fragment.weather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetterbericht.R
import com.example.wetterbericht.model.local.entity.WeatherLocal
import kotlinx.android.synthetic.main.itemcv_weather_home.view.*
import kotlin.math.round

class WeatherHomeRecyclerViewAdapter: RecyclerView.Adapter<WeatherHomeRecyclerViewAdapter.viewmodel>() {
    private var datalist = emptyList<WeatherLocal>()

    class viewmodel(itemview : View): RecyclerView.ViewHolder(itemview) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewmodel {
        return viewmodel(LayoutInflater.from(parent.context).inflate(R.layout.itemcv_weather_home,parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: viewmodel, position: Int) {
        val item = datalist[position]

        val temp = item.temp.toDouble()
        val temperature = round(temp).toInt().toString()
        holder.itemView.tv_homew_temp.text = temperature


        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(item.image)
            .into(holder.itemView.img_home_icon)

    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setData(data : List<WeatherLocal>){
        datalist = data
        notifyDataSetChanged()
    }
}