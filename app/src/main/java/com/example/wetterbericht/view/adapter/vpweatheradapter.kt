package com.example.wetterbericht.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.wetterbericht.R
import com.example.wetterbericht.model.APIforecast.Foredata
import kotlinx.android.synthetic.main.cv_forecast.view.*
import kotlinx.android.synthetic.main.vplay_forecast.view.*

class vpweatheradapter(private val context: Context,private var fordata : List<Foredata>): PagerAdapter() {
    var layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return fordata.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun instantiateItem(container : ViewGroup,position : Int): Any{
        val view = LayoutInflater.from(context).inflate(R.layout.vplay_forecast,container,false)

        var pitem = fordata[position]
        view.tv_pager_date.text = pitem.date
        view.tv_pager_temp.text = pitem.temp
        view.tv_pager_loc.text = pitem.desc

        container.addView(view)
        return view
    }

}