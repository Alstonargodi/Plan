package com.example.wetterbericht.presentation.fragment.habits.cardview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.wetterbericht.databinding.FragmentHabitsCardViewBinding
import com.example.wetterbericht.presentation.fragment.habits.habistlistfragment.adapter.HabitsCardViewAdapter
import com.example.wetterbericht.presentation.fragment.habits.viewmodel.HabitsViewModel
import com.example.wetterbericht.viewmodel.viewmodelfactory.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator


class HabitsCardViewFragment : Fragment() {
    private lateinit var binding : FragmentHabitsCardViewBinding
    private val viewModel : HabitsViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitsCardViewBinding.inflate(layoutInflater)

        showCard()

        return binding.root
    }

    private fun showCard(){
        val viewPager = binding.viewpagerHabits
        val adapter = HabitsCardViewAdapter{

        }
        viewPager.adapter = adapter

        viewModel.readHabits().observe(viewLifecycleOwner){
            it.forEach { data ->
                adapter.submitData(HabitsCardViewAdapter.PageType.HIGH,data)
            }
        }
        val tabsLayout = binding.tablayoutHabitsRandom
        TabLayoutMediator(tabsLayout,viewPager){ tab,position ->
            tab.text = "${position + 1}"
        }.attach()
    }


}