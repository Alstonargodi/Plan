package com.example.wetterbericht.view.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.Detail_weather
import com.example.wetterbericht.R
import com.example.wetterbericht.model.repo.api.mainrepo
import com.example.wetterbericht.model.room.cuaca
import com.example.wetterbericht.view.adapter.weatheradapter
import com.example.wetterbericht.view.util.updatefragmentArgs
import com.example.wetterbericht.viewmodel.api.Mainviewmodel
import com.example.wetterbericht.viewmodel.api.Vmfactory
import com.example.wetterbericht.viewmodel.room.Cuacaviewmodel
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.view.*

class Fragment_Weather : Fragment(){
    private val navargs by navArgs<updatefragmentArgs>()
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
        mapiviewmodel = ViewModelProvider(this, vmfactory).get(Mainviewmodel::class.java) //set

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
            val intent = Intent(context,Detail_weather::class.java)

            intent.putExtra("lokasi",datacari)
            startActivity(intent)
        }

        return view
    }
}