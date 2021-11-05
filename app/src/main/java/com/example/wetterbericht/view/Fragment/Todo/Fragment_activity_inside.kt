package com.example.wetterbericht.view.Fragment.Todo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.R
import com.example.wetterbericht.Todo_add
import com.example.wetterbericht.view.adapter.Recyclerview.Home.Todoinsideadapter
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import kotlinx.android.synthetic.main.fragment_activity_inside.view.*


class Fragment_activity_inside : Fragment() {
    lateinit var localviewmodel : todoviewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_activity_inside, container, false)
        localviewmodel = ViewModelProvider(this).get(todoviewmodel::class.java)

        val adapter = Todoinsideadapter()

        val recview = view.rectodo_inside
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(requireContext())
        localviewmodel.readinside.observe(viewLifecycleOwner, Observer { todo ->
            adapter.setdata(todo)

            Log.d("item",adapter.itemCount.toString())

            if(adapter.itemCount == 0){
                recview.setBackgroundResource(R.drawable.emptyview)
            }else{
                adapter.setdata(todo)
            }


        })


        view.btn_add.setOnClickListener {
            startActivity(Intent(context,Todo_add::class.java))
        }
        return view
    }

}