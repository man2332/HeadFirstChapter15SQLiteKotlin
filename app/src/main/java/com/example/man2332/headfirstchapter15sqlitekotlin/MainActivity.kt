package com.example.man2332.headfirstchapter15sqlitekotlin

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    lateinit var exampleSQLiteHelper: ExampleSQLiteHelper
    lateinit var db: SQLiteDatabase
    lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exampleSQLiteHelper = ExampleSQLiteHelper(this)
        //read database
        insertValues("Ant","Brown",1)
        deleteValue(3)
        query()

    }

    fun query(): Unit {
        db = exampleSQLiteHelper.readableDatabase

        cursor = db.query("BUGS", arrayOf("_id","NAME","DESCRIPTION","AGE"),null,null,null,null,null)
        if(cursor!=null){
            if(cursor.moveToFirst()){
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val desc = cursor.getString(2)
                val age = cursor.getInt(3)
                Log.d("MTag", "ROW: "+id+" "+name+" "+desc+" "+age)
                while(cursor.moveToNext()){
                    val id = cursor.getInt(0)
                    val name = cursor.getString(1)
                    val desc = cursor.getString(2)
                    val age = cursor.getInt(3)
                    Log.d("MTag", "ROW: "+id+" "+name+" "+desc+" "+age)
                }
            }
        }

    }
    fun insertValues(name: String, description: String, age: Int) {
        db = exampleSQLiteHelper.writableDatabase

        val cv = ContentValues()
        cv.put("NAME", name)
        cv.put("DESCRIPTION", description)
        cv.put("AGE", age)

        db.insert("BUGS", null, cv)
    }
    fun deleteValue(row: Int){
        db = exampleSQLiteHelper.writableDatabase

        db.delete("BUGS","_id = ?", arrayOf(row.toString()))
    }
}

//-CRUD- 
