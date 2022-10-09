package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.DB.DBManager
import com.example.myapplication.DB.Subject
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    val db_manager = DBManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        db_manager.openDB()
    }

    fun onClickAdd(view : View){
        if (edTitle.text.toString() != "" && edContent.text.toString() != ""){
            db_manager.insertToDB(edTitle.text.toString(), edContent.text.toString())
        }
        else{
            Toast.makeText(this, "Дополните сведения.", Toast.LENGTH_SHORT).show()
            return
        }


        val addIntent = Intent(this,  MainActivity::class.java)
        startActivity(addIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        db_manager.closeDB()
    }

}