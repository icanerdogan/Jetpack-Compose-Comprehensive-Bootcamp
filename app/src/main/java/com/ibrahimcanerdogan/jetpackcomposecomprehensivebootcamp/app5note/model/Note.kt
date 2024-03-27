package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.model

import android.os.Build
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.UUID

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteID: Int? = null,
    @ColumnInfo("note_title")
    val noteTitle: String,
    @ColumnInfo("note_description")
    val noteDescription: String,
    @ColumnInfo("note_date")
    val noteEntryDate: String = currentEntryNoteDate()
)

fun currentEntryNoteDate() : String {
    val calendar = Calendar.getInstance()

    // Format today's date as "yyyy-MM-dd"
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    return dateFormat.format(calendar.time)
}