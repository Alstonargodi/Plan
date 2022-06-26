package com.example.wetterbericht.view.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.databinding.FragmentHomeBinding
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.entity.WeatherLocal
import com.example.wetterbericht.view.adapter.WeatherRvHomeAdapter
import com.example.wetterbericht.view.fragment.home.adapter.TodoRvHomeAdapter
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import java.time.LocalDate

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    private val roomViewModel : LocalViewModel by viewModels{ ViewModelFactory.getInstance(requireContext())}


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        showCurrentWeather()


        binding.tabLayout.getTabAt(0)?.select()
        showTodayTaskList()

        binding.tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{showTodayTaskList()}
                    1->{showUpComingTaskList()}
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

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
        val adapter = TodoRvHomeAdapter()
        val taskRecyclerView = binding.rectodo
        taskRecyclerView.adapter = adapter
        taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        roomViewModel.getTodayTask().observe(viewLifecycleOwner) { todo ->
            adapter.setdata(todo)
        }


        adapter.detilOnItemCallback(object : TodoRvHomeAdapter.detailCallBack{
            override fun detailCallBack(data: TodoLocal) {
                showDetailTaskDialog(data)
            }
        })
    }

    private fun showUpComingTaskList(){
        val currentDate = LocalDate.now().toString()
        val adapter = TodoRvHomeAdapter()
        val taskRecyclerView = binding.rectodo
        taskRecyclerView.adapter = adapter
        taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        roomViewModel.readTodoLocal().observe(viewLifecycleOwner) { data ->
            data.forEach { value ->
                if (value.dateStart != currentDate){
                    adapter.setdata(data)
                }
            }
        }
    }

    private fun showPreviousTask(){
        val currentDate = LocalDate.now().toString()
        val adapter = TodoRvHomeAdapter()
        val taskRecyclerView = binding.rectodo
        taskRecyclerView.adapter = adapter
        taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        roomViewModel.readTodoLocal().observe(viewLifecycleOwner) { data ->
            data.forEach { value ->
                if (value.dateStart != currentDate){
                    adapter.setdata(data)
                }
            }
        }
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

    companion object{
        const val homepage_key = "detailpage"
        const val dialog_key = "dialog"
    }

}