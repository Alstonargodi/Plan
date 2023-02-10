package com.example.wetterbericht.presentation.fragment.habits.insert

import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.data.local.entities.dailyhabits.ColorHabits
import com.example.wetterbericht.data.local.entities.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entities.dailyhabits.IconHabits
import com.example.wetterbericht.databinding.FragmentInsertHabitsBinding
import com.example.wetterbericht.helpers.ConstantTask
import com.example.wetterbericht.presentation.fragment.habits.insert.adapter.ColorRecyclerviewAdapter
import com.example.wetterbericht.presentation.fragment.habits.insert.adapter.IconHabitsRecylerViewAdapter
import com.example.wetterbericht.presentation.fragment.habits.viewmodel.HabitsViewModel
import com.example.wetterbericht.viewmodel.ViewModelFactory
import java.util.*

class InsertHabitsFragment : Fragment() {
    private lateinit var binding : FragmentInsertHabitsBinding
    private val viewModel : HabitsViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }
    private var typeColor = Color.parseColor("#FFFFFF")
    private var typeIcon = ""
    private var startTime = "00:00"
    private var timeEnd = "00:00"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertHabitsBinding.inflate(layoutInflater)
        viewModel.apply {
            readHabitsColors().observe(viewLifecycleOwner){color->
                showColorHabits(color)
            }
            readHabitsIcon().observe(viewLifecycleOwner){ icon->
                showHabitsIcon(icon)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnInsertHabits.setOnClickListener {
            insertNewHabits()
        }

        binding.btnbackInsertHabits.setOnClickListener {
            findNavController().navigate(
                InsertHabitsFragmentDirections
                .actionInsertDetailHabitsFragmentToHabitsListFragment()
            )
        }
        binding.btnHabitsTimestart.setOnClickListener {
            timePicker("end")
            timePicker("start")
        }
    }

    private fun insertNewHabits(){
        val name  = binding.etInserthabitsName.text.toString()
        val duration = binding.etInserthabitsDuration.text.toString().toInt()
        val insertData = DailyHabits(
            0,
            name,
            duration.toLong(),
            startTime,
            typeIcon,
            typeColor
        )
        viewModel.insertHabits(insertData)
        findNavController().navigate(
            InsertHabitsFragmentDirections
                .actionInsertDetailHabitsFragmentToHabitsListFragment()
        )
    }

    private fun showHabitsIcon(icon : List<IconHabits>){
        val adapter = IconHabitsRecylerViewAdapter(icon)
        val recyclerView = binding.recviewIcon
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapter.getIconName(object : IconHabitsRecylerViewAdapter.NameItemCallback{
            override fun iconCallback(name: String) {
                typeIcon = name
                val icon = requireContext().resources.getIdentifier(
                    name,
                    "drawable",
                    requireContext().packageName
                )
                binding.titleIconPicker.setCompoundDrawablesWithIntrinsicBounds(
                    0,0,icon,0,
                )
            }
        })
    }

    private fun showColorHabits(colors : List<ColorHabits>){
        val adapter = ColorRecyclerviewAdapter(colors)
        val recyclerView = binding.recviewColor
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(
            requireContext(),
            2,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapter.getColorHabits(object : ColorRecyclerviewAdapter.ColorCallback{
            override fun colorCallback(name: String) {
                typeColor = Color.parseColor(name)
                binding.titleColorPicker.setTextColor(typeColor)
            }
        }
        )
    }

    private fun timePicker(tag : String){
        val calendar = Calendar.getInstance()
        val timePick = TimePickerDialog(
            requireContext(), { _, hourOfDay, minute ->
            val timeTemp = Calendar.getInstance()
            timeTemp.set(Calendar.HOUR_OF_DAY,hourOfDay)
            timeTemp.set(Calendar.MINUTE,minute)
            val timeSet = ConstantTask.formatTime.format(timeTemp.time)
            when(tag){
                "start"->{
                    startTime = timeSet
                }
                "end" ->{
                    timeEnd = timeSet
                }
            }
            binding.btnHabitsTimestart.text = "$startTime - $timeEnd"
        },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
        timePick.show()
    }

}