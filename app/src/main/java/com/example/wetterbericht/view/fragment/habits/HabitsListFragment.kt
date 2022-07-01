package com.example.wetterbericht.view.fragment.habits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentHabitsListBinding
import com.example.wetterbericht.model.local.entity.HabitsLocal
import com.example.wetterbericht.view.fragment.habits.adapter.HabitsRecyclerViewAdapter
import com.example.wetterbericht.view.fragment.home.HomeFragment
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.ViewModelFactory


class HabitsListFragment : Fragment() {
    private lateinit var binding : FragmentHabitsListBinding

    private val localViewModel : LocalViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitsListBinding.inflate(layoutInflater)

        showHabitsList()
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

    private fun showHabitsList(){
        localViewModel.readHabits().observe(viewLifecycleOwner){
            val adapter = HabitsRecyclerViewAdapter(it)
            val recyclerView = binding.rvHabitsList
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            adapter.detailHabitsCallback(object : HabitsRecyclerViewAdapter.DetailHabitsCallback{
                override fun detailHabitsCallback(data: HabitsLocal) {
                    findNavController().navigate(
                        HabitsListFragmentDirections.actionHabitsListFragmentToCountdownFragment(
                            data.duration,data.name
                        )
                    )
                }
            })
        }
    }


}