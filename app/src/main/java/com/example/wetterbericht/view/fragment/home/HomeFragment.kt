package com.example.wetterbericht.view.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.databinding.FragmentHomeBinding
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.entity.WeatherLocal
import com.example.wetterbericht.view.fragment.home.adapter.TodoRvHomeAdapter
import com.example.wetterbericht.view.adapter.WeatherRvHomeAdapter
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.obtainViewModel

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var localViewModel : LocalViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        localViewModel = obtainViewModel(requireActivity())

        localViewModel.readWeatherLocal()
        localViewModel.responseWeatherLocal.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                binding.recviewweather.visibility = View.GONE
            }else{
                binding.recviewweather.visibility = View.VISIBLE
                setCurrentWeather(it)
            }
        }

        setTodo()

        binding.btnHometomenu.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionFragmentHomeToMenuFragment()
            )
        }
        return binding.root
    }

    private fun setTodo(){
        val adapter = TodoRvHomeAdapter()
        val recyclerView = binding.rectodo
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        localViewModel.readTodoLocal()
        localViewModel.responseTodoLocal.observe(viewLifecycleOwner) { todo ->
            adapter.setdata(todo)
        }

        adapter.detilOnItemCallback(object : TodoRvHomeAdapter.detailCallBack{
            override fun detailCallBack(data: TodoLocal) {
                toDetailPage(data)
            }
        })
    }


    private fun setCurrentWeather(data : List<WeatherLocal>){
        val weatherRvAdapter = WeatherRvHomeAdapter()
        val weatherRv = binding.recviewweather
        weatherRv.adapter = weatherRvAdapter
        weatherRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
        weatherRvAdapter.setdata(data)
    }

    private fun toDetailPage(data : TodoLocal){
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