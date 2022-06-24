package com.example.wetterbericht.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentHomeBinding
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.model.local.WeatherLocal
import com.example.wetterbericht.view.home.adapter.TodoRvHomeAdapter
import com.example.wetterbericht.view.adapter.WeatherRvHomeAdapter
import com.example.wetterbericht.view.home.adapter.SubTaskAdapter
import com.example.wetterbericht.view.insert.insert.InsertTodoFragment
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

//        binding.btnAddTodo.setOnClickListener {
//            val sFragment = requireActivity().supportFragmentManager
//           sFragment
//               .beginTransaction()
//               .replace(R.id.hostfragment, InsertTodoFragment())
//               .addToBackStack(null)
//               .commit()
//        }


        setTodo()

        return binding.root
    }

    private fun setTodo(){
        val adapter = TodoRvHomeAdapter()
        val recview = binding.rectodo
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(requireContext())
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
            val spfragment = requireActivity().supportFragmentManager
            val args = Bundle()
            args.putParcelable(homepage_key,data)
            dialog.arguments = args
            dialog.show(spfragment, dialog_key)
    }

    companion object{
        const val homepage_key = "detailpage"
        const val dialog_key = "dialog"
    }

}