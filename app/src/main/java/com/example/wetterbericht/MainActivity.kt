package com.example.wetterbericht

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wetterbericht.databinding.ActivityMainBinding
import com.example.wetterbericht.model.local.ChipAlarm
import com.example.wetterbericht.view.home.HomeFragment
import com.example.wetterbericht.view.weather.WeatherFragment
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.obtainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var localViewModel: LocalViewModel


    private var waktu : Long = 0
    private var type : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        localViewModel = obtainViewModel(this)

        window.statusBarColor = getColor(R.color.main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setDefaultOption()

        val navControl = findNavController(R.id.hostfragment)
        binding.navmenu.apply {
            setupWithNavController(navControl)
        }
    }



    private fun setDefaultOption(){
        localViewModel.apply {
            insertAlarmChip(ChipAlarm(
                "tonight",
                "19 : 00 PM"
            ))
            insertAlarmChip(ChipAlarm(
                "tomorrow morning",
                "05 : 00 AM"
            ))
            insertAlarmChip(ChipAlarm(
                "tomorrow noon",
                "10 : 00 AM"
            ))
            insertAlarmChip(ChipAlarm(
                "tomorrow night",
                "19 : 00 PM"
            ))
        }
    }



}