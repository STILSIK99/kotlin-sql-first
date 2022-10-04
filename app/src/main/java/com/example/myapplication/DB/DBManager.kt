package com.example.myapplication.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

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
    fun readDBData() : ArrayList<Subject>{
        val dataList = ArrayList<Subject>()

        val cursor = db?.query(MyDbName.TABLE_NAME, null, null,
            null, null, null, null)
        while(cursor?.moveToNext()!!){
            val title = cursor.getString(cursor.getColumnIndex(MyDbName.COLUMN_NAME_TITLE))
            val content = cursor.getString(cursor.getColumnIndex(MyDbName.COLUMN_NAME_CONTENT))
            val id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            dataList.add(Subject(id, title, content))
//            dataList.add(dataText.toString())
        }
        cursor.close()
        return dataList
    }


    fun deleteDBData(id : Int){
        val request = "delete from ${MyDbName.TABLE_NAME} where ${BaseColumns._ID} = $id"
        db?.execSQL(request)
    }

    fun closeDB(){
        myDBHelper.close()
    }
}