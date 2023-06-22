package com.example.jatin.weatherapp.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jatin.weatherapp.ui.screens.WeatherScreen
import com.example.jatin.weatherapp.ui.screens.SearchScreen
import com.example.jatin.weatherapp.ui.screens.SplashScreen

@SuppressLint("MissingPermission")
@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    val homeScreenName = Screens.HomeScreen.name

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.name
    ) {

        composable(Screens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(Screens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }


        //  uri=android-app://androidx.navigation/HomeScreen/Chicago
        composable(
            "$homeScreenName/{city}",
            arguments = listOf(navArgument(name = "city") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            WeatherScreen(
                navController = navController,
                city = backStackEntry.arguments?.getString("city"),
                lat = null, lon = null
            )
        }

        //  uri=android-app://androidx.navigation/HomeScreen/lat&lon
        composable(
            "$homeScreenName/{lat}&{lon}",
            arguments = listOf(
                navArgument(name = "lat") {
                    type = NavType.StringType
                }, navArgument(name = "lon") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            WeatherScreen(
                navController = navController,
                city = null,
                lat = backStackEntry.arguments?.getString("lat"),
                lon = backStackEntry.arguments?.getString("lon")
            )
        }

//        //  uri=android-app://androidx.navigation/HomeScreen?city=city&lat=lat&long=long
//        composable(
//            "$homeScreenName?city={city}&lat={lat}&lon={lon}",
//            arguments = listOf(
//                navArgument(name = "city") {
//                    type = NavType.StringType
//                },
//                navArgument(name = "lat") {
//                    type = NavType.StringType
//                },
//                navArgument(name = "lon") {
//                    type = NavType.StringType
//                })
//        ) { backStackEntry ->
//            WeatherScreen(
//                navController = navController,
//                city = backStackEntry.arguments?.getString("city"),
//                lat = backStackEntry.arguments?.getString("lat"),
//                lon = backStackEntry.arguments?.getString("lon")
//            )
//        }
    }
}