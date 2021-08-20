package com.example.wetterbericht.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
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
class fragment_Weather : Fragment(){
    private val navargs by navArgs<fragment_WeatherArgs>()
    lateinit var mapiviewmodel : Mainviewmodel
    lateinit var mroomviewmodel : Cuacaviewmodel

    private var datalist = emptyList<cuaca>()

    //todo add weather hisorty
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_weather, container, false)
        //get from api
        val repo = mainrepo()
        val vmfactory = Vmfactory(repo)
        mapiviewmodel = ViewModelProvider(this,vmfactory).get(Mainviewmodel::class.java) //set

        //set history pencarian
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
            if(response.isSuccessful){

                val desc = response.body()?.weather?.get(0)?.description.toString()
                val url = response.body()?.weather?.get(0)?.icon
                val urlimage = "http://openweathermap.org/img/w/${url}.png"

                tv_d_location.setText(response.body()?.name.toString())
                tv_d_temp.setText(response.body()?.main?.temp.toString())
                tv_d_desc.setText(desc)
                tv_d_time.setText(response.body()?.timezone.toString())
                tv_d_feelslike.setText("Feels like  :"+ response.body()?.main?.feelsLike.toString())
                tv_d_wind.setText("Wind speed       :"+response.body()?.wind?.speed)
                tv_d_cloud.setText("Cloud Cover     :" + response.body()?.clouds?.all.toString())
                tv_d_visiblity.setText("Visibility  :" + response.body()?.visibility)
                tv_d_presure.setText("Presure       :" + response.body()?.main?.pressure.toString())
                tv_d_humid.setText("Humidity        :" + response.body()?.main?.humidity.toString())

                Glide.with(this)
                    .asBitmap()
                    .load(urlimage)
                    .into(img_icon)

                val input = cuaca(0,
                    response.body()?.name.toString(),
                    desc,
                    response.body()?.main?.temp.toString(),
                    response.body()?.main?.feelsLike.toString(),
                    Integer.parseInt(response.body()?.main?.humidity.toString()),
                    urlimage
                )
                mroomviewmodel.add(input)
            }else{
                Toast.makeText(context,"Cannot find $cari",Toast.LENGTH_SHORT).show()
            }
        })
    }
}