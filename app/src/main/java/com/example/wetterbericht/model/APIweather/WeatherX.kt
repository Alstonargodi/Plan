package com.example.wetterbericht.model.APIweather


import com.google.gson.annotations.SerializedName

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