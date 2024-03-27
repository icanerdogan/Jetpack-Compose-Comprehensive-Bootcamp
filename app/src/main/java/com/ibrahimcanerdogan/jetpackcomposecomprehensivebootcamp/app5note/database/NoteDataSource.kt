package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.database

import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.model.Note

class NoteDataSource {
    fun loadNotes(): List<Note> {
        return listOf(

            Note(noteTitle = "A good day", noteDescription = "We went on a vacation by the lake"),
            Note(noteTitle = "Android Compose", noteDescription = "Working on Android Compose course today"),
            Note(noteTitle = "Keep at it...", noteDescription = "Sometimes things just happen"),
            Note(noteTitle = "A movie day", noteDescription = "Watching a movie with family today"),
            Note(noteTitle = "A movie day", noteDescription = "Watching a movie with family today"),
            Note(noteTitle = "A movie day", noteDescription = "Watching a movie with family today"),
            Note(noteTitle = "A movie day", noteDescription = "Watching a movie with family today"),
            Note(noteTitle = "A movie day", noteDescription = "Watching a movie with family today"),
            Note(noteTitle = "A movie day", noteDescription = "Watching a movie with family today"),
            Note(noteTitle = "A movie day", noteDescription = "Watching a movie with family")

        )
    }
}