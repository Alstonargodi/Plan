package com.example.wetterbericht.view.insert.dialog

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.wetterbericht.databinding.FragmentInsertAlarmChipBinding
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.obtainViewModel
import java.text.SimpleDateFormat
import java.util.*


class InsertAlarmChipFragment : DialogFragment() {
    private lateinit var binding : FragmentInsertAlarmChipBinding
    private lateinit var localViewModel: LocalViewModel

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
        localViewModel = obtainViewModel(requireActivity())

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
        val timePick = TimePickerDialog(requireContext(), { view, hourOfDay, minute ->
            val timeTemp = Calendar.getInstance()
            timeTemp.set(Calendar.HOUR_OF_DAY,hourOfDay)
            timeTemp.set(Calendar.MINUTE,minute)
            val settime = formatTime.format(timeTemp.time)
            binding.etNewChipTime.text = settime
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
            time
        )

        localViewModel.insertAlarmChip(chip)
        timeCallback.timeCallBack(time)
    }

    interface timeCallBack{
        fun timeCallBack(time : String)
    }
}