package com.example.wetterbericht.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetterbericht.Detail_weather
import com.example.wetterbericht.R

import com.example.wetterbericht.model.room.cuaca
import com.example.wetterbericht.view.Fragment.Fragment_WeatherDirections
import kotlinx.android.synthetic.main.cv_weather.view.*

class weatheradapter: RecyclerView.Adapter<weatheradapter.viewholder>() {
    private var weatherlist = emptyList<cuaca>()

    class viewholder(itemview : View): RecyclerView.ViewHolder(itemview) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.cv_weather,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var curitem = weatherlist[position]
        val feels = "Feels like ${curitem.feelslike} c"
        val humid = "Humidty ${curitem.humid} %"
        holder.itemView.tv_weather_desc.text = curitem.desc
        holder.itemView.tv_weather_loc.text = curitem.loc
        holder.itemView.tv_weather_temp.text = curitem.temp.toString()
        holder.itemView.tv_weather_feels.text = feels
        holder.itemView.tv_weather_humid.text = humid


        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(curitem.image)
            .into(holder.itemView.img_weather_icon)

//        holder.itemView.cv_weather.setOnClickListener {
//            val intent = Fragment_WeatherDirections.actionFragmentWeatherToCurrWeather(curitem)
//            holder.itemView.findNavController().navigate(intent)
//        }

        holder.itemView.cv_weather.setOnClickListener {
            val intent = Intent(holder.itemView.context,Detail_weather::class.java)
            intent.putExtra("loc",curitem.loc)
            intent.putExtra("desc",curitem.desc)
            intent.putExtra("temp",curitem.temp)
            intent.putExtra("feels",curitem.temp)
            intent.putExtra("wind",curitem.windspeed)
            intent.putExtra("visib",curitem.visibility)
            intent.putExtra("pres",curitem.presure)
            intent.putExtra("cloud",curitem.cloud)
            intent.putExtra("link",curitem.image)


            holder.itemView.context.startActivity(intent)

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