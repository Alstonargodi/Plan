package com.example.wetterbericht.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wetterbericht.R
import com.example.wetterbericht.fragment.adapter.weatheradapter
import com.example.wetterbericht.fragment.util.addweather
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
        view.btn_go.setOnClickListener {
            val datacari = et_seacrh.text.toString()
            val bunder = bundleOf("datacari" to datacari)
            findNavController().navigate(R.id.action_fragment_weather_to_addweather,bunder)
        }

        return view
    }
}