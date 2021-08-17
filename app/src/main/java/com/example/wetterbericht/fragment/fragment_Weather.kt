package com.example.wetterbericht.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wetterbericht.R
import com.example.wetterbericht.fragment.adapter.weatheradapter
import com.example.wetterbericht.model.room.cuaca
import com.example.wetterbericht.repo.api.mainrepo
import com.example.wetterbericht.viewmodel.api.Mainviewmodel
import com.example.wetterbericht.viewmodel.api.Vmfactory
import com.example.wetterbericht.viewmodel.room.Cuacaviewmodel
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.view.*

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_Weather.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_Weather : Fragment() {
    lateinit var mapiviewmodel : Mainviewmodel
    lateinit var mroomviewmodel : Cuacaviewmodel

    //todo add weather hisorty
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_weather, container, false)
        //get from api
        val repo = mainrepo()
        val vmfactory = Vmfactory(repo)
        mapiviewmodel = ViewModelProvider(this,vmfactory).get(Mainviewmodel::class.java) //set

        //set room
        val adapter = weatheradapter()
        val recyclerv = view.reclist
        recyclerv.adapter = adapter
        recyclerv.layoutManager = LinearLayoutManager(requireContext())
        mroomviewmodel = ViewModelProvider(this).get(Cuacaviewmodel::class.java)
        mroomviewmodel.readdata.observe(viewLifecycleOwner, Observer { response ->
            adapter.setdata(response)
        })

        view.btn_find.setOnClickListener {
            findweather()
        }
        return view
    }


    @SuppressLint("SetTextI18n")
    private fun findweather(){
        val cari = et_carikan.text.toString()
        mapiviewmodel.getdata(cari)
        mapiviewmodel.datarespon.observe(viewLifecycleOwner, Observer { response ->
            val desc = response.body()?.current?.weatherDescriptions.toString()
                ?.replace("[","")
                ?.replace("]","")

            val url = response.body()?.current?.weatherIcons.toString()
                        ?.replace("[","")
                        ?.replace("]","")

            tv_d_location.setText(response.body()?.location?.name.toString())
            tv_d_temp.setText(response.body()?.current?.temperature.toString())
            tv_d_desc.setText(desc)
            tv_d_time.setText(response.body()?.location?.localtime.toString())
            tv_d_uvindex.setText("UV index :" + response.body()?.current?.uvIndex.toString())
            tv_d_feelslike.setText("Feels like :"+ response.body()?.current?.feelslike.toString())
            tv_d_wind.setText("Wind speed :"+response.body()?.current?.windSpeed.toString())
            tv_d_cloud.setText("Cloud Cover :" + response.body()?.current?.cloudcover.toString())
            tv_d_visiblity.setText("Visibility :" + response.body()?.current?.visibility.toString())
            tv_d_presure.setText("Presure :" + response.body()?.current?.pressure.toString())
            tv_d_humid.setText("Humidity :" + response.body()?.current?.humidity.toString())

            Glide.with(this)
                .asBitmap()
                .load(url)
                .into(img_icon)

            val input = cuaca(0,
                response.body()?.location?.name.toString(),
                desc,
                Integer.parseInt(response.body()?.current?.temperature.toString()),
                Integer.parseInt(response.body()?.current?.uvIndex.toString()),
                Integer.parseInt(response.body()?.current?.humidity.toString()),
                url
            )
            mroomviewmodel.add(input)
        })
    }
}