package com.psychotechbd.psyche.ui.study.course

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.psychotechbd.psyche.R
import com.psychotechbd.psyche.ui.study.StudyViewModel
import kotlinx.android.synthetic.main.fragment_studies.view.*

class Study : Fragment(), View.OnClickListener {

    private lateinit var studyViewModel: StudyViewModel
    private val year: String = "4th Year"

    private lateinit var code1: TextView
    private lateinit var code2: TextView
    private lateinit var code3: TextView
    private lateinit var code4: TextView
    private lateinit var code5: TextView
    private lateinit var code6: TextView
    private lateinit var batchName: TextView

    private lateinit var name1: TextView
    private lateinit var name2: TextView
    private lateinit var name3: TextView
    private lateinit var name4: TextView
    private lateinit var name5: TextView
    private lateinit var name6: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studyViewModel = ViewModelProvider(this).get(StudyViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_studies, container, false)

        // set course code and name
        setAllCourse(view)

        // item click listener
        view.ln_01.setOnClickListener(this)
        view.ln_02.setOnClickListener(this)
        view.ln_03.setOnClickListener(this)
        view.ln_04.setOnClickListener(this)
        view.ln_05.setOnClickListener(this)
        view.ln_06.setOnClickListener(this)

        loadFab(view)

        return view
    }

    @SuppressLint("SetTextI18n")
    private fun setAllCourse(view: View) {
        //hooks
        code1 = view.findViewById(R.id.tv_study_code1)
        code2 = view.findViewById(R.id.tv_study_code2)
        code3 = view.findViewById(R.id.tv_study_code3)
        code4 = view.findViewById(R.id.tv_study_code4)
        code5 = view.findViewById(R.id.tv_study_code5)
        code6 = view.findViewById(R.id.tv_study_code6)
        batchName = view.findViewById(R.id.tv_study_batch)

        name1 = view.findViewById(R.id.tv_study_name1)
        name2 = view.findViewById(R.id.tv_study_name2)
        name3 = view.findViewById(R.id.tv_study_name3)
        name4 = view.findViewById(R.id.tv_study_name4)
        name5 = view.findViewById(R.id.tv_study_name5)
        name6 = view.findViewById(R.id.tv_study_name6)

        when (year) {
            "1st Year" -> {
                code1.text = "Psy 101"
                code2.text = "Psy 102"
                code3.text = "Psy 103"
                code4.text = "Psy 104"
                code5.text = "Psy 105"
                code6.text = "Psy 106"
                batchName.text = "1st Year"

                name1.text = "General Psychology"
                name2.text = "Social Psychology"
                name3.text = "Experimental Psychology"
                name4.text = "Sociology"
                name5.text = "Psychological Statistic i"
                name6.text = "Fundamentals of Computer"
            }

            "2nd Year" -> {
                code1.text = "Psy 201"
                code2.text = "Psy 202"
                code3.text = "Psy 203"
                code4.text = "Psy 204"
                code5.text = "Psy 205"
                code6.text = "Psy 206"
                batchName.text = "2nd Year"

                name1.text = "Developmental Psychology"
                name2.text = "Educational Psychology"
                name3.text = "Behavioral NeuroScience"
                name4.text = "Cultural Anthropology"
                name5.text = "Psychological Statistic ii"
                name6.text = "Computer Applications"
            }

            "3rd Year" -> {
                code1.text = "Psy 301"
                code2.text = "Psy 302"
                code3.text = "Psy 303"
                code4.text = "Psy 304"
                code5.text = "Psy 305"
                code6.text = "Psy 306"
//                code7.text = "Psy 307"
                batchName.text = "3rd Year"

                name1.text = "Psychological Testing"
                name2.text = "Research Methodology"
                name3.text = "Abnormal Psychology"
                name4.text = "Industrial Psychology"
                name5.text = "Counseling Psychology"
                name6.text = "Health Psychology"
//                name7.text = "Psychology of Crime"
            }

            "4th Year" -> {
                code1.text = "Psy 401"
                code2.text = "Psy 402"
                code3.text = "Psy 403"
                code4.text = "Psy 404"
                code5.text = "Psy 405"
                code6.text = "Psy 406"
//                code7.text = "Psy 407"
//                code8.text = "Psy 408"
                batchName.text = "4th Year"

                name1.text = "Psychology of Learning"
                name2.text = "Personality Development"
                name3.text = "Theories of Perception"
                name4.text = "Developmental Psy. ii"
                name5.text = "Organizational Behavior"
                name6.text = "Clinical Psychology"
//                name7.text = "Cognitive Psychology"
//                name8.text = "Positive Psychology"
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ln_01 -> {
                findNavController().navigate(R.id.action_navigation_study_to_courseList)
                studySharedPref(code1.text.toString ())
                Log.i("study1", "code:${code1.text}")

            }
            R.id.ln_02 -> {
                findNavController().navigate(R.id.action_navigation_study_to_courseList)
                studySharedPref(code2.text.toString ())
            }
            R.id.ln_03 -> {
                findNavController().navigate(R.id.action_navigation_study_to_courseList)
                studySharedPref(code3.text.toString ())
            }
            R.id.ln_04 -> {
                findNavController().navigate(R.id.action_navigation_study_to_courseList)
                studySharedPref(code4.text.toString ())
            }
            R.id.ln_05 -> {
                findNavController().navigate(R.id.action_navigation_study_to_courseList)
                studySharedPref(code5.text.toString ())
            }
            R.id.ln_06 -> {
                findNavController().navigate(R.id.action_navigation_study_to_courseList)
                studySharedPref(code6.text.toString ())
            }
        }
    }

    private fun loadFab(view: View) {
        val fab: FloatingActionButton = view.findViewById(R.id.fab_study_login)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_study_to_studyMain)
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun studySharedPref(
        value1: String
    ) {
        val sharedPreferences = context?.getSharedPreferences("study", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("key1", value1)
        editor?.apply()
    }
}