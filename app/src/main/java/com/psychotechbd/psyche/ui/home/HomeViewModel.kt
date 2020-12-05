package com.psychotechbd.psyche.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.psychotechbd.psyche.ui.home.teacher.data.TeacherItemList

class HomeViewModel(app: Application): AndroidViewModel(app) {

    private val dataRepo = HomeRepository(app)
    val b15Data = dataRepo.repoData15
    val b14Data = dataRepo.repoData14
    val b13Data = dataRepo.repoData13
    val b12Data = dataRepo.repoData12

    //
    val bLoggedInData = dataRepo.repoDataLoggedIn

    val presentData = dataRepo.repoDataPresent
    val absentData = dataRepo.repoDataAbsent

    val selectedTeacher = MutableLiveData<TeacherItemList>()
}