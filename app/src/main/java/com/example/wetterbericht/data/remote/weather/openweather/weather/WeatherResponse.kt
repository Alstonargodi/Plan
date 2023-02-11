package com.example.wetterbericht.data.remote.weather.openweather.weather

import com.google.gson.annotations.SerializedName

class WeatherResponse(
    @SerializedName("base")
    var base: String,
    @SerializedName("clouds")
    var clouds: Clouds,
    @SerializedName("cod")
    var cod: Int,
    @SerializedName("dt")
    var dt: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var main: Main,
    @SerializedName("name")
    var name: String,
    @SerializedName("visibility")
    var visibility: Int,
    @SerializedName("weather")
    var weather: List<WeatherX>,
    @SerializedName("wind")
    var wind: Wind
)

class Clouds(
    @SerializedName("all")
    var all: Int
)


class Sys(
    @SerializedName("id")
    var id: Int,
    @SerializedName("type")
    var type: Int
)

class Main(
    @SerializedName("feels_like")
    var feelsLike: Double,
    @SerializedName("temp")
    var temp: Double
)

class WeatherX(
    @SerializedName("description")
    var description: String,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var main: String
)

class Wind(
    @SerializedName("speed")
    var speed: Double
)