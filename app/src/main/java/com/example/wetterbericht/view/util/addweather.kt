package com.example.wetterbericht.view.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wetterbericht.R
import com.example.wetterbericht.view.adapter.forecastadapter
import com.example.wetterbericht.model.APIforecast.Foredata
import com.example.wetterbericht.model.room.cuaca
import com.example.wetterbericht.model.repo.api.mainrepo
import com.example.wetterbericht.viewmodel.api.Mainviewmodel
import com.example.wetterbericht.viewmodel.api.Vmfactory
import com.example.wetterbericht.viewmodel.room.Cuacaviewmodel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_addweather.*
import kotlinx.android.synthetic.main.fragment_addweather.view.*


class addweather : Fragment() {

    lateinit var mapiviewmodel : Mainviewmodel
    lateinit var mroomviewmodel : Cuacaviewmodel

    private var foreadapter = forecastadapter()
    private var datalist = ArrayList<Foredata>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addweather, container, false)

        //based on search
        val repo = mainrepo()
        val vmfactory = Vmfactory(repo)
        mapiviewmodel = ViewModelProvider(this,vmfactory).get(Mainviewmodel::class.java) //set
        mroomviewmodel = ViewModelProvider(this).get(Cuacaviewmodel::class.java)
        findweather()


        datalist = arrayListOf()
        foreadapter = forecastadapter()
        val forerecview = view?.forerecyclerview

        if (forerecview != null) {
            forerecview.adapter = foreadapter
        }

        if (forerecview != null) {
            forerecview.layoutManager = LinearLayoutManager(requireContext())
        }

        return view
    }


    private fun findweather(){
        val cari = arguments?.getString("datacari").toString()
        mapiviewmodel.getdata(cari)
        mapiviewmodel.datarespon.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                val desc = response.body()?.weather?.get(0)?.description.toString()
                val url = response.body()?.weather?.get(0)?.icon
                val urlimage = "http://openweathermap.org/img/w/${url}.png"

                tv_d_location.setText(response.body()?.name.toString())
                tv_d_temp.setText(response.body()?.main?.temp.toString())
                tv_d_desc.setText(desc)
                tv_d_time.setText(response.body()?.timezone.toString()+ " utc")
                tv_d_feelslike.setText(response.body()?.main?.feelsLike.toString()+ " c")
                tv_d_wind.setText(response.body()?.wind?.speed.toString()+ " Km/h")
                tv_d_cloud.setText(response.body()?.clouds?.all.toString()+ " %")
                tv_d_visiblity.setText(response.body()?.visibility.toString()+ " km")
                tv_d_pressure.setText(response.body()?.main?.pressure.toString()+" mbar")
                tv_d_humid.setText(response.body()?.main?.humidity.toString()+ " %")

                Glide.with(this)
                    .asBitmap()
                    .load(urlimage)
                    .into(img_icon)

               btn_fav.setOnClickListener {
                    Toast.makeText(context,"add to favorites",Toast.LENGTH_SHORT).show()

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
                    mroomviewmodel.add(input)
                }
            }else{
                Toast.makeText(context,"Cannot find $cari", Toast.LENGTH_SHORT).show()
            }
        })

        //forecast
        //forerecyclerview


        mapiviewmodel.getforecast(cari)
        mapiviewmodel.forecastrespon.observe(viewLifecycleOwner, Observer { fore->
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

                    Log.d("forecast",datalist.toString())
                    datalist.add(forcast)
                    foreadapter.setdata(datalist)
                }
            }
        })
    }
}