package com.example.wetterbericht.view.fragment.habits.insert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentInsertDetailHabitsBinding
import com.example.wetterbericht.model.local.entity.HabitsLocal
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.ViewModelFactory


class InsertDetailHabitsFragment : Fragment() {

    private lateinit var binding : FragmentInsertDetailHabitsBinding
    private val localViewModel : LocalViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInsertDetailHabitsBinding.inflate(layoutInflater)

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

        val insertData = HabitsLocal(
            0,
            name,
            duration
        )

        localViewModel.insertHabits(insertData)


    }

}