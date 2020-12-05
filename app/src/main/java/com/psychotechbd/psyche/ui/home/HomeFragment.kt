package com.psychotechbd.psyche.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.psychotechbd.psyche.R
import com.psychotechbd.psyche.ui.home.student.ui.data.StudentItemList
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    //    private lateinit var homeViewModel: HomeViewModel
    private lateinit var navController: NavController
    private lateinit var ivProfile: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        ivProfile = view.findViewById(R.id.iv_home_student_image)

        //
        view.textView_home_student.setOnClickListener {
                navController.navigate(R.id.action_navigation_home_to_batchLoged)
        }

        view.textView_home_teacher.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_teacherMain)
        }


        return view
    }

    override fun onStart() {
        super.onStart()

        firebase()
    }

    private fun firebase() {
        val db = FirebaseDatabase.getInstance()
        val ref = db.getReference("Student")

        val items = ArrayList<StudentItemList>()
        //
        val sharedPref = requireActivity().getSharedPreferences("SignUp1", Context.MODE_PRIVATE)
        val batch = sharedPref.getString("batch", "").toString()
        val id = sharedPref.getString("id", "").toString()

        ref.child(batch).child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(StudentItemList::class.java)
                items.add(data!!)

                val ivLink = data.imageUrl
                Log.i("profile_img", ivLink)

                // view image
                ivProfile.load(data.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                    transformations(CircleCropTransformation())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}