package com.example.wetterbericht.presentation.fragment.insertnewtask.dialog

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.wetterbericht.databinding.FragmentInsertAlarmChipBinding
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.presentation.fragment.insertnewtask.InsertTodoViewModel
import com.example.wetterbericht.viewmodel.viewmodelfactory.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class InsertAlarmChipFragment : DialogFragment() {
    private lateinit var binding : FragmentInsertAlarmChipBinding

    private val roomViewModel : InsertTodoViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }

    private var formatTime = SimpleDateFormat("HH:mm", Locale.ENGLISH)

    private lateinit var timeCallback : timeCallBack

    fun onTimeCallback(timeCallBack: timeCallBack){
        this.timeCallback = timeCallBack
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertAlarmChipBinding.inflate(layoutInflater)


        binding.etNewChipTime.setOnClickListener {
            timePicker()
        }
        binding.btnAddchip.setOnClickListener {
            setChip()
            this.dismiss()
        }

        return binding.root
    }

    private fun timePicker(){
        val calendar = Calendar.getInstance()
        val timePick = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
            val timeTemp = Calendar.getInstance()
            timeTemp.set(Calendar.HOUR_OF_DAY,hourOfDay)
            timeTemp.set(Calendar.MINUTE,minute)
            val timeSet = formatTime.format(timeTemp.time)
            binding.etNewChipTime.text = timeSet
        },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
        timePick.show()
    }

    private fun setChip(){
        val name = binding.etNewChipName.text.toString()
        val time = binding.etNewChipTime.text.toString()

        val chip = ChipAlarm(
            name,
            time,
            1
        )

        roomViewModel.insertAlarmChip(chip)
        timeCallback.timeCallBack(time)
    }

    interface timeCallBack{
        fun timeCallBack(time : String)
    }
}