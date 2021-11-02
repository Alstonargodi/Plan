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
import com.example.wetterbericht.model.room.todo
import com.example.wetterbericht.view.adapter.Tab.Tabtodoadapter
import com.example.wetterbericht.view.adapter.todoadapter
import com.example.wetterbericht.view.adapter.weatheradapter
import com.example.wetterbericht.view.util.addfragment
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_todo.view.*


class Fragment_todo : Fragment() {
    lateinit var mtodoviewmodel : todoviewmodel
    lateinit var cuacaadapter : weatheradapter
    private var todolist = emptyList<todo>()

    lateinit var adapterpager : Tabtodoadapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_todo, container, false)

        val fragmentmanager = (activity as FragmentActivity).supportFragmentManager
        val viewpager = view.viewp_todo
        val tabpager = view.tabp_todo
        adapterpager = Tabtodoadapter(fragmentmanager,lifecycle)
        viewpager.adapter = adapterpager

        TabLayoutMediator(tabpager,viewpager){tab,position ->
            when(position){
                0 ->{
                    tab.text = "outside"
                }
                1 ->{
                    tab.text = "inside"
                }
            }
        }.attach()




//        view.btn_add.setOnClickListener {
//            //custom dialog add fragment
//            var customdialog = addfragment()
//            customdialog.showsDialog
//            val sp = (activity as AppCompatActivity).supportFragmentManager
//            val adfragment = addfragment()
//            adfragment.show(sp,"dialogadd")
//        }

        return view
    }
}