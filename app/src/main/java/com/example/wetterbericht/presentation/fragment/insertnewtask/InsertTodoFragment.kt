package com.example.wetterbericht.presentation.fragment.insertnewtask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.dailytask.TodoSubTask
import com.example.wetterbericht.databinding.FragmentInsertTodoBinding
import com.example.wetterbericht.helpers.ConstantTask.formatDate
import com.example.wetterbericht.helpers.ConstantTask.formatDay
import com.example.wetterbericht.helpers.ConstantTask.formatTime
import com.example.wetterbericht.helpers.ConstantTask.todoId
import com.example.wetterbericht.helpers.ConstantTask.userId
import com.example.wetterbericht.presentation.fragment.home.adapter.SubtaskRecyclerViewAdapter
import com.example.wetterbericht.presentation.fragment.insertnewtask.adapter.ChipAdapter
import com.example.wetterbericht.presentation.fragment.insertnewtask.dialog.InsertAlarmChipFragment
import com.example.wetterbericht.viewmodel.localviewmodel.LocalViewModel
import com.example.wetterbericht.viewmodel.viewmodelfactory.ViewModelFactory
import kotlinx.coroutines.launch
import java.util.*
import kotlin.properties.Delegates

class InsertTodoFragment : Fragment(){
    private var _binding : FragmentInsertTodoBinding? = null
    private val binding get()= _binding!!

    private val roomViewModel : LocalViewModel by viewModels{ ViewModelFactory.getInstance(requireContext())}
    private var numberDay = 0
    private var millisDay : Long = 0
    private var taskList = arrayListOf<TodoSubTask>()
    private var leveColour = Color.parseColor("#383636")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertTodoBinding.inflate(layoutInflater)
        readChipReminder()
        binding.etInsertName.setTextColor(leveColour)


