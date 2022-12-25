package com.example.wetterbericht.presentation.fragment.habits.habistlistfragment

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentHabitsListBinding
import com.example.wetterbericht.data.local.entity.dailyhabits.DailyHabits
import com.example.wetterbericht.helpers.sortfilter.HabitSortType
import com.example.wetterbericht.presentation.fragment.habits.adapter.HabitsRecyclerViewAdapter
import com.example.wetterbericht.presentation.fragment.habits.viewmodel.HabitsViewModel
import com.example.wetterbericht.viewmodel.viewmodelfactory.ViewModelFactory

class HabitsListFragment : Fragment() {
    private lateinit var binding : FragmentHabitsListBinding

    private val localViewModel : HabitsViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitsListBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.habitstoolbar)
        showHabitsList()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.habits_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_filter->{
                showFilteringMenu()
                true
            }
            R.id.action_cardview->{
                findNavController().navigate(
                    HabitsListFragmentDirections.actionHabitsListFragmentToHabitsCardViewFragment())
                true
            }
            else -> { true }
        }
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
        localViewModel.getHabits().observe(viewLifecycleOwner){
            val adapter = HabitsRecyclerViewAdapter(it)
            val recyclerView = binding.rvHabitsList
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

            adapter.detailHabitsCallback(object : HabitsRecyclerViewAdapter.DetailHabitsCallback{
                override fun detailHabitsCallback(data: DailyHabits) {
                    findNavController().navigate(
                        HabitsListFragmentDirections.actionHabitsListFragmentToCountdownFragment(
                            data.minuteFocus, data.title
                        )
                    )
                }
            })
        }
    }

    private fun showFilteringMenu(){
        val view = requireActivity().findViewById<View>(R.id.action_filter) ?: return
        PopupMenu(requireContext(),view).run {
            menuInflater.inflate(R.menu.habits_filter, menu)

            setOnMenuItemClickListener {
                localViewModel.filter(
                    when(it.itemId){
                        R.id.minutes_focus -> HabitSortType.MINUTE_FOCUS
                        R.id.title_name -> HabitSortType.TITLE_NAME
                        else -> HabitSortType.START_TIME
                    }
                )
                true
            }
            show()
        }
    }


}