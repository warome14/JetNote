package com.example.jetnote.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.components.model.Note
import com.example.jetnote.components.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _notes.asStateFlow()

    init {
       viewModelScope.launch(Dispatchers.IO) {
           noteRepository.getNotes().distinctUntilChanged().collect {
               if (it.isEmpty()) Log.d("NoteViewModel-init", "empty list")
               else _notes.value = it
           }
       }
    }



    fun getNotes(): StateFlow<List<Note>> = noteList
    fun addNote(note: Note) = viewModelScope.launch {noteRepository.addNote(note) }
    fun removeNote(note: Note) = viewModelScope.launch {noteRepository.removeNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch {noteRepository.updateNote(note) }

}