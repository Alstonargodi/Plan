package com.example.wetterbericht.view.adapter.Tab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wetterbericht.view.Fragment.Todo.Fragment_activity_inside
import com.example.wetterbericht.view.Fragment.Todo.Fragment_activity_outside

class Tabtodoadapter(activity : FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(activity,lifecycle){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 ->{
                Fragment_activity_inside()
            }
            1->{
                Fragment_activity_outside()
            }
            else -> Fragment()
        }
    }

}