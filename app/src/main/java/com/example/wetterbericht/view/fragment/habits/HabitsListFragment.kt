package com.example.wetterbericht.view.fragment.habits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentHabitsListBinding


class HabitsListFragment : Fragment() {
    private lateinit var binding : FragmentHabitsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitsListBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddHabits.setOnClickListener {
            findNavController().navigate(
                HabitsListFragmentDirections.actionHabitsListFragmentToInsertDetailHabitsFragment()
            )
        }

        binding.btnbackHabits.setOnClickListener {
            findNavController().navigate(
                HabitsListFragmentDirections.actionHabitsListFragmentToMenuFragment()
            )
        }

    }


}