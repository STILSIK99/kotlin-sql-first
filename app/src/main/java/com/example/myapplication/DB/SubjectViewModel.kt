package com.example.myapplication.DB

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SubjectViewModel() : ViewModel() {
    var subjectList : MutableLiveData<List<Subject>> = MutableLiveData()

    init {
    }

    fun getListSubjects() = subjectList

    fun updateListSubject() {
        subjectList.value = listOf()
    }


}