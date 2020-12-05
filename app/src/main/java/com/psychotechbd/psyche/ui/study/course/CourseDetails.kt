package com.psychotechbd.psyche.ui.study.course

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.psychotechbd.psyche.R
import kotlinx.android.synthetic.main.fragment_course_details.*

class CourseDetails : Fragment() {

    private var getCode: String? = null
    private var getPdf: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getCourseListSharedPref()

        val view =  inflater.inflate(R.layout.fragment_course_details, container, false)

        retrieveFirebaseData(getCode.toString(), getPdf.toString())

        return view
    }

    private fun getCourseListSharedPref() {
        val sharedPreferences = context?.getSharedPreferences("details", Context.MODE_PRIVATE)
        getCode = sharedPreferences?.getString("key1", "code")
        getPdf = sharedPreferences?.getString("key2", "pdfLink")
    }

    private fun retrieveFirebaseData(code: String, name: String) {
        val db = FirebaseDatabase.getInstance()
        val ref = db.getReference("resource").child(code).child(name)

        val mItems = ArrayList<PdfItemList>()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val data = it.getValue(PdfItemList::class.java)
                    mItems.add(data!!)
                    Log.i("pdfDetails", it.toString())

                    val adapter = PdfDetailsAdapter(requireContext(), mItems)
                    recyclerView_all.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    requireContext(),
                    "Resource Data Error: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

}