package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.database.NoteDatabase
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.database.NoteDatabaseDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) : NoteDatabaseDAO {
        return noteDatabase.noteDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : NoteDatabase {
        return Room.databaseBuilder(context, NoteDatabase::class.java, "notes_db")
            .fallbackToDestructiveMigration()
            .build()

    }
}