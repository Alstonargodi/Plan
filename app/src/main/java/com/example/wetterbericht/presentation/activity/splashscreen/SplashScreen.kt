package com.example.wetterbericht.presentation.activity.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.wetterbericht.presentation.activity.mainactivity.MainActivity
import com.example.wetterbericht.R
import com.example.wetterbericht.presentation.activity.onboarding.OnBoardingActivity
import com.example.wetterbericht.presentation.activity.onboarding.OnBoardingViewModel
import com.example.wetterbericht.viewmodel.ViewModelFactory

class SplashScreen : AppCompatActivity() {

    private val localViewModel : OnBoardingViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        window.statusBarColor = getColor(R.color.spalshscreen)

        localViewModel.getOnBoardingStatus().observe(this){ status->
            if (status){
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, MainActivity::class.java))
                },3000)
            }else{
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, OnBoardingActivity::class.java))
                },3000)
            }
        }

    }

}