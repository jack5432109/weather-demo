package com.example.jatin.weatherapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.jatin.weatherapp.api.ApiResult
import com.example.jatin.weatherapp.data.model.WeatherData
import com.example.jatin.weatherapp.navigation.Screens
import com.example.jatin.weatherapp.ui.widgets.WeatherAppBar
import com.example.jatin.weatherapp.utils.Constants.IMAGE_BASE_URL
import com.example.jatin.weatherapp.utils.formatDate
import com.example.jatin.weatherapp.utils.formatDateTime
import com.example.jatin.weatherapp.viewmodels.WeatherViewModel

@Composable
fun WeatherScreen(
    navController: NavController,
    weatherViewModel: WeatherViewModel = hiltViewModel(),
    city: String?,
    lat: String?,
    lon: String?
) {
    val curCity: String = city ?: "Chicago"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val apiResult = produceState<ApiResult<WeatherData?>>(initialValue = ApiResult.Loading()) {
            println("producing state for $curCity")
            if (!lat.isNullOrEmpty() && !lon.isNullOrEmpty())
                value = weatherViewModel.loadWeather(lat, lon)
            else
                value = weatherViewModel.loadWeather(curCity)
        }
        MainScaffold(
            apiResult.value,
            navController
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(
    apiResult: ApiResult<WeatherData?>,
//    weather: WeatherData,
    navController: NavController
) {
    val weather = apiResult.data
    Scaffold(topBar = {
        WeatherAppBar(
            title =
            if (apiResult is ApiResult.Success && weather != null)
                "${weather.name}, ${weather.sys.country}"
            else
                "Unknown Location",
            navController = navController,
            onSearchActionClicked = {
                navController.navigate(Screens.SearchScreen.name)
            },
//            icon = Icons.Default.ArrowBack,
        )
    }) {
        MainContent(weather)
    }
}

@Composable
fun MainContent(weatherData: WeatherData?) {
    if (weatherData == null) {
        Text(
            text = "Please try again",
            modifier = Modifier.padding(16.dp)
        )
    } else {
        val imageUrl = IMAGE_BASE_URL + weatherData.weather[0].icon + ".png"
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatDate(weatherData.dt),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "%.0f".format(weatherData.main.temp) + " F",
                        fontWeight = FontWeight.Bold,
                        fontSize = 64.sp
                    )
                    Text(
                        text = "Feels like ${weatherData.main.temp} F",
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "weather icon image",
                        modifier = Modifier.size(80.dp)
                    )
                    Text(
                        text = weatherData.weather[0].main,
                    )
                }
            }

            Divider()
            Text(
                text = "Other details",
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 18.dp, top = 24.dp, bottom = 6.dp)
            )
            DetailsPair("Sunrise", formatDateTime(weatherData.sys.sunrise))
            DetailsPair("Sunset", formatDateTime(weatherData.sys.sunset))
            DetailsPair("Pressure", "${weatherData.main.pressure} hPa")
            DetailsPair("Humidity", "${weatherData.main.humidity} %")
            DetailsPair("Wind Speed", "${weatherData.wind.speed} mph")

        }

    }

}


@Composable
fun DetailsPair(key: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = key)
        Text(text = value)
    }
}
