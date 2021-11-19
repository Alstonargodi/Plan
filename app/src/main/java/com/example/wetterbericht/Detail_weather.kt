package com.example.wetterbericht

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wetterbericht.model.APIforecast.Foredata
import com.example.wetterbericht.model.repo.api.mainrepo
import com.example.wetterbericht.model.room.cuaca
import com.example.wetterbericht.view.adapter.forecastadapter
import com.example.wetterbericht.viewmodel.api.Mainviewmodel
import com.example.wetterbericht.viewmodel.api.Vmfactory
import com.example.wetterbericht.viewmodel.room.Cuacaviewmodel
import kotlinx.android.synthetic.main.activity_detail_weather.*
import kotlin.math.round

class Detail_weather : AppCompatActivity() {
    lateinit var cuacaviewmodel: Mainviewmodel
    lateinit var localcuacavm : Cuacaviewmodel

    private var datalist = ArrayList<Foredata>()
    private var foreadapter = forecastadapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_weather)

        //cari cuaca
        val lokasi = intent.getStringExtra("lokasi")
        Log.d("lokasi",lokasi.toString())

        val repo = mainrepo()
        val vmfactory = Vmfactory(repo)
        cuacaviewmodel = ViewModelProvider(this,vmfactory).get(Mainviewmodel::class.java)
        localcuacavm = ViewModelProvider(this).get(Cuacaviewmodel::class.java)

        datalist = arrayListOf()
        foreadapter = forecastadapter()
        val forrecview = recview_forecast
        forrecview.adapter = foreadapter
        forrecview.layoutManager = LinearLayoutManager(this)


        if (lokasi != null) {
            getdataweather(lokasi)
        }

        detailweatherbycurr()

    }

    private fun getdataweather(lokasi : String){
        cuacaviewmodel.getdata(lokasi)
        cuacaviewmodel.datarespon.observe(this, Observer { response ->
            if(response.isSuccessful){
                val desc = response.body()?.weather?.get(0)?.description.toString()
                val url = response.body()?.weather?.get(0)?.icon
                val urlimage = "http://openweathermap.org/img/w/${url}.png"

                tvdetail_weather_loc.setText(response.body()?.name.toString())
                tvdetail_weather_temp.setText(response.body()?.main?.temp.toString())
                tvdetail_weather_desc.setText(desc)
                tvdetail_weather_feels.setText(response.body()?.main?.feelsLike.toString()+ " c")
                tvdetail_weather_wind.setText(response.body()?.wind?.speed.toString()+ " Km/h")
                tvdetail_weather_cloud.setText(response.body()?.clouds?.all.toString()+ " %")
                tvdetail_weather_visib.setText(response.body()?.visibility.toString()+ " km")
                tvdetail_weather_presuare.setText(response.body()?.main?.pressure.toString()+" mbar")

                Glide.with(this)
                    .asBitmap()
                    .load(urlimage)
                    .into(imgv_weather)

                btn_favorit.setOnClickListener {
                    Toast.makeText(this,"add to favorites", Toast.LENGTH_SHORT).show()

                    val input = cuaca(
                        response.body()?.name.toString(),
                        0,
                        desc,
                        response.body()?.main?.temp.toString(),
                        response.body()?.main?.feelsLike.toString(),
                        Integer.parseInt(response.body()?.main?.humidity.toString()),
                        urlimage,
                        response.body()?.main?.feelsLike.toString(),
                        response.body()?.wind?.speed.toString(),
                        response.body()?.clouds?.all.toString(),
                        response.body()?.visibility.toString(),
                        response.body()?.main?.pressure.toString()
                    )
                    localcuacavm.add(input)
                }

                //background condition
                when(desc){
                    "few clouds" -> layout_detail_weather.setBackgroundResource(R.drawable.bgconcloud)
                    "overcast clouds" -> layout_detail_weather.setBackgroundResource(R.drawable.bgconpartycloudy)
                    "light rain" -> layout_detail_weather.setBackgroundResource(R.drawable.bgconrain)
                    "moderate rain" -> layout_detail_weather.setBackgroundResource(R.drawable.bgconrain)
                    "sunny" -> layout_detail_weather.setBackgroundResource(R.drawable.bgconclear)
                    else -> layout_detail_weather.setBackgroundResource(R.drawable.bgconclear)
                }

            }else{
                Toast.makeText(this,"Cannot find $lokasi", Toast.LENGTH_SHORT).show()
            }
        })

        //forecast
        cuacaviewmodel.getforecast(lokasi)
        cuacaviewmodel.forecastrespon.observe(this, Observer { fore->
            val data = fore.body()?.list
            if (data != null) {
                for (i in 0 until data.size){
                    val date = data[i].dtTxt
                    val desc = data[i].weather.get(0).description
                    val temp = data[i].main.temp.toString()

                    val forcast = Foredata(
                        date,
                        desc,
                        temp
                    )

                    //view pager
                    Log.d("forecast",datalist.toString())
                    datalist.add(forcast)
                    foreadapter.setdata(datalist)
                }
            }
        })
    }

    private fun detailweatherbycurr(){

            val desc = intent.getStringExtra("desc")
            val temp = intent.getStringExtra("temp")?.toDouble()
            val ftemp = temp?.let { round(it) }
            val image = intent.getStringExtra("link")

            //layout condition
            when(desc){
                "few clouds" -> layout_detail_weather.setBackgroundResource(R.drawable.bgconcloud)
                "overcast clouds" -> layout_detail_weather.setBackgroundResource(R.drawable.bgconpartycloudy)
                "light rain" -> layout_detail_weather.setBackgroundResource(R.drawable.bgconrain)
                "moderate rain" -> layout_detail_weather.setBackgroundResource(R.drawable.bgconrain)
                "sunny" -> layout_detail_weather.setBackgroundResource(R.drawable.bgconclear)
                else -> layout_detail_weather.setBackgroundResource(R.drawable.bgconclear)
            }

            val loc = intent.getStringExtra("loc")

            tvdetail_weather_loc.setText(intent.getStringExtra("loc"))
            tvdetail_weather_temp.setText(ftemp.toString()+ " c")
            tvdetail_weather_desc.setText(desc)
            tvdetail_weather_feels.setText(intent.getStringExtra("feels")+ " c")
            tvdetail_weather_wind.setText(intent.getStringExtra("wind")+ " km/h")
            tvdetail_weather_visib.setText(intent.getStringExtra("visib")+ " %")
            tvdetail_weather_presuare.setText(intent.getStringExtra("pres")+ " %")
            tvdetail_weather_cloud.setText(intent.getStringExtra("cloud")+ " %")

            Glide.with(this)
                .asBitmap()
                .load(image)
                .into(imgv_weather)

            //forecast
            intent.getStringExtra("loc")?.let { cuacaviewmodel.getforecast(it) }
            cuacaviewmodel.forecastrespon.observe(this, Observer { fore->
                val data = fore.body()?.list
                if (data != null) {
                    for (i in 0 until data.size){
                        val date = data[i].dtTxt
                        val desc = data[i].weather.get(0).description
                        val temp = data[i].main.temp.toString()

                        val forcast = Foredata(
                            date,
                            desc,
                            temp
                        )

                        //view pager
                        Log.d("forecast",datalist.toString())
                        datalist.add(forcast)
                        foreadapter.setdata(datalist)
                    }
                }
            })

    }

}