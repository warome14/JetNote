package com.example.jetnote.components.repository

import com.example.jetnote.components.data.NoteDao
import com.example.jetnote.components.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    fun getNotes(): Flow<List<Note>> = noteDao.getNotes().flowOn(Dispatchers.IO).conflate()
    fun getNote(id: String) = noteDao.getNote(id)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun addNote(note: Note) = noteDao.addNote(note)
    suspend fun removeNote(note: Note) = noteDao.removeNote(note)
}