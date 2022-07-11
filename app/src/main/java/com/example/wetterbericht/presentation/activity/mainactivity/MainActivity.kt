package com.example.wetterbericht.presentation.activity.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.ActivityMainBinding
import com.example.wetterbericht.data.local.ChipAlarm
import com.example.wetterbericht.viewmodel.localviewmodel.LocalViewModel
import com.example.wetterbericht.viewmodel.viewmodelfactory.obtainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var localViewModel: LocalViewModel

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
                "morning",
                "07:00",
                0
            ))
            insertAlarmChip(ChipAlarm(
                "middle day",
                "09:00",
                1
            ))
            insertAlarmChip(ChipAlarm(
                "noon",
                "12:00",
                1
            ))
            insertAlarmChip(ChipAlarm(
                "afternoon",
                "15:00",
                1
            ))
        }
    }



}