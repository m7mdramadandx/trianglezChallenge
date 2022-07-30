package com.ramadan.cache

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "words_db"
        private const val TABLE_NAME = "RepeatedWords"
        private const val DATABASE_VERSION = 1
        private const val COL_FREQUENCY = "FREQUENCY"
        private const val COL_WORD = "WORD"
        private const val query: String = ("CREATE TABLE $TABLE_NAME ( "
                + "$COL_WORD TEXT PRIMARY KEY, "
                + "$COL_FREQUENCY INTEGER " +
                ")")
    }

    private lateinit var mDatabase: SQLiteDatabase

    override fun onCreate(db: SQLiteDatabase) {
        mDatabase = db
        mDatabase.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    fun readCourses(): List<Pair<String, Int>> {
        // on below line we are creating a
        // database for reading our database.
        val db = this.readableDatabase

        // on below line we are creating a cursor with query to read data from database.
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        // on below line we are creating a new array list.
        val wordsList: MutableList<Pair<String, Int>> = mutableListOf()

        // moving our cursor to first position.
        // on below line we are adding the data from cursor to our array list.
        // moving our cursor to next.
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                wordsList.add(
                    Pair(
                        cursor.getStringOrNull(0) ?: "",
                        cursor.getIntOrNull(1) ?: 0,
                    )
                )
            } while (cursor.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close()
        return wordsList
    }

    fun addNewWord(pair: Pair<String, Int>) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase

        // on below line we are creating a
        // variable for content values.
        val contentValues = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        contentValues.put(COL_WORD, pair.first)
        contentValues.put(COL_FREQUENCY, pair.second)
        // after adding all values we are passing
        // content values to our table.
        db.insertWithOnConflict(
            TABLE_NAME,
            null,
            contentValues,
            SQLiteDatabase.CONFLICT_REPLACE
        )

        // at last we are closing our
        // database after adding database.
        db.close()
    }

}