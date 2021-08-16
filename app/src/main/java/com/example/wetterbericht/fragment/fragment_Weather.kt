package com.example.wetterbericht.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wetterbericht.R
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

        //get from api
        val repo = mainrepo()
        val vmfactory = Vmfactory(repo)
        mapiviewmodel = ViewModelProvider(this,vmfactory).get(Mainviewmodel::class.java) //set

        //set room
        mroomviewmodel = ViewModelProvider(this).get(Cuacaviewmodel::class.java)

        val view =  inflater.inflate(R.layout.fragment_weather, container, false)
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
            tv_d_location.setText(response.body()?.location?.name.toString())
            tv_d_temp.setText(response.body()?.current?.temperature.toString())
            tv_d_desc.setText(response.body()?.current?.weatherDescriptions.toString())
            tv_d_time.setText(response.body()?.location?.localtime.toString())
            tv_d_uvindex.setText("UV index :" + response.body()?.current?.uvIndex.toString())
            tv_d_feelslike.setText("Feels like :"+ response.body()?.current?.feelslike.toString())
            tv_d_wind.setText("Wind speed :"+response.body()?.current?.windSpeed.toString())
            tv_d_cloud.setText("Cloud Cover :" + response.body()?.current?.cloudcover.toString())
            tv_d_visiblity.setText("Visibility :" + response.body()?.current?.visibility.toString())
            tv_d_presure.setText("Presure :" + response.body()?.current?.pressure.toString())
            tv_d_humid.setText("Humidity :" + response.body()?.current?.humidity.toString())
        })
    }

}