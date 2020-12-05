package com.psychotechbd.psyche.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.psychotechbd.psyche.MainActivity
import com.psychotechbd.psyche.R
import com.psychotechbd.psyche.registration.Login
import com.psychotechbd.psyche.registration.SignUp
import java.util.*

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler().postDelayed({
            if (onBoardingFinish()) {
                findNavController().navigate(R.id.action_splashFragment_to_login)
            } else {
                  // when onBoardingFinish ==
//                findNavController().navigate(R.id.action_splashFragment_to_onBoardigViewPager)

                findNavController().navigate(R.id.action_splashFragment_to_login)
            }
        }, 2000)

        return view
    }


    private fun onBoardingFinish(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("sharePref", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("finished", false)
    }
}