package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.DB.DBManager
import com.example.myapplication.DB.Subject
import kotlinx.android.synthetic.main.activity_main2.*

class SubjectActivity : AppCompatActivity() {
    val db_manager = DBManager(this)
    var id_subject : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        db_manager.openDB()
        getIntents()
    }

    fun onClickAdd(view : View){
        if (edTitle.text.toString() == ""){
            Toast.makeText(this, "Дополните сведения.", Toast.LENGTH_SHORT).show()
            return
        }
        when(id_subject){
            -1 -> db_manager.insertToDB(
                edTitle.text.toString(),
                edContent.text.toString()
            )
            else ->
                db_manager.updateSubject(
                id_subject,
                edTitle.text.toString(),
                edContent.text.toString()
            )
        }
        val addIntent = Intent(this,  MainActivity::class.java)
        startActivity(addIntent)
    }


    fun getIntents(){
        if (intent != null) {
            if(intent.hasExtra("Subject")){
                val subj = intent.getParcelableExtra<Subject>("Subject") as Subject
                edTitle.setText(subj.title)
                edContent.setText(subj.content)
                id_subject = subj.id
                textView.setText("Change Subject")
            }
        }


    }



    override fun onDestroy() {
        super.onDestroy()
        db_manager.closeDB()
    }

}