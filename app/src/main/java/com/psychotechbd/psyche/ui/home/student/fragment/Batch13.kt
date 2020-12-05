package com.psychotechbd.psyche.ui.home.student.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psychotechbd.psyche.R
import com.psychotechbd.psyche.ui.home.student.ui.data.StudentAdapter
import com.psychotechbd.psyche.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_batch13.view.*

class Batch13 : Fragment() {

    private lateinit var viewModel : HomeViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_batch13, container, false)

        recyclerView = view.recyclerView_batch_13
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        viewModel.b13Data.observe(viewLifecycleOwner, Observer {
            val adapter = StudentAdapter(requireContext(), it)
            recyclerView.adapter = adapter
        })

        return view
    }
}