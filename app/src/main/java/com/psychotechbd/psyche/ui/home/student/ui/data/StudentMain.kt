package com.psychotechbd.psyche.ui.home.student.ui.data

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.psychotechbd.psyche.R
import com.psychotechbd.psyche.ui.home.student.fragment.Batch12
import com.psychotechbd.psyche.ui.home.student.fragment.Batch13
import com.psychotechbd.psyche.ui.home.student.fragment.Batch14
import com.psychotechbd.psyche.ui.home.student.fragment.Batch15

class StudentMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main)

        val pagerAdapter = StudentPagerAdapter(this, supportFragmentManager)
        pagerAdapter.addFragment(Batch15(), "15th Batch")
        pagerAdapter.addFragment(Batch14(), "14th Batch")
        pagerAdapter.addFragment(Batch13(), "13th Batch")
        pagerAdapter.addFragment(Batch12(), "12th Batch")

        val viewPager: ViewPager = findViewById(R.id.view_pager_student)
        viewPager.adapter = pagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)


    }
}