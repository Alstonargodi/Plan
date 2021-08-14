package com.example.wetterbericht.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.R
import com.example.wetterbericht.fragment.adapter.todoadapter
import com.example.wetterbericht.fragment.util.addfragment
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import kotlinx.android.synthetic.main.fragment_todo.*
import kotlinx.android.synthetic.main.fragment_todo.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [homeweather.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_todo : Fragment() {
    lateinit var mtodoviewmodel : todoviewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_todo, container, false)

        val adapter = todoadapter()
        val recyclerViewhome = view.rechome
        recyclerViewhome.adapter = adapter
        recyclerViewhome.layoutManager = LinearLayoutManager(requireContext())

        mtodoviewmodel = ViewModelProvider(this).get(todoviewmodel::class.java)
        mtodoviewmodel.readdata.observe(viewLifecycleOwner, Observer { data->
            adapter.setdata(data)
        })

        view.btn_add.setOnClickListener {
            //custom dialog add fragment
            var customdialog = addfragment()
            customdialog.showsDialog
            val sp = (activity as AppCompatActivity).supportFragmentManager
            val adfragment = addfragment()
            adfragment.show(sp,"dialogadd")
        }

        return view
    }

}