package com.psychotechbd.psyche.ui.home

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.psychotechbd.psyche.ui.home.student.ui.data.StudentItemList
import com.psychotechbd.psyche.ui.home.teacher.data.TeacherItemList

class HomeRepository(val app: Application) {
    val repoData15 = MutableLiveData<List<StudentItemList>>()
    val repoData14 = MutableLiveData<List<StudentItemList>>()
    val repoData13 = MutableLiveData<List<StudentItemList>>()
    val repoData12 = MutableLiveData<List<StudentItemList>>()

    val repoDataLoggedIn = MutableLiveData<List<StudentItemList>>()

    val repoDataPresent = MutableLiveData<List<TeacherItemList>>()
    val repoDataAbsent = MutableLiveData<List<TeacherItemList>>()

    //
    private val sharedPref: SharedPreferences = app.getSharedPreferences("SignUp1", Context.MODE_PRIVATE)
    val batch = sharedPref.getString("batch", "").toString()

    init {
        callFirebaseService("Batch 15", repoData15)
        callFirebaseService("Batch 14", repoData14)
        callFirebaseService("Batch 13", repoData13)
        callFirebaseService("Batch 12", repoData12)
        callFirebaseService(batch, repoDataLoggedIn)

        callFirebaseService1("Present", repoDataPresent)
        callFirebaseService1("Absent", repoDataAbsent)
    }

    private fun callFirebaseService(batch: String, mutableStudentList: MutableLiveData<List<StudentItemList>>) {
        val db = FirebaseDatabase.getInstance()
        val ref = db.getReference("Student")

        val items = ArrayList<StudentItemList>()

        ref.child(batch).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val data = it.getValue(StudentItemList::class.java)
                    items.add(data!!)

                    mutableStudentList.value = items
                    Log.i("student", data.name)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun callFirebaseService1(status: String, mutableTeacherList: MutableLiveData<List<TeacherItemList>>) {
        val db = FirebaseDatabase.getInstance()
        val ref = db.reference.child("Teacher")

        val items = ArrayList<TeacherItemList>()

        ref.child(status).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val data = it.getValue(TeacherItemList::class.java)
                    items.add(data!!)

                    mutableTeacherList.value = items
                    Log.i("teacher", data.name)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}
