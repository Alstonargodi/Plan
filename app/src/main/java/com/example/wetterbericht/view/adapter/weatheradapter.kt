package com.example.wetterbericht.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetterbericht.DetailWeatherActivity
import com.example.wetterbericht.R

import com.example.wetterbericht.model.room.cuaca
import kotlinx.android.synthetic.main.itemcv_weather.view.*
import kotlin.math.round

class weatheradapter: RecyclerView.Adapter<weatheradapter.viewholder>() {
    private var weatherlist = emptyList<cuaca>()

    class viewholder(itemview : View): RecyclerView.ViewHolder(itemview) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.itemcv_weather,parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var curitem = weatherlist[position]
        val feels = "Feels like ${curitem.feelslike} c"
        val humid = "Humidty ${curitem.humid} %"

        val temp = round(curitem.temp.toDouble())
        holder.itemView.tv_weather_desc.text = curitem.desc
        holder.itemView.tv_weather_loc.text = curitem.loc
        holder.itemView.tv_weather_temp.text = temp.toString()



        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(curitem.image)
            .into(holder.itemView.img_weather_icon)


        holder.itemView.cv_weather.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailWeatherActivity::class.java)
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

        when(curitem.desc){
            "sunny"->{

            }
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