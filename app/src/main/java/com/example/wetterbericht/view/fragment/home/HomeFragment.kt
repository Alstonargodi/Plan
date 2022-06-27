package com.example.wetterbericht.view.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wetterbericht.databinding.FragmentHomeBinding
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.entity.WeatherLocal
import com.example.wetterbericht.view.adapter.WeatherRvHomeAdapter
import com.example.wetterbericht.view.fragment.home.adapter.TodoRecyclerViewHomeAdapter
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import java.util.concurrent.Executors

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    private val roomViewModel : LocalViewModel by viewModels{ ViewModelFactory.getInstance(requireContext())}


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        showCurrentWeather()


        ItemTouchHelper(Callback()).attachToRecyclerView(binding.rectodo)

        binding.tabLayout.getTabAt(1)?.select()
        showTodayTaskList()

        binding.tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{showPreviousTask()}
                    1->{showTodayTaskList()}
                    2->{showUpComingTaskList()}
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHometomenu.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionFragmentHomeToMenuFragment()
            )
        }
    }

    private fun showCurrentWeather(){
        roomViewModel.readWeatherLocal().observe(viewLifecycleOwner){
            if (it.isEmpty()){
                binding.recviewweather.visibility = View.GONE
            }else{
                binding.recviewweather.visibility = View.VISIBLE
                setCurrentWeather(it)
            }
        }
    }

    private fun showTodayTaskList(){
        roomViewModel.getTodayTask().observe(viewLifecycleOwner) { data ->
            showTaskList(data)
        }
    }

    private fun showUpComingTaskList(){
        roomViewModel.getUpcomingTask().observe(viewLifecycleOwner) { data ->
            showTaskList(data)
        }
    }

    private fun showPreviousTask(){
        roomViewModel.getPreviousTask().observe(viewLifecycleOwner) { data ->
            showTaskList(data)
        }
    }

    private fun showTaskList(data : List<TodoLocal>){
        val adapter = TodoRecyclerViewHomeAdapter(data)
        val taskRecyclerView = binding.rectodo
        taskRecyclerView.adapter = adapter
        taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.detailOnItemCallback(object : TodoRecyclerViewHomeAdapter.DetailCallback{
            override fun detailCallBack(data: TodoLocal) {
                showDetailTaskDialog(data)
            }
        })


    }


    private fun setCurrentWeather(data : List<WeatherLocal>){
        val weatherRvAdapter = WeatherRvHomeAdapter()
        val weatherRecyclerView = binding.recviewweather
        weatherRecyclerView.adapter = weatherRvAdapter
        weatherRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
        weatherRvAdapter.setdata(data)
    }


    private fun showDetailTaskDialog(data : TodoLocal){
            val dialog = DetailTodoDialog()
            val supportFragment = requireActivity().supportFragmentManager
            val args = Bundle()
            args.putParcelable(homepage_key,data)
            dialog.arguments = args
            dialog.show(supportFragment, dialog_key)
    }

    inner class Callback : ItemTouchHelper.Callback(){
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
           return makeMovementFlags(0,ItemTouchHelper.RIGHT)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val task = (viewHolder as TodoRecyclerViewHomeAdapter.ViewHolder).getData()
            Toast.makeText(requireContext(),"Delete ${task.title}",Toast.LENGTH_SHORT).show()
            Executors.newSingleThreadExecutor().execute {
                roomViewModel.deleteTodoLocal(task.title)
            }
        }

    }
    companion object{
        const val homepage_key = "detailpage"
        const val dialog_key = "dialog"
    }

}