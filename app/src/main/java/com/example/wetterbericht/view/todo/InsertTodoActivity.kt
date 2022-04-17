package com.example.wetterbericht.view.todo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.ActivityInserttodoBinding
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.viewmodel.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.obtainViewModel
import kotlinx.android.synthetic.main.activity_inserttodo.*
import java.text.SimpleDateFormat
import java.util.*

class InsertTodoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInserttodoBinding

    private lateinit var localViewModel : LocalViewModel

    private var dateFormat = SimpleDateFormat("dd-MMM-YYY", Locale.ENGLISH)
    private var timeFormat = SimpleDateFormat("hh : mm a", Locale.ENGLISH)

    private var category : String = ""
    private var colour : Any = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInserttodoBinding.inflate(layoutInflater)
        localViewModel = obtainViewModel(this)

        setContentView(binding.root)
        window.statusBarColor = getColor(R.color.main)


        binding.apply {
            btnStatpickLow.setOnClickListener {
                btnStatpickLow.setBackgroundResource(R.drawable.bgchoice_stat_low)
                btnStatpickMed.setBackgroundResource(R.drawable.bg_stroke_choice)
                btnStatpickHigh.setBackgroundResource(R.drawable.bg_stroke_choice)
                colorPicker("low")
            }
            btnStatpickMed.setOnClickListener {
                btnStatpickMed.setBackgroundResource(R.drawable.bgchoice_stat_med)
                btnStatpickLow.setBackgroundResource(R.drawable.bgchoice_stat_low)
                btnStatpickHigh.setBackgroundResource(R.drawable.bg_stroke_choice)
                colorPicker("medium")
            }
            btnStatpickHigh.setOnClickListener {
                btnStatpickHigh.setBackgroundResource(R.drawable.bgchoice_stat_high)
                btnStatpickMed.setBackgroundResource(R.drawable.bg_stroke_choice)
                btnStatpickLow.setBackgroundResource(R.drawable.bg_stroke_choice)
                colorPicker("high")
            }

            etTodoaddTanggal.setOnClickListener {
                datePicker()
            }
            etTodoaddWaktu.setOnClickListener {
                timePicker()
            }

            btn_addtodo.setOnClickListener {
                insertTodo()
            }

        }
    }

    private fun colorPicker(status : String){
        if (status == ""){
            val color = Color.parseColor("#E4E4E4")
            colour = color
        }else if(status == "low"){
            val color = Color.parseColor("#4DBC08")
            colour = color
            Log.d("warna dipilih",colour.toString())
        }else if(status == "medium"){
            val color = Color.parseColor("#FF810D")
            colour = color
        }else if (status == "high"){
            val color = Color.parseColor("#FF1B0D")
            colour = color
        }
    }

    private fun datePicker(){
        val cInstance = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val calInstance = Calendar.getInstance()
            calInstance.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            calInstance.set(Calendar.MONTH,month)
            calInstance.set(Calendar.YEAR,year)
            binding.etTodoaddTanggal.setText(dateFormat.format(calInstance.time))
        },
            cInstance.get(Calendar.YEAR),
            cInstance.get(Calendar.MONTH),
            cInstance.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun timePicker(){
        val curtime = Calendar.getInstance().time
        val sekarang = dateFormat.format(curtime)
        Log.d("day",sekarang.toString())


        val calendar = Calendar.getInstance()
        val timePIck = TimePickerDialog(this, { _, hourOfDay, minute ->
            val tim = Calendar.getInstance()
            tim.set(Calendar.HOUR_OF_DAY,hourOfDay)
            tim.set(Calendar.MINUTE,minute)
            binding.etTodoaddWaktu.setText(timeFormat.format(tim.time))
        },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
        false
        )
        timePIck.show()
    }



    private fun insertTodo(){
        val temp = TodoLocal(
            et_todoadd_nama.text.toString(),
            et_todoadd_desc.text.toString(),
            category,
            et_todoadd_tanggal.text.toString(),
            et_todoadd_waktu.text.toString(),
            colour.toString()
        )
        localViewModel.insertTodoLocal(temp)

        Toast.makeText(this,"Sucess add inside activity",Toast.LENGTH_SHORT).show()
    }
}