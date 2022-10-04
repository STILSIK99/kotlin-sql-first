package com.example.myapplication.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DBManager(val context: Context) {
    val myDBHelper = DBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB(){
        db = myDBHelper.writableDatabase
    }

    fun insertToDB(title: String, content: String){
        val values = ContentValues().apply {
            put(MyDbName.COLUMN_NAME_TITLE, title)
            put(MyDbName.COLUMN_NAME_CONTENT, content)
        }
        db?.insert(MyDbName.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDBData() : ArrayList<String>{
        val dataList = ArrayList<String>()

        val cursor = db?.query(MyDbName.TABLE_NAME, null, null,
            null, null, null, null)
        while(cursor?.moveToNext()!!){
            val dataText = cursor.getString(cursor.getColumnIndex(MyDbName.COLUMN_NAME_TITLE))
            dataList.add(dataText.toString())
        }
        cursor.close()
        return dataList
    }

    fun closeDB(){
        myDBHelper.close()
    }
}