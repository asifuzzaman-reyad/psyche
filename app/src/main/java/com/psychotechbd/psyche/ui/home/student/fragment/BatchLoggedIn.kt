package com.psychotechbd.psyche.ui.home.student.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.psychotechbd.psyche.R
import com.psychotechbd.psyche.ui.home.student.ui.data.StudentAdapter
import com.psychotechbd.psyche.ui.home.HomeViewModel
import com.psychotechbd.psyche.ui.home.student.ui.data.StudentMain
import kotlinx.android.synthetic.main.fragment_batch_login.view.*

class BatchLoggedIn : Fragment() {

    private lateinit var viewModel : HomeViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_batch_login, container, false)

        recyclerView = view.recyclerView_batch_login
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        viewModel.bLoggedInData.observe(viewLifecycleOwner, Observer {
            val adapter = StudentAdapter(requireContext(), it)
            recyclerView.adapter = adapter
        })

        loadFab(view)

        return view
    }

    private fun loadFab(view: View) {
        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Toast.makeText(requireActivity(), "Show All Batch", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), StudentMain::class.java)
            startActivity(intent)
        }
    }
}