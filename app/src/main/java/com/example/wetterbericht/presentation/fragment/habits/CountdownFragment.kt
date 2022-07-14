package com.example.wetterbericht.presentation.fragment.habits

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.wetterbericht.databinding.FragmentCountdownBinding
import com.example.wetterbericht.viewmodel.countdownviewmodel.CountDownViewModel


class CountdownFragment : Fragment() {
    private lateinit var binding : FragmentCountdownBinding
    private val countViewModel : CountDownViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountdownBinding.inflate(layoutInflater)

        val name = CountdownFragmentArgs.fromBundle(requireArguments()).name
        val duration = CountdownFragmentArgs.fromBundle(requireArguments()).time

        binding.tvCountdownName.text = name

        countdown(duration)

        countViewModel.currentTimeString.observe(viewLifecycleOwner){ time ->
            binding.tvCountdownTimer.text = time
        }

        countViewModel.progressBar.observe(viewLifecycleOwner){
            binding.pgcircleCountdown.setProgress(it,true)
        }

        countViewModel.eventFinish.observe(viewLifecycleOwner){
            countViewModel.resetTimer()
            binding.pgcircleCountdown.setProgress(0,false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCountdownStart.setOnClickListener {
            countViewModel.startTimer()
            statusButton(true)
        }


        binding.btnCountdownStop.setOnClickListener {
            countViewModel.resetTimer()
            binding.pgcircleCountdown.setProgress(0,false)
            statusButton(false)
        }
    }

    private fun countdown(time : Long){
        countViewModel.setInitialCountDown(time)
    }

    private fun statusButton(status : Boolean){
        binding.btnCountdownStart.isEnabled = !status
        binding.btnCountdownStop.isEnabled = status
    }




}