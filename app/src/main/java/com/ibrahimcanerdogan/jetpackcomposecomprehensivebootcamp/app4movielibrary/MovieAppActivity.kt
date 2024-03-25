package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.navigation.MovieNavigation
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.ui.theme.JetpackComposeComprehensiveBootcampTheme

class MovieAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviePage {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MoviePage(mainContent: @Composable () -> Unit) {
    JetpackComposeComprehensiveBootcampTheme {
        mainContent()
    }
}



@Preview(showBackground = true)
@Composable
fun MoviePagePreview() {
    JetpackComposeComprehensiveBootcampTheme {
        MoviePage {
            MovieNavigation()
        }
    }
}