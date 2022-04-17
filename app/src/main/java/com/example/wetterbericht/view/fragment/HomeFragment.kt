package com.example.wetterbericht.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.databinding.FragmentHomeBinding
import com.example.wetterbericht.view.adapter.Tab.Tabtodoadapter
import com.example.wetterbericht.view.adapter.weatherhomeadapter
import com.example.wetterbericht.viewmodel.room.RoomViewModel
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var cuacaViewModel : RoomViewModel
    private lateinit var tabAdapter : Tabtodoadapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val weatherRvAdapter = weatherhomeadapter()
        val weatherRv = binding.recviewweather
        weatherRv.adapter = weatherRvAdapter
        weatherRv.layoutManager = LinearLayoutManager(requireContext())
        cuacaViewModel = ViewModelProvider(this)[RoomViewModel::class.java]

        cuacaViewModel.readdata.observe(viewLifecycleOwner) { cuaca ->
            weatherRvAdapter.setdata(cuaca)
        }

        val fragment = (activity as FragmentActivity).supportFragmentManager
        val viewPager = binding.viewpActHome
        val tabLayout = binding.tabpTodoHome
        tabAdapter = Tabtodoadapter(fragment,lifecycle)
        viewPager.adapter = tabAdapter

        TabLayoutMediator(tabLayout,viewPager){ tab, position ->
            when(position){
                0 ->{
                    tab.text = "inside"
                }
                1 ->{
                    tab.text = "outside"
                }
            }
        }.attach()


        return binding.root
    }

}