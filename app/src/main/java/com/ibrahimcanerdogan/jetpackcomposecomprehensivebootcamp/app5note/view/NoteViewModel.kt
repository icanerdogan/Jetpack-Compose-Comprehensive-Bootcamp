package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.model.Note
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllNoteData()
        }
    }

    private suspend fun getAllNoteData() {
        repository.getAllNotesDatabase()
            .distinctUntilChanged()
            .collect { listNote ->
                _noteList.value = listNote
            }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNoteDatabase(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNoteDatabase(note)
    }

    fun removeNote(note: Note) = viewModelScope.launch {
        repository.deleteNoteDatabase(note)
    }
}