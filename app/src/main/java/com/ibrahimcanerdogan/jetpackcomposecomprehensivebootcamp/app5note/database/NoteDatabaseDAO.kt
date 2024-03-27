package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.model.Note

@Dao
interface NoteDatabaseDAO {

    @Query("SELECT * FROM notes_table")
    fun getAllNotesFromDatabase() : Flow<List<Note>>

    @Query("SELECT * FROM notes_table where noteID=:selectedNoteID")
    suspend fun getNoteFromDatabase(selectedNoteID: String) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteToDatabase(newNote: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNoteToDatabase(updateNote: Note)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotesFromDatabase()

    @Delete
    suspend fun deleteNote(deleteNote: Note)
}