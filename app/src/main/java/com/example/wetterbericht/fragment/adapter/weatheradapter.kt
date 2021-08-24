package com.example.wetterbericht.fragment.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetterbericht.R
import com.example.wetterbericht.fragment.fragment_Weather
import com.example.wetterbericht.fragment.fragment_WeatherDirections
import com.example.wetterbericht.model.room.cuaca
import kotlinx.android.synthetic.main.cv_weather.view.*

class weatheradapter: RecyclerView.Adapter<weatheradapter.viewholder>() {
    private var weatherlist = emptyList<cuaca>()

    class viewholder(itemview : View): RecyclerView.ViewHolder(itemview) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.cv_weather,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var curitem = weatherlist[position]
        holder.itemView.tv_weather_desc.text = curitem.desc
        holder.itemView.tv_weather_loc.text = curitem.loc
        holder.itemView.tv_weather_temp.text = curitem.temp.toString()

        //todo glide
        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(curitem.image)
            .into(holder.itemView.img_weather_icon)

        holder.itemView.cv_weather.setOnClickListener {
            val intent = fragment_WeatherDirections.actionFragmentWeatherToCurrWeather(curitem)
            holder.itemView.findNavController().navigate(intent)
        }
    }

    override fun getItemCount(): Int {
        return weatherlist.size
    }

    fun setdata(cuaca : List<cuaca>){
        weatherlist = cuaca
        notifyDataSetChanged()
    }

}