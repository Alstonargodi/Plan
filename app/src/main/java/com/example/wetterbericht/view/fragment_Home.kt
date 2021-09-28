package com.example.wetterbericht.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.R
import com.example.wetterbericht.view.adapter.todohomeadapter
import com.example.wetterbericht.view.adapter.weatherhomeadapter
import com.example.wetterbericht.viewmodel.room.Cuacaviewmodel
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import kotlinx.android.synthetic.main.fragment_home.view.*


class fragment_Home : Fragment() {
    lateinit var mroomodel : todoviewmodel
    lateinit var mcuacamodel : Cuacaviewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //weather
        val cadapter = weatherhomeadapter()
        val mrecview = view.recviewweather
        mrecview.adapter = cadapter
        mrecview.layoutManager = LinearLayoutManager(requireContext())
        mcuacamodel = ViewModelProvider(this).get(Cuacaviewmodel::class.java)
        mcuacamodel.readdata.observe(viewLifecycleOwner, Observer { cuaca ->
            cadapter.setdata(cuaca)
        })

        //to do list
        val madapter = todohomeadapter()
        val recyclerhome = view.recviewhome
        recyclerhome.adapter = madapter
        recyclerhome.layoutManager = LinearLayoutManager(requireContext())
        mroomodel = ViewModelProvider(this).get(todoviewmodel::class.java)
        mroomodel.readdata.observe(viewLifecycleOwner, Observer { data ->
            madapter.setdata(data)
        })

        return view
    }

}