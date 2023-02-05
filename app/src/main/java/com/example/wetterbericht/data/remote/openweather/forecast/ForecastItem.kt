package com.example.wetterbericht.data.remote.openweather.forecast

data class ForecastItem(
    val date : String,
    val desc : String,
    val temp : String,
    val iconUrl : String
)