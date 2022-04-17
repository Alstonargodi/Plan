package com.example.wetterbericht.view.fragment.Todo

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
import kotlinx.android.synthetic.main.fragment_activity_outside.view.*


class OutsideActivityFragment : Fragment() {
    private lateinit var localViewModel : todoviewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_activity_outside, container, false)
        localViewModel = ViewModelProvider(this)[todoviewmodel::class.java]

        val adapter = Todooutsideadapter()
        val recview = view.recview_todo_out
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(requireContext())
        localViewModel.readoutside.observe(viewLifecycleOwner, Observer { data ->

            adapter.setdata(data)

            val count = "${adapter.itemCount} Activitas"

            view.tvact_count_outside.setText(count.toString())
        })

        return view
    }


}