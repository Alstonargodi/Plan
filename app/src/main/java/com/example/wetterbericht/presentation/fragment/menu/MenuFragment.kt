package com.example.wetterbericht.presentation.fragment.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wetterbericht.databinding.FragmentMenuBinding
import com.example.wetterbericht.presentation.activity.TestComposeActivity

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
//                findNavController().navigate(
//                    MenuFragmentDirections.actionMenuFragmentToSettingsFragment()
//                )
                startActivity(Intent(activity, TestComposeActivity::class.java))
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