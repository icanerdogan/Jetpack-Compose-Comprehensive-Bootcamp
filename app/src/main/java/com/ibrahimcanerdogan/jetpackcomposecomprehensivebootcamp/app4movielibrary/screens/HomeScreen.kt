package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.R
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.model.Movie
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.model.getMovies
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.navigation.MovieScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            MainContent(
                navController = navController
            )
        }
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {
    LazyColumn {
        items(movieList) {
            MovieRow(movie = it) { movie ->
                navController.navigate(route = MovieScreens.DetailScreen.name + "/$movie")
                Log.d("MovieAppActivity", "Movie Name: $movie")
            }
        }
    }
}

@Composable
fun MovieRow(modifier: Modifier = Modifier, movie: Movie, onItemClick: (String) -> Unit = {}) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .fillMaxWidth()
            //.height(130.dp) Close for Animated Visibility.
            .clickable {
                onItemClick(movie.id)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = modifier
                    .padding(12.dp)
                    .size(100.dp)
                    .background(Color.White),
                color = Color.White,
                shape = RectangleShape,
                shadowElevation = 5.dp
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .size(Size.ORIGINAL) // Set the target size to load the image at.
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build()
                )

                Image(
                    painter = painter,
                    contentDescription = "Movie Image"
                )

                /*
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    tint = Color.Black,
                    contentDescription = "Movie Image"
                )
                */
            }
            Column(
                modifier = modifier.padding(10.dp)
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = "Date: ${movie.year}", style = MaterialTheme.typography.bodyMedium)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 11.sp
                                    )
                                ) {
                                    append("Plot: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                    )
                                ) {
                                    append(movie.plot)
                                }
                            },
                            modifier = modifier.padding(6.dp)
                        )
                        Divider()
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.bodySmall)
                    }
                }

                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray,
                    contentDescription = "Down Arrow"
                )
            }
        }

    }
}