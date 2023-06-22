package com.example.jatin.weatherapp.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//@Preview
@Composable
fun WeatherAppBar(
    title: String = "Title",
    elevation: Dp = 3.dp,
    navController: NavController,
    onSearchActionClicked: () -> Unit,
    isMainScreen: Boolean = true,
    icon: ImageVector? = null,
    onIconClicked: () -> Unit = {}
) {
    TopAppBar(title = {
        Text(
            text = title,
            color = MaterialTheme.colors.onSecondary,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )

    }, actions = {
        if (isMainScreen) {
            IconButton(onClick = onSearchActionClicked) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }
        } else Box{}

    }, navigationIcon = {
        if(icon != null) {
            Icon(imageVector = icon, contentDescription = null,
                tint = MaterialTheme.colors.onSecondary,
                modifier = Modifier.clickable {
                    onIconClicked.invoke()
                })
        }

    },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )

}