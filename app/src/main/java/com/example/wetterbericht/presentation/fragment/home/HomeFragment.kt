package com.example.wetterbericht.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetterbericht.R
import com.example.wetterbericht.data.local.entity.dailytask.TodoLocal
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal
import com.example.wetterbericht.databinding.FragmentHomeBinding
import com.example.wetterbericht.helpers.sortfilter.TodoSortType
import com.example.wetterbericht.presentation.fragment.home.adapter.TodoRecyclerViewAdapter
import com.example.wetterbericht.presentation.fragment.home.detail.DetailTodoDialog
import com.example.wetterbericht.viewmodel.viewmodelfactory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlin.math.round

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get()= _binding!!

    private lateinit var taskRecyclewViewAdapter : TodoRecyclerViewAdapter

    private val homeViewModel : HomeViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        showCurrentWeather()
        ItemTouchHelper(Callback()).attachToRecyclerView(binding.rectodo)
        binding.tabLayout.getTabAt(1)?.select()

        showTodayTaskList()

        binding.FilterMenu.setNavigationItemSelectedListener {
            homeViewModel.taskFilter(
                when(it.itemId){
                    R.id.all_task-> TodoSortType.ALL_TASKS
                    R.id.active_task->TodoSortType.ACTIVE_TASKS
                    R.id.completed_task->TodoSortType.COMPLETED_TASKS
                    else -> TodoSortType.ALL_TASKS
                }
            )
            true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnHometomenu.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionFragmentHomeToMenuFragment()
            )
        }

        binding.tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        showPreviousTask()
                    }
                    1->{
                        showTodayTaskList()
                    }
                    2->{
                        showUpComingTaskList()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }

    private fun showCurrentWeather(){
        homeViewModel.readWeatherLocal().observe(viewLifecycleOwner){
            setCurrentWeather(it[0])
        }
    }

    private fun showTodayTaskList(){
        homeViewModel.readTodoTaskFilter().observe(viewLifecycleOwner){ respon ->
            showTaskList(respon)
        }
    }

    private fun showUpComingTaskList(){
        homeViewModel.getUpcomingTask().observe(viewLifecycleOwner) { data ->
            showTaskList(data)
        }
    }

    private fun showPreviousTask(){
        homeViewModel.getPreviousTask().observe(viewLifecycleOwner) { data ->
            showTaskList(data)
        }
    }

    private fun showTaskList(data : List<TodoLocal>){
        taskRecyclewViewAdapter = TodoRecyclerViewAdapter(data) { value, condition ->
            homeViewModel.updateTask(
                value.taskID,
                condition
            )
        }

        val taskRecyclerView = binding.rectodo
        taskRecyclerView.adapter = taskRecyclewViewAdapter
        taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        taskRecyclewViewAdapter.detailOnItemCallback(
            object : TodoRecyclerViewAdapter.DetailCallback{
            override fun detailCallBack(data: TodoLocal) {
                showDetailTaskDialog(data)
            }
        })

        if (data.isEmpty()){
            binding.tvActivitesStatus.visibility = View.VISIBLE
        }else{
            binding.tvActivitesStatus.visibility = View.GONE
        }
    }

    private fun setCurrentWeather(data : WeatherLocal){
        val temp = data.temp.toDouble()
        val temperature = round(temp).toInt().toString()
        binding.tvHomewTemp2.apply {
            (temperature + "c").also { text = it }
        }

        Glide.with(requireContext())
            .asDrawable()
            .load(data.image)
            .into(binding.imgWeatherIconHome2)
    }


    private fun showDetailTaskDialog(data : TodoLocal){
        val detailDialog = DetailTodoDialog()
        val supportFragment = requireActivity().supportFragmentManager
        val arguments = Bundle()
        arguments.putParcelable(homepage_key,data)
        detailDialog.arguments = arguments
        detailDialog.show(supportFragment, dialog_key)
    }


    inner class Callback : ItemTouchHelper.Callback(){
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int { return makeMovementFlags(0,ItemTouchHelper.RIGHT) }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean { return false }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val task = (viewHolder as TodoRecyclerViewAdapter.ViewHolder).getData()
            homeViewModel.deleteTodoLocal(task.title)
            homeViewModel.snackbarEvent.observe(viewLifecycleOwner){
                showSnackBar(it)
            }
        }
    }

    private fun showSnackBar(title : String){
        Snackbar.make(binding.root,"$title has been deleted",Snackbar.LENGTH_SHORT).show()
    }

    companion object{
        const val homepage_key = "detailpage"
        const val dialog_key = "dialog"
    }
}