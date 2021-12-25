package com.example.wetterbericht.view.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.R
import com.example.wetterbericht.view.adapter.Tab.Tabtodoadapter
import com.example.wetterbericht.view.adapter.weatherhomeadapter
import com.example.wetterbericht.viewmodel.room.Cuacaviewmodel
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.view.*

class Fragment_Home : Fragment() {
    lateinit var mroomodel : todoviewmodel
    lateinit var mcuacamodel : Cuacaviewmodel

    lateinit var tabadapter : Tabtodoadapter
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

            for(i in 0 until cuaca.size){
                val desc = cuaca[i].desc
                if(desc == "few clouds"){

                }else if ( desc == "overcast clouds"){

                }else{

                }
            }
        })

        val fragment = (activity as FragmentActivity).supportFragmentManager
        val viewpager = view.viewp_act_home
        val tablay = view.tabp_todo_home
        tabadapter = Tabtodoadapter(fragment,lifecycle)
        viewpager.adapter = tabadapter

        TabLayoutMediator(tablay,viewpager){tab,position ->
            when(position){
                0 ->{
                    tab.text = "inside"
                }
                1 ->{
                    tab.text = "outside"
                }
            }
        }.attach()


        return view
    }

}