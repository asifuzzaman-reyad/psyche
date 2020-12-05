package com.psychotechbd.psyche.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.psychotechbd.psyche.R
import kotlinx.android.synthetic.main.fragment_on_boarding_main.view.*


class OnBoardigViewPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_on_boarding_main, container, false)

        val fragmentList = arrayListOf(
            OnBoarding1(),
            OnBoarding2(),
            OnBoarding3()
        )

        val adapter =
            OnBoardingViewPagerAdapter(fragmentList,
                requireActivity().supportFragmentManager,
                lifecycle)


        view.view_pager_on_board.adapter = adapter

        return  view
    }
}