package com.example.jatin.weatherapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.jatin.weatherapp.navigation.Screens
import com.example.jatin.weatherapp.ui.widgets.SearchBar
import com.example.jatin.weatherapp.ui.widgets.WeatherAppBar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Weather ",
                navController = navController,
                onSearchActionClicked = { },
                isMainScreen = false,
//                icon = Icons.Default.ArrowBack,
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface() {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar { mCity ->
                    navController.navigate(Screens.HomeScreen.name + "/$mCity")
                }
            }
        }
    }
}





