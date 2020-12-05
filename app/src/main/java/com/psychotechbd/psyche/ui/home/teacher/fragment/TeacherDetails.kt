package com.psychotechbd.psyche.ui.home.teacher.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.psychotechbd.psyche.R
import com.psychotechbd.psyche.databinding.FragmentTeacherDetailsBinding
import com.psychotechbd.psyche.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_teacher_details.view.*

class TeacherDetails : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)  //show back button
        }
        setHasOptionsMenu(true)  //enable back button
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_teacher_details, container, false)

        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
//            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.)
        } else {
            Log.i("check", "Uid: $uid")
        }

        val call = view.imageViewCall_teacher

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        viewModel.selectedTeacher.observe(viewLifecycleOwner, Observer { it ->
            Log.i("reyad", "selected Id: ${it.mobile}")

           call.setOnClickListener {
//                val mobileNO = it.mobile
//                val call = "tel:$mobileNO"
//                val intent = Intent(Intent.ACTION_DIAL)
//                intent.data = Uri.parse(call)
//                v.context.startActivity(intent)
                Toast.makeText(requireContext(), "Open Dialer", Toast.LENGTH_SHORT).show()
               Log.i("click", "click: ")
            }
        })


        val binding = FragmentTeacherDetailsBinding.inflate(
            inflater, container, false
        )
        binding.lifecycleOwner = this
        binding.teacherViewModel = viewModel

        return binding.root
    }

}