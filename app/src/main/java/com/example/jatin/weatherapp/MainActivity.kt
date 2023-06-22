package com.example.jatin.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jatin.weatherapp.navigation.WeatherNavigation
import com.example.jatin.weatherapp.ui.theme.WeatherDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//                val noteViewModel: HomeViewModel by viewModels()
            WeatherApp()
        }


//        val locationPermissionRequest = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) { permissions ->
//            when {
//                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
//                    // Precise location access granted.
//                    println("ACCESS_FINE_LOCATION granted")
//                }
//
//                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
//                    // Only approximate location access granted.
//                    println("ACCESS_COARSE_LOCATION granted")
//                }
//
//                else -> {
//                    // No location access granted.
//                    println("No location access granted")
//                }
//            }
//        }
//        locationPermissionRequest.launch(
//            arrayOf(
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            )
//        )

    }
}

@Composable
fun WeatherApp() {
    WeatherDemoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            WeatherNavigation()
        }
    }
}
