package com.example.wetterbericht.view.fragment.addnewtask.insert

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.databinding.FragmentInsertTodoBinding
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.TodoSubTask
import com.example.wetterbericht.view.fragment.home.adapter.SubTaskAdapter
import com.example.wetterbericht.view.fragment.addnewtask.adapter.ChipAdapter
import com.example.wetterbericht.view.fragment.addnewtask.dialog.InsertAlarmChipFragment
import com.example.wetterbericht.view.fragment.addnewtask.dialog.InsertTagFragment
import com.example.wetterbericht.util.TaskReminder
import com.example.wetterbericht.util.TaskReminder.Companion.type_one_time
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.ViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates
import kotlin.streams.asSequence


class InsertTodoFragment : Fragment(){
    private lateinit var binding : FragmentInsertTodoBinding
    private val roomViewModel : LocalViewModel by viewModels{ ViewModelFactory.getInstance(requireContext())}

    private var formatTime = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    private var formatDate = SimpleDateFormat("yyy-MM-dd", Locale.getDefault())

    private var taskList = arrayListOf<TodoSubTask>()
    private val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    private var userId = Random().ints(10, 0, source.length)
        .asSequence()
        .map(source::get)
        .joinToString("")


    private lateinit var alarm : String
    private var leveColour by Delegates.notNull<Int>()
    private lateinit var taskReminder : TaskReminder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertTodoBinding.inflate(layoutInflater)

        taskReminder = TaskReminder()

        readChipReminder()


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddchipalarm.setOnClickListener {
            showNewAddAlarm()
        }

        binding.addtag.setOnClickListener {
            showAddNewTag()
        }


        binding.btnAddsubtask.setOnClickListener {
            newSubtask()
        }

        binding.btnaddtodo.setOnClickListener {
            insertTodo()
        }

        binding.btnTodoTimestart.setOnClickListener {
            timePicker("start")
        }

        binding.btnTodoTimeend.setOnClickListener {
            timePicker("end")
        }

        binding.btnTodoDatestart.setOnClickListener {
            datePicker()
        }
    }


    private fun readChipReminder(){
        val adapter = ChipAdapter()
        val sideRecyclerView = binding.rvChip
        sideRecyclerView.adapter = adapter
        sideRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        roomViewModel.readAlarmChip().observe(viewLifecycleOwner){
            adapter.setData(it)
        }

        adapter.onTimeCallback(object : ChipAdapter.timeCallBack{
            override fun timeCallBack(time: String) {
               deadlineTime(time)
            }
        })
    }

    private fun deadlineTime(time : String){
        binding.etDeadlineTodo.apply {
            visibility = View.VISIBLE
            text = "notif me every $time minute"
            alarm = time
        }
    }

    private fun readSubtask(){
        val adapter = SubTaskAdapter()
        val taskRecyclerView = binding.rvSubtask
        taskRecyclerView.adapter = adapter
        taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter.submitList(taskList)
    }


    private fun insertTodo(){
        val name = binding.inserttodoName.text.toString()
        val description = binding.inserttodoDescription.text.toString()
        val levelTitle = binding.addtag.text.toString()
        val startTime = binding.tvTodoTimestart.text.toString()
        val endTime = binding.tvTodoTimeend.text.toString()
        val dateStart = binding.btnTodoDatestart.text.toString()


        val tempData = TodoLocal(
            userId,
            name,
            description,
            levelTitle,
            leveColour,
            dateStart,
            alarm.toInt(),
            startTime,
            endTime,
            false
        )

        taskList.forEach {
            val tempSubtask = TodoSubTask(
                it.id,
                it.title,
                it.isComplete,
                it.todoId
            )
            roomViewModel.insertSubtask(tempSubtask)
        }
        roomViewModel.insertTodoLocal(tempData)

        lifecycleScope.launch {
            setAlarm(name)
        }
    }

    private suspend fun setAlarm(name : String){
        delay(2000L)
        roomViewModel.readTodo(name).observe(viewLifecycleOwner){
            showToast("add new task")
            val alarmId= (1..200).random()
            taskReminder.setOneAlarm(
                requireContext(),
                type_one_time,
                it[0].dateStart,
                it[0].endTime,
                it[0].title,
                alarmId
            )
        }
    }

    private fun showNewAddAlarm(){
        val dialog = InsertAlarmChipFragment()
        val sFragment= requireActivity().supportFragmentManager
        dialog.show(sFragment,"dialog")
        dialog.onTimeCallback(object : InsertAlarmChipFragment.timeCallBack{
            override fun timeCallBack(time: String) {
                deadlineTime(time)
            }
        })
    }

    private fun showAddNewTag(){
        val dialog = InsertTagFragment()
        val supportFragment= requireActivity().supportFragmentManager
        dialog.show(supportFragment,"dialog")
        dialog.onTagCallBack(object : InsertTagFragment.onTagCallback{
            override fun tagCallBack(name: String, color: Int) {
                binding.addtag.text = "#$name"
                binding.addtag.setTextColor(Color.WHITE)
                binding.addtag.setBackgroundColor(color)
                leveColour = color
            }
        })
    }

    private fun newSubtask(){
        val task = binding.inserttodoSubtask.text.toString()
        val temp = TodoSubTask(
            0,
            task,
            false,
            userId
        )
        taskList.add(temp)
        lifecycleScope.launch {
            readSubtask()
        }
    }

    private fun showToast(title : String){
        Toast.makeText(requireContext(),title,Toast.LENGTH_SHORT).show()
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
                    binding.tvTodoTimestart.text = timeSet
                }
                "end"->{
                    binding.tvTodoTimeend.text = timeSet
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
        val instance = Calendar.getInstance()
        val datePicker = DatePickerDialog(requireContext(),{_,year,month,dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.YEAR,year)
            val setdate = formatDate.format(calendar.time)
            binding.btnTodoDatestart.text = setdate.toString()
        },
            instance.get(Calendar.YEAR),
            instance.get(Calendar.MONTH),
            instance.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }



}