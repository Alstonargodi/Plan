package com.example.wetterbericht

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wetterbericht.databinding.ActivityMainBinding
import com.example.wetterbericht.view.home.HomeFragment
import com.example.wetterbericht.view.weather.WeatherFragment
import com.example.wetterbericht.viewmodel.local.LocalViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var tdoviewmodel: LocalViewModel


    private var waktu : Long = 0
    private var type : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR


        binding.navmenu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.fragment_home ->{
                    setFragment(HomeFragment())
                }
                R.id.fragment_weather ->{
                    setFragment(WeatherFragment())
                }
            }
            true
        }
    }

    private fun setFragment(fragment : Fragment){
        val sFragment = supportFragmentManager
        sFragment
            .beginTransaction()
            .replace(R.id.hostfragment, fragment)
            .commit()
    }




}