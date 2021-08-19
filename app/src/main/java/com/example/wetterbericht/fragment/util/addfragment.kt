package com.example.wetterbericht.fragment.util

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.todo
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import kotlinx.android.synthetic.main.fragment_addfragment.*
import kotlinx.android.synthetic.main.fragment_addfragment.view.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [Addfragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addfragment : DialogFragment(){
    lateinit var mtodoviewmodel : todoviewmodel
    private var formatdate = SimpleDateFormat("dd/MMM/YYYY", Locale.ENGLISH)
    private var formattime = SimpleDateFormat("hh : mm a", Locale.ENGLISH)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addfragment, container, false)
        var rootview : View = inflater.inflate(R.layout.fragment_addfragment, container, false)

        mtodoviewmodel = ViewModelProvider(this).get(todoviewmodel::class.java)

        view.btn_save.setOnClickListener {
            insertdata()
        }
        view.btn_settime.setOnClickListener {
            settime()
        }
        return view
    }
    private fun settime(){
        //set date
        val instCal = Calendar.getInstance()
        val datepicker = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val kalender = Calendar.getInstance()
            kalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            kalender.set(Calendar.MONTH,month)
            kalender.set(Calendar.YEAR,year)
            val setdate = formatdate.format(kalender.time)
            et_deadline.setText(setdate)
        }, instCal.get(Calendar.YEAR),instCal.get(Calendar.MONTH),instCal.get(Calendar.DAY_OF_MONTH))
        datepicker.show()

        //set time
        val timepicker = TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val time = Calendar.getInstance()
            time.set(Calendar.HOUR_OF_DAY,hourOfDay)
            time.set(Calendar.MINUTE,minute)
            val setitme = formattime.format(time.time)
            tv_time.setText(setitme)
        }, instCal.get(Calendar.HOUR_OF_DAY), instCal.get(Calendar.MINUTE),false)
        timepicker.show()
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