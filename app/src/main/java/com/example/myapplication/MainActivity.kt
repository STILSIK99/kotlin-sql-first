package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.DB.DBManager
import com.example.myapplication.DB.SubjectAdapter
import com.example.myapplication.DB.SubjectViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val db_manager = DBManager(this)
    private val subViewModel by lazy {ViewModelProvider(this).get(SubjectViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db_manager.openDB()
        subViewModel.subjectList.value = db_manager.readDBData()

        val adapter = SubjectAdapter()
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter
        subViewModel.getListSubjects().observe(this, Observer {
            it?.let {
                adapter.refreshSubjects(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    fun onClickSave(view : View){
        db_manager.insertToDB(edTitle.text.toString(), edContent.text.toString())
        updateView()
        edTitle.text.clear()
        edContent.text.clear()
        subViewModel.updateListSubject()
    }

    override fun onDestroy() {
        super.onDestroy()
        db_manager.closeDB()
    }

    fun updateView(){
//        textView.text = ""
//        val dataList = db_manager.readDBData()
//        for(item in dataList){
//            textView.append(item.toString() + "\n")
//        }


    }

    fun delete(view : View){
        db_manager.deleteDBData(4)
        updateView()
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
    }
}