package com.example.wetterbericht.view.Login.onboarding

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wetterbericht.MainActivity
import com.example.wetterbericht.R
import com.example.wetterbericht.view.adapter.Recyclerview.OnBoardingAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_onboardmain.*

class Onboardmain : AppCompatActivity() {
    lateinit var adapterpager : OnBoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboardmain)


        intro()

    }

    private fun intro(){
        val fragmentmanager = supportFragmentManager
        val viewpager = vp_onboard
        val indpager = tab_onboard

        adapterpager = OnBoardingAdapter(fragmentmanager,lifecycle)
        viewpager.adapter = adapterpager

        TabLayoutMediator(indpager,viewpager){tab,position ->
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


        btn_onboard_next.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun Onboardmainfinish(): Boolean{
        val sharedpref = Activity().getSharedPreferences("onboard",Context.MODE_PRIVATE)
        return sharedpref.getBoolean("finish",false)
    }


}