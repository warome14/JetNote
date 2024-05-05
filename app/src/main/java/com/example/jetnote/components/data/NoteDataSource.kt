package com.example.jetnote.components.data

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.jetnote.components.model.Note
import java.time.LocalDateTime

private const val DATABASE_NAME = "mydatabase.db"
private const val DATABASE_VERSION = 1

object MyDatabaseHelper : SQLiteOpenHelper(null, DATABASE_NAME, null, DATABASE_VERSION) {

    private const val DATABASE_NAME = "mydatabase.db"
    private const val DATABASE_VERSION = 1

    override fun onCreate(db: SQLiteDatabase) {
        // Create tables and define schema here
        db.execSQL("CREATE TABLE IF NOT EXISTS notes (id TEXT PRIMARY KEY, title TEXT, description TEXT, data TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades here
    }

    fun getReadableDatabaseInstance(): SQLiteDatabase {
        return readableDatabase
    }

    fun getWritableDatabaseInstance(): SQLiteDatabase {
        return writableDatabase
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("Range")
fun loadNotesFromDatabase(): List<Note> {
    val notes = mutableListOf<Note>()

    val db = MyDatabaseHelper.getReadableDatabaseInstance()

    val projection = arrayOf("id", "title", "description", "data")
    val cursor = db.query("notes", projection, null, null, null, null, null)

    cursor?.use {
        while (cursor.moveToNext()) {
            val id = cursor.getString(cursor.getColumnIndex("id"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val description = cursor.getString(cursor.getColumnIndex("description"))
            val dateString = cursor.getString(cursor.getColumnIndex("data"))
            val data = LocalDateTime.parse(dateString)

            val note = Note(id, title, description, data)
            notes.add(note)
        }
    }

    db.close()

    return notes
}

