package com.example.wetterbericht.presentation.fragment.habits.countdown

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wetterbericht.databinding.FragmentCountdownBinding
import com.example.wetterbericht.viewmodel.countdownviewmodel.CountDownViewModel


class CountdownFragment : Fragment() {
    private lateinit var binding : FragmentCountdownBinding
    private val countViewModel : CountDownViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountdownBinding.inflate(layoutInflater)

        val name = CountdownFragmentArgs.fromBundle(
            requireArguments()
        ).name
        val duration = CountdownFragmentArgs.fromBundle(
            requireArguments()
        ).time
        val color = CountdownFragmentArgs.fromBundle(
            requireArguments()
        ).color

        binding.tvCountdownName.text = name

        countdown(duration)

        countViewModel.currentTimeString.observe(viewLifecycleOwner){ time ->
            binding.tvCountdownTimer.text = time
        }

        countViewModel.progressBar.observe(viewLifecycleOwner){
            binding.pgcircleCountdown.setProgress(it,true)
            binding.pgcircleCountdown.indeterminateDrawable.setColorFilter(
                Color.parseColor("#2a4c6b"), android.graphics.PorterDuff.Mode.SRC_ATOP
            )
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