package com.example.wetterbericht.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.R
import com.example.wetterbericht.model.APIforecast.Foredata
import kotlinx.android.synthetic.main.cv_forecast.view.*
import kotlin.math.round

class forecastadapter: RecyclerView.Adapter<forecastadapter.viewholder>() {
    var forelist = emptyList<Foredata>()
    class viewholder(itemview : View): RecyclerView.ViewHolder(itemview) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): forecastadapter.viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.cv_forecast,parent,false))
    }

    override fun onBindViewHolder(holder: forecastadapter.viewholder, position: Int) {
        var item = forelist[position]
        val temp = round(item.temp.toDouble())
        holder.itemView.tv_f_date.text = item.date
        holder.itemView.tv_f_desc.text = item.desc
        holder.itemView.tv_f_temp.text = temp.toString() + " c"
    }

    override fun getItemCount(): Int {
        return forelist.size
    }

    fun setdata(dataf: List<Foredata>){
        forelist = dataf
        notifyDataSetChanged()
    }
}