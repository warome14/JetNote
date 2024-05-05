package com.example.jetnote.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetnote.components.data.loadNotesFromDatabase
import com.example.jetnote.components.model.Note

@RequiresApi(Build.VERSION_CODES.O)
class NoteViewModel: ViewModel(){

    private val notes = mutableStateListOf<Note>()

    init {
       // val notesFromDatabase = loadNotesFromDatabase()
       // notes.addAll(notesFromDatabase)
    }



    fun getNotes(): List<Note> = notes
    fun addNote(note: Note) = notes.add(note)
    fun removeNote(note: Note) = notes.remove(note)
}