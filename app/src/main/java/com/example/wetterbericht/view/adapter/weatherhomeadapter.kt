package com.example.wetterbericht.view.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.cuaca
import kotlinx.android.synthetic.main.cv_weather.view.*
import kotlinx.android.synthetic.main.cv_weather_home.view.*

class weatherhomeadapter: RecyclerView.Adapter<weatherhomeadapter.viewmodel>() {
    private var datalist = emptyList<cuaca>()

    class viewmodel(itemview : View): RecyclerView.ViewHolder(itemview) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): weatherhomeadapter.viewmodel {
        return viewmodel(LayoutInflater.from(parent.context).inflate(R.layout.cv_weather_home,parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: weatherhomeadapter.viewmodel, position: Int) {
        var curitem = datalist[position]
        holder.itemView.tv_homew_loca.text = curitem.loc
        holder.itemView.tv_homew_desc.text = curitem.desc
        holder.itemView.tv_homew_temp.text = curitem.temp.toString()


        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(curitem.image)
            .into(holder.itemView.img_home_icon)



        if(curitem.desc == "few clouds"){
            holder.itemView.lay_bg_weather_home.setBackgroundResource(R.drawable.pantai)
        }else if (curitem.desc == "overcast clouds"){
            holder.itemView.lay_bg_weather_home.setBackgroundResource(R.drawable.clearbg)
        }else if(curitem.desc == "light rain"){
            holder.itemView.lay_bg_weather_home.setBackgroundResource(R.drawable.wrain)
        }else if (curitem.desc == "moderate rain"){
            holder.itemView.lay_bg_weather_home.setBackgroundResource(R.drawable.wrain)
        }

    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setdata(data : List<cuaca>){
        datalist = data
        notifyDataSetChanged()
    }
}