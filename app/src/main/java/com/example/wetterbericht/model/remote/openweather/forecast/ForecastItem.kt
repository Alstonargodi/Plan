package com.example.wetterbericht.model.remote.openweather.forecast

data class ForecastItem(
    val date : String,
    val desc : String,
    val temp : String,
)