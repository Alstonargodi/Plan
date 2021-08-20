package com.example.wetterbericht.model.APIweather


import com.google.gson.annotations.SerializedName

class Wind(
    @SerializedName("deg")
    var deg: Int,
    @SerializedName("speed")
    var speed: Double
)