package com.example.wetterbericht.view.adapter.Recyclerview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wetterbericht.view.Login.onboarding.Introtiga
import com.example.wetterbericht.view.Login.onboarding.introdua
import com.example.wetterbericht.view.Login.onboarding.introsatu

class Tabonboardadapter(activity : FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(activity,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                introsatu()
            }
            1->{
                introdua()
            }
            2->{
                Introtiga()
            }
            else -> Fragment()
        }
    }

}