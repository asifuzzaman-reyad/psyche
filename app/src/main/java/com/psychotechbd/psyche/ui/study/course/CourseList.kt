package com.psychotechbd.psyche.ui.study.course

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.psychotechbd.psyche.R
import kotlinx.android.synthetic.main.fragment_course_list.view.*

class CourseList : Fragment(), View.OnClickListener {

    private var courseCode: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_course_list, container, false)

        getStudySharedPref()

        view.textView_pdf_syllabus.setOnClickListener(this)
        view.textView_pdf_questions.setOnClickListener(this)
        view.textView_pdf_notes.setOnClickListener(this)
        view.textView_pdf_books.setOnClickListener(this)
        view.textView_pdf_teacher1.setOnClickListener(this)
        view.textView_pdf_teacher2.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textView_pdf_syllabus -> {
                findNavController().navigate(R.id.action_courseList_to_courseDetails)
                courseListSharedPref(courseCode.toString(), "syllabus")
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun getStudySharedPref() {
        val sharedPreferences = context?.getSharedPreferences("study", Context.MODE_PRIVATE)
        val getCode = sharedPreferences?.getString("key1", "")

        courseCode = getCode.toString()
        Log.i("study", "code:${getCode.toString()}" )
    }

    @SuppressLint("CommitPrefEdits")
    fun courseListSharedPref(
        value1: String, value2: String
    ) {
        val sharedPreferences = context?.getSharedPreferences("details", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("key1", value1)
        editor?.putString("key2", value2)
        editor?.apply()
    }



}