package com.example.wetterbericht.view.fragment.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {
    private lateinit var binding : FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)

        binding.btnmenuBack.setOnClickListener {
            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToFragmentHome()
            )
        }

        binding.btnMenuTodo.setOnClickListener {
            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToFragmentHome()
            )
        }

        binding.btnMenuWeather.setOnClickListener {
            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToFragmentWeather()
            )
        }

        binding.btnMenuSetting.setOnClickListener {
            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToSettingsFragment()
            )
        }

        binding.btnMenuHabits.setOnClickListener {
            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToHabitsListFragment()
            )
        }

        return binding.root
    }


}