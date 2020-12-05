package com.psychotechbd.psyche.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.psychotechbd.psyche.R
import kotlinx.android.synthetic.main.fragment_on_boarding1.*
import kotlinx.android.synthetic.main.fragment_on_boarding1.view.*

class OnBoarding1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_on_boarding1, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager_on_board)

        view.btn_onBoard1.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return view
    }

}