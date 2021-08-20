package com.example.wetterbericht.model.APIforecast


import com.google.gson.annotations.SerializedName

class Wind(
    @SerializedName("deg")
    var deg: Int,
    @SerializedName("gust")
    var gust: Double,
    @SerializedName("speed")
    var speed: Double
)