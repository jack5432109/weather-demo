package com.example.jatin.weatherapp.utils

import java.sql.Timestamp
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(timestamp: Int): String {
//    val dateFormat = getDateInstance()
    val dateFormat = SimpleDateFormat("EEE, MMM d YYYY", Locale.US)
    val date = Date(timestamp.toLong() * 1000)
    return dateFormat.format(date)
}


fun formatDateTime(timestamp: Int): String {
    val dateFormat = SimpleDateFormat("K:mm a", Locale.US)
    val date = Date(timestamp.toLong() * 1000)
    return dateFormat.format(date)

}