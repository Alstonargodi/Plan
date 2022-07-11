package com.example.wetterbericht.presentation.activity.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wetterbericht.presentation.activity.onboarding.page.IntroThree
import com.example.wetterbericht.presentation.activity.onboarding.page.IntroOne
import com.example.wetterbericht.presentation.activity.onboarding.page.IntroTwo

class OnBoardingAdapter(activity : FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(activity,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                IntroTwo()
            }
            1->{
                IntroOne()
            }
            2->{
                IntroThree()
            }
            else -> Fragment()
        }
    }

}