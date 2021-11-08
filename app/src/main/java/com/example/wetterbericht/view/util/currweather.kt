package com.example.wetterbericht.view.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.wetterbericht.R
import kotlinx.android.synthetic.main.cv_weather_home.view.*
import kotlinx.android.synthetic.main.fragment_curr_weather.*
import kotlinx.android.synthetic.main.fragment_curr_weather.view.*
import kotlinx.android.synthetic.main.fragment_curr_weather.view.tv_c_desc
import kotlinx.android.synthetic.main.fragment_curr_weather.view.tv_c_feelslike
import kotlinx.android.synthetic.main.fragment_curr_weather.view.tv_c_location


class currweather : Fragment() {
    private val args by navArgs<currweatherArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_curr_weather, container, false)

        val desc = args.currData.desc
        if(desc == "few clouds"){
            view.lay_curr_weather.setBackgroundResource(R.drawable.day_cloudy)
        }else if (desc == "overcast clouds"){
            view.lay_curr_weather.setBackgroundResource(R.drawable.day_cloudy)
        }else if(desc == "light rain"){
            view.lay_curr_weather.setBackgroundResource(R.drawable.day_rain)
        }else if (desc == "moderate rain"){
            view.lay_curr_weather.setBackgroundResource(R.drawable.day_rain)
        }
        view.tv_c_location.setText(args.currData.loc)
        view.tv_c_desc.setText(args.currData.desc)
        view.tv_c_feelslike.setText(args.currData.feelslike + " c")
        view.tv_c_wind.setText(args.currData.windspeed + " km/h")
        view.tv_c_cloud.setText(args.currData.cloud + " %")
        view.tv_c_pressure.setText(args.currData.presure + " mbar")
        view.tv_c_visiblity.setText(args.currData.visibility + " km")
        view.tv_c_humid.setText(args.currData.humid.toString()+ " %")
        view.tv_c_temp.setText(args.currData.temp)

        val image = args.currData.image
        Glide.with(this)
            .asBitmap()
            .load(image)
            .into(view.img_c_icon)

        return view
    }
}