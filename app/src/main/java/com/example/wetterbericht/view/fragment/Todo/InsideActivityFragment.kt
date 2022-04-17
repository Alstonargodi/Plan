package com.example.wetterbericht.view.fragment.Todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.R
import com.example.wetterbericht.view.adapter.Recyclerview.Home.Todoinsideadapter
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import kotlinx.android.synthetic.main.fragment_activity_inside.view.*


class InsideActivityFragment : Fragment() {
    private lateinit var localViewModel : todoviewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_activity_inside, container, false)
        localViewModel = ViewModelProvider(this)[todoviewmodel::class.java]

        val adapter = Todoinsideadapter()
        val recview = view.rectodo_inside
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(requireContext())
        localViewModel.readinside.observe(viewLifecycleOwner) { todo ->
            adapter.setdata(todo)
        }


        return view
    }

}