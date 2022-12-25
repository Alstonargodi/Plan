package com.example.wetterbericht.presentation.fragment.habits.insert

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.databinding.FragmentInsertDetailHabitsBinding
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.data.local.entity.dailyhabits.IconHabits
import com.example.wetterbericht.presentation.fragment.habits.viewmodel.HabitsViewModel
import com.example.wetterbericht.viewmodel.viewmodelfactory.ViewModelFactory

class InsertDetailHabitsFragment : Fragment() {
    private lateinit var binding : FragmentInsertDetailHabitsBinding
    private val viewModel : HabitsViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }
    private var typeColor = Color.parseColor("#FFFFFF")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertDetailHabitsBinding.inflate(layoutInflater)
        viewModel.readHabitsIcon().observe(viewLifecycleOwner){ icon->
            showHabitsIcon(icon)
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
                InsertDetailHabitsFragmentDirections.actionInsertDetailHabitsFragmentToHabitsListFragment()
            )
        }
    }

    private fun insertNewHabits(){
        val name  = binding.etInserthabitsName.text.toString()
        val duration = binding.etInserthabitsDuration.text.toString().toInt()
        val insertData = DailyHabits(
            0,
            name,
            duration.toLong(),
            "0",
            "",
            "ic_baseline_brunch_dining_24",
            typeColor
        )
        viewModel.insertHabits(insertData)
        findNavController().navigate(
            InsertDetailHabitsFragmentDirections.actionInsertDetailHabitsFragmentToHabitsListFragment()
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
    }

}