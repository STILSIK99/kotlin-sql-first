package com.example.myapplication.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.widget.Toast

class DBManager(val context: Context) {
    val myDBHelper = DBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB(){
        db.let{
            db = myDBHelper.writableDatabase
        }
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
            dataList.add(Subject(title, content, id))
        }
        cursor.close()
        return dataList
    }

    @SuppressLint("Range")
    fun getElementById(id : Int) : Subject?{
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("$id")
        val cursor = db?.query(
            MyDbName.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        if (cursor?.moveToNext()!!){
            val title = cursor.getString(cursor.getColumnIndex(MyDbName.COLUMN_NAME_TITLE))
            val content = cursor.getString(cursor.getColumnIndex(MyDbName.COLUMN_NAME_CONTENT))
            val id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val result = Subject(title, content, id)
            cursor.close()
            return result
        }
        cursor.close()
        return null
    }

    fun updateSubject(id : Int, title: String, content: String){
        val values = ContentValues().apply {
            put(MyDbName.COLUMN_NAME_TITLE, title)
            put(MyDbName.COLUMN_NAME_CONTENT, content)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("$id")
        val count = db?.update(
            MyDbName.TABLE_NAME,
            values,
            selection,
            selectionArgs)
        if (count != 0){
            Toast.makeText(context, "Изменения внесены.", Toast.LENGTH_LONG).show()
        }
    }

    fun deleteDBData(id : Int){
        val request = "delete from ${MyDbName.TABLE_NAME} where ${BaseColumns._ID} = $id"
        db?.execSQL(request)
    }

    fun closeDB(){
        myDBHelper.close()
    }
}