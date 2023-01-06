package com.example.wetterbericht.presentation.fragment.stats

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentStatisticBinding
import com.example.wetterbericht.viewmodel.viewmodelfactory.ViewModelFactory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class StatisticFragment : Fragment() {

    private lateinit var binding : FragmentStatisticBinding

    private val viewModel : StatisticFragmentViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }
    private var dateList = mutableListOf<Long>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticBinding.inflate(layoutInflater)

        viewModel.readTodoTask().observe(viewLifecycleOwner){ data->
            binding.tvTotalActivites.setText(data.size.toString())
            data.forEach { data->
                dateList.add(data.dateDueMillis)
            }
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val dateMax = dateFormat.format(dateList.max())
            val dateMin = dateFormat.format(dateList.min())
            binding.tvStatDaterange.text = "from $dateMin until $dateMax"

        }

        return binding.root
    }

}