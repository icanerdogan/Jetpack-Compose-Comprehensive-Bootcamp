package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.screen.JetTriviaApp
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.ui.theme.JetpackComposeComprehensiveBootcampTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JetTriviaAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetTriviaApp()
        }
    }
}


@Preview
@Composable
private fun JetTriviaAppPreview() {
    JetTriviaApp()
}