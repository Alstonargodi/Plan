package com.example.wetterbericht.view.activity.onboarding

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.wetterbericht.view.activity.mainactivity.MainActivity
import com.example.wetterbericht.databinding.ActivityOnboardmainBinding
import com.example.wetterbericht.view.activity.onboarding.adapter.OnBoardingAdapter
import com.example.wetterbericht.viewmodel.localviewmodel.LocalViewModel
import com.example.wetterbericht.viewmodel.viewmodelfactory.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var adapterPager : OnBoardingAdapter
    private lateinit var binding: ActivityOnboardmainBinding

    private val localViewModel : LocalViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardmainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBoardingIntro()
    }

    private fun onBoardingIntro(){
        val fragmentManager = supportFragmentManager
        val viewPager = binding.vpOnboard
        val tabPager = binding.tabOnboard

        adapterPager = OnBoardingAdapter(fragmentManager,lifecycle)
        viewPager.adapter = adapterPager

        TabLayoutMediator(tabPager,viewPager){ tab, position ->
            when(position){
                0->{
                    tab.text =" "
                }
                1->{
                    tab.text = " "
                }
                2->{
                    tab.text = " "
                }
            }
        }.attach()


        binding.btnOnboardNext.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            onBoardFinished()
        }
    }

    private fun onBoardFinished() {
        lifecycleScope.launch {
            localViewModel.savePreferences(true)
        }
    }


}