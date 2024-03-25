package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.model.Movie
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.model.getMovies


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(modifier: Modifier = Modifier, navController: NavController, movieId: String?) {

    val movieList = getMovies().filter {
        it.id == movieId
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Detail Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Log.d("DetailScreen", "Movie favorite added.")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Favorite"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MovieRow(movie = movieList.first())
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(text = "Movie Images")
            HorizontalScrollableImageView(movieList, modifier)
            // Text(text = movieList.first().title, style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(
    movieList: List<Movie>,
    modifier: Modifier
) {
    LazyRow {
        items(movieList.first().images) { image ->
            ElevatedCard(
                modifier = modifier
                    .padding()
                    .size(240.dp),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build()
                )

                Image(
                    painter = painter,
                    contentDescription = "Movie Image"
                )
            }
        }
    }
}