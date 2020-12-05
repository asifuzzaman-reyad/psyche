package com.psychotechbd.psyche.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.psychotechbd.psyche.MainActivity
import com.psychotechbd.psyche.R
import com.psychotechbd.psyche.databinding.FragmentOnBoarding3Binding
import kotlinx.android.synthetic.main.fragment_on_boarding3.view.*

class OnBoarding3 : Fragment() {

    private var _binding : FragmentOnBoarding3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
       _binding = FragmentOnBoarding3Binding.inflate(inflater, container, false)

        binding.btnOnBoard3Student.setOnClickListener {
//            Navigation.findNavController(requireView()).navigate(R.)
            onBoardingFinish()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBoardingFinish(){
        val sharedPre = requireActivity().getSharedPreferences("sharePref", Context.MODE_PRIVATE)
        val editor = sharedPre.edit()
        editor.putBoolean("finished", true)
        editor.apply()
    }
}