package com.example.wetterbericht.view.Login.Onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wetterbericht.R
import com.example.wetterbericht.view.Login.Loginactivity
import com.example.wetterbericht.view.adapter.Tab.Tabonboardadapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_onboardmain.*

class Onboardmain : AppCompatActivity() {
    lateinit var adapterpager : Tabonboardadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboardmain)

        val fragmentmanager = supportFragmentManager
        val viewpager = vp_onboard
        val indpager = tab_onboard

        adapterpager = Tabonboardadapter(fragmentmanager,lifecycle)
        viewpager.adapter = adapterpager

        TabLayoutMediator(indpager,viewpager){tab,position ->
            when(position){
                0->{
                    tab.text =" "
                }
                1->{
                    tab.text = " "
                }
            }
        }.attach()


        btn_onboard_next.setOnClickListener {
            startActivity(Intent(this,Loginactivity::class.java))
        }
    }


}