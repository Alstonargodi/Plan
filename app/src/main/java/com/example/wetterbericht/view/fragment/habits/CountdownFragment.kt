package com.example.wetterbericht.view.fragment.habits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentCountdownBinding
import com.example.wetterbericht.viewmodel.countdown.CountDownViewModel


class CountdownFragment : Fragment() {
    private lateinit var binding : FragmentCountdownBinding
    private val countViewModel : CountDownViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountdownBinding.inflate(layoutInflater)

        val name = CountdownFragmentArgs.fromBundle(requireArguments()).name
        val duration = CountdownFragmentArgs.fromBundle(requireArguments()).time.toLong()

        binding.tvCountdownName.text = name

        countdown(duration)

        countViewModel.currentTimeString.observe(viewLifecycleOwner){ time ->
            binding.tvCountdownTimer.text = time
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCountdownStart.setOnClickListener {
            countViewModel.start()
        }
    }

    private fun countdown(time : Long){
        countViewModel.setInitialCountDown(time)

    }


}