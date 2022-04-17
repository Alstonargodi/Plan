package com.example.wetterbericht.view.adapter.Tab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wetterbericht.view.fragment.Todo.InsideActivityFragment
import com.example.wetterbericht.view.fragment.Todo.OutsideActivityFragment

class Tabtodoadapter(activity : FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(activity,lifecycle){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 ->{
                InsideActivityFragment()
            }
            1->{
                OutsideActivityFragment()
            }
            else -> Fragment()
        }
    }

}