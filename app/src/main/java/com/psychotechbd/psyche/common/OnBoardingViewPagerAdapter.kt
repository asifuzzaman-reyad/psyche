package com.psychotechbd.psyche.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingViewPagerAdapter(
    private val list: ArrayList<Fragment>,
    private val fm: FragmentManager,
    private val lifeCycle: Lifecycle
) :
    FragmentStateAdapter(fm, lifeCycle) {

    override fun getItemCount(): Int {
        return list.size
    }

    private val fragmentList = list

    override fun createFragment(position: Int): Fragment {
       return fragmentList[position]
    }

}