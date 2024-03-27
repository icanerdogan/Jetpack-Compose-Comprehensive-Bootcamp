package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.repository

import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.database.NoteDatabaseDAO
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDatabaseDAO: NoteDatabaseDAO
) {
    fun getAllNotesDatabase() = noteDatabaseDAO.getAllNotesFromDatabase()
            .flowOn(Dispatchers.IO)
            .conflate()

    suspend fun addNoteDatabase(addNote: Note) {
        noteDatabaseDAO.insertNoteToDatabase(addNote)
    }

    suspend fun updateNoteDatabase(updateNote: Note) {
        noteDatabaseDAO.updateNoteToDatabase(updateNote)
    }

    suspend fun deleteNoteDatabase(deleteNote: Note) {
        noteDatabaseDAO.deleteNote(deleteNote)
    }

    suspend fun deleteAllNotesDatabase() {
        noteDatabaseDAO.deleteAllNotesFromDatabase()
    }
}