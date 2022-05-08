package com.example.wetterbericht.view.insert.insert

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.databinding.FragmentInsertTodoBinding
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.TodoSubTask
import com.example.wetterbericht.view.home.adapter.SubTaskAdapter
import com.example.wetterbericht.view.insert.adapter.ChipAdapter
import com.example.wetterbericht.view.insert.dialog.InsertAlarmChipFragment
import com.example.wetterbericht.view.insert.dialog.InsertTagFragment
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.obtainViewModel
import kotlinx.android.synthetic.main.fragment_insert_todo.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.streams.asSequence


class InsertTodoFragment : Fragment() {
    private lateinit var binding : FragmentInsertTodoBinding
    private lateinit var localViewModel: LocalViewModel
    private var taskList = arrayListOf<TodoSubTask>()
    private val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private var userId = Random().ints(10, 0, source.length)
        .asSequence()
        .map(source::get)
        .joinToString("")

    private lateinit var alarm : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertTodoBinding.inflate(layoutInflater)
        localViewModel = obtainViewModel(requireActivity())

        readChipAlarm()





        binding.btnAddchipalarm.setOnClickListener {
            val dialog = InsertAlarmChipFragment()
            val sFragment= requireActivity().supportFragmentManager
            dialog.show(sFragment,"dialog")
            dialog.onTimeCallback(object : InsertAlarmChipFragment.timeCallBack{
                override fun timeCallBack(time: String) {
                    deadlineTime(time)
                }
            })
        }

        binding.addtag.setOnClickListener {
            val dialog = InsertTagFragment()
            val sFragment= requireActivity().supportFragmentManager
            dialog.show(sFragment,"dialog")
            dialog.onTagCallBack(object : InsertTagFragment.onTagCallback{
                override fun tagCallBack(name: String, color: Int) {
                    binding.addtag.text = "#$name"
                    binding.addtag.setTextColor(Color.WHITE)
                    binding.addtag.setBackgroundColor(color)
                }
            })
        }


        binding.btnAddsubtask.setOnClickListener {
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

        binding.btnaddtodo.setOnClickListener {
            insertTodo()
        }

        return binding.root
    }

    private fun readChipAlarm(){
        val adapter = ChipAdapter()
        val sideRv = binding.rvChip
        sideRv.adapter = adapter
        sideRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        localViewModel.readAlarmChip()
        localViewModel.responseAlarmChip.observe(viewLifecycleOwner){
            adapter.setData(it)
        }

        adapter.onTimeCallback(object : ChipAdapter.timeCallBack{
            override fun timeCallBack(time: String) {
               deadlineTime(time)
                alarm = time
            }
        })
    }

    private fun deadlineTime(time : String){
        binding.etDeadlineTodo.apply {
            visibility = View.VISIBLE
            text = "Deadline set at $time"
        }
    }

    private fun readSubtask(){
        val adapter = SubTaskAdapter()
        val recView = binding.rvSubtask
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireContext())

        adapter.submitList(taskList)
    }


    private fun insertTodo(){
        val name = binding.inserttodoName.text.toString()
        val description = binding.inserttodoDescription.text.toString()
        val tag = binding.addtag.text.toString()

        val tempData = TodoLocal(
            userId,
            name,
            description,
            tag,
            getDate(),
            alarm,
            false
        )



        taskList.forEach {
            val tempSubtask = TodoSubTask(
                it.id,
                it.title,
                it.isComplete,
                it.todoId
            )
            localViewModel.insertSubtask(tempSubtask)
            Log.d("insert todo",tempSubtask.toString())
        }
        Log.d("insert todo",tempData.toString())
        localViewModel.insertTodoLocal(tempData)
    }

    private fun getDate(): String{
        val formatter = LocalDate.now().plusDays(1)
        return formatter.toString()
    }








    companion object{
        const val time_key = "time_picker"
    }

}