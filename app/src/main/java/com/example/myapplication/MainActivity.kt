package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.DB.DBManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db_manager = DBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db_manager.openDB()
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
    }

    override fun onDestroy() {
        super.onDestroy()
        db_manager.closeDB()
    }

    fun updateView(){
        textView.text = ""
        val dataList = db_manager.readDBData()
        for(item in dataList){
            textView.append(item.toString() + "\n")
        }
    }

    fun delete(view : View){
        db_manager.deleteDBData(4)
        updateView()
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
    }
}