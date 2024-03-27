package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDAO
}