        binding.etInsertName.addTextChangedListener ( object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text != null) {
                    if (text.isEmpty()){
                        binding.btnColorPicker.visibility = View.GONE
                        showColorPalette(false)
                    }else{
                        binding.btnColorPicker.visibility = View.VISIBLE
                    }
                }else{
                    binding.btnColorPicker.visibility = View.GONE
                }
            }
        })

        binding.etInserttodoSubtask.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text != null) {
                    if (text.isEmpty()){
                        binding.btnAddsubtask.visibility = View.GONE
                    }else{
                        binding.btnAddsubtask.visibility = View.VISIBLE
                    }
                }else{
                    binding.btnAddsubtask.visibility = View.GONE
                }
            }

        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnAddchipalarm.setOnClickListener {
                insertNewStartTimeChip()
            }

            btnAddsubtask.setOnClickListener {
                insertNewSubtask()
            }
            btnTodoTimestart.setOnClickListener {
                timePicker("start")
            }
            btnTodoTimeend.setOnClickListener {
                timePicker("end")
            }
            btnTodoDatestart.setOnClickListener {
                datePicker()
            }

            binding.btnColorPicker.setOnClickListener {
                colorPalette()
            }

            btnaddtodo.setOnClickListener {
                insertTodo()
            }
        }
    }

    private fun readChipReminder(){
        val chipAdapter = ChipAdapter()
        val sideRecyclerView = binding.rvChip
        sideRecyclerView.adapter = chipAdapter
        sideRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        roomViewModel.readAlarmChip().observe(viewLifecycleOwner){
            chipAdapter.setData(it)
        }

        chipAdapter.onTimeCallback(object : ChipAdapter.timeCallBack{
            override fun timeCallBack(time: String) {
               timeStart(time)
            }
        })
    }

    private fun timeStart(time : String){
        binding.btnTodoTimestart.text = time
    }

    private fun readSubtask(){
        val subTaskAdapter = SubtaskRecyclerViewAdapter()
        val taskRecyclerView = binding.rvSubtask
        taskRecyclerView.adapter = subTaskAdapter
        taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        subTaskAdapter.submitList(taskList)
    }


    private fun insertTodo(){
        val taskName = binding.etInsertName.text.toString()
        val description = binding.inserttodoDescription.text.toString()
        val startTime = binding.btnTodoTimestart.text.toString()
        val endTime = binding.btnTodoTimeend.text.toString()
        val dateStart = binding.btnTodoDatestart.text.toString()

        val insertTask = TodoLocal(
            taskID = todoId,
            title = taskName,
            description = description,
            levelColor = leveColour,
            dateStart = dateStart,
            dateDay = numberDay,
            dateDueMillis = millisDay,
            notificationInterval = 20,
            startTime = startTime,
            endTime = endTime,
            isComplete = false
        )

        taskList.forEach {
            val insertSubtask = TodoSubTask(
                id = it.id,
                title = it.title,
                isComplete = it.isComplete,
                todoId = it.todoId
            )
            roomViewModel.insertSubtask(insertSubtask)
        }
        roomViewModel.insertTodoLocal(insertTask)
        findNavController().navigate(
            InsertTodoFragmentDirections.actionInsertTodoFragmentToFragmentHome()
        )
    }


    private fun insertNewStartTimeChip(){
        val insertDialog = InsertAlarmChipFragment()
        val supportFragment = requireActivity().supportFragmentManager
        insertDialog.show(supportFragment,"dialog")
        insertDialog.onTimeCallback(object : InsertAlarmChipFragment.timeCallBack{
            override fun timeCallBack(time: String) {
                timeStart(time)
            }
        })
    }


    private fun insertNewSubtask(){
        val taskName = binding.etInserttodoSubtask.text.toString()
        val insertData = TodoSubTask(
            id = 0,
            title = taskName,
            isComplete = false,
            todoId = userId
        )
        taskList.add(insertData)
        lifecycleScope.launch {
            readSubtask()
        }
    }

    private fun timePicker(tag : String){
        val calendar = Calendar.getInstance()
        val timePick = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
            val timeTemp = Calendar.getInstance()
            timeTemp.set(Calendar.HOUR_OF_DAY,hourOfDay)
            timeTemp.set(Calendar.MINUTE,minute)
            val timeSet = formatTime.format(timeTemp.time)
            when(tag){
                "start"->{
                    binding.btnTodoTimestart.text = timeSet
                }
                "end"->{
                    binding.btnTodoTimeend.text = timeSet
                }
            }
        },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
        timePick.show()
    }

    private fun datePicker(){
        val calenderInstance = Calendar.getInstance()
        val datePicker = DatePickerDialog(requireContext(),{_,year,month,dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.YEAR,year)
            val setDate = formatDate.format(calendar.time)
            binding.btnTodoDatestart.text = setDate.toString()
            numberDay = formatDay.format(calendar.time).toInt()
            millisDay = calendar.timeInMillis
        },
            calenderInstance.get(Calendar.YEAR),
            calenderInstance.get(Calendar.MONTH),
            calenderInstance.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun colorPalette(){
        showColorPalette(true)
        binding.apply {
            colorNameBlack.apply {
                setOnClickListener {
                    leveColour = Color.parseColor("#383636")
                    changeTextColor()
                }
            }
            colorNameBlue.apply {
                setOnClickListener {
                    leveColour = Color.parseColor("#2196F3")
                    changeTextColor()
                }
            }
            colorNameOrange.apply {
                setOnClickListener {
                    leveColour = Color.parseColor("#FF5722")
                    changeTextColor()
                }
            }
            colorNamePurple.apply {
                setOnClickListener {
                    leveColour = Color.parseColor("#3F51B5")
                    changeTextColor()
                }
            }
        }
    }

    private fun changeTextColor(){
        Handler(Looper.getMainLooper()).postDelayed({
            binding.etInsertName.setTextColor(leveColour)
        },200)
    }

    private fun showColorPalette(cond : Boolean){
        if (cond){
            binding.apply {
                colorNameBlack.apply { visibility = View.VISIBLE }
                colorNameBlue.apply { visibility = View.VISIBLE }
                colorNameOrange.apply { visibility = View.VISIBLE }
                colorNamePurple.apply { visibility = View.VISIBLE }
            }
        }else{
            binding.apply {
                colorNameBlack.apply { visibility = View.GONE }
                colorNameBlue.apply { visibility = View.GONE }
                colorNameOrange.apply { visibility = View.GONE }
                colorNamePurple.apply { visibility = View.GONE }
            }
        }
    }
}