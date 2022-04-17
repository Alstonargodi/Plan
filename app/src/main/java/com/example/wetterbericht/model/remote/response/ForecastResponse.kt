package com.example.wetterbericht.model.remote.response


import com.google.gson.annotations.SerializedName

class ForecastResponse(
    @SerializedName("city")
    var city: City,
    @SerializedName("cnt")
    var cnt: Int,
    @SerializedName("cod")
    var cod: String,
    @SerializedName("list")
    var list: List<util>,
    @SerializedName("message")
    var message: Int
)

class City(
    @SerializedName("coord")
    var coord: Coord,
    @SerializedName("country")
    var country: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("population")
    var population: Int,
    @SerializedName("sunrise")
    var sunrise: Int,
    @SerializedName("sunset")
    var sunset: Int,
    @SerializedName("timezone")
    var timezone: Int
)

class Clouds(
    @SerializedName("all")
    var all: Int
)

class Coord(
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("lon")
    var lon: Double
)

class mainfore(
    @SerializedName("feels_like")
    var feelsLike: Double,
    @SerializedName("grnd_level")
    var grndLevel: Int,
    @SerializedName("humidity")
    var humidity: Int,
    @SerializedName("pressure")
    var pressure: Int,
    @SerializedName("sea_level")
    var seaLevel: Int,
    @SerializedName("temp")
    var temp: Double,
    @SerializedName("temp_kf")
    var tempKf: Double,
    @SerializedName("temp_max")
    var tempMax: Double,
    @SerializedName("temp_min")
    var tempMin: Double
)

class Rain(
    @SerializedName("3h")
    var h: Any
)

class Sys(
    @SerializedName("pod")
    var pod: String
)

class util(
    @SerializedName("clouds")
    var clouds: Clouds,
    @SerializedName("dt")
    var dt: Int,
    @SerializedName("dt_txt")
    var dtTxt: String,
    @SerializedName("main")
    var main: mainfore,
    @SerializedName("pop")
    var pop: Double,
    @SerializedName("rain")
    var rain: Rain,
    @SerializedName("sys")
    var sys: Sys,
    @SerializedName("visibility")
    var visibility: Int,
    @SerializedName("weather")
    var weather: List<Weather>,
    @SerializedName("wind")
    var wind: Wind
)

class Weather(
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
    @SerializedName("deg")
    var deg: Int,
    @SerializedName("gust")
    var gust: Double,
    @SerializedName("speed")
    var speed: Double
)