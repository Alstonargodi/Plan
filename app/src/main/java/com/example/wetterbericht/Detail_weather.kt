package com.example.wetterbericht

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_weather.*
import kotlin.math.round

class Detail_weather : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_weather)


        val desc = intent.getStringExtra("desc")
        val temp = intent.getStringExtra("temp")?.toDouble()
        val ftemp = temp?.let { round(it) }
        val image = intent.getStringExtra("link")

        //layout condition
        when(desc){
            "few clouds" -> layout_detail_weather.setBackgroundResource(R.drawable.wrain)
            "overcast clouds" -> layout_detail_weather.setBackgroundResource(R.drawable.wrain)
            "light rain" -> layout_detail_weather.setBackgroundResource(R.drawable.wrain)
            "moderate rain" -> layout_detail_weather.setBackgroundResource(R.drawable.wrain)
            "sunny" -> layout_detail_weather.setBackgroundResource(R.drawable.wclear)
            else -> layout_detail_weather.setBackgroundResource(R.drawable.wclear)
        }

        tvdetail_weather_loc.setText(intent.getStringExtra("loc"))
        tvdetail_weather_temp.setText(ftemp.toString())
        tvdetail_weather_desc.setText(desc)
        tvdetail_weather_feels.setText(intent.getStringExtra("feels"))
        tvdetail_weather_wind.setText(intent.getStringExtra("wind"))
        tvdetail_weather_visib.setText(intent.getStringExtra("visib"))
        tvdetail_weather_presuare.setText(intent.getStringExtra("pres"))
        tvdetail_weather_cloud.setText(intent.getStringExtra("cloud"))

        Glide.with(this)
            .asBitmap()
            .load(image)
            .into(imgv_weather)
    }

}