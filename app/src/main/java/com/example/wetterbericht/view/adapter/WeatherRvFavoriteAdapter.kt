package com.example.wetterbericht.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetterbericht.view.weather.DetailWeatherActivity
import com.example.wetterbericht.R
import com.example.wetterbericht.model.local.WeatherLocal
import kotlinx.android.synthetic.main.itemcv_weather.view.*
import kotlin.math.round

class WeatherRvFavoriteAdapter: RecyclerView.Adapter<WeatherRvFavoriteAdapter.viewholder>() {
    private var weatherlist = emptyList<WeatherLocal>()

    class viewholder(itemview : View): RecyclerView.ViewHolder(itemview) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.itemcv_weather,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var curitem = weatherlist[position]


        val temp = round(curitem.temp.toDouble()).toInt()
        holder.itemView.tv_weather_desc.text = curitem.desc
        holder.itemView.tv_weather_loc.text = curitem.loc
        holder.itemView.tv_weather_temp.text = temp.toString() + "c"



        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(curitem.image)
            .into(holder.itemView.img_weather_icon)


        holder.itemView.cv_weather.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailWeatherActivity::class.java)
            intent.putExtra("loc",curitem.loc)

            holder.itemView.context.startActivity(intent)
        }

        when(curitem.desc){
            "sunny"->{

            }
        }
    }

    override fun getItemCount(): Int {
        return weatherlist.size
    }

    fun setdata(WeatherLocal : List<WeatherLocal>){
        weatherlist = WeatherLocal
        notifyDataSetChanged()
    }

}