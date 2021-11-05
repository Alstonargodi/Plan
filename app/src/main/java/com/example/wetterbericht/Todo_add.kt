package com.example.wetterbericht

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.model.room.Do.Outside
import com.example.wetterbericht.model.room.Do.subtaskoutside
import com.example.wetterbericht.model.room.Inside
import com.example.wetterbericht.model.room.subtaskinside
import com.example.wetterbericht.view.adapter.Recyclerview.Subtask.Addsubinsideadapter
import com.example.wetterbericht.view.adapter.Recyclerview.Subtask.Addsuboutsideadapter
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import kotlinx.android.synthetic.main.activity_todo_add.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Todo_add : AppCompatActivity() {
    lateinit var todovmodel: todoviewmodel
    lateinit var subtaslist : ArrayList<subtaskinside>
    lateinit var subtaskout : ArrayList<subtaskoutside>

    private var dateformat = SimpleDateFormat("dd-MMM-YYY", Locale.ENGLISH)
    private var timeformat = SimpleDateFormat("hh : mm a", Locale.ENGLISH)
    private var adapter = Addsubinsideadapter()
    private var adapterout = Addsuboutsideadapter()

    private var kategori : String = ""
    private var warna : Any = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_add)

        todovmodel = ViewModelProvider(this).get(todoviewmodel::class.java)
        val statusitem = resources.getStringArray(R.array.status)

        subtaslist = arrayListOf<subtaskinside>()
        subtaskout = arrayListOf<subtaskoutside>()

        val recview = rec_todoadd_sub
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(this)
        recview.adapter = adapterout
        recview.layoutManager = LinearLayoutManager(this)

        btn_actpick_out.setOnClickListener {
            btn_actpick_out.setBackgroundColor(Color.GRAY)
            kategori = "outside"
        }
        btn_actpick_in.setOnClickListener {
            btn_actpick_in.setBackgroundColor(Color.GRAY)
            kategori = "inside"
        }
        btn_act_tanggal.setOnClickListener {
            datepick()
        }
        btn_act_waktu.setOnClickListener {
            timepick()
        }

        spinner_act.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var warna = parent?.getItemAtPosition(position)
                if (warna!= null){
                    colorpick(warna)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        btn_addsubtask.setOnClickListener {
            setsub()
        }


        btn_addtodo.setOnClickListener {
            settodo()
        }

    }

    private fun colorpick(item : Any){
        if (item == "plain"){
            val color = Color.parseColor("#E4E4E4")
            spinner_act.setBackgroundColor(color)
            warna = color
        }else if(item == "low"){
            val color = Color.parseColor("#4DBC08")
            spinner_act.setBackgroundColor(color)
            warna = color
            Log.d("warna dipilih",warna.toString())
        }else if(item == "medium"){
            val color = Color.parseColor("#FF810D")
            spinner_act.setBackgroundColor(color)
            warna = color
        }else if (item == "high"){
            val color = Color.parseColor("#FF1B0D")
            spinner_act.setBackgroundColor(color)
            warna = color
        }
    }

    private fun datepick(){
        val kal = Calendar.getInstance()
        val datepick = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val cal = Calendar.getInstance()
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.YEAR,year)
            val setdate = dateformat.format(cal.time)
            et_todoadd_tanggal.setText(setdate)
        },
            kal.get(Calendar.YEAR),
            kal.get(Calendar.MONTH),
            kal.get(Calendar.DAY_OF_MONTH)
        )
        datepick.show()
    }

    private fun timepick(){
        val curtime = Calendar.getInstance().time
        val sekarang = dateformat.format(curtime)
        Log.d("day",sekarang.toString())


        val kal = Calendar.getInstance()
        val timepick = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val tim = Calendar.getInstance()
            tim.set(Calendar.HOUR_OF_DAY,hourOfDay)
            tim.set(Calendar.MINUTE,minute)
            val settime = timeformat.format(tim.time)
            et_todoadd_waktu.setText(settime)
        },
            kal.get(Calendar.HOUR_OF_DAY),
            kal.get(Calendar.MINUTE),
        false
        )
        timepick.show()
    }

    //todo id subtask

    private fun setsub(){
        val task = et_todoadd_subtask.text.toString()
        if (kategori == "inside"){
            val data = subtaskinside(0,task)
            subtaslist.add(data)
        }else if (kategori == "outside"){
            val out = subtaskoutside(0,task)
            subtaskout.add(out)
        }
        Log.d("test sub", subtaslist.toString())
        adapter.setdata(subtaslist)
        adapterout.setout(subtaskout)

    }

    private fun settodo(){

            if(kategori == "inside"){
                val inside = Inside(
                    et_todoadd_nama.text.toString(),
                    et_todoadd_desc.text.toString(),
                    kategori,
                    et_todoadd_tanggal.text.toString(),
                    et_todoadd_waktu.text.toString(),
                    warna.toString()
                )
                Log.d("input",kategori)
                todovmodel.addinside(inside)
                todovmodel.addsubinside(subtaslist)

            }else if(kategori == "outside"){
                val outside = Outside(
                    et_todoadd_nama.text.toString(),
                    et_todoadd_desc.text.toString(),
                    kategori,
                    et_todoadd_tanggal.text.toString(),
                    et_todoadd_waktu.text.toString(),
                    warna.toString()
                )

                Log.d("input",kategori)
                todovmodel.addoutside(outside)
                todovmodel.addsuboutside(subtaskout)
            }


    }
}