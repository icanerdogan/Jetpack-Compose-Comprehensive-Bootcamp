package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.screen.NoteScreen
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app5note.ui.theme.JetpackComposeComprehensiveBootcampTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeComprehensiveBootcampTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val noteViewModel: NoteViewModel by viewModels()
                    NoteApp(modifier = Modifier.padding(innerPadding), viewModel = noteViewModel)
                }
            }
        }
    }
}

@Composable
fun NoteApp(modifier: Modifier = Modifier, viewModel: NoteViewModel) {
    val noteList = viewModel.noteList.collectAsState().value

    NoteScreen(notes = noteList,
        onAddNote = {
            viewModel.addNote(it)
        },
        onRemoveNote = {
            viewModel.removeNote(it)
        })

}