package com.psychotechbd.psyche.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.psychotechbd.psyche.R

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(requireActivity()).get(NotificationsViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
        })

        val db = FirebaseDatabase.getInstance()
        val ref = db.getReference("student")


        ref.child("batch 13").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    Log.i("key", it.toString())

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("notification", error.message)
            }
        })

        Log.e("key", ref.toString())

        return view
    }
}