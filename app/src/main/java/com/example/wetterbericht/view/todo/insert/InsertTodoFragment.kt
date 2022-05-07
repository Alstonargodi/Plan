package com.example.wetterbericht.view.todo.insert

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.databinding.FragmentInsertTodoBinding
import com.example.wetterbericht.view.todo.adapter.ChipAdapter
import com.example.wetterbericht.view.todo.dialog.InsertAlarmChipFragment
import com.example.wetterbericht.view.todo.dialog.InsertTagFragment
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.obtainViewModel
import kotlinx.android.synthetic.main.fragment_insert_todo.*


class InsertTodoFragment : Fragment() {
    private lateinit var binding : FragmentInsertTodoBinding
    private lateinit var localViewModel: LocalViewModel


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
                    binding.addtag.setBackgroundColor(color)
                }
            })
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
            }
        })
    }

    private fun deadlineTime(time : String){
        binding.etDeadlineTodo.apply {
            visibility = View.VISIBLE
            text = "Deadline set at $time"
        }
    }

    companion object{
        const val time_key = "time_picker"
    }

}