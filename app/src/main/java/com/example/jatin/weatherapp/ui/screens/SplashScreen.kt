package com.example.jatin.weatherapp.ui.screens

import android.Manifest
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import com.example.jatin.weatherapp.navigation.Screens
import com.example.jatin.weatherapp.ui.widgets.CenteredColumn
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreen(navController: NavController) {

//    val locationPermissionState = rememberMultiplePermissionsState(
//        listOf(
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.ACCESS_COARSE_LOCATION
//        )
//    )
    val locationPermissionState = rememberPermissionState(
            Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    if (locationPermissionState.status.isGranted) {
        Surface {
            CenteredColumn {
                Text("Location permission granted")
                CurrentLocationContent(navController)
            }
        }
    } else {
        Surface {
            CenteredColumn {
                Text(text = "Allow Location permission to access Local Weather")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
                    Text(text = "Provide Location")
                }
            }
        }
    }
}


@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun CurrentLocationContent(navController: NavController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var locationInfo by remember {
        mutableStateOf("")
    }

    Surface {
        CenteredColumn {
            Button(
                onClick = {
                    scope.launch(Dispatchers.Main) {
                        val result = locationClient.getCurrentLocation(
                            Priority.PRIORITY_HIGH_ACCURACY,
//                        Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                            CancellationTokenSource().token,
                        ).await()
                        result?.let { fetchedLocation ->
                            val lat = fetchedLocation.latitude
                            val lon = fetchedLocation.longitude
                            navController.navigate(Screens.HomeScreen.name + "/${lat}&${lon}")
                        }
                    }
                },
            ) {
                Text(text = "View Weather")
            }
            Text(
                text = locationInfo,
            )
        }
    }
}

