package com.example.wetterbericht.viewmodel.viewmodelfactory

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wetterbericht.viewmodel.localviewmodel.LocalViewModel

fun obtainViewModel(activity: FragmentActivity): LocalViewModel {
    val factory = ViewModelFactory.getInstance(activity.application)
    return ViewModelProvider(activity,factory)[LocalViewModel::class.java]
}