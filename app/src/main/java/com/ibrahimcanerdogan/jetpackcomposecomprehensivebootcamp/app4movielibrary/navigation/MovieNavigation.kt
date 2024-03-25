package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.screens.DetailScreen
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app4movielibrary.screens.HomeScreen


@Composable
fun MovieNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(MovieScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(
                navArgument("movie") {type = NavType.StringType}
            )
        ) {
            DetailScreen(navController = navController, movieId = it.arguments?.getString("movie"))
        }
    }
}