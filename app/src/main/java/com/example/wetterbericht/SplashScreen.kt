package com.example.wetterbericht

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.wetterbericht.view.Login.onboarding.Onboardmain

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        window.statusBarColor = getColor(R.color.main)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, Onboardmain::class.java))
        },3000)
    }

}