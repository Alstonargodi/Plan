package com.example.wetterbericht.fragment.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.todo
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import kotlinx.android.synthetic.main.fragment_addfragment.*
import kotlinx.android.synthetic.main.fragment_addfragment.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [Addfragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addfragment : DialogFragment() {
    lateinit var mtodoviewmodel : todoviewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addfragment, container, false)
        var rootview : View = inflater.inflate(R.layout.fragment_addfragment, container, false)

        mtodoviewmodel = ViewModelProvider(this).get(todoviewmodel::class.java)

        view.btn_save.setOnClickListener {
            insertdata()
        }

        return view
    }

    //fun input data
    private fun insertdata(){
        val title = et_title.text.toString()
        val status = et_status.text.toString()
        val deadline = et_deadline.text.toString()
        val desc = et_desc.text.toString()

        val todo = todo(0,title, desc, deadline, status)
        mtodoviewmodel.add(todo)


    }

}