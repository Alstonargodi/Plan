package com.example.wetterbericht.view.Fragment.Todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.R
import com.example.wetterbericht.view.adapter.Recyclerview.Home.Todooutsideadapter
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import kotlinx.android.synthetic.main.fragment_activity_inside.view.*
import kotlinx.android.synthetic.main.fragment_activity_outside.view.*


class Fragment_activity_outside : Fragment() {
    lateinit var localviewmodel : todoviewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_activity_outside, container, false)
        localviewmodel = ViewModelProvider(this).get(todoviewmodel::class.java)

        val adapter = Todooutsideadapter()
        val recview = view.recview_todo_out
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(requireContext())
        localviewmodel.readoutside.observe(viewLifecycleOwner, Observer { data ->

            adapter.setdata(data)

            val count = "${adapter.itemCount} Activitas"

            view.tvact_count_outside.setText(count.toString())
        })

        return view
    }


}