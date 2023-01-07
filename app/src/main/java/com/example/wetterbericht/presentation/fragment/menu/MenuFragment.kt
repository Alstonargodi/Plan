package com.example.wetterbericht.presentation.fragment.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wetterbericht.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding : FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        binding.apply {
            btnmenuBack.setOnClickListener {
                findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToFragmentHome()
                )
            }

            btnMenuTodo.setOnClickListener {
                findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToFragmentHome()
                )
            }

            binding.btnMenuWeather.setOnClickListener {
                findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToFragmentWeather()
                )
            }

            btnMenuSetting.setOnClickListener {
                findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToSettingsFragment()
                )
            }

            btnMenuHabits.setOnClickListener {
                findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToHabitsListFragment()
                )
            }
            btnMenuStats.setOnClickListener {
                findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToStatisticFragment()
                )
            }
        }
        return binding.root
    }


}