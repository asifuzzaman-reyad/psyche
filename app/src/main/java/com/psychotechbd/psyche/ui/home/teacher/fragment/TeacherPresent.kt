package com.psychotechbd.psyche.ui.home.teacher.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psychotechbd.psyche.R
import com.psychotechbd.psyche.ui.home.HomeViewModel
import com.psychotechbd.psyche.ui.home.teacher.data.TeacherAdapter
import com.psychotechbd.psyche.ui.home.teacher.data.TeacherItemList
import kotlinx.android.synthetic.main.fragment_teacher_present.view.*

class TeacherPresent : Fragment(), TeacherAdapter.TeacherItemListener {

    private lateinit var viewModel : HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_teacher_present, container, false)

        navController = Navigation.findNavController((requireActivity() as AppCompatActivity), R.id.nav_host_fragment)
        recyclerView = view.recyclerView_present
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        viewModel.presentData.observe(viewLifecycleOwner, Observer {
            val adapter = TeacherAdapter(requireContext(), it, this)
            recyclerView.adapter = adapter
        })

        return view
    }

    override fun onItemClick(item: TeacherItemList) {
        Log.i("teacher_pre","selected item:${item.name}")
        viewModel.selectedTeacher.value = item
        navController.navigate(R.id.action_teacherMain2_to_teacherDetails)
    }
}