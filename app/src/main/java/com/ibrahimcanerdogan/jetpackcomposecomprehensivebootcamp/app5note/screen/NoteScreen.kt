package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.R
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.component.NoteScreenComponent
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.database.NoteDataSource
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.titleMedium) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFDADFE3)
                ),
                actions = {
                    Icon(
                        imageVector = Icons.Rounded.Notifications,
                        contentDescription = "Icon"
                    )
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteScreenComponent().NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                })

            NoteScreenComponent().NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                })

            NoteScreenComponent().NoteButton(text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(
                            Note(
                                noteTitle = title,
                                noteDescription = description
                            )
                        )
                        title = ""
                        description = ""
                        Toast.makeText(
                            context, "Note Added",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

            Divider(modifier = Modifier.padding(10.dp))
            LazyColumn {
                items(notes) { note ->
                    NoteRow(note = note,
                        onNoteClicked = { onRemoveNote(note) })
                }
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp
    ) {
        Column(modifier
            .clickable { onNoteClicked(note) }
            .padding(
                horizontal = 14.dp,
                vertical = 6.dp),
                horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.noteTitle, style = MaterialTheme.typography.titleMedium)
            Text(text = note.noteDescription, style = MaterialTheme.typography.titleSmall)
            Text(text = note.noteEntryDate, style = MaterialTheme.typography.labelSmall)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NoteScreen(notes =  NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}