package com.example.man2332.headfirstchapter15sqlitekotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ExampleSQLiteHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(SQL_CREATE)
            insertValues(db,"Spider","First bug", 4)
        }

    }
    //delete entire database if db versions dont match
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL(SQL_DELETE)
            onCreate(db)
        }
    }
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insertValues(db: SQLiteDatabase, name: String, description: String, age: Int) {
        val cv = ContentValues()
        cv.put("NAME", name)
        cv.put("DESCRIPTION", description)
        cv.put("AGE", age)

        db.insert("BUGS", null, cv)
    }

    //CONSTANTS
    companion object {
        val DB_NAME = "animals.db"
        val DB_VERSION = 1

        val SQL_CREATE = "CREATE TABLE BUGS (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT, " +
                "DESCRIPTION TEXT, " +
                "AGE INTEGER);"

        val SQL_DELETE = "DROP TABLE IF EXISTS BUGS"
    }
